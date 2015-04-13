package mnuster.dchant.network;

import mnuster.dchant.gui.GuiInprinter;
import mnuster.dchant.inventory.ContainerInprinter;
import mnuster.dchant.tileentity.TileEntityInprinter;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {
		TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
		if (tileEntity instanceof TileEntityInprinter) {
			return new ContainerInprinter(player.inventory, (TileEntityInprinter) tileEntity);
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {
		TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
		if (tileEntity instanceof TileEntityInprinter) {
			return new GuiInprinter(player.inventory, (TileEntityInprinter) tileEntity);
		}
		return null;
	}

}
