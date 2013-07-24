package mnuster.dchant.config;

import java.io.File;

import mnuster.dchant.info.BlockInfo;
import mnuster.dchant.info.ItemInfo;
import net.minecraftforge.common.Configuration;

public class ConfigHandler {

	public static void init(File file) {
		Configuration config = new Configuration(file);

		config.load();

		// load block info
		BlockInfo.SCANNER.ID = config.getBlock(BlockInfo.SCANNER.KEY, BlockInfo.SCANNER.DEFAULT)
				.getInt();

		// load item info
		ItemInfo.LIBRARY.ID = config.getItem(ItemInfo.LIBRARY.KEY, ItemInfo.LIBRARY.DEFAULT)
				.getInt() - 256;

		config.save();

	}

}
