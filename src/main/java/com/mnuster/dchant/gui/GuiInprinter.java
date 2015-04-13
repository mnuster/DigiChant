package mnuster.dchant.gui;

import org.lwjgl.opengl.GL11;

import mnuster.dchant.inventory.ContainerInprinter;
import mnuster.dchant.lib.BlockInfo;
import mnuster.dchant.lib.ModInfo;
import mnuster.dchant.tileentity.TileEntityInprinter;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GuiInprinter extends GuiContainer {

	public static final ResourceLocation location = new ResourceLocation(ModInfo.TEXTURE_LOCATION, BlockInfo.INPRINTER.GUI);
	private TileEntityInprinter inprinterInventory;
	
	public GuiInprinter(InventoryPlayer inventoryPlayer, TileEntityInprinter tileEntity) {
		super(new ContainerInprinter(inventoryPlayer, tileEntity));
		inprinterInventory = tileEntity;
	}
	@Override
	protected void drawGuiContainerForegroundLayer(int par1, int par2)
	{
		String title = inprinterInventory.getInvName();
		this.fontRenderer.drawString(title, this.xSize / 2 - this.fontRenderer.getStringWidth(title) / 2, 6, 4210752);
		// for drawing "Inventory" text
		this.fontRenderer.drawString(I18n.func_135053_a("container.inventory"), 8, this.ySize - 96 + 2, 4210752);
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.func_110434_K().func_110577_a(location);
		int x = (width - xSize) / 2;
        int y = (height - ySize) / 2;
        drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
	}

}
