package mnuster.dchant.config;

import java.io.File;

import mnuster.dchant.lib.Ref;
import net.minecraftforge.common.Configuration;

public class ConfigHandler {

	public static void init(File file) {
		Configuration config = new Configuration(file);
		
		config.load();
		
		int baseBlockID = config.get("id's", "baseBlockID", Ref.START_BLOCK_ID).getInt();
		int baseItemID = config.get("id's", "baseItemID", Ref.START_ITEM_ID).getInt();
		
		config.save();
		
		System.out.println("Base Block ID: " + baseBlockID);
		System.out.println("Base Item ID: " + baseItemID);
		
	}
	
}
