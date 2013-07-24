package mnuster.dchant.gui;

import org.lwjgl.opengl.GL11;

import mnuster.dchant.info.BlockInfo;
import mnuster.dchant.info.ModInfo;
import mnuster.dchant.inventory.ContainerEnchantDB;
import mnuster.dchant.tileentity.TileEntityEnchantDB;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GuiEnchantDB extends GuiContainer {

	public static final ResourceLocation location = new ResourceLocation(ModInfo.TEXTURE_LOCATION,
			BlockInfo.ENCHANTDB.GUI);

	public GuiEnchantDB(InventoryPlayer inventory, TileEntityEnchantDB tileEntity) {
		super(new ContainerEnchantDB(inventory, tileEntity));
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
		// GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.func_110434_K().func_110577_a(location);
		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
	}

}
