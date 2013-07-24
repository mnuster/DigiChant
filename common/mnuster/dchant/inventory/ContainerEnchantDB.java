package mnuster.dchant.inventory;

import mnuster.dchant.item.Items;
import mnuster.dchant.tileentity.TileEntityEnchantDB;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ContainerEnchantDB extends Container {
	
	private TileEntityEnchantDB enchantDB;

	public ContainerEnchantDB(InventoryPlayer inventory, TileEntityEnchantDB tileEntity) {
		enchantDB = tileEntity;
		
		addSlotToContainer(new Slot(tileEntity, 0, 10, 10));
		addSlotToContainer(new Slot(tileEntity, 1, 10, 30));
		
		// standard for player inventory
		for (int i = 0; i < 3; ++i) {
			for (int j = 0; j < 9; ++j) {
				addSlotToContainer(new Slot(inventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
			}
		}

		for (int i = 0; i < 9; ++i) {
			addSlotToContainer(new Slot(inventory, i, 8 + i * 18, 142));
		}
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return enchantDB.isUseableByPlayer(player);
	}
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int index) {
		return null;
	}

}
