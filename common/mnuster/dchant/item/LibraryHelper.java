package mnuster.dchant.item;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagIntArray;
import net.minecraft.nbt.NBTTagList;

public class LibraryHelper {

	private static short getBookEnchantID(ItemStack stack) {
		if (stack.itemID == Item.enchantedBook.itemID) {
			NBTTagList tagList = Item.enchantedBook.func_92110_g(stack);
			return tagList != null ? ((NBTTagCompound)tagList.tagAt(0)).getShort("id") : -1;
		} else {
			return -1;
		}
	}

	public static short getBookEnchantLevel(ItemStack stack) {
		if (stack.itemID == Item.enchantedBook.itemID) {
			NBTTagList tagList = Item.enchantedBook.func_92110_g(stack);
			return tagList != null ? ((NBTTagCompound)tagList.tagAt(0)).getShort("lvl") : -1;
		} else {
			return -1;
		}
	}

	private static int getEnchantIndex(short enchantID, NBTTagList enchantTagList) {
		for (int i = 0; i < enchantTagList.tagCount(); i++) {
			NBTTagCompound enchantCompound = (NBTTagCompound)enchantTagList.tagAt(i);
			if (enchantCompound.getShort("enchantID") == enchantID) {
				return i;
			}
		}
		return -1;
	}

	private static int addEnchantType(short enchantID, NBTTagList enchantTagList) {
		NBTTagCompound enchantCompound = new NBTTagCompound();
		enchantCompound.setShort("enchantID", enchantID);
		NBTTagIntArray lvlArray = new NBTTagIntArray(Short.toString(enchantID));
		lvlArray.intArray = new int[Enchantment.enchantmentsList[enchantID].getMaxLevel()];
		enchantCompound.setTag("lvlArray", lvlArray);
		enchantTagList.appendTag(enchantCompound);
		return enchantTagList.tagCount() - 1;
	}

	private static boolean hasLibrary(ItemStack library) {
		return (library.hasTagCompound() && library.getTagCompound().hasKey("Library"));
	}

	private static void initLibrary(ItemStack library) {
		if (!library.hasTagCompound()) {
			System.out.println("Adding Tag Compound");
			library.setTagCompound(new NBTTagCompound());
		}
		System.out.println("Initializing Database");
		NBTTagList tagList = new NBTTagList();
		library.getTagCompound().setTag("Library", tagList);
		library.setItemDamage(0);
	}

	public static void addBook(ItemStack library, ItemStack book) {
		if (!hasLibrary(library)) {
			initLibrary(library);
		}

		System.out.println("Adding Book");
		NBTTagList enchantTagList = (NBTTagList)(library.getTagCompound()).getTag("Library");

		short enchantID = getBookEnchantID(book);
		short enchantLevel = getBookEnchantLevel(book);

		int enchantIndex = getEnchantIndex(enchantID, enchantTagList);
		if (enchantIndex == -1) {
			enchantIndex = addEnchantType(enchantID, enchantTagList);
		}
		NBTTagCompound enchantCompound = (NBTTagCompound)enchantTagList.tagAt(enchantIndex);
		NBTTagIntArray lvlArray = (NBTTagIntArray)enchantCompound.getTag("lvlArray");
		lvlArray.intArray[enchantLevel - 1]++;
		library.setItemDamage(library.getItemDamage() + 1);

		printLibrary(library);
	}

	public static void printLibrary(ItemStack library) {
		if (hasLibrary(library)) {
			NBTTagList enchantTagList = (NBTTagList)(library.getTagCompound()).getTag("Library");
			NBTTagList lvlTagList;

			System.out.println(library.getItemDamage() + " Stored Books -------------------");
			for (int i = 0; i < enchantTagList.tagCount(); i++) {

				NBTTagCompound enchantCompound = (NBTTagCompound)enchantTagList.tagAt(i);
				short enchantID = enchantCompound.getShort("enchantID");
				NBTTagIntArray lvlArray = (NBTTagIntArray)enchantCompound.getTag("lvlArray");
				System.out.println("\nEnchant: "
						+ Enchantment.enchantmentsList[enchantID].getName());
				for (int j = 0; j < lvlArray.intArray.length; j++) {
					System.out.print("\tL" + (j + 1) + ": " + lvlArray.intArray[j]);
				}
				System.out.println();
			}
		} else {
			System.out.println("No database found");
		}
	}

}
