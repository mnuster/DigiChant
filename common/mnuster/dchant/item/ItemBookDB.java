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

public class ItemBookDB extends Item {

	public ItemBookDB(int id) {
		super(id);
		this.setCreativeTab(DChant.tabEnchant);
		setMaxStackSize(1);
		setUnlocalizedName(ItemInfo.BOOK_DB.UNLOCALIZED_NAME);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister register) {
		itemIcon = register.registerIcon(ModInfo.TEXTURE_LOCATION + ":" + ItemInfo.BOOK_DB.ICON);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List info,
			boolean useExtraInformation) {
		if (DBHelper.hasDB(stack)) {
			info.add("Books: " + Short.toString(stack.getTagCompound().getShort("numBooks")));
		}
	}
}
