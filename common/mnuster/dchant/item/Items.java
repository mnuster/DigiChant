package mnuster.dchant.item;

import mnuster.dchant.lib.ItemInfo;
import net.minecraft.item.Item;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class Items {

	public static Item template;
	
	public static void registerItems() {
		template = new ItemTemplate(ItemInfo.TEMPLATE_ID);
		//GameRegistry.registerItem(template, ItemInfo.TEMPLATE_UNLOCALIZED_NAME);
	}
	
	public static void registerNames() {
		LanguageRegistry.addName(template, ItemInfo.TEMPLATE_NAME);
	}
	
}
