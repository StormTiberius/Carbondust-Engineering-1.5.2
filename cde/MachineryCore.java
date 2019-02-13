/**
 *
 * @author StormTiberius
 */

package cde;

import cde.core.Defaults;
import cde.core.Namings;
import cde.core.Version;
import cde.machinery.BlockMachineAlpha;
import cde.machinery.BlockGrate;
import cde.machinery.ItemGoggles;
import cde.machinery.ItemMachineAlpha;
import cde.machinery.TileEntityGenerator;
import cde.machinery.TileEntityHeater;
import cde.machinery.TileEntityMixer;
import cde.machinery.TileEntityPump;
import cde.machinery.TileEntitySolarPanel;
import cde.machinery.TileEntityTransformer;
import cde.machinery.TileEntityTurbine;
import cpw.mods.fml.common.FMLCommonHandler;
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
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import ic2.api.Ic2Recipes;
import ic2.api.Items;
import java.io.File;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.src.ModLoader;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.MinecraftForge;
import railcraft.common.api.core.items.ItemRegistry;

@Mod(modid="CDE|Machinery", name="Machinery", version=Version.VERSION, dependencies = "required-after:Forge@[6.6.2.534,);required-after:CDE|Core")
@NetworkMod(clientSideRequired=true, serverSideRequired=true)
public class MachineryCore
{
    private static Configuration cfg;
    private static int machineAlphaId,grateId,gogglesId;
    public static int generatorVolume,heaterVolume,mixerVolume,pumpVolume,solarVolume,transformerVolume,turbineVolume;
    public static int generatorPitch,heaterPitch,mixerPitch,pumpPitch,solarPitch,transformerPitch,turbinePitch;
    public static Item goggles;
    public static Block machineAlpha,grate;
    private static int ironGearId,tankBlockId,batteryEmptyId,batteryFullId,indigoDyeId;
    private final boolean[] CRAFTABLE = new boolean[9];
    
    @PreInit
    public void preInit(FMLPreInitializationEvent event) 
    {
        cfg = new Configuration(new File(event.getModConfigurationDirectory(), "cde/machinery.cfg"));
        cfg.load();
        
        machineAlphaId = cfg.get(Configuration.CATEGORY_BLOCK, "machinealphaid", Defaults.BLOCK_MACHINERY_ALPHA_ID).getInt();
        
        grateId = cfg.get(Configuration.CATEGORY_BLOCK, "grateid", Defaults.BLOCK_GRATE_ID).getInt();
        
        gogglesId = cfg.get(Configuration.CATEGORY_ITEM, "gogglesid", Defaults.ITEM_GOGGLES_ID).getInt() + CDECore.ID_SHIFT;
        
        // Individual Machine Sound Settings.
        
        int defaultVolume = 20;
        int defaultPitch = 100;
        
        // Volume
        generatorVolume = cfg.get(Configuration.CATEGORY_GENERAL, "generatorvolume", defaultVolume, "Generator Volume").getInt();
        heaterVolume = cfg.get(Configuration.CATEGORY_GENERAL, "heatervolume", defaultVolume, "Heater Volume").getInt();
        mixerVolume = cfg.get(Configuration.CATEGORY_GENERAL, "mixervolume", defaultVolume, "Mixer Volume").getInt();
        pumpVolume = cfg.get(Configuration.CATEGORY_GENERAL, "pumpvolume", defaultVolume, "Pump Volume").getInt();
        solarVolume = cfg.get(Configuration.CATEGORY_GENERAL, "solarvolume", 0, "Solar Volume").getInt();
        transformerVolume = cfg.get(Configuration.CATEGORY_GENERAL, "transformervolume", defaultVolume, "Transformer Volume").getInt();
        turbineVolume = cfg.get(Configuration.CATEGORY_GENERAL, "turbinevolume", defaultVolume, "Turbine Volume").getInt();
        
        // Pitch
        generatorPitch = cfg.get(Configuration.CATEGORY_GENERAL, "generatorpitch", defaultPitch, "Generator Pitch").getInt();
        heaterPitch = cfg.get(Configuration.CATEGORY_GENERAL, "heaterpitch", defaultPitch, "Heater Pitch").getInt();
        mixerPitch = cfg.get(Configuration.CATEGORY_GENERAL, "mixerpitch", defaultPitch, "Mixer Pitch").getInt();
        pumpPitch = cfg.get(Configuration.CATEGORY_GENERAL, "pumppitch", defaultPitch, "Pump Pitch").getInt();
        solarPitch = cfg.get(Configuration.CATEGORY_GENERAL, "solarpitch", defaultPitch, "Solar Pitch").getInt();
        transformerPitch = cfg.get(Configuration.CATEGORY_GENERAL, "transformerpitch", defaultPitch, "Transformer Pitch").getInt();
        turbinePitch = cfg.get(Configuration.CATEGORY_GENERAL, "turbinepitch", defaultPitch, "Turbine Pitch").getInt();
        
        CRAFTABLE[0] = cfg.get(Configuration.CATEGORY_GENERAL, "recipegenerator", true, "Enable/Disable Crafting recipe for Generator").getBoolean(true);
        CRAFTABLE[1] = cfg.get(Configuration.CATEGORY_GENERAL, "recipeturbine", true, "Enable/Disable Crafting recipe for Turbine").getBoolean(true);
        CRAFTABLE[2] = cfg.get(Configuration.CATEGORY_GENERAL, "recipeheater", true, "Enable/Disable Crafting recipe for Heater").getBoolean(true);
        CRAFTABLE[3] = cfg.get(Configuration.CATEGORY_GENERAL, "recipepump", true, "Enable/Disable Crafting recipe for Pump").getBoolean(true);
        CRAFTABLE[4] = cfg.get(Configuration.CATEGORY_GENERAL, "recipemixer", true, "Enable/Disable Crafting recipe for Mixer").getBoolean(true);
        CRAFTABLE[5] = cfg.get(Configuration.CATEGORY_GENERAL, "recipesolarpanel", true, "Enable/Disable Crafting recipe for Solar Panel").getBoolean(true);
        CRAFTABLE[6] = cfg.get(Configuration.CATEGORY_GENERAL, "recipetransformer", true, "Enable/Disable Crafting recipe for Transformer").getBoolean(true);
        
        CRAFTABLE[7] = cfg.get(Configuration.CATEGORY_GENERAL, "recipegrate", true, "Enable/Disable Crafting recipe for Grate").getBoolean(true);
        CRAFTABLE[8] = cfg.get(Configuration.CATEGORY_GENERAL, "recipegoggles", true, "Enable/Disable Crafting recipe for Nightvision Goggles").getBoolean(true);
        
        cfg.save();
    }

