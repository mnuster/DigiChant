package mnuster.dchant.gui;

import mnuster.dchant.inventory.ContainerScanner;
import mnuster.dchant.tileentity.TileEntityScanner;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class GUIHandler implements IGuiHandler {

	public static final int SCANNER = 0;

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
		switch (ID) {
			case SCANNER:
				return (tileEntity != null && tileEntity instanceof TileEntityScanner) ? new ContainerScanner(
						player.inventory, (TileEntityScanner)tileEntity) : null;
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
		switch (ID) {
			case SCANNER:
				return (tileEntity != null && tileEntity instanceof TileEntityScanner) ? new GUIScanner(
						player.inventory, (TileEntityScanner)tileEntity) : null;
		}
		return null;
	}

}
