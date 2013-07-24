package mnuster.dchant.block;

import mnuster.dchant.info.BlockInfo;
import mnuster.dchant.tileentity.TileEntityEnchantDB;
import mnuster.dchant.tileentity.TileEntityInprinter;
import net.minecraft.block.Block;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class Blocks {

	public static Block inprinter;
	public static Block enchantDB;
	
	public static void registerBlocks() {
		inprinter = new BlockInprinter(BlockInfo.INPRINTER.ID);
		GameRegistry.registerBlock(inprinter, BlockInfo.INPRINTER.UNLOCALIZED_NAME);
		
		enchantDB = new BlockEnchantDB(BlockInfo.ENCHANTDB.ID);
		GameRegistry.registerBlock(enchantDB, BlockInfo.ENCHANTDB.UNLOCALIZED_NAME);
	}
	
	public static void registerNames() {
		LanguageRegistry.addName(inprinter, BlockInfo.INPRINTER.NAME);
		LanguageRegistry.addName(enchantDB, BlockInfo.ENCHANTDB.NAME);
	}
	
	public static void registerTileEntities() {
		GameRegistry.registerTileEntity(TileEntityInprinter.class, BlockInfo.INPRINTER.TE_KEY);
		GameRegistry.registerTileEntity(TileEntityEnchantDB.class, BlockInfo.ENCHANTDB.TE_KEY);
	}
	
}
