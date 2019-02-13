/**
 *
 * @author StormTiberius
 */

package cde;

import cde.api.Blocks;
import cde.api.Materials;
import cde.core.CommonProxy;
import cde.core.CreativeTabCDE;
import cde.core.Defaults;
import cde.core.EventManager;
import cde.core.FuelManager;
import cde.core.Namings;
import cde.core.RecipeManager;
import cde.core.Version;
import cde.core.block.BlockOre;
import cde.core.block.BlockStorage;
import cde.core.item.ItemOre;
import cde.core.item.ItemMaterial;
import cde.core.item.ItemStorage;
import cde.core.network.PacketHandler;
import cde.core.sound.SoundTickHandler;
import cde.core.speaker.SpeakerModule;
import cde.core.worldgen.WorldGenModule;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.Mod.ServerStarting;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;
import forestry.api.core.ItemInterface;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.block.Block;
import static net.minecraft.block.Block.soundMetalFootstep;
import static net.minecraft.block.Block.soundStoneFootstep;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.src.ModLoader;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.oredict.OreDictionary;

@Mod(modid="CDE|Core", name="Core", version=Version.VERSION, dependencies = "required-after:Forge@[6.6.2.534,);required-after:IC2;required-after:BuildCraft|Core;required-after:Forestry;required-after:Railcraft;required-after:AppliedEnergistics")
@NetworkMod(channels = { "CDE" }, packetHandler = PacketHandler.class, clientSideRequired=true, serverSideRequired=true)
public class CDECore
{
    @SidedProxy(clientSide = "cde.core.ClientProxy", serverSide = "cde.core.CommonProxy")
    
    public static CommonProxy proxy;
    public static final int ID_SHIFT = -256;
    public static final String CDE_BLOCKS = "/cde/blocks.png";
    public static final String CDE_ITEMS = "/cde/items.png";
    public static final CreativeTabs TAB_CDE = new CreativeTabCDE("carbondustengineering");
    public static Block oreBlock,storageBlock;
    public static Item materialsItem;
    private static Configuration cfg;
    private static int networkUpdateRate,oreBlockId,storageBlockId,materialsItemId;
    private static boolean sounds,altRainSounds,altExplosionSounds;
    public static int apatiteId,apatiteMeta;
    
    @PreInit
    public void preInit(FMLPreInitializationEvent event) 
    {
        cfg = new Configuration(new File(event.getModConfigurationDirectory(), "cde/core.cfg"));
        cfg.load();
        
        networkUpdateRate = cfg.get(Configuration.CATEGORY_GENERAL, "networkupdaterate", 40, "Network Update Rate").getInt();
        sounds = cfg.get(Configuration.CATEGORY_GENERAL, "sounds", true, "Enable/Disable CDE Sounds").getBoolean(false);
        altRainSounds = cfg.get(Configuration.CATEGORY_GENERAL, "rain", true, "MC 1.9 Rain Sounds").getBoolean(false);
        altExplosionSounds = cfg.get(Configuration.CATEGORY_GENERAL, "explosion", true, "Alternate Explosion Sounds").getBoolean(false);
        
        oreBlockId = cfg.get(Configuration.CATEGORY_BLOCK, "oreblockid", Defaults.BLOCK_ORE_ID).getInt();
        storageBlockId = cfg.get(Configuration.CATEGORY_BLOCK, "storageblockid", Defaults.BLOCK_STORAGE_ID).getInt();
        
        materialsItemId = cfg.get(Configuration.CATEGORY_ITEM, "materialsitemid", Defaults.ITEM_MATERIALS_ID).getInt() + ID_SHIFT;
        
        apatiteId = materialsItemId;
        apatiteMeta = 51;
        
        cfg.save();
        
        initItems();
        initBlocks();
        
        if(playSounds())
        {
            proxy.setupSound();
            TickRegistry.registerTickHandler(new SoundTickHandler(), Side.CLIENT);
        }
        
        SpeakerModule.preInit(event);
        WorldGenModule.preInit(event);
    }
    
    @Init
    public void init(FMLInitializationEvent event) 
    {   
        proxy.preloadTextures();
        
        SpeakerModule.init(event);
        WorldGenModule.init(event);
        
        GameRegistry.registerFuelHandler(new FuelManager());
        
        MinecraftForge.EVENT_BUS.register(new EventManager());
    }

    @PostInit
    public void postInit(FMLPostInitializationEvent event) 
    {
        if(ModLoader.isModLoaded("Forestry"))
        {
            ItemStack is = ItemInterface.getItem("apatite");
            
            if(is != null)
            {
                apatiteId = is.itemID;
                apatiteMeta = is.getItemDamage();
            }
        }
        
        RecipeManager.addRecipes();
    }

    @ServerStarting
    public void serverStarting(FMLServerStartingEvent event)
    {
        
    }
        
