package mnuster.dchant.item;

import mnuster.dchant.lib.ItemInfo;
import net.minecraft.item.Item;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class Items {

	public static Item templateBlank;
	public static Item templateEnchanted;

	public static void registerItems() {
		templateBlank = new ItemTemplateBlank(ItemInfo.TEMPLATE_BLANK_ID);
		templateEnchanted = new ItemTemplateEnchanted(ItemInfo.TEMPLATE_ENCH_ID);
	}

	public static void registerNames() {
		LanguageRegistry.addName(templateBlank, ItemInfo.TEMPLATE_BLANK_NAME);
		LanguageRegistry.addName(templateEnchanted, ItemInfo.TEMPLATE_ENCH_NAME);
	}

}
