package mnuster.dchant.block;

import mnuster.dchant.DChant;
import mnuster.dchant.gui.GuiHandler;
import mnuster.dchant.info.BlockInfo;
import mnuster.dchant.info.ModInfo;
import mnuster.dchant.tileentity.TileEntityInprinter;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockInprinter extends BlockContainer {

	public BlockInprinter(int par1) {
		super(par1, Material.iron);
		
		this.setCreativeTab(DChant.tabEnchant);
		this.setHardness(2F);
		this.setStepSound(soundMetalFootstep);
		this.setUnlocalizedName(BlockInfo.INPRINTER.UNLOCALIZED_NAME);
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IconRegister iconRegister) {
		blockIcon = iconRegister.registerIcon(ModInfo.TEXTURE_LOCATION +":"+ BlockInfo.INPRINTER.ICON);
	}
	
	@Override
	public boolean onBlockActivated(World world, int i, int j, int k, EntityPlayer player, int par6, float par7, float par8, float par9) {
		if (!world.isRemote) {
			player.openGui(DChant.instance, GuiHandler.INPRINTER, world, i, j, k);
		}
		return true;
    }
	
	@Override
	public void onNeighborBlockChange(World world, int i, int j, int k, int id) {
		if (!world.isRemote) {			
			boolean isPowered = world.isBlockIndirectlyGettingPowered(i, j, k);
			world.setBlockMetadataWithNotify(i, j, k, isPowered ? 1 : 0, 3);
		}
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileEntityInprinter();
	}

}
