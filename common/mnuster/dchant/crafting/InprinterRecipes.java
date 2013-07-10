package mnuster.dchant.crafting;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class InprinterRecipes {

	private static final InprinterRecipes inprinterBase = new InprinterRecipes();
	
	private Map printingList = new HashMap();
	
	public static final InprinterRecipes Printing() {
		return inprinterBase;
	}
	
	public InprinterRecipes() {
		addPrinting(Item.enchantedBook.itemID, new ItemStack(Item.book));
	}
	
	public void addPrinting(int itemID, ItemStack stack) {
		printingList.put(itemID, stack);
	}
	
	public ItemStack getPrintingResult(ItemStack item) {
		if (item != null) {
			ItemStack result = (ItemStack)printingList.get(item.itemID);
			return result == null ? null : result;
		}
		return null;
	}
}