    private static void initItems()
    {
        if(materialsItemId > 0)
        {
            materialsItem = (new ItemMaterial(materialsItemId)).setItemName("materials").setCreativeTab(TAB_CDE);
            
            GameRegistry.registerItem(materialsItem, "materials");
            
            for(int i = 0; i < Namings.EXTERNAL_PART_ITEM_NAMES.length; i++)
            {              
                ItemStack is = new ItemStack(materialsItem.itemID, 1, i);
                
                switch(i)
                {
                    case 0: Materials.ingotCopper = is; break;
                    case 1: Materials.ingotTin = is; break;
                    case 2: Materials.ingotSilver = is; break;
                    case 3: Materials.ingotLead = is; break;
                    case 4: Materials.ingotUranium = is; break;
                    case 5: Materials.ingotZinc = is; break;
                    case 6: Materials.ingotBronze = is; break;
                    case 7: Materials.ingotBrass = is; break;
                    case 8: Materials.ingotSteel = is; break;
                    case 9: Materials.fuelPeat = is; break;
                    case 10: Materials.fuelBituminousPeat = is; break;
                    
                    case 11: Materials.dustCoal = is; break;
                    case 12: Materials.dustCharcoal = is; break;
                    case 13: Materials.dustIron = is; break;
                    case 14: Materials.dustGold = is; break;
                    case 15: Materials.dustCopper = is; break;
                    case 16: Materials.dustTin = is; break;
                    case 17: Materials.dustSilver = is; break;
                    case 18: Materials.dustLead = is; break;
                    case 19: Materials.dustZinc = is; break;
                    case 20: Materials.dustBronze = is; break;
                    case 21: Materials.dustBrass = is; break;
                    case 22: Materials.dustSulfur = is; break;
                    case 23: Materials.dustSaltpeter = is; break;
                    case 24: Materials.dustQuartz = is; break;
                    case 25: Materials.dustApatite = is; break;
                    case 26: Materials.dustYellowcake = is; break;
                    
                    case 27: Materials.nuggetIron = is; break;
                    case 28: Materials.nuggetCopper = is; break;
                    case 29: Materials.nuggetTin = is; break;
                    case 30: Materials.nuggetSilver = is; break;
                    case 31: Materials.nuggetLead = is; break;
                    case 32: Materials.nuggetZinc = is; break;
                    case 33: Materials.nuggetBronze = is; break;
                    case 34: Materials.nuggetBrass = is; break;
                    case 35: Materials.nuggetSteel = is; break;
                    
                    case 36: Materials.washedIron = is; break;
                    case 37: Materials.washedGold = is; break;
                    case 38: Materials.washedCopper = is; break;
                    case 39: Materials.washedTin = is; break;
                    case 40: Materials.washedSilver = is; break;
                    case 41: Materials.washedLead = is; break;
                    case 42: Materials.washedUranium = is; break;
                    
                    case 43: Materials.crushedIron = is; break;
                    case 44: Materials.crushedGold = is; break;
                    case 45: Materials.crushedCopper = is; break;
                    case 46: Materials.crushedTin = is; break;
                    case 47: Materials.crushedSilver = is; break;
                    case 48: Materials.crushedLead = is; break;
                    case 49: Materials.crushedUranium = is; break;
                    
                    case 50: Materials.gemQuartz = is; break;
                    case 51: Materials.gemApatite = is; break;
                    case 52: Materials.gemRuby = is; break;
                    case 53: Materials.gemJade = is; break;
                    case 54: Materials.gemSapphire = is; break;
                    
                    case 55: Materials.fuelCoke = is; break;
                }
                
                LanguageRegistry.addName(is, Namings.EXTERNAL_PART_ITEM_NAMES[i]);
                OreDictionary.registerOre(is.getItemName(), is);
            }
            
            OreDictionary.registerOre("ingotQuartz", Materials.gemQuartz);
            OreDictionary.registerOre("dropUranium", Materials.washedUranium);
            OreDictionary.registerOre("brickPeat", Materials.fuelPeat);
        }
    }
    
