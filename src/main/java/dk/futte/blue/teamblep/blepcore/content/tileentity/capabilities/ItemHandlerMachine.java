package dk.futte.blue.teamblep.blepcore.content.tileentity.capabilities;

import dk.futte.blue.teamblep.blepcore.content.inventory.EnumSlotType;
import dk.futte.blue.teamblep.blepcore.content.inventory.InventoryMachineContainer;
import dk.futte.blue.teamblep.blepcore.content.inventory.SlotData;
import net.minecraft.item.ItemStack;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.items.ItemStackHandler;

/**
 * @author Blue
 * @author Kelan
 */

public class ItemHandlerMachine extends ItemStackHandler
{
    private InventoryMachineContainer inventoryContainer;

    public ItemHandlerMachine(InventoryMachineContainer inventoryContainer)
    {
        super(inventoryContainer.getNumSlots());
        this.inventoryContainer = inventoryContainer;
    }

    @Override
    public ItemStack insertItem(int slot, ItemStack stack, boolean simulate)
    {
        SlotData slotData = inventoryContainer.getSlotData(slot);

        if (inventoryContainer.getSlotsWithType(EnumSlotType.INPUT).contains(slotData))
        {
            return super.insertItem(slot, stack, simulate);
        }
        if (inventoryContainer.getSlotsWithType(EnumSlotType.BATTERY).contains(slotData) && stack.hasCapability(CapabilityEnergy.ENERGY, null))
        {
            return super.insertItem(slot, stack, simulate);
        }
        return null;
    }

    @Override
    public ItemStack extractItem(int slot, int amount, boolean simulate)
    {
//        SlotData slotData = inventoryContainer.getSlotData(slot);
//
//        if (inventoryContainer.getSlotsWithType(EnumSlotType.OUTPUT).contains(slotData))
//        {
//            return super.extractItem(slot, amount, simulate);
//        }
//        if (inventoryContainer.getSlotsWithType(EnumSlotType.BATTERY).contains(slotData)) //TODO: check if battery is empty, and if it is, pull it out of the machine.
//        {
//            return super.extractItem(slot, amount, simulate);
//        }
        return super.extractItem(slot, amount, simulate);
    }
}