package mnuster.dchant.block;

import mnuster.dchant.DChant;
import mnuster.dchant.gui.GuiHandler;
import mnuster.dchant.info.BlockInfo;
import mnuster.dchant.info.ModInfo;
import mnuster.dchant.tileentity.TileEntityEnchantDB;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockEnchantDB extends BlockContainer {

	protected BlockEnchantDB(int id) {
		super(id, Material.iron);
		
		this.setCreativeTab(DChant.tabEnchant);
		this.setHardness(2F);
		this.setStepSound(soundMetalFootstep);
		this.setUnlocalizedName(BlockInfo.ENCHANTDB.UNLOCALIZED_NAME);		
	}
	
	@Override
	public boolean onBlockActivated(World world, int i, int j, int k, EntityPlayer player, int par6, float par7, float par8, float par9) {
		if (!world.isRemote) {
			player.openGui(DChant.instance, GuiHandler.ENCHANTDB, world, i, j, k);
		}
		return true;
    }
	
	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IconRegister iconRegister) {
		blockIcon = iconRegister.registerIcon(ModInfo.TEXTURE_LOCATION +":"+ BlockInfo.ENCHANTDB.ICON);
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileEntityEnchantDB();
	}
	
	

}
