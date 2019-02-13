/**
 *
 * @author StormTiberius
 */

package cde.core.speaker;

import cde.CDECore;
import cde.core.Defaults;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import java.io.File;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class SpeakerModule
{
    private static Configuration cfg;
    
    private static final String[] DEFAULT_SOUNDS = {
    "computalk1.wav",
    "computalk2.wav",
    "techamb1.wav",
    "techamb2.wav",

    "boiler.ogg",
    "chemical-plant.ogg",
    "electric-furnace.ogg",
    "electric-mining-drill.ogg",
    "furnace.ogg",
    "lab.ogg",
    "oil-refinery.ogg",
    "pipe.ogg",
    "pumpjack.ogg",
    "steam-engine-90bpm.ogg",
    "storage-tank.ogg",
    "substation.ogg",
    "train-engine.ogg"};
    
    private static final int[] DEFAULT_VOLUMES = {20, 20, 20, 20, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40};
    private static final int[] DEFAULT_PITCHS = {100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100};
    
    protected static String[] sounds;
    protected static int[] volumes,pitchs;
    
    private static int speakerId;
    public static Block speaker;
    
    public static void preInit(FMLPreInitializationEvent event) 
    {
        cfg = new Configuration(new File(event.getModConfigurationDirectory(), "cde/speaker.cfg"));
        cfg.load();
        
        speakerId = cfg.get(Configuration.CATEGORY_BLOCK, "speaker", Defaults.BLOCK_SPEAKER_ID).getInt();
        
        sounds = cfg.get(Configuration.CATEGORY_GENERAL, "sounds", DEFAULT_SOUNDS, "Sound File Names").valueList;
        volumes = cfg.get(Configuration.CATEGORY_GENERAL, "volumes", DEFAULT_VOLUMES, "Sound Volumes").getIntList();
        pitchs = cfg.get(Configuration.CATEGORY_GENERAL, "pitchs", DEFAULT_PITCHS, "Sound Pitchs").getIntList();
        
        cfg.save();
    }

    public static void init(FMLInitializationEvent event) 
    {   
        if(speakerId > 0)
        {
            speaker = new BlockSpeaker(speakerId).setBlockName("cdeSpeaker").setCreativeTab(CDECore.TAB_CDE).setHardness(2.0F);
            GameRegistry.registerBlock(speaker, "cdeSpeaker");
            LanguageRegistry.addName(speaker, "Speaker");
            GameRegistry.registerTileEntity(TileEntitySpeaker.class, "cdeSpeakerTile");
            MinecraftForge.setBlockHarvestLevel(speaker, "axe", 1);
            
            ItemStack is = new ItemStack(speaker.blockID, 1, 0);
            
            cde.api.Blocks.machineSpeaker = is;
            
            GameRegistry.addRecipe(new ShapedOreRecipe(is,
            "xxx",
            "xyx",
            "xxx",
            'x', "plankWood",
            'y', new ItemStack(Item.emerald.itemID, 1, 0)));
        }
    }
}
