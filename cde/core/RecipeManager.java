/**
 *
 * @author StormTiberius
 */

package cde.core;

import cde.api.Blocks;
import cde.api.Materials;
import cde.core.util.Utils;
import cpw.mods.fml.common.registry.GameRegistry;
import ic2.api.Ic2Recipes;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.src.ModLoader;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import railcraft.common.api.crafting.RailcraftCraftingManager;

public class RecipeManager
{
    public static void addRecipes()
    {
        // Blocks
        FurnaceRecipes.smelting().addSmelting(Blocks.oreCopper.itemID, Blocks.oreCopper.getItemDamage(), Utils.getNewItemStackWithQuantity(Materials.ingotCopper, 1), 0.5F);
        FurnaceRecipes.smelting().addSmelting(Blocks.oreTin.itemID, Blocks.oreTin.getItemDamage(), Utils.getNewItemStackWithQuantity(Materials.ingotTin, 1), 0.5F);
        FurnaceRecipes.smelting().addSmelting(Blocks.oreSilver.itemID, Blocks.oreSilver.getItemDamage(), Utils.getNewItemStackWithQuantity(Materials.ingotSilver, 1), 0.8F);
        FurnaceRecipes.smelting().addSmelting(Blocks.oreLead.itemID, Blocks.oreLead.getItemDamage(), Utils.getNewItemStackWithQuantity(Materials.ingotLead, 1), 0.8F);
        // FurnaceRecipes.smelting().addSmelting(Blocks.oreUranium.itemID, Blocks.oreUranium.getItemDamage(), Utils.getNewItemStackWithQuantity(Materials.ingotUranium, 1), 0.8F);
        
        // Items
        FurnaceRecipes.smelting().addSmelting(Materials.dustIron.itemID, Materials.dustIron.getItemDamage(), new ItemStack(Item.ingotIron.itemID, 1, 0), 0.7F);
        FurnaceRecipes.smelting().addSmelting(Materials.dustGold.itemID, Materials.dustGold.getItemDamage(), new ItemStack(Item.ingotGold.itemID, 1, 0), 1.0F);
        
        FurnaceRecipes.smelting().addSmelting(Materials.dustCopper.itemID, Materials.dustCopper.getItemDamage(), Utils.getNewItemStackWithQuantity(Materials.ingotCopper, 1), 0.7F);
        FurnaceRecipes.smelting().addSmelting(Materials.dustTin.itemID, Materials.dustTin.getItemDamage(), Utils.getNewItemStackWithQuantity(Materials.ingotTin, 1), 0.7F);
        FurnaceRecipes.smelting().addSmelting(Materials.dustSilver.itemID, Materials.dustSilver.getItemDamage(), Utils.getNewItemStackWithQuantity(Materials.ingotSilver, 1), 1.0F);
        FurnaceRecipes.smelting().addSmelting(Materials.dustLead.itemID, Materials.dustLead.getItemDamage(), Utils.getNewItemStackWithQuantity(Materials.ingotLead, 1), 1.0F);
        
        FurnaceRecipes.smelting().addSmelting(Materials.dustZinc.itemID, Materials.dustZinc.getItemDamage(), Utils.getNewItemStackWithQuantity(Materials.ingotZinc, 1), 1.0F);
        
        FurnaceRecipes.smelting().addSmelting(Materials.dustBronze.itemID, Materials.dustBronze.getItemDamage(), Utils.getNewItemStackWithQuantity(Materials.ingotBronze, 1), 1.0F);
        FurnaceRecipes.smelting().addSmelting(Materials.dustBrass.itemID, Materials.dustBrass.getItemDamage(), Utils.getNewItemStackWithQuantity(Materials.ingotBrass, 1), 1.0F);
        
        // Iron Nugget -> Steel Nugget
        FurnaceRecipes.smelting().addSmelting(Materials.nuggetIron.itemID, Materials.nuggetIron.getItemDamage(), Utils.getNewItemStackWithQuantity(Materials.nuggetSteel, 1), 0.1F);
        
        GameRegistry.addRecipe(new ShapelessOreRecipe(Utils.getNewItemStackWithQuantity(Materials.dustBronze, 2), "dustCopper", "dustCopper", "dustCopper", "dustTin"));
        GameRegistry.addRecipe(new ShapelessOreRecipe(Utils.getNewItemStackWithQuantity(Materials.dustBronze, 2), "dustCopper", "dustCopper", "dustLead", "dustTin"));
        GameRegistry.addRecipe(new ShapelessOreRecipe(Utils.getNewItemStackWithQuantity(Materials.dustBrass, 2), "dustCopper", "dustCopper", "dustCopper", "dustZinc"));
        GameRegistry.addRecipe(new ShapelessOreRecipe(Utils.getNewItemStackWithQuantity(Materials.dustBrass, 2), "dustCopper", "dustCopper", "dustLead", "dustZinc"));
        
        // Nugget Recipes
        GameRegistry.addRecipe(new ShapelessOreRecipe(Utils.getNewItemStackWithQuantity(Materials.nuggetIron, 9), new ItemStack(Item.ingotIron.itemID, 1, 0)));        
        GameRegistry.addRecipe(new ShapelessOreRecipe(Utils.getNewItemStackWithQuantity(Materials.nuggetCopper, 9), "ingotCopper"));
        GameRegistry.addRecipe(new ShapelessOreRecipe(Utils.getNewItemStackWithQuantity(Materials.nuggetTin, 9), "ingotTin"));
        GameRegistry.addRecipe(new ShapelessOreRecipe(Utils.getNewItemStackWithQuantity(Materials.nuggetSilver, 9), "ingotSilver"));
        GameRegistry.addRecipe(new ShapelessOreRecipe(Utils.getNewItemStackWithQuantity(Materials.nuggetLead, 9), "ingotLead"));
        GameRegistry.addRecipe(new ShapelessOreRecipe(Utils.getNewItemStackWithQuantity(Materials.nuggetZinc, 9), "ingotZinc"));
        GameRegistry.addRecipe(new ShapelessOreRecipe(Utils.getNewItemStackWithQuantity(Materials.nuggetBronze, 9), "ingotBronze"));
        GameRegistry.addRecipe(new ShapelessOreRecipe(Utils.getNewItemStackWithQuantity(Materials.nuggetBrass, 9), "ingotBrass"));
        GameRegistry.addRecipe(new ShapelessOreRecipe(Utils.getNewItemStackWithQuantity(Materials.nuggetSteel, 9), "ingotSteel"));

        // Nugget Reverse Recipes
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Item.ingotIron.itemID, 1, 0), 
        "xxx",
        "xxx",
        "xxx",
        'x', "nuggetIron"));
        
        GameRegistry.addRecipe(new ShapedOreRecipe(Utils.getNewItemStackWithQuantity(Materials.ingotCopper, 1),
        "xxx",
        "xxx",
        "xxx",
        'x', "nuggetCopper"));
        
        GameRegistry.addRecipe(new ShapedOreRecipe(Utils.getNewItemStackWithQuantity(Materials.ingotTin, 1),
        "xxx",
        "xxx",
        "xxx",
        'x', "nuggetTin"));
        
        GameRegistry.addRecipe(new ShapedOreRecipe(Utils.getNewItemStackWithQuantity(Materials.ingotSilver, 1),
        "xxx",
        "xxx",
        "xxx",
        'x', "nuggetSilver"));
        
        GameRegistry.addRecipe(new ShapedOreRecipe(Utils.getNewItemStackWithQuantity(Materials.ingotLead, 1),
        "xxx",
        "xxx",
        "xxx",
        'x', "nuggetLead"));
        
        GameRegistry.addRecipe(new ShapedOreRecipe(Utils.getNewItemStackWithQuantity(Materials.ingotZinc, 1),
        "xxx",
        "xxx",
        "xxx",
        'x', "nuggetZinc"));
        
        GameRegistry.addRecipe(new ShapedOreRecipe(Utils.getNewItemStackWithQuantity(Materials.ingotBronze, 1),
        "xxx",
        "xxx",
        "xxx",
        'x', "nuggetBronze"));
        
        GameRegistry.addRecipe(new ShapedOreRecipe(Utils.getNewItemStackWithQuantity(Materials.ingotBrass, 1),
        "xxx",
        "xxx",
        "xxx",
        'x', "nuggetBrass"));
        
        GameRegistry.addRecipe(new ShapedOreRecipe(Utils.getNewItemStackWithQuantity(Materials.ingotSteel, 1),
        "xxx",
        "xxx",
        "xxx",
        'x', "nuggetSteel"));
        
        // Storage Block Recipes
        GameRegistry.addRecipe(new ShapedOreRecipe(Utils.getNewItemStackWithQuantity(Blocks.blockCopper, 1),
        "xxx",
        "xxx",
        "xxx",
        'x', "ingotCopper"));

        GameRegistry.addRecipe(new ShapedOreRecipe(Utils.getNewItemStackWithQuantity(Blocks.blockTin, 1),
        "xxx",
        "xxx",
        "xxx",
        'x', "ingotTin"));
        
        GameRegistry.addRecipe(new ShapedOreRecipe(Utils.getNewItemStackWithQuantity(Blocks.blockSilver, 1),
        "xxx",
        "xxx",
        "xxx",
        'x', "ingotSilver"));
        
        GameRegistry.addRecipe(new ShapedOreRecipe(Utils.getNewItemStackWithQuantity(Blocks.blockLead, 1),
        "xxx",
        "xxx",
        "xxx",
        'x', "ingotLead"));
        
        GameRegistry.addRecipe(new ShapedOreRecipe(Utils.getNewItemStackWithQuantity(Blocks.blockUranium, 1),
        "xxx",
        "xxx",
        "xxx",
        'x', "ingotUranium"));
        
        GameRegistry.addRecipe(new ShapedOreRecipe(Utils.getNewItemStackWithQuantity(Blocks.blockZinc, 1),
        "xxx",
        "xxx",
        "xxx",
        'x', "ingotZinc"));
        
        GameRegistry.addRecipe(new ShapedOreRecipe(Utils.getNewItemStackWithQuantity(Blocks.blockBronze, 1),
        "xxx",
        "xxx",
        "xxx",
        'x', "ingotBronze"));
        
        GameRegistry.addRecipe(new ShapedOreRecipe(Utils.getNewItemStackWithQuantity(Blocks.blockBrass, 1),
        "xxx",
        "xxx",
        "xxx",
        'x', "ingotBrass"));
        
        GameRegistry.addRecipe(new ShapedOreRecipe(Utils.getNewItemStackWithQuantity(Blocks.blockSteel, 1),
        "xxx",
        "xxx",
        "xxx",
        'x', "ingotSteel"));
        
        GameRegistry.addRecipe(new ShapedOreRecipe(Utils.getNewItemStackWithQuantity(Blocks.blockRuby, 1),
        "xxx",
        "xxx",
        "xxx",
        'x', "gemRuby"));
                
        GameRegistry.addRecipe(new ShapedOreRecipe(Utils.getNewItemStackWithQuantity(Blocks.blockJade, 1),
        "xxx",
        "xxx",
        "xxx",
        'x', "gemJade"));
                        
        GameRegistry.addRecipe(new ShapedOreRecipe(Utils.getNewItemStackWithQuantity(Blocks.blockSapphire, 1),
        "xxx",
        "xxx",
        "xxx",
        'x', "gemSapphire"));
        
        // Reverse Storage Block Recipes
        GameRegistry.addRecipe(new ShapelessOreRecipe(Utils.getNewItemStackWithQuantity(Materials.ingotCopper, 9), "blockCopper"));
        GameRegistry.addRecipe(new ShapelessOreRecipe(Utils.getNewItemStackWithQuantity(Materials.ingotTin, 9), "blockTin"));
        GameRegistry.addRecipe(new ShapelessOreRecipe(Utils.getNewItemStackWithQuantity(Materials.ingotSilver, 9), "blockSilver"));
        GameRegistry.addRecipe(new ShapelessOreRecipe(Utils.getNewItemStackWithQuantity(Materials.ingotLead, 9), "blockLead"));
        GameRegistry.addRecipe(new ShapelessOreRecipe(Utils.getNewItemStackWithQuantity(Materials.ingotUranium, 9), "blockUranium"));
        GameRegistry.addRecipe(new ShapelessOreRecipe(Utils.getNewItemStackWithQuantity(Materials.ingotZinc, 9), "blockZinc"));
        GameRegistry.addRecipe(new ShapelessOreRecipe(Utils.getNewItemStackWithQuantity(Materials.ingotBronze, 9), "blockBronze"));
        GameRegistry.addRecipe(new ShapelessOreRecipe(Utils.getNewItemStackWithQuantity(Materials.ingotBrass, 9), "blockBrass"));
        GameRegistry.addRecipe(new ShapelessOreRecipe(Utils.getNewItemStackWithQuantity(Materials.ingotSteel, 9), "blockSteel"));
        GameRegistry.addRecipe(new ShapelessOreRecipe(Utils.getNewItemStackWithQuantity(Materials.gemRuby, 9), "blockRuby"));
        GameRegistry.addRecipe(new ShapelessOreRecipe(Utils.getNewItemStackWithQuantity(Materials.gemJade, 9), "blockJade"));
        GameRegistry.addRecipe(new ShapelessOreRecipe(Utils.getNewItemStackWithQuantity(Materials.gemSapphire, 9), "blockSapphire"));
        
        
        if(ModLoader.isModLoaded("IC2"))
        {
            Ic2Recipes.addMaceratorRecipe(Utils.getNewItemStackWithQuantity(Blocks.oreLead, 1), Utils.getNewItemStackWithQuantity(Materials.dustLead, 2));
            Ic2Recipes.addMaceratorRecipe(Utils.getNewItemStackWithQuantity(Materials.ingotLead, 1), Utils.getNewItemStackWithQuantity(Materials.dustLead, 1));
            Ic2Recipes.addMaceratorRecipe(Utils.getNewItemStackWithQuantity(Materials.ingotZinc, 1), Utils.getNewItemStackWithQuantity(Materials.dustZinc, 1));
            
            addCompressorRecipes();
            addAERecipes();
        }
        
        if(ModLoader.isModLoaded("Railcraft"))
        {
            LinkedHashMap output = new LinkedHashMap();
            
            output.put(Utils.getNewItemStackWithQuantity(Materials.dustLead, 2), 1.0F);
            output.put(Utils.getNewItemStackWithQuantity(Materials.dustZinc, 1), 0.25F);
            
            RailcraftCraftingManager.rockCrusher.addRecipe(Utils.getNewItemStackWithQuantity(Blocks.oreLead, 1), output);
        }
    }
    
    private static void addAERecipes()
    {
        if(ModLoader.isModLoaded("AppliedEnergistics") && appeng.api.Materials.matSilicon != null)
        {
            FurnaceRecipes.smelting().addSmelting(Materials.dustQuartz.itemID, Materials.dustQuartz.getItemDamage(), Utils.getNewItemStackWithQuantity(appeng.api.Materials.matSilicon, 1), 0.2F);
        
            Ic2Recipes.addCompressorRecipe(Utils.getNewItemStackWithQuantity(Materials.dustQuartz, 4), Utils.getNewItemStackWithQuantity(Materials.gemQuartz, 1));

            Ic2Recipes.addMaceratorRecipe(Utils.getNewItemStackWithQuantity(Materials.gemQuartz, 1), Utils.getNewItemStackWithQuantity(Materials.dustQuartz, 4));
            Ic2Recipes.addMaceratorRecipe(Utils.getNewItemStackWithQuantity(Blocks.oreQuartz, 1), Utils.getNewItemStackWithQuantity(Materials.dustQuartz, 10));
        }
    }
    
    private static void addCompressorRecipes()
    {
        // ArrayList copper = OreDictionary.getOres("ingotCopper");
        // ArrayList tin = OreDictionary.getOres("ingotTin");
        // ArrayList silver = OreDictionary.getOres("ingotSilver");
        ArrayList lead = OreDictionary.getOres("ingotLead");
        // ArrayList uranium = OreDictionary.getOres("ingotUranium");
        ArrayList zinc = OreDictionary.getOres("ingotZinc");
        ArrayList bronze = OreDictionary.getOres("ingotBronze");
        ArrayList brass = OreDictionary.getOres("ingotBrass");
        ArrayList steel = OreDictionary.getOres("ingotSteel");
        ArrayList ruby = OreDictionary.getOres("gemRuby");
        ArrayList jade = OreDictionary.getOres("gemJade");
        ArrayList sapphire = OreDictionary.getOres("gemSapphire");
        
        // for(Object o : copper)
        // {
        //     Ic2Recipes.addCompressorRecipe(Utils.getNewItemStackWithQuantity((ItemStack)o, 9), Utils.getNewItemStackWithQuantity(Blocks.blockCopper, 1));
        // }
        
        // for(Object o : tin)
        // {
        //     Ic2Recipes.addCompressorRecipe(Utils.getNewItemStackWithQuantity((ItemStack)o, 9), Utils.getNewItemStackWithQuantity(Blocks.blockTin, 1));
        // }
                
        // for(Object o : silver)
        // {
        //     Ic2Recipes.addCompressorRecipe(Utils.getNewItemStackWithQuantity((ItemStack)o, 9), Utils.getNewItemStackWithQuantity(Blocks.blockSilver, 1));
        // }
        
        for(Object o : lead)
        {
            Ic2Recipes.addCompressorRecipe(Utils.getNewItemStackWithQuantity((ItemStack)o, 9), Utils.getNewItemStackWithQuantity(Blocks.blockLead, 1));
        }
                
        // for(Object o : uranium)
        // {
        //     Ic2Recipes.addCompressorRecipe(Utils.getNewItemStackWithQuantity((ItemStack)o, 9), Utils.getNewItemStackWithQuantity(Blocks.blockUranium, 1));
        // }
        
        for(Object o : zinc)
        {
            Ic2Recipes.addCompressorRecipe(Utils.getNewItemStackWithQuantity((ItemStack)o, 9), Utils.getNewItemStackWithQuantity(Blocks.blockZinc, 1));
        }
                                
        for(Object o : bronze)
        {
            Ic2Recipes.addCompressorRecipe(Utils.getNewItemStackWithQuantity((ItemStack)o, 9), Utils.getNewItemStackWithQuantity(Blocks.blockBronze, 1));
        }
                                        
        for(Object o : brass)
        {
            Ic2Recipes.addCompressorRecipe(Utils.getNewItemStackWithQuantity((ItemStack)o, 9), Utils.getNewItemStackWithQuantity(Blocks.blockBrass, 1));
        }
                                                
        for(Object o : steel)
        {
            Ic2Recipes.addCompressorRecipe(Utils.getNewItemStackWithQuantity((ItemStack)o, 9), Utils.getNewItemStackWithQuantity(Blocks.blockSteel, 1));
        }
                                                        
        for(Object o : ruby)
        {
            Ic2Recipes.addCompressorRecipe(Utils.getNewItemStackWithQuantity((ItemStack)o, 9), Utils.getNewItemStackWithQuantity(Blocks.blockRuby, 1));
        }
                                                                
        for(Object o : jade)
        {
            Ic2Recipes.addCompressorRecipe(Utils.getNewItemStackWithQuantity((ItemStack)o, 9), Utils.getNewItemStackWithQuantity(Blocks.blockJade, 1));
        }
        
        for(Object o : sapphire)
        {
            Ic2Recipes.addCompressorRecipe(Utils.getNewItemStackWithQuantity((ItemStack)o, 9), Utils.getNewItemStackWithQuantity(Blocks.blockSapphire, 1));
        }
    }
}
