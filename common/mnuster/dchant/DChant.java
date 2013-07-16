package mnuster.dchant;

import mnuster.dchant.block.BlockInprinter;
import mnuster.dchant.block.Blocks;
import mnuster.dchant.config.ConfigHandler;
import mnuster.dchant.item.Items;
import mnuster.dchant.lib.ModInfo;
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

@Mod(modid = ModInfo.ID, name = ModInfo.NAME, version = ModInfo.VERSION)
@NetworkMod(channels = { ModInfo.CHANNEL }, clientSideRequired = true, serverSideRequired = false, packetHandler = PacketHandler.class)
public class DChant {

	@Instance(ModInfo.ID)
	public static DChant instance;

	@SidedProxy(clientSide = "mnuster.dchant.proxy.ClientProxy", serverSide = "mnuster.dchant.proxy.CommonProxy")
	public static CommonProxy proxy;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {

		ConfigHandler.init(event.getSuggestedConfigurationFile());
		proxy.init();

		NetworkRegistry.instance().registerGuiHandler(instance,
				new GuiHandler());

		Blocks.registerBlocks();
		Items.registerItems();

	}

	@EventHandler
	public void init(FMLInitializationEvent event) {

		Blocks.registerNames();
		Blocks.registerTileEntities();

		Items.registerNames();

	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {

	}
}
