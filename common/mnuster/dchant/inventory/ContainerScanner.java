package mnuster.dchant.inventory;

import mnuster.dchant.info.GUIInfo;
import mnuster.dchant.tileentity.TileEntityScanner;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerScanner extends Container {

	private TileEntityScanner scannerTE;

	public ContainerScanner(InventoryPlayer inventory, TileEntityScanner tileEntity) {
		scannerTE = tileEntity;
		
		// standard for adding player inventory
		for (int i = 0; i < 3; ++i) {
			for (int j = 0; j < 9; ++j) {
				int x = GUIInfo.SCANNER.INV_X_START + j * 18;
				int y = GUIInfo.SCANNER.INV_Y_START + i * 18;
				addSlotToContainer(new Slot(inventory, j + i * 9 + 9, x, y));
			}
		}

		for (int i = 0; i < 9; ++i) {
			int x = GUIInfo.SCANNER.INV_X_START + i * 18;
			int y = GUIInfo.SCANNER.INV_Y_START + 58;
			addSlotToContainer(new Slot(inventory, i, x, y));
		}

		// add Library slot
		addSlotToContainer(new Slot(tileEntity, 0, GUIInfo.SCANNER.LIB_X_START,
				GUIInfo.SCANNER.LIB_Y_START));

		// add Enchanted Book slot
		addSlotToContainer(new Slot(tileEntity, 1, GUIInfo.SCANNER.BOOK_X_START,
				GUIInfo.SCANNER.BOOK_Y_START));

	}

	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return scannerTE.isUseableByPlayer(player);
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int index) {
		return null;
	}

}
