package mnuster.dchant;

import mnuster.dchant.block.BlockInprinter;
import mnuster.dchant.config.ConfigHandler;
import mnuster.dchant.lib.Ref;
import mnuster.dchant.network.GuiHandler;
import mnuster.dchant.network.PacketHandler;
import mnuster.dchant.proxy.CommonProxy;
import mnuster.dchant.tileentity.TileEntityInprinter;
import net.minecraft.block.Block;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod(modid = Ref.ID, name = Ref.NAME, version = Ref.VERSION)
@NetworkMod(channels = {Ref.CHANNEL}, clientSideRequired = true, serverSideRequired = false, packetHandler = PacketHandler.class)
public class DChant {

	@Instance(Ref.ID)
	public static DChant instance;

	@SidedProxy(clientSide = "mnuster.dchant.proxy.ClientProxy", serverSide = "mnuster.dchant.proxy.CommonProxy")
	public static CommonProxy proxy;
	
	
	public static int blockRunID = Ref.START_BLOCK_ID;
	public static Block blockInprinter;
	

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		proxy.init();
		ConfigHandler.init(event.getSuggestedConfigurationFile());
		NetworkRegistry.instance().registerGuiHandler(instance, new GuiHandler());
		
		blockInprinter = new BlockInprinter(blockRunID++);
		LanguageRegistry.addName(blockInprinter, "Inprinter");
		GameRegistry.registerBlock(blockInprinter, ((BlockInprinter) blockInprinter).getIDWName());
		GameRegistry.registerTileEntity(TileEntityInprinter.class, "containerInprinter");
		
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {

	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		
	}
}
