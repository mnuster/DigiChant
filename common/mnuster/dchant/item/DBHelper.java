package mnuster.dchant.item;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagShort;

public class DBHelper {

	public static void initDB(ItemStack stackDB) {
		if (!stackDB.hasTagCompound()) {
			System.out.println("Adding Tag Compound");
			stackDB.setTagCompound(new NBTTagCompound());
		}
		System.out.println("Initializing Database");
		NBTTagList tagList = new NBTTagList();
		for (Enchantment enchant : Enchantment.enchantmentsList) {
			if (enchant != null) {
				NBTTagList subTagList = new NBTTagList();
				subTagList.setName(String.valueOf(enchant.effectId));
				for (int i = enchant.getMinLevel(); i <= enchant.getMaxLevel(); i++) {
					subTagList.appendTag(new NBTTagShort(String.valueOf(i), (short)0));
				}
				tagList.appendTag(subTagList);
			}
		}
		stackDB.getTagCompound().setTag("StoredBooks", tagList);
		stackDB.getTagCompound().setShort("numBooks", (short)0);
	}

	public static void printDB(ItemStack stackDB) {
		if (hasDB(stackDB)) {
			short numBooks = stackDB.getTagCompound().getShort("numBooks");
			NBTTagList enchantTagList = (NBTTagList)(stackDB.getTagCompound())
					.getTag("StoredBooks");
			NBTTagList lvlTagList;
			NBTTagShort lvlCount;
			System.out.println(numBooks + " Stored Enchants -------------------");
			for (int i = 0; i < enchantTagList.tagCount(); i++) {
				lvlTagList = ((NBTTagList)enchantTagList.tagAt(i));
				System.out.println("\nEnchant: "
						+ Enchantment.enchantmentsList[Integer.parseInt(lvlTagList.getName())]
								.getName());
				for (int j = 0; j < lvlTagList.tagCount(); j++) {
					lvlCount = (NBTTagShort)lvlTagList.tagAt(j);
					System.out.print("\tL " + lvlCount.getName() + ": " + lvlCount.data);
				}
				System.out.println();
			}
		} else {
			System.out.println("No database found");
		}
	}

	public static boolean hasDB(ItemStack stackDB) {
		return (stackDB.hasTagCompound() && stackDB.getTagCompound().hasKey("StoredBooks"));
	}

	public static void addBook(ItemStack stackDB, ItemStack stackBook) {
		if (!hasDB(stackDB)) {
			initDB(stackDB);
		}
		////// crashes on world reload--- don't know why.... hurts my feelings
		// names of tags are "" after reload
		System.out.println("Adding Book");
		NBTTagList enchantTagList = (NBTTagList)(stackDB.getTagCompound()).getTag("StoredBooks");
		NBTTagList lvlTagList;
		NBTTagShort lvlCount;
		short enchantID = TemplateHelper.getEnchantID(stackBook);
		short enchantLevel = TemplateHelper.getEnchantLevel(stackBook);
		for (int i = 0; i < enchantTagList.tagCount(); i++) {
			lvlTagList = ((NBTTagList)enchantTagList.tagAt(i));
			if (Short.parseShort(lvlTagList.getName()) == enchantID) {
				for (int j = 0; j < lvlTagList.tagCount(); j++) {
					lvlCount = (NBTTagShort)lvlTagList.tagAt(j);
					if (Short.parseShort(lvlCount.getName()) == enchantLevel) {
						lvlCount.data++;
						stackDB.getTagCompound().setShort("numBooks",
								(short)(stackDB.getTagCompound().getShort("numBooks") + 1));
						break;
					}
				}
			}
		}

		printDB(stackDB);

	}
}
