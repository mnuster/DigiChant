package mnuster.dchant.inventory;

import mnuster.dchant.tileentity.TileEntityInprinter;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.tileentity.TileEntityFurnace;

public class ContainerInprinter extends Container {

	private TileEntityInprinter inprinter;
	
	public ContainerInprinter(InventoryPlayer inventory, TileEntityInprinter tileEntity) {
		inprinter = tileEntity;
		
		addSlotToContainer(new Slot(tileEntity, 0, 27, 35));
		addSlotToContainer(new Slot(tileEntity, 1, 76, 35));
		addSlotToContainer(new Slot(tileEntity, 2, 134, 35));
		
		// standard for adding player inventory
		int i;
		for (i = 0; i < 3; ++i)
        {
            for (int j = 0; j < 9; ++j)
            {
                this.addSlotToContainer(new Slot(inventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for (i = 0; i < 9; ++i)
        {
            this.addSlotToContainer(new Slot(inventory, i, 8 + i * 18, 142));
        }
	}

	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return inprinter.isUseableByPlayer(player);
	}
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2)
    {
        return null;
    }
	

}
