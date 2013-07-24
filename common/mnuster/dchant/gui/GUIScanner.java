package mnuster.dchant.gui;

import mnuster.dchant.info.BlockInfo;
import mnuster.dchant.info.GUIInfo;
import mnuster.dchant.info.ModInfo;
import mnuster.dchant.inventory.ContainerScanner;
import mnuster.dchant.tileentity.TileEntityScanner;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

public class GUIScanner extends GuiContainer {

	private TileEntityScanner scannerTE;

	public static final ResourceLocation location = new ResourceLocation(ModInfo.TEXTURE_LOCATION,
			BlockInfo.SCANNER.GUI);

	public GUIScanner(InventoryPlayer inventoryPlayer, TileEntityScanner tileEntity) {
		super(new ContainerScanner(inventoryPlayer, tileEntity));
		scannerTE = tileEntity;
		xSize = GUIInfo.SCANNER.GUI_WIDTH;
		ySize = GUIInfo.SCANNER.GUI_HEIGHT;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		mc.func_110434_K().func_110577_a(location);
		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
		drawTexturedModalRect(guiLeft + GUIInfo.SCANNER.PAPER_X_DEST, guiTop + GUIInfo.SCANNER.PAPER_Y_DEST,
				GUIInfo.SCANNER.PAPER_X_SRC, GUIInfo.SCANNER.PAPER_Y_SRC,
				GUIInfo.SCANNER.PAPER_WIDTH, GUIInfo.SCANNER.PAPER_HEIGHT);
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int par1, int par2) {
		String title = scannerTE.getInvName();
		fontRenderer.drawString(title, xSize / 2 - fontRenderer.getStringWidth(title) / 2, 6,
				0X404040);
		// for drawing "Inventory" text
		fontRenderer.drawString(I18n.func_135053_a("container.inventory"), 8, ySize - 96 + 2,
				4210752);
	}

}
