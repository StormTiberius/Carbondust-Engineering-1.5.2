/**
 *
 * @author StormTiberius
 */

package cde;

import cde.compat.CompatTickHandler;
import cde.compat.PlayerEventManager;
import cde.compat.WorldEventManager;
import cde.core.Version;
import com.eloraam.redpower.core.Config;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.Mod.ServerStarting;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;
import ic2.api.Ic2Recipes;
import java.io.File;
import java.util.ArrayList;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.oredict.OreDictionary;
import railcraft.common.api.core.items.ItemRegistry;

@Mod(modid="CDE|Compat", name="Compat", version=Version.VERSION, dependencies = "required-after:Forge@[6.6.2.534,);required-after:CDE|Core;required-after:RedPowerCore")
@NetworkMod(clientSideRequired=true, serverSideRequired=true)
public class CompatCore
{
    private static int oreId,storageId,ironNuggetId,ioexpanderId,backPlaneId;
    private static Configuration cfg;
    private static boolean tungsten,storageBlock,nuggetSmelting,canvas,ioexpander,timergui,backPlane;
    
    @PreInit
    public void preInit(FMLPreInitializationEvent event) 
    {
        cfg = new Configuration(new File(event.getModConfigurationDirectory(), "cde/compat.cfg"));
        cfg.load();
        tungsten = cfg.get(Configuration.CATEGORY_GENERAL, "tungsten", false, "IC2 macerator tungsten recipe").getBoolean(false);
        storageBlock = cfg.get(Configuration.CATEGORY_GENERAL, "recipe", true, "IC2 compressor ingot recipes").getBoolean(false);
        nuggetSmelting = cfg.get(Configuration.CATEGORY_GENERAL, "nugget", true, "RP2 iron nugget smelting to railcraft steel nugget").getBoolean(false);
        canvas = cfg.get(Configuration.CATEGORY_GENERAL, "canvasbag", true, "Prevents canvas bag use in multiplayer, still works in singleplayer").getBoolean(false);
        ioexpander = cfg.get(Configuration.CATEGORY_GENERAL, "ioexpander", true, "Allows you to crouch and use screwdriver to set redbus ID").getBoolean(false);
        timergui = cfg.get(Configuration.CATEGORY_GENERAL, "timergui", true, "Prevents invoking of timergui while holding block/item").getBoolean(false);
        backPlane = cfg.get(Configuration.CATEGORY_GENERAL, "backplane", true, "Prevents certain RP2 blocks dropping twice").getBoolean(false);
        cfg.save();
    }

    @Init
    public void init(FMLInitializationEvent event) 
    {

    }

    @PostInit
    public void postInit(FMLPostInitializationEvent event) 
    {   
        storageId = Config.getInt("blocks.world.storage.id");
        
        OreDictionary.registerOre("blockCopper", new ItemStack(storageId, 1, 5));
        OreDictionary.registerOre("blockTin", new ItemStack(storageId, 1, 4));
        OreDictionary.registerOre("blockSilver", new ItemStack(storageId, 1, 3));
        
        if(backPlane)
        {
            ioexpanderId = Config.getInt("blocks.control.peripheralFlat.id");
            backPlaneId = Config.getInt("blocks.control.backplane.id");
            ItemStack[] isa = {new ItemStack(ioexpanderId, 1, 0), new ItemStack(backPlaneId, 1, 0), new ItemStack(backPlaneId, 1, 1)};
            MinecraftForge.EVENT_BUS.register(new WorldEventManager(isa));
            TickRegistry.registerTickHandler(new CompatTickHandler(), Side.SERVER);
        }
        
        if(canvas || ioexpander || timergui)
        {
            MinecraftForge.EVENT_BUS.register(new PlayerEventManager(canvas, ioexpander, timergui));
        }
        
        if(nuggetSmelting && Loader.isModLoaded("Railcraft"))
        {   
            ironNuggetId = Config.getInt("items.base.nuggets.id");
            ironNuggetId += 256;
            FurnaceRecipes.smelting().addSmelting(ironNuggetId, 0, new ItemStack(ItemRegistry.getItem("nugget.steel", 1).itemID, 1, 1), 0.1F);
        }
        
        if(Loader.isModLoaded("IC2"))
        {
            if(storageBlock)
            {
                ArrayList<ItemStack> copperIngots = OreDictionary.getOres("ingotCopper");
                for(ItemStack is : copperIngots)
                {
                    Ic2Recipes.addCompressorRecipe(new ItemStack(is.itemID, 9, is.getItemDamage()), new ItemStack(storageId, 1, 5));
                }

                ArrayList<ItemStack> tinIngots = OreDictionary.getOres("ingotTin");
                for(ItemStack is : tinIngots)
                {
                    Ic2Recipes.addCompressorRecipe(new ItemStack(is.itemID, 9, is.getItemDamage()), new ItemStack(storageId, 1, 4));
                }

                ArrayList<ItemStack> silverIngots = OreDictionary.getOres("ingotSilver");
                for(ItemStack is : silverIngots)
                {
                    Ic2Recipes.addCompressorRecipe(new ItemStack(is.itemID, 9, is.getItemDamage()), new ItemStack(storageId, 1, 3));
                }
            }
            
            if(tungsten)
            {
                oreId = Config.getInt("blocks.world.ores.id");

                Ic2Recipes.addMaceratorRecipe(new ItemStack(oreId, 1, 6), new ItemStack(Item.diamond.itemID, 2, 0));
            }
        }
    }

    @ServerStarting
    public void serverStarting(FMLServerStartingEvent event)
    {
        
    }
}
