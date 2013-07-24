package mnuster.dchant.block;

import mnuster.dchant.info.BlockInfo;
import mnuster.dchant.tileentity.TileEntityScanner;
import net.minecraft.block.Block;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class Blocks {

	public static Block scanner;

	public static void registerBlocks() {
		scanner = new BlockScanner(BlockInfo.SCANNER.ID);
		GameRegistry.registerBlock(scanner, BlockInfo.SCANNER.UNLOCALIZED_NAME);
	}

	public static void registerNames() {
		LanguageRegistry.addName(scanner, BlockInfo.SCANNER.NAME);
	}

	public static void registerTileEntities() {
		GameRegistry.registerTileEntity(TileEntityScanner.class, BlockInfo.SCANNER.TE_KEY);
	}

}