    @Init
    public void init(FMLInitializationEvent event) 
    {   
        setupIds();
        
        ItemStack is;
        
        if(gogglesId > 0)
        {
            String textureFile = "";
            int spriteIndex = 0;
            int renderId = 0;
            
            if(ModLoader.isModLoaded("IC2") && FMLCommonHandler.instance().getSide().isClient())
            {
                ItemStack nvg = Items.getItem("nightvisionGoggles");
                
                if(nvg != null)
                {
                    spriteIndex = nvg.getIconIndex();
                    
                    Item item = nvg.getItem();

                    if(item instanceof ItemArmor)
                    {
                        textureFile = item.getTextureFile();
                        renderId = ((ItemArmor)item).renderIndex;
                    }
                }
            }
            
            goggles = new ItemGoggles(gogglesId, spriteIndex, renderId, textureFile).setItemName("goggles").setCreativeTab(CDECore.TAB_CDE);
            GameRegistry.registerItem(goggles, Namings.INTERNAL_GOGGLES_NAME);
            LanguageRegistry.addName(goggles, Namings.EXTERNAL_GOGGLES_NAME);
            
            is = new ItemStack(goggles.itemID, 1, 0);
            
            cde.api.Items.goggles = is;
            
            if(CRAFTABLE[8])
            {
                Ic2Recipes.addCraftingRecipe(is,
                "xyx",
                "aza",
                "bcb",
                'x', new ItemStack(Items.getItem("reactorHeatSwitchDiamond").itemID, 1, 0),
                'y', new ItemStack(Items.getItem("reBattery").itemID, 1, 0),
                'z', new ItemStack(Items.getItem("reinforcedGlass").itemID, 1, 0),
                'a', new ItemStack(Items.getItem("luminator").itemID, 1, 0),
                'b', new ItemStack(Items.getItem("rubber").itemID, 1, 0),
                'c', new ItemStack(Items.getItem("advancedCircuit").itemID, 1, 0));
            }
        }
        
        if(machineAlphaId > 0)
        {
            machineAlpha = new BlockMachineAlpha(machineAlphaId).setBlockName("cdeMachineAlpha").setCreativeTab(CDECore.TAB_CDE).setHardness(3.5F);
            GameRegistry.registerBlock(machineAlpha, ItemMachineAlpha.class, "cdeMachineAlpha");

            MinecraftForge.setBlockHarvestLevel(machineAlpha, "pickaxe", 1);
            
            // Generator
            is = new ItemStack(machineAlpha.blockID, 1, 0);
            
            cde.api.Blocks.machineGenerator = is;
            
            LanguageRegistry.addName(is, Namings.EXTERNAL_MACHINE_ALPHA_BLOCK_NAMES[0]);
            GameRegistry.registerTileEntity(TileEntityGenerator.class, "cdeGeneratorTile");
            
            if(CRAFTABLE[0])
            {
                Ic2Recipes.addCraftingRecipe(is,
                "xxx",
                "byb",
                "aza",
                'x', new ItemStack(ironGearId, 1, 0),
                'y', new ItemStack(Items.getItem("machine").itemID, 1, 0),
                'z', new ItemStack(Items.getItem("electronicCircuit").itemID, 1, 0),
                'a', new ItemStack(Items.getItem("insulatedCopperCableItem").itemID, 1, 0),
                'b', new ItemStack(Items.getItem("reBattery").itemID, 1, 0));
            }
            // Turbine
            is = new ItemStack(machineAlpha.blockID, 1, 1);
            
            cde.api.Blocks.machineTurbine = is;
            
            LanguageRegistry.addName(is, Namings.EXTERNAL_MACHINE_ALPHA_BLOCK_NAMES[1]);
            GameRegistry.registerTileEntity(TileEntityTurbine.class, "cdeTurbineTile");
            
            if(CRAFTABLE[1])
            {
                Ic2Recipes.addCraftingRecipe(is,
                "xxx",
                "byb",
                "aza",
                'x', new ItemStack(ItemRegistry.getItem("part.turbine.disk", 1).itemID, 1, 0),
                'y', new ItemStack(Items.getItem("machine").itemID, 1, 0),
                'z', new ItemStack(Items.getItem("electronicCircuit").itemID, 1, 0),
                'a', new ItemStack(Items.getItem("insulatedCopperCableItem").itemID, 1, 0),
                'b', new ItemStack(Items.getItem("reBattery").itemID, 1, 0));
            }
        
            // Heater
            is = new ItemStack(machineAlpha.blockID, 1, 2);
            
            cde.api.Blocks.machineHeater = is;
            
            LanguageRegistry.addName(is, Namings.EXTERNAL_MACHINE_ALPHA_BLOCK_NAMES[2]);
            GameRegistry.registerTileEntity(TileEntityHeater.class, "cdeHeaterTile");
            
            if(CRAFTABLE[2])
            {
                Ic2Recipes.addCraftingRecipe(is,
                "xxx",
                "byb",
                "aza",
                'x', new ItemStack(Item.blazeRod.itemID, 1, 0),
                'y', new ItemStack(Items.getItem("machine").itemID, 1, 0),
                'z', new ItemStack(Items.getItem("electronicCircuit").itemID, 1, 0),
                'a', new ItemStack(Items.getItem("insulatedCopperCableItem").itemID, 1, 0),
                'b', new ItemStack(Items.getItem("reBattery").itemID, 1, 0));
            }
        
            // Pump
            is = new ItemStack(machineAlpha.blockID, 1, 3);
            
            cde.api.Blocks.machinePump = is;
            
            LanguageRegistry.addName(is, Namings.EXTERNAL_MACHINE_ALPHA_BLOCK_NAMES[3]);
            GameRegistry.registerTileEntity(TileEntityPump.class, "cdePumpTile");
            
            if(CRAFTABLE[3])
            {
                Ic2Recipes.addCraftingRecipe(is,
                "xxx",
                "byb",
                "aza",
                'x', new ItemStack(tankBlockId, 1, 0),
                'y', new ItemStack(Items.getItem("machine").itemID, 1, 0),
                'z', new ItemStack(Items.getItem("electronicCircuit").itemID, 1, 0),
                'a', new ItemStack(Items.getItem("insulatedCopperCableItem").itemID, 1, 0),
                'b', new ItemStack(Items.getItem("reBattery").itemID, 1, 0));
            }
        
            // Mixer
            is = new ItemStack(machineAlpha.blockID, 1, 4);
            
            cde.api.Blocks.machineMixer = is;
            
            LanguageRegistry.addName(is, Namings.EXTERNAL_MACHINE_ALPHA_BLOCK_NAMES[4]);
            GameRegistry.registerTileEntity(TileEntityMixer.class, "cdeMixerTile");
            
            if(CRAFTABLE[4])
            {
                Ic2Recipes.addCraftingRecipe(is,
                "xxx",
                "byb",
                "aza",
                'x', new ItemStack(Items.getItem("carbonPlate").itemID, 1, 0),
                'y', new ItemStack(Items.getItem("machine").itemID, 1, 0),
                'z', new ItemStack(Items.getItem("electronicCircuit").itemID, 1, 0),
                'a', new ItemStack(Items.getItem("insulatedCopperCableItem").itemID, 1, 0),
                'b', new ItemStack(Items.getItem("reBattery").itemID, 1, 0));
            }
            
            // Solar Panel
            is = new ItemStack(machineAlpha.blockID, 1, 5);
            
            cde.api.Blocks.machineSolarPanel = is;
            
            LanguageRegistry.addName(is, Namings.EXTERNAL_MACHINE_ALPHA_BLOCK_NAMES[5]);
            GameRegistry.registerTileEntity(TileEntitySolarPanel.class, "cdeSolarPanelTile");
            
            if(CRAFTABLE[5])
            {
                Ic2Recipes.addCraftingRecipe(is,
                "aba",
                "bab",
                "cdc",
                'a', new ItemStack(Block.glass.blockID, 1, 0),
                'b', new ItemStack(Items.getItem("coalDust").itemID, 1, 0),
                'c', new ItemStack(Items.getItem("electronicCircuit").itemID, 1, 0),
                'd', new ItemStack(Items.getItem("generator").itemID, 1, 0));
            }
        
            // Transformer
            is = new ItemStack(machineAlpha.blockID, 1, 6);
            
            cde.api.Blocks.machineTransformer = is;
            
            LanguageRegistry.addName(is, Namings.EXTERNAL_MACHINE_ALPHA_BLOCK_NAMES[6]);
            GameRegistry.registerTileEntity(TileEntityTransformer.class, "cdeTransformerTile");
            
            if(CRAFTABLE[6])
            {
                Ic2Recipes.addCraftingRecipe(is,
                "xxx",
                "byb",
                "aza",
                'x', new ItemStack(Items.getItem("tinCableItem").itemID, 1, 10),
                'y', new ItemStack(Items.getItem("machine").itemID, 1, 0),
                'z', new ItemStack(Items.getItem("electronicCircuit").itemID, 1, 0),
                'a', new ItemStack(Items.getItem("insulatedCopperCableItem").itemID, 1, 0),
                'b', new ItemStack(Items.getItem("reBattery").itemID, 1, 0));
            }
        }
        
        if(grateId > 0)
        {
            grate = new BlockGrate(grateId).setBlockName(Namings.INTERNAL_GRATE_NAME).setCreativeTab(CDECore.TAB_CDE).setHardness(3.5F);
            GameRegistry.registerBlock(grate, Namings.INTERNAL_GRATE_NAME);
            LanguageRegistry.addName(grate, Namings.EXTERNAL_GRATE_NAME);
            
            MinecraftForge.setBlockHarvestLevel(grate, "pickaxe", 1);
            
            is = new ItemStack(grate.blockID, 1, 0);
            
            cde.api.Blocks.blockGrate = is;
            
            if(CRAFTABLE[7])
            {
                Ic2Recipes.addCraftingRecipe(is,
                "xxx",
                "x x",
                "xxx",
                'x', new ItemStack(Block.fenceIron.blockID, 1, 0));
            }
        }
    }

