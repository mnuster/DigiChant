package mnuster.dchant.item;

import mnuster.dchant.info.ItemInfo;
import net.minecraft.item.Item;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class Items {

	public static Item templateBlank;
	public static Item templateEnchanted;
	public static ItemBookDB bookDB;

	public static void registerItems() {
		templateBlank = new ItemTemplateBlank(ItemInfo.TEMPLATE_BLANK.ID);
		templateEnchanted = new ItemTemplateEnchanted(ItemInfo.TEMPLATE_ENCH.ID);
		bookDB = new ItemBookDB(ItemInfo.BOOK_DB.ID);
	}

	public static void registerNames() {
		LanguageRegistry.addName(templateBlank, ItemInfo.TEMPLATE_BLANK.NAME);
		LanguageRegistry.addName(templateEnchanted, ItemInfo.TEMPLATE_ENCH.NAME);
		LanguageRegistry.addName(bookDB, ItemInfo.BOOK_DB.NAME);
	}

}
