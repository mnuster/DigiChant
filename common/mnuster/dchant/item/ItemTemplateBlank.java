package mnuster.dchant.item;

import java.util.List;

import mnuster.dchant.DChant;
import mnuster.dchant.info.ItemInfo;
import mnuster.dchant.info.ModInfo;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemTemplateBlank extends Item {

	public ItemTemplateBlank(int id) {
		super(id);
		this.setCreativeTab(DChant.tabEnchant);
		setMaxStackSize(16);
		setUnlocalizedName(ItemInfo.TEMPLATE_BLANK.UNLOCALIZED_NAME);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister register) {
		itemIcon = register.registerIcon(ModInfo.TEXTURE_LOCATION + ":"
				+ ItemInfo.TEMPLATE_BLANK.ICON);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List info,
			boolean useExtraInformation) {
		super.addInformation(stack, player, info, useExtraInformation);

		info.add("No Enchantment");
	}

}
