package mnuster.dchant;

import mnuster.dchant.block.Blocks;
import mnuster.dchant.config.ConfigHandler;
import mnuster.dchant.gui.GUIHandler;
import mnuster.dchant.info.ModInfo;
import mnuster.dchant.item.Items;
import mnuster.dchant.network.PacketHandler;
import mnuster.dchant.proxy.CommonProxy;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;

@Mod(modid = ModInfo.ID, name = ModInfo.NAME, version = ModInfo.VERSION)
@NetworkMod(channels = { ModInfo.CHANNEL }, clientSideRequired = true, serverSideRequired = false, packetHandler = PacketHandler.class)
public class DChant {

	@Instance(ModInfo.ID)
	public static DChant instance;

	@SidedProxy(clientSide = "mnuster.dchant.proxy.ClientProxy", serverSide = "mnuster.dchant.proxy.CommonProxy")
	public static CommonProxy proxy;

	public static CreativeTabs tabEnchant = new CreativeTabs("tabEnchant") {
		public ItemStack getIconItemStack() {
			return new ItemStack(Items.library);
		}
	};

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {

		ConfigHandler.init(event.getSuggestedConfigurationFile());
		proxy.init();

		Blocks.registerBlocks();
		Items.registerItems();

	}

	@EventHandler
	public void init(FMLInitializationEvent event) {

		Blocks.registerNames();
		Blocks.registerTileEntities();

		Items.registerNames();

		NetworkRegistry.instance().registerGuiHandler(DChant.instance, new GUIHandler());

	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {

	}
}
