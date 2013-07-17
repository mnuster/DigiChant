package mnuster.dchant.item;

import mnuster.dchant.lib.ItemInfo;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.StatCollector;

public class TemplateHelper {

	public static boolean isTemplate(ItemStack stack) {
		return (stack.itemID == Items.templateBlank.itemID || stack.itemID == Items.templateEnchanted.itemID);
	}

	public static boolean isEnchBook(ItemStack stack) {
		return (stack.itemID == Item.enchantedBook.itemID);
	}

	public static String getEnchantmentName(ItemStack stack) {
		return StatCollector.translateToLocal(Enchantment.enchantmentsList[TemplateHelper
				.getEnchantID(stack)].getName());
	}

	public static boolean hasEnchantment(ItemStack stack) {
		return getEnchantID(stack) != -1;
	}

	public static ItemStack getPrintResult(ItemStack stackBook, ItemStack stackTemplate) {
		// check for book and template
		if (!isEnchBook(stackBook) || !isTemplate(stackTemplate)) {
			return null;
		}

		ItemStack result = new ItemStack(Items.templateEnchanted);

		if (hasEnchantment(stackTemplate)) {
			setEnchantment(getEnchantID(stackTemplate), result);
			result.setItemDamage(stackTemplate.getItemDamage());
		} else {
			setEnchantment(getEnchantID(stackBook), result);
		}

		addCharge(getEnchantID(stackBook), result);
		
		return result;
	}

	public static short getEnchantID(ItemStack stack) {
		if (isTemplate(stack)) {
			return stack.hasTagCompound() ? stack.stackTagCompound
					.getShort(ItemInfo.TEMPLATE_ENCH_TAG) : -1;
		} else if (isEnchBook(stack)) {
			NBTTagList tagList = Item.enchantedBook.func_92110_g(stack);
			return tagList != null ? ((NBTTagCompound) tagList.tagAt(0)).getShort("id") : -1;
		} else {
			return -1;
		}
	}

	public static void setEnchantment(short enchantID, ItemStack template) {
		if (!template.hasTagCompound()) {
			template.setTagCompound(new NBTTagCompound());
		}
		template.stackTagCompound.setShort(ItemInfo.TEMPLATE_ENCH_TAG, enchantID);
	}

	public static void addCharge(short enchantID, ItemStack stack) {
		// temp code for testing purpose
		if (getEnchantID(stack) == enchantID) {
			stack.setItemDamage(stack.getItemDamage() + 100);
		} else {
			stack.setItemDamage(stack.getItemDamage() - 1);
		}
	}

}
