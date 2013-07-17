package mnuster.dchant.inventory;

import mnuster.dchant.item.Items;
import mnuster.dchant.tileentity.TileEntityInprinter;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ContainerInprinter extends Container {

	private TileEntityInprinter inprinter;

	public ContainerInprinter(InventoryPlayer inventory, TileEntityInprinter tileEntity) {
		inprinter = tileEntity;

		addSlotToContainer(new Slot(tileEntity, 0, 27, 35));
		addSlotToContainer(new Slot(tileEntity, 1, 76, 35));
		addSlotToContainer(new SlotOutput(tileEntity, 2, 134, 35));

		// standard for adding player inventory
		int i;
		for (i = 0; i < 3; ++i) {
			for (int j = 0; j < 9; ++j) {
				addSlotToContainer(new Slot(inventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
			}
		}

		for (i = 0; i < 9; ++i) {
			addSlotToContainer(new Slot(inventory, i, 8 + i * 18, 142));
		}
	}

	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return inprinter.isUseableByPlayer(player);
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int index) {
		ItemStack itemstack = null;
		Slot slot = (Slot) inventorySlots.get(index);

		if (slot != null && slot.getHasStack()) {
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();

			if (index == 2) {
				if (!mergeItemStack(itemstack1, 3, 39, true)) return null;
				slot.onSlotChange(itemstack1, itemstack);
			} else if (index != 1 && index != 0) {
				if (itemstack1.itemID == Item.enchantedBook.itemID) {
					if (!mergeItemStack(itemstack1, 0, 1, false)) return null;
				} else if (itemstack1.itemID == Items.templateBlank.itemID || itemstack1.itemID == Items.templateEnchanted.itemID) {
					if (!mergeItemStack(itemstack1, 1, 2, false)) return null;
				} else if (index >= 3 && index < 30) {
					if (!mergeItemStack(itemstack1, 30, 39, false)) return null;
				} else if (index >= 30 && index < 39) {
					if (!mergeItemStack(itemstack1, 3, 30, false)) return null;
				}
			} else {
				if (!mergeItemStack(itemstack1, 3, 39, false)) return null;
			}

			if (itemstack1.stackSize == 0) {
				slot.putStack((ItemStack) null);
			} else {
				slot.onSlotChanged();
			}

			if (itemstack1.stackSize == itemstack.stackSize) return null;

			slot.onPickupFromSlot(player, itemstack1);
		}

		return itemstack;
	}

}
