package com.kaijin.ChargingBench;

import ic2.api.IElectricItem;
import net.minecraft.src.IInventory;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Slot;

public class SlotInput extends Slot
{
	public int invIndex;
	public int chargeTier;

	public SlotInput(IInventory inv, int index, int xpos, int ypos)
	{
		super(inv, index, xpos, ypos);
		this.invIndex = index;
	}

	@Override
    public boolean isItemValid(ItemStack stack)
    {
    	// Decide if the item is a valid IC2 electrical item
    	if (stack != null && stack.getItem() instanceof IElectricItem)
    	{
    		IElectricItem item = (IElectricItem)(stack.getItem());
    		if (item.getTier() <= ((TEChargingBench)inventory).baseTier) return true;
    	}
        return false; 
    }

	@Override
    public void onSlotChanged()
    {
    	if (this.inventory instanceof TEChargingBench)
    	{
            ((TEChargingBench)this.inventory).onInventoryChanged(this.invIndex);
    	}
    	else
    	{
            this.inventory.onInventoryChanged();
    	}
    }
}