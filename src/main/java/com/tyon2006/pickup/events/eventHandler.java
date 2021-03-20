package com.tyon2006.pickup.events;

import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.ItemStackHandler;
import net.mcft.copy.backpacks.api.BackpackHelper;
import net.mcft.copy.backpacks.api.IBackpack;
import net.mcft.copy.backpacks.api.IBackpackData;
import net.mcft.copy.backpacks.misc.BackpackDataItems;
import net.mcft.copy.backpacks.misc.BackpackSize;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.util.NonNullList;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;

public class eventHandler {
	
	@SubscribeEvent(priority = EventPriority.LOWEST)
	@SideOnly(Side.CLIENT)
    public void pickupItem(EntityItemPickupEvent e) {
		
		//check if the player is wearing a backpack and cancel if not
		IBackpack backpack = BackpackHelper.getBackpack(e.getEntityPlayer());
		if (backpack == null) return;
		
        //get player's inventory
		EntityPlayer player = e.getEntityPlayer();

		//get the item
		ItemStack item = e.getItem().getItem();
		
		//quit if either is null
		if (item.isEmpty() || player == null) return;
		
		//get the player's inventory
		NonNullList<ItemStack> playerInventory = e.getEntityPlayer().inventory.mainInventory;
		ItemStack itemstack = backpack.getStack();
		
		
		ItemStack packItem = item.copy();
		System.out.println("player picked up item: " + item.getCount() + " " + item.getDisplayName());
		
		System.out.println("player inventory size is: " + playerInventory.size());

        //get the backpack
		IBackpackData backpackdata = backpack.getData();
		
		//grabs the dimensions of the backpack, not the data.
		NBTBase serial = backpackdata.serializeNBT();
		System.out.println(serial.toString());
		
		//get the data in the backpack
		BackpackDataItems data = ((BackpackDataItems)backpack.getData());
		
		//get the backpack's capacity
		BackpackSize packDimensions = data.getSize();
		int packSize = packDimensions.getColumns() * packDimensions.getRows();
		System.out.println("backpack capacity is: " + packSize);
		
		ItemStackHandler contents = data.getItems(e.getEntityPlayer().getEntityWorld(),e.getEntityPlayer());
		
		System.out.println("contents: " + contents.serializeNBT().toString());
		
		//count items in backpack
		int counterPack = packSize-1;
		int packFill = 0;
		while (counterPack > 0) {
			if(contents.getStackInSlot(counterPack) != null) packFill++;
			counterPack--;
		}
		
		//count items in inventory
		int counterInventory = playerInventory.size()-1;
		int inventoryFill = 0;
		while (counterInventory > 0) {
			if(playerInventory.get(counterInventory) != null) inventoryFill++;
			counterInventory--;
		}

		//cancel if inventory and backpack are full
		if(inventoryFill >= playerInventory.size() 
				&& packFill >= packSize) return;
		//for each slot, attempt to insert
		//5
		while(packSize -1 >= 0) {
			packSize--;
			packItem = contents.insertItem(packSize, packItem, false);
		}
		//2 fit, 3 remaining
		item.setCount(packItem.getCount());
		
		if (item.getCount() > 0) {
			ItemStack drop = item.copy();
		}

		System.out.println("does inventory contain item?: " + playerInventory.contains(item));
		//e.getEntityPlayer().inventory.clear(); //clear the inventory like a jerk

        //check if backpack is full
        //stop if full
        //check if the item is in the inventory of the backpack
        //get its stack size

        //loop until the item stack is empty or bag is full
        //put the rest in the main inventory if bag is full

    }
	
}
