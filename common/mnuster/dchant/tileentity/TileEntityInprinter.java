package mnuster.dchant.tileentity;

import java.util.Map;

import mnuster.dchant.item.Items;
import mnuster.dchant.item.TemplateHelper;
import mnuster.dchant.lib.BlockInfo;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

public class TileEntityInprinter extends TileEntity implements ISidedInventory {

	private static final int[] slots_top = new int[] { 0, 1 };
	private static final int[] slots_sides = new int[] { 0, 1 };
	private static final int[] slots_bottom = new int[] { 2 };

	private ItemStack[] inprinterStacks;

	public int currentItemPrintTime;
	public int inprinterPrintTime;

	public TileEntityInprinter() {
		inprinterStacks = new ItemStack[3];
	}

	@Override
	public int getSizeInventory() {
		return inprinterStacks.length;
	}

	@Override
	public ItemStack getStackInSlot(int slot) {
		return inprinterStacks[slot];
	}

	@Override
	public ItemStack decrStackSize(int slot, int amt) {
		ItemStack stack = getStackInSlot(slot);
		if (stack != null) {
			if (stack.stackSize <= amt) {
				setInventorySlotContents(slot, null);
			} else {
				stack = stack.splitStack(amt);
				if (stack.stackSize == 0) {
					setInventorySlotContents(slot, null);
				}
			}
		}
		return stack;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int slot) {
		ItemStack stack = getStackInSlot(slot);
		if (stack != null) {
			setInventorySlotContents(slot, null);
		}
		return stack;
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack stack) {
		if (stack != null && stack.stackSize > getInventoryStackLimit()) {
			stack.stackSize = getInventoryStackLimit();
		}
		inprinterStacks[slot] = stack;
	}

	@Override
	public String getInvName() {
		return BlockInfo.INPRINTER_NAME;
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
	public boolean isUseableByPlayer(EntityPlayer entityplayer) {
		if (this.worldObj.getBlockTileEntity(this.xCoord, this.yCoord, this.zCoord) != this) {
			return false;
		} else {
			return entityplayer.getDistanceSq((double) this.xCoord + 0.5D,
					(double) this.yCoord + 0.5D, (double) this.zCoord + 0.5D) <= 64.0D;
		}
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
				return (stack.itemID == Item.enchantedBook.itemID);
			case 1:
				return TemplateHelper.isTemplate(stack);
			default:
				return false;
		}

	}

	@Override
	public int[] getAccessibleSlotsFromSide(int side) {
		return side == 0 ? slots_bottom : (side == 1 ? slots_top : slots_sides);
	}

	@Override
	public boolean canInsertItem(int slot, ItemStack stack, int side) {
		return isItemValidForSlot(slot, stack);
	}

	@Override
	public boolean canExtractItem(int slot, ItemStack itemstack, int side) {
		return (side != 0 || slot != 0 || slot != 1);
	}

	@Override
	public void readFromNBT(NBTTagCompound tagCompound) {
		super.readFromNBT(tagCompound);

		NBTTagList tagList = tagCompound.getTagList("Inventory");
		for (int i = 0; i < tagList.tagCount(); i++) {
			NBTTagCompound tag = (NBTTagCompound) tagList.tagAt(i);
			byte slot = tag.getByte("Slot");
			if (slot >= 0 && slot < inprinterStacks.length) {
				inprinterStacks[slot] = ItemStack.loadItemStackFromNBT(tag);
			}
		}
	}

	@Override
	public void writeToNBT(NBTTagCompound tagCompound) {
		super.writeToNBT(tagCompound);

		NBTTagList itemList = new NBTTagList();
		for (int i = 0; i < inprinterStacks.length; i++) {
			ItemStack stack = inprinterStacks[i];
			if (stack != null) {
				NBTTagCompound tag = new NBTTagCompound();
				tag.setByte("Slot", (byte) i);
				stack.writeToNBT(tag);
				itemList.appendTag(tag);
			}
		}
		tagCompound.setTag("Inventory", itemList);
	}

	@Override
	public void updateEntity() {
		if (worldObj.getBlockMetadata(xCoord, yCoord, zCoord) == 0 && canPrint()) {
			printItem();
			onInventoryChanged();
		}
	}

	public boolean canPrint() {
		// check for both input slots empty
		if (inprinterStacks[0] == null || inprinterStacks[1] == null || inprinterStacks[2] != null)
			return false;

		// check for book and template
		if (TemplateHelper.isEnchBook(inprinterStacks[0])
				&& TemplateHelper.isTemplate(inprinterStacks[1])) {
			return (TemplateHelper.getPrintResult(inprinterStacks[0], inprinterStacks[1]) != null);
		}
		return false;
	}

	public void printItem() {
		// copy template to output
		inprinterStacks[2] = TemplateHelper.getPrintResult(inprinterStacks[0], inprinterStacks[1]);

		// consume enchanted book and one template
		inprinterStacks[0] = null;
		if (--inprinterStacks[1].stackSize == 0) {
			inprinterStacks[1] = null;
		}

	}

}
