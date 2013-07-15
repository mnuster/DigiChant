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
		
		ItemInfo.TEMPLATE_ID = config.getItem(ItemInfo.TEMPLATE_KEY, ItemInfo.TEMPLATE_DEFAULT).getInt() - 256;
		
		config.save();
		
	}
	
}