    private static void initBlocks()
    {
        if(oreBlockId > 0)
        {
            oreBlock = (new BlockOre(oreBlockId)).setHardness(3.0F).setResistance(5.0F).setStepSound(soundStoneFootstep).setBlockName("cdeOreBlock").setCreativeTab(TAB_CDE);
            
            GameRegistry.registerBlock(oreBlock, ItemOre.class, "cdeOreBlock");
            
            for(int i = 0; i < Namings.EXTERNAL_ORE_BLOCK_NAMES.length; i++)
            {              
                ItemStack is = new ItemStack(oreBlock.blockID, 1, i);
                
                switch(i)
                {
                    case 0: Blocks.oreCopper = is; break;
                    case 1: Blocks.oreTin = is; break;
                    case 2: Blocks.oreSilver = is; break;
                    case 3: Blocks.oreLead = is; break;
                    case 4: Blocks.oreUranium = is; break;
                    case 5: Blocks.oreSulfur = is; break;
                    case 6: Blocks.oreSaltpeter = is; break;
                    case 7: Blocks.oreQuartz = is; break;
                    case 8: Blocks.oreApatite = is; break;
                    case 9: Blocks.oreRuby = is; break;
                    case 10: Blocks.oreJade = is; break;
                    case 11: Blocks.oreSapphire = is; break;
                }
                
                LanguageRegistry.addName(is, Namings.EXTERNAL_ORE_BLOCK_NAMES[i]);
                OreDictionary.registerOre(is.getItemName(), is);
            }
            
            MinecraftForge.setBlockHarvestLevel(oreBlock, 0, "pickaxe", 1); // Copper
            MinecraftForge.setBlockHarvestLevel(oreBlock, 1, "pickaxe", 1); // Tin
            MinecraftForge.setBlockHarvestLevel(oreBlock, 2, "pickaxe", 1); // Silver
            MinecraftForge.setBlockHarvestLevel(oreBlock, 3, "pickaxe", 2); // Lead
            MinecraftForge.setBlockHarvestLevel(oreBlock, 4, "pickaxe", 2); // Uranium
            MinecraftForge.setBlockHarvestLevel(oreBlock, 5, "pickaxe", 2); // Sulfur
            MinecraftForge.setBlockHarvestLevel(oreBlock, 6, "pickaxe", 2); // Saltpeter
            MinecraftForge.setBlockHarvestLevel(oreBlock, 7, "pickaxe", 0); // Quartz
            MinecraftForge.setBlockHarvestLevel(oreBlock, 8, "pickaxe", 1); // Apatite
            MinecraftForge.setBlockHarvestLevel(oreBlock, 9, "pickaxe", 2); // Ruby
            MinecraftForge.setBlockHarvestLevel(oreBlock, 10, "pickaxe", 2); // Jade
            MinecraftForge.setBlockHarvestLevel(oreBlock, 11, "pickaxe", 2); // Sapphire
        }
        
        if(storageBlockId > 0)
        {
            storageBlock = (new BlockStorage(storageBlockId)).setHardness(3.0F).setResistance(10.0F).setStepSound(soundMetalFootstep).setBlockName("cdeStorageBlock").setCreativeTab(TAB_CDE);
            
            GameRegistry.registerBlock(storageBlock, ItemStorage.class, "cdeStorageBlock");
            
            MinecraftForge.setBlockHarvestLevel(storageBlock, "pickaxe", 1);
            
            for(int i = 0; i < Namings.EXTERNAL_STORAGE_BLOCK_NAMES.length; i++)
            {                
                ItemStack is = new ItemStack(storageBlock.blockID, 1, i);
                
                switch(i)
                {
                    case 0: Blocks.blockCopper = is; break;
                    case 1: Blocks.blockTin = is; break;
                    case 2: Blocks.blockSilver = is; break;
                    case 3: Blocks.blockLead = is; break;
                    case 4: Blocks.blockUranium = is; break;
                    case 5: Blocks.blockZinc = is; break;
                    case 6: Blocks.blockBronze = is; break;
                    case 7: Blocks.blockBrass = is; break;
                    case 8: Blocks.blockSteel = is; break;
                    case 9: Blocks.blockRuby = is; break;
                    case 10: Blocks.blockJade = is; break;
                    case 11: Blocks.blockSapphire = is; break;
                }
                
                LanguageRegistry.addName(is, Namings.EXTERNAL_STORAGE_BLOCK_NAMES[i]);
                OreDictionary.registerOre(is.getItemName(), is);
            }
        }
    }
    
    public static int getNetworkUpdateRate()
    {
        return networkUpdateRate;
    }
    
    public static boolean playSounds()
    {
        return sounds;
    }
    
    public static boolean altRainSounds()
    {
        return altRainSounds;
    }
    
    public static boolean altExplosionSounds()
    {
        return altExplosionSounds;
    }
    
    public static List<Item> getItemsByClass(String n)
    {
        List<Item> m = new ArrayList();
        for(Item i : Item.itemsList)
        {
            if(i != null && i.getClass().getName().equals(n))
            {                    
                m.add(i);
            }
        }
        
        return m;
    }

    public static List<Block> getBlocksByClass(String n)
    {
        List<Block> m = new ArrayList();
        
        for(Block i : Block.blocksList)
        {
            if(i != null && i.getClass().getName().equals(n))
            {
                m.add(i);
            }
        }
        
        return m;
    }

    public static Item getItemByClass(String n)
    {
        List<Item> m = getItemsByClass(n);
        
        if(m.size() >= 1)
        {
            return m.get(0);
        }
        
        return null;
    }

    public static Block getBlockByClass(String n)
    {
        for(Block b : Block.blocksList)
        {
            if(b != null && b.getClass().getName().equals(n))
            {
                return b;
            }
        }
        
        return null;
    }
}
