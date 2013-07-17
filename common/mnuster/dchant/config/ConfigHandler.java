package mnuster.dchant.config;

import java.io.File;

import mnuster.dchant.lib.BlockInfo;
import mnuster.dchant.lib.ItemInfo;
import net.minecraftforge.common.Configuration;

public class ConfigHandler {

	public static void init(File file) {
		Configuration config = new Configuration(file);
		
		config.load();

		BlockInfo.INPRINTER_ID = config.getBlock(BlockInfo.INPRINTER_KEY, BlockInfo.INPRINTER_DEFAULT).getInt();
		
		ItemInfo.TEMPLATE_BLANK_ID = config.getItem(ItemInfo.TEMPLATE_BLANK_KEY, ItemInfo.TEMPLATE_BLANK_DEFAULT).getInt() - 256;
		ItemInfo.TEMPLATE_ENCH_ID = config.getItem(ItemInfo.TEMPLATE_ENCH_KEY, ItemInfo.TEMPLATE_ENCH_DEFAULT).getInt() - 256;
		
		config.save();
		
	}
	
}
