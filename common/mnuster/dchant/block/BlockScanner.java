package mnuster.dchant.block;

import mnuster.dchant.DChant;
import mnuster.dchant.gui.GUIHandler;
import mnuster.dchant.info.BlockInfo;
import mnuster.dchant.info.ModInfo;
import mnuster.dchant.tileentity.TileEntityScanner;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockScanner extends BlockContainer {

	public BlockScanner(int id) {
		super(id, Material.iron);

		this.setCreativeTab(DChant.tabEnchant);
		this.setHardness(2F);
		this.setStepSound(soundMetalFootstep);
		this.setUnlocalizedName(BlockInfo.SCANNER.UNLOCALIZED_NAME);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IconRegister iconRegister) {
		blockIcon = iconRegister.registerIcon(ModInfo.TEXTURE_LOCATION + ":"
				+ BlockInfo.SCANNER.ICON);
	}

	@Override
	public boolean onBlockActivated(World world, int i, int j, int k, EntityPlayer player,
			int par6, float par7, float par8, float par9) {
		if (!world.isRemote) {
			player.openGui(DChant.instance, GUIHandler.SCANNER, world, i, j, k);
		}
		return true;
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileEntityScanner();
	}

}
