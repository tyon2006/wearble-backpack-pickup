package com.tyon2006.pickup.events;

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

public class eventHandler {
	
	@SubscribeEvent
	@SideOnly(Side.CLIENT)
    public void pickupItem(EntityItemPickupEvent e) {
		
		//check if the player is wearing a backpack and cancel if not
		IBackpack backpack = BackpackHelper.getBackpack(e.getEntityPlayer());
		if (backpack == null) return;
		
        //get player's inventory
		EntityPlayer player = e.getEntityPlayer();
		NonNullList<ItemStack> playerInventory = e.getEntityPlayer().inventory.mainInventory;
		
		ItemStack itemstack = backpack.getStack();
		
        //get the item that triggered the event
		ItemStack item = e.getItem().getItem();
		System.out.println("player picked up item: " + item.getCount() + " " + item.getDisplayName());		

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
		
		//

		//for each slot, attempt to insert
		while(packSize -1 > 0) {
			packSize--;
			item = contents.insertItem(packSize, item, false);
		}

		System.out.println("does inventory contain item?: " + playerInventory.contains(item));
;

		//e.getEntityPlayer().inventory.clear(); //clear the inventory like a jerk

        //check if backpack is full
        //stop if full
        //check if the item is in the inventory of the backpack
        //get its stack size

        //loop until the item stack is empty or bag is full
        //put the rest in the main inventory if bag is full

    }
	
}
