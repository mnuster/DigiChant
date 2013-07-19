package mnuster.dchant.config;

import java.io.File;

import mnuster.dchant.lib.BlockInfo;
import mnuster.dchant.lib.ItemInfo;
import net.minecraftforge.common.Configuration;

public class ConfigHandler {

	public static void init(File file) {
		Configuration config = new Configuration(file);
		
		config.load();

		BlockInfo.INPRINTER.ID = config.getBlock(BlockInfo.INPRINTER.KEY, BlockInfo.INPRINTER.DEFAULT).getInt();
		
		ItemInfo.TEMPLATE_BLANK.ID = config.getItem(ItemInfo.TEMPLATE_BLANK.KEY, ItemInfo.TEMPLATE_BLANK.DEFAULT).getInt() - 256;
		ItemInfo.TEMPLATE_ENCH.ID = config.getItem(ItemInfo.TEMPLATE_ENCH.KEY, ItemInfo.TEMPLATE_ENCH.DEFAULT).getInt() - 256;
		
		config.save();
		
	}
	
}
