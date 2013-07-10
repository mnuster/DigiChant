package mnuster.dchant.tileentity;

import mnuster.dchant.crafting.InprinterRecipes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class TileEntityInprinter extends TileEntity implements IInventory{

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
		if (stack != null && stack.stackSize >getInventoryStackLimit()) {
			stack.stackSize = getInventoryStackLimit();
		}
		inprinterStacks[slot] = stack;		
	}

	@Override
	public String getInvName() {
		return "Inprinter";
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
			return entityplayer.getDistanceSq((double)this.xCoord + 0.5D, 
											(double)this.yCoord + 0.5D, 
											(double)this.zCoord + 0.5D) <= 64.0D;
		}
	}

	@Override
	public void openChest() {}

	@Override
	public void closeChest() {}

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack stack) {
		// will have to change later when templates implemented
		return true;
	}
	
	
	@SideOnly(Side.CLIENT)
    public int getPrintProgressScaled(int scale)
    {
        return inprinterPrintTime * scale / 200;
    }
	
	
	@Override
	public void updateEntity() {
		if (canPrint()) {
			printItem();
			onInventoryChanged();
		}
	}
	
	public boolean canPrint() {
		// check for both input slots empty
		if (inprinterStacks[0] == null || inprinterStacks[1] == null) return false;
		// find result of input and check if null or no (redstone) provided
		ItemStack stackResult = InprinterRecipes.Printing().getPrintingResult(inprinterStacks[0]);
		if (stackResult == null || inprinterStacks[1].itemID != Item.redstone.itemID) return false;
		// check if output slot empty
		if (inprinterStacks[2] == null) return true;
		// check if current output equal to result
		if (!inprinterStacks[2].isItemEqual(stackResult)) return false;
		// check if output is equal to result and not max stack size
		int amt = inprinterStacks[2].stackSize + stackResult.stackSize;
		return (amt <= getInventoryStackLimit() && amt <= stackResult.getMaxStackSize());
	}
	
	public void printItem() {
		if (canPrint()) {
			ItemStack stackResult = InprinterRecipes.Printing().getPrintingResult(inprinterStacks[0]);
			if (inprinterStacks[2] == null) {
				inprinterStacks[2] = stackResult.copy();
			} else if (inprinterStacks[2].isItemEqual(stackResult)) {
				inprinterStacks[2].stackSize += stackResult.stackSize;
			}
			
			--inprinterStacks[0].stackSize;
			--inprinterStacks[1].stackSize;
			
			if (inprinterStacks[0].stackSize <= 0) inprinterStacks[0] = null;
			if (inprinterStacks[1].stackSize <= 0) inprinterStacks[1] = null;
		}
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
		
		//inprinterPrintTime = tagCompound.getShort("PrintTime");
    }
	
	@Override
	public void writeToNBT(NBTTagCompound tagCompound) {
		super.writeToNBT(tagCompound);
		
		//tagCompound.setShort("PrintTime", (short)inprinterPrintTime);
		                 
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
	

}
