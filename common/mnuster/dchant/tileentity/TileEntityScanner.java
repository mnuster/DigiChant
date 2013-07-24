package mnuster.dchant.tileentity;

import mnuster.dchant.info.BlockInfo;
import mnuster.dchant.item.Items;
import mnuster.dchant.item.LibraryHelper;
import net.minecraft.block.BlockIce;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class TileEntityScanner extends TileEntityBase {
	
	public TileEntityScanner() {
		inventory = new ItemStack[2];
	}

	@Override
	public String getInvName() {
		return BlockInfo.SCANNER.NAME;
	}
	
	@Override
	public boolean isItemValidForSlot(int slot, ItemStack stack) {
		switch(slot) {
			case 0:
				return stack.itemID == Items.library.itemID;
			case 1:
				return stack.itemID == Item.enchantedBook.itemID;
			default:
				return false;
		}
	}
	
	@Override
	public boolean canInsertItem(int slot, ItemStack stack, int side) {
		return isItemValidForSlot(slot, stack);
	}
	
	@Override
	public boolean canExtractItem(int slot, ItemStack stack, int side) {
		return false;
	}
	
	@Override
	public void updateEntity() {
		if (!worldObj.isRemote) {
			if (inventory[1] != null && inventory[1].itemID == Item.enchantedBook.itemID) {
				if (inventory[0] != null && inventory[0].itemID == Items.library.itemID) {
					LibraryHelper.addBook(inventory[0], inventory[1]);
					inventory[1] = null;
					onInventoryChanged();
				}
			}
		}
	}
}
