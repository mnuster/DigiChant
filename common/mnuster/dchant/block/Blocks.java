package mnuster.dchant.block;

import mnuster.dchant.lib.BlockInfo;
import mnuster.dchant.tileentity.TileEntityInprinter;
import net.minecraft.block.Block;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class Blocks {

	public static Block inprinter;
	
	public static void registerBlocks() {
		inprinter = new BlockInprinter(BlockInfo.INPRINTER_ID);
		GameRegistry.registerBlock(inprinter, BlockInfo.INPRINTER_UNLOCALIZED_NAME);
	}
	
	public static void registerNames() {
		LanguageRegistry.addName(inprinter, BlockInfo.INPRINTER_NAME);
	}
	
	public static void registerTileEntities() {
		GameRegistry.registerTileEntity(TileEntityInprinter.class, BlockInfo.INPRINTER_TE_KEY);
	}
	
}
