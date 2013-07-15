package mnuster.dchant.item;

import java.util.List;

import mnuster.dchant.lib.ItemInfo;
import mnuster.dchant.lib.ModInfo;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemTemplate extends Item {

	@SideOnly(Side.CLIENT)
	private Icon itemIconBlank;
	
	@SideOnly(Side.CLIENT)
	private Icon itemIconEnch;
	
	public ItemTemplate(int id) {
		super(id);
		setCreativeTab(CreativeTabs.tabMisc);
		setMaxStackSize(1);
		setUnlocalizedName(ItemInfo.TEMPLATE_UNLOCALIZED_NAME);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister register) {
		itemIconBlank = register.registerIcon(ModInfo.TEXTURE_LOCATION +":"+ ItemInfo.TEMPLATE_ICON_BLANK);
		itemIconEnch = register.registerIcon(ModInfo.TEXTURE_LOCATION +":"+ ItemInfo.TEMPLATE_ICON_ENCH);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIconFromDamage(int dmg) {
		return dmg == 0 ? itemIconBlank : itemIconEnch;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack itemstack, EntityPlayer player, List info, boolean useExtraInformation) {
		if (itemstack.getItemDamage() > 0) info.add("Charge: " + itemstack.getItemDamage());
	}
	
	@Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
    {
		// temporary for testing purposes
		if (!world.isRemote) {
			if (!player.isSneaking()) {
				stack.setItemDamage(stack.getItemDamage() + 1);
			} else {
				stack.setItemDamage(0);
			}
		}
        return stack;
    }
	

}
