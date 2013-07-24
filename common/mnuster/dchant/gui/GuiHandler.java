package mnuster.dchant.gui;

import mnuster.dchant.DChant;
import mnuster.dchant.inventory.ContainerEnchantDB;
import mnuster.dchant.inventory.ContainerInprinter;
import mnuster.dchant.tileentity.TileEntityEnchantDB;
import mnuster.dchant.tileentity.TileEntityInprinter;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.network.NetworkRegistry;

public class GuiHandler implements IGuiHandler {

	public static final int INPRINTER = 0;
	public static final int ENCHANTDB = 1;

	public GuiHandler() {
		NetworkRegistry.instance().registerGuiHandler(DChant.instance, this);
	}

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
		switch (ID) {
			case INPRINTER:
				return (tileEntity != null && tileEntity instanceof TileEntityInprinter) ? new ContainerInprinter(
						player.inventory, (TileEntityInprinter) tileEntity) : null;
			case ENCHANTDB:
				return (tileEntity != null && tileEntity instanceof TileEntityEnchantDB) ? new ContainerEnchantDB(
						player.inventory, (TileEntityEnchantDB) tileEntity) : null;
			default:
				return null;
		}
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
		switch (ID) {
			case INPRINTER:
				return (tileEntity != null && tileEntity instanceof TileEntityInprinter) ? new GuiInprinter(
						player.inventory, (TileEntityInprinter) tileEntity) : null;
			case ENCHANTDB:
				return (tileEntity != null && tileEntity instanceof TileEntityEnchantDB) ? new GuiEnchantDB(
						player.inventory, (TileEntityEnchantDB) tileEntity) : null;
			default:
				return null;
		}
	}

}
