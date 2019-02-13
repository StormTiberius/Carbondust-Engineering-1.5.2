/**
 *
 * @author StormTiberius
 */

package cde;

import cde.core.Version;
import cde.tweak.EntityEventManager;
import cde.tweak.PlantSeedGrass;
import cde.tweak.RecipeManager;
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
import java.io.File;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.liquids.LiquidContainerRegistry;
import net.minecraftforge.liquids.LiquidDictionary;
import railcraft.common.api.fuel.FuelManager;

@Mod(modid="CDE|Tweak", name="Tweak", version=Version.VERSION, dependencies = "required-after:Forge@[6.6.2.534,);required-after:CDE|Core;after:CDE|Compat")
@NetworkMod(clientSideRequired=true, serverSideRequired=true)
public class TweakCore
{
    private static Configuration cfg;
    private static final boolean[] FLAGS = new boolean[40];
    private static int lavaHeatValue;
    
    @PreInit
    public void preInit(FMLPreInitializationEvent event) 
    {
        cfg = new Configuration(new File(event.getModConfigurationDirectory(), "cde/tweak.cfg"));
        cfg.load();
        
        FLAGS[0] = cfg.get(Configuration.CATEGORY_GENERAL, "stonebrick", true, "Stonebrick smelting recipe").getBoolean(false);
        FLAGS[1] = cfg.get(Configuration.CATEGORY_GENERAL, "glowstone", true, "Glowstone dust smelting recipe").getBoolean(false);
        FLAGS[2] = cfg.get(Configuration.CATEGORY_GENERAL, "emerald", true, "Emerald smelting recipe").getBoolean(false);
        FLAGS[3] = cfg.get(Configuration.CATEGORY_GENERAL, "feather", true, "Feather recipe").getBoolean(false);
        FLAGS[4] = cfg.get(Configuration.CATEGORY_GENERAL, "stoneslab", true, "Double stoneslab recipe").getBoolean(false);
        FLAGS[5] = cfg.get(Configuration.CATEGORY_GENERAL, "ironnugget", true, "Iron nugget smelting recipe").getBoolean(false);
        FLAGS[6] = cfg.get(Configuration.CATEGORY_GENERAL, "reactorvent", false, "Reactor vent steel recipe").getBoolean(false);
        FLAGS[7] = cfg.get(Configuration.CATEGORY_GENERAL, "slimeball", true, "Slimeball recipe").getBoolean(false);
        FLAGS[8] = cfg.get(Configuration.CATEGORY_GENERAL, "leather", true, "Leather recipe").getBoolean(false);
        FLAGS[9] = cfg.get(Configuration.CATEGORY_GENERAL, "grinpowder", true, "Grinpowder recipe").getBoolean(false);
        FLAGS[10] = cfg.get(Configuration.CATEGORY_GENERAL, "scrapbox", true, "Scrapbox smelting recipe").getBoolean(false);
        FLAGS[11] = cfg.get(Configuration.CATEGORY_GENERAL, "uurecipes", false, "Remove UU recipes").getBoolean(false);
        FLAGS[12] = cfg.get(Configuration.CATEGORY_GENERAL, "ic2steel", false, "IC2 use steel recipe fix").getBoolean(false);
        FLAGS[13] = cfg.get(Configuration.CATEGORY_GENERAL, "rc2steel", false, "RC2 use steel recipe fix").getBoolean(false);
        FLAGS[14] = cfg.get(Configuration.CATEGORY_GENERAL, "fall", false, "Fall distance tweak").getBoolean(false);
        FLAGS[15] = cfg.get(Configuration.CATEGORY_GENERAL, "noxporbs", false, "Disables xp orb drops").getBoolean(false);
        FLAGS[16] = cfg.get(Configuration.CATEGORY_GENERAL, "fertilizer", true, "Fertilizer recipe").getBoolean(false);
        
        lavaHeatValue = cfg.get(Configuration.CATEGORY_GENERAL, "lavaheatvalue", 20000, "20000 for 50EU/t, 8000 for 20EU/t, 0 disable").getInt();
        
        FLAGS[17] = cfg.get(Configuration.CATEGORY_GENERAL, "brown", true, "Brown mushroom drop").getBoolean(false);
        FLAGS[18] = cfg.get(Configuration.CATEGORY_GENERAL, "red", true, "Red mushroom drop").getBoolean(false);
        FLAGS[19] = cfg.get(Configuration.CATEGORY_GENERAL, "stalk", true, "Netherwart drop").getBoolean(false);
        FLAGS[20] = cfg.get(Configuration.CATEGORY_GENERAL, "pumpkin", true, "Pumpkin seed drop").getBoolean(false);
        FLAGS[21] = cfg.get(Configuration.CATEGORY_GENERAL, "lily", true, "Waterlily drop").getBoolean(false);
        FLAGS[22] = cfg.get(Configuration.CATEGORY_GENERAL, "melon", true, "Melon seed drop").getBoolean(false);
        FLAGS[23] = cfg.get(Configuration.CATEGORY_GENERAL, "bush", true, "Dead bush drop").getBoolean(false);
        FLAGS[24] = cfg.get(Configuration.CATEGORY_GENERAL, "cactus", true, "Cactus drop").getBoolean(false);
        FLAGS[25] = cfg.get(Configuration.CATEGORY_GENERAL, "vine", true, "Vine drop").getBoolean(false);
        FLAGS[26] = cfg.get(Configuration.CATEGORY_GENERAL, "reed", true, "Reed drop").getBoolean(false);
        FLAGS[27] = cfg.get(Configuration.CATEGORY_GENERAL, "carrot", true, "Carrot grow").getBoolean(false);
        FLAGS[28] = cfg.get(Configuration.CATEGORY_GENERAL, "potato", true, "Potato grow").getBoolean(false);
        FLAGS[29] = cfg.get(Configuration.CATEGORY_GENERAL, "oak", true, "Oak sapling grow").getBoolean(false);
        FLAGS[30] = cfg.get(Configuration.CATEGORY_GENERAL, "spruce", true, "Spruce sapling grow").getBoolean(false);
        FLAGS[31] = cfg.get(Configuration.CATEGORY_GENERAL, "birch", true, "Birch sapling grow").getBoolean(false);
        FLAGS[32] = cfg.get(Configuration.CATEGORY_GENERAL, "jungle", true, "Jungle sapling grow").getBoolean(false);
        FLAGS[33] = cfg.get(Configuration.CATEGORY_GENERAL, "rubber", true, "Rubber sapling grow").getBoolean(false);
        
        FLAGS[34] = cfg.get(Configuration.CATEGORY_GENERAL, "nightvision", true, "Disable nightvision fading").getBoolean(false);
        
        FLAGS[35] = cfg.get(Configuration.CATEGORY_GENERAL, "dcpfix", true, "Alternate recipe for Dense Copper Plate").getBoolean(false);
        
        FLAGS[36] = cfg.get(Configuration.CATEGORY_GENERAL, "netherrack", true, "Netherrack Recipe").getBoolean(false);
        FLAGS[37] = cfg.get(Configuration.CATEGORY_GENERAL, "soulsand", true, "Soulsand Recipe").getBoolean(false);
        
        FLAGS[38] = cfg.get(Configuration.CATEGORY_GENERAL, "enderman", true, "Enderman Carriable Blocks").getBoolean(false);
        
        FLAGS[39] = cfg.get(Configuration.CATEGORY_GENERAL, "gtrecipe", true, "Ghast Tear Recipe").getBoolean(false);
        
        cfg.save();
      
    }

    @Init
    public void init(FMLInitializationEvent event) 
    {   
        if(FLAGS[14] || FLAGS[15] || FLAGS[34])
        {
            MinecraftForge.EVENT_BUS.register(new EntityEventManager(FLAGS));
        }
        
        if(lavaHeatValue > 0 && Loader.isModLoaded("Railcraft"))
        {
            FuelManager.addBoilerFuel(LiquidDictionary.getLiquid("Lava", LiquidContainerRegistry.BUCKET_VOLUME), lavaHeatValue);
        }
        
        PlantSeedGrass.init(FLAGS);
    }

    @PostInit
    public void postInit(FMLPostInitializationEvent event) 
    {
        RecipeManager.delRecipes(FLAGS);
        RecipeManager.addRecipes(FLAGS);
        
        if(FLAGS[38])
        {
            for(int i = 0; i < EntityEnderman.carriableBlocks.length; i++)
            {
                EntityEnderman.carriableBlocks[i] = false;
            }
        }
    }

    @ServerStarting
    public void serverStarting(FMLServerStartingEvent event)
    {
        
    }  
}
