package mnuster.dchant.item;

import mnuster.dchant.info.ItemInfo;
import net.minecraft.item.Item;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class Items {

	public static Item library;

	public static void registerItems() {
		library = new ItemLibrary(ItemInfo.LIBRARY.ID);
	}

	public static void registerNames() {
		LanguageRegistry.addName(library, ItemInfo.LIBRARY.NAME);
	}

}
