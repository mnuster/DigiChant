package mnuster.dchant.item;

import java.util.List;

import mnuster.dchant.lib.ItemInfo;
import mnuster.dchant.lib.ModInfo;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemTemplateEnchanted extends Item {

	public ItemTemplateEnchanted(int par1) {
		super(par1);
		setCreativeTab(CreativeTabs.tabMisc);
		setMaxStackSize(1);
		setUnlocalizedName(ItemInfo.TEMPLATE_ENCH.UNLOCALIZED_NAME);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister register) {
		itemIcon = register.registerIcon(ModInfo.TEXTURE_LOCATION + ":"
				+ ItemInfo.TEMPLATE_ENCH.ICON);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List info,
			boolean useExtraInformation) {
		// super.addInformation(stack, player, info, useExtraInformation);

		info.add(TemplateHelper.getEnchantmentName(stack));
		info.add("Charge: " + stack.getItemDamage());
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack par1ItemStack) {
		return true;
	}

	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
		// temporary for testing purposes
		if (!world.isRemote) {
			stack = new ItemStack(Items.templateBlank);
		}
		return stack;
	}

}
