package mnuster.dchant.block;

import mnuster.dchant.DChant;
import mnuster.dchant.lib.Ref;
import mnuster.dchant.tileentity.TileEntityInprinter;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockInprinter extends BlockContainer {

	public BlockInprinter(int par1) {
		super(par1, Material.wood);
		
		this.setCreativeTab(CreativeTabs.tabBlock);
		this.setHardness(2.5F);
		this.setStepSound(soundStoneFootstep);
		this.setUnlocalizedName("inprinter");
	}
	
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister) {
		blockIcon = iconRegister.registerIcon(getIDWName());
	}

	public String getIDWName() {
		return Ref.ID + ":" + this.getUnlocalizedName().substring(5);
	}
	
	@Override
	public boolean onBlockActivated(World world, int i, int j, int k, EntityPlayer player, int par6, float par7, float par8, float par9) {
		if (!world.isRemote) {
			TileEntityInprinter tileEntityInprinter = (TileEntityInprinter)world.getBlockTileEntity(i, j, k);
			if (tileEntityInprinter != null) {
				player.openGui(DChant.instance, 0, world, i, j, k);
			}
		}
		return true;
    }

	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileEntityInprinter();
	}

}
