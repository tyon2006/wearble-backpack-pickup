package com.tyon2006.pickup;

import net.minecraft.init.Blocks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

import com.tyon2006.pickup.events.eventHandler;
import com.tyon2006.pickup.proxy.ClientProxy;
import com.tyon2006.pickup.proxy.CommonProxy;

@Mod(modid = wearableBackpacksPickup.MODID, name = wearableBackpacksPickup.NAME, version = wearableBackpacksPickup.VERSION)
public class wearableBackpacksPickup
{
    public static final String MODID = "wbpickup";
    public static final String NAME = "Wearable Backpacks Pickup";
    public static final String VERSION = "1.0";

    private static Logger logger;
    
    //@SidedProxy(clientSide = "com.tyon2006.pickup.proxy.ClientProxy", serverSide = "com.tyon2006.whereAmIGoing.proxy.CommonProxy")
    //public static ClientProxy proxy;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        logger = event.getModLog();
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
    	MinecraftForge.EVENT_BUS.register(new eventHandler());
    	// some example code
    	//proxy.init(event);
        //logger.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());
    }
}
