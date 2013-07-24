package mnuster.dchant.tileentity;

import mnuster.dchant.info.BlockInfo;
import mnuster.dchant.item.DBHelper;
import mnuster.dchant.item.ItemBookDB;
import mnuster.dchant.item.Items;
import mnuster.dchant.block.BlockInprinter;
import net.minecraft.block.BlockIce;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

public class TileEntityEnchantDB extends TileEntity implements ISidedInventory {

	private ItemStack[] enchantDBStacks;

	public TileEntityEnchantDB() {
		enchantDBStacks = new ItemStack[2];
	}

	@Override
	public int getSizeInventory() {
		return enchantDBStacks.length;
	}

	@Override
	public ItemStack getStackInSlot(int slot) {
		return enchantDBStacks[slot];
	}

	@Override
	public ItemStack decrStackSize(int slot, int count) {
		ItemStack stack = getStackInSlot(slot);
		if (stack != null) {
			if (stack.stackSize <= count) {
				setInventorySlotContents(slot, null);
			} else {
				stack = stack.splitStack(count);
				onInventoryChanged();
			}
		}
		return stack;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int slot) {
		ItemStack stack = getStackInSlot(slot);
		setInventorySlotContents(slot, null);
		return stack;
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack stack) {
		if (stack != null && stack.stackSize > getInventoryStackLimit()) {
			stack.stackSize = getInventoryStackLimit();
		}
		enchantDBStacks[slot] = stack;
		onInventoryChanged();
	}

	@Override
	public String getInvName() {
		return BlockInfo.ENCHANTDB.NAME;
	}

	@Override
	public boolean isInvNameLocalized() {
		return false;
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		return player.getDistanceSq(xCoord + 0.5, yCoord + 0.5, zCoord + 0.5) <= 64;
	}

	@Override
	public void openChest() {
	}

	@Override
	public void closeChest() {
	}

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack stack) {
		switch (slot) {
			case 0:
				return stack.itemID == Items.bookDB.itemID;
			case 1:
				return stack.itemID == Item.enchantedBook.itemID;
			default:
				return false;
		}
	}

	@Override
	public int[] getAccessibleSlotsFromSide(int side) {
		return (side != 0) ? new int[] { 0, 1 } : null;
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
	public void writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);

		NBTTagList itemList = new NBTTagList();
		for (int i = 0; i < getSizeInventory(); i++) {
			ItemStack stack = enchantDBStacks[i];
			if (stack != null) {
				NBTTagCompound item = new NBTTagCompound();
				item.setByte("Slot", (byte)i);
				stack.writeToNBT(item);
				itemList.appendTag(item);
			}
		}
		compound.setTag("Inventory", itemList);

	}

	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);

		NBTTagList itemList = compound.getTagList("Inventory");
		for (int i = 0; i < itemList.tagCount(); i++) {
			NBTTagCompound item = (NBTTagCompound)itemList.tagAt(i);
			int slot = item.getByte("Slot");
			if (slot >= 0 && slot < getSizeInventory()) {
				setInventorySlotContents(slot, ItemStack.loadItemStackFromNBT(item));
			}
		}
	}

	@Override
	public void updateEntity() {
		if (!worldObj.isRemote) {
			if (enchantDBStacks[0] == null || enchantDBStacks[1] == null) {
				return;
			}
			if (enchantDBStacks[0].itemID == Items.bookDB.itemID
					&& enchantDBStacks[1].itemID == Item.enchantedBook.itemID) {
				DBHelper.addBook(enchantDBStacks[0], enchantDBStacks[1]);
				enchantDBStacks[1] = null;
				onInventoryChanged();
			}
		}
	}

}