    @PostInit
    public void postInit(FMLPostInitializationEvent event) 
    {
        setupItemIds();
    }

    @ServerStarting
    public void serverStarting(FMLServerStartingEvent event)
    {

    }
    
    private void setupIds()
    {
        for(Item item : CDECore.getItemsByClass("buildcraft.core.ItemBuildCraft"))
        {
            if(item.getItemName().equalsIgnoreCase("item.ironGearItem"))
            {
                ironGearId = item.itemID;
            }
        }
        
        Block block = CDECore.getBlockByClass("buildcraft.factory.BlockTank");
        
        if(block != null)
        {
            tankBlockId = block.blockID;
        }
    }
    
    private void setupItemIds()
    {
        for(Item item : CDECore.getItemsByClass("com.eloraam.redpower.core.ItemTextured"))
        {
            if(item != null && item.getItemName().equalsIgnoreCase("item.btbattery"))
            {
                batteryEmptyId = item.itemID;
            }
        }
        
        Item item = CDECore.getItemByClass("com.eloraam.redpower.machine.ItemBattery");
        if(item != null && item.getItemName().equalsIgnoreCase("item.btbattery"))
        {
            batteryFullId = item.itemID;
        }
        
        item = CDECore.getItemByClass("com.eloraam.redpower.base.ItemDyeIndigo");
        if(item != null)
        {
            indigoDyeId = item.itemID;
        }
    }
    
    public static int getItemId(int i)
    {
        switch(i)
        {
            case 0: return batteryEmptyId;
            case 1: return batteryFullId;
            case 2: return indigoDyeId;
            default: return 0;
        }
    }
}
