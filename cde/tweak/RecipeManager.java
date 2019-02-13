/**
 *
 * @author StormTiberius
 */

package cde.tweak;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.GameRegistry;
import forestry.api.core.ItemInterface;
import forestry.api.recipes.RecipeManagers;
import ic2.api.Ic2Recipes;
import ic2.api.Items;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.liquids.LiquidContainerRegistry;
import net.minecraftforge.liquids.LiquidDictionary;
import net.minecraftforge.oredict.OreDictionary;
import railcraft.common.api.core.items.ItemRegistry;
import railcraft.common.api.crafting.RailcraftCraftingManager;

public class RecipeManager
{
    public static void addRecipes(boolean[] flags)
    {
        // Stonebrick smelting recipe
        if(flags[0])
        {
            FurnaceRecipes.smelting().addSmelting(Block.stoneBrick.blockID, 0, new ItemStack(Block.stoneBrick.blockID, 1, 2), 0.1F);
        }
        
        // Glowstone dust smelting recipe
        if(flags[1])
        {
            FurnaceRecipes.smelting().addSmelting(Item.lightStoneDust.itemID, new ItemStack(Item.blazeRod.itemID, 1, 0), 1.0F);
        }
        
        // Emerald smelting recipe
        if(flags[2])
        {
            FurnaceRecipes.smelting().addSmelting(Item.emerald.itemID, new ItemStack(Item.enderPearl.itemID, 1, 0), 1.0F);
        }
        
        // Diamond smelting recipe
        if(flags[39])
        {
            FurnaceRecipes.smelting().addSmelting(Item.diamond.itemID, new ItemStack(Item.ghastTear.itemID, 1, 0), 1.0F);
        }
        
        // Feather recipe
        if(flags[3])
        {
            GameRegistry.addShapelessRecipe(new ItemStack(Item.feather.itemID, 1, 0), new ItemStack(Item.stick.itemID, 1, 0), new ItemStack(Item.silk.itemID, 1, 0));
        }
        
        // Double stoneslab recipe
        if(flags[4])
        {
            GameRegistry.addRecipe(new ItemStack(Block.stoneDoubleSlab.blockID, 4, 0), 
            "xx",
            "xx",
            'x', new ItemStack(Block.stoneBrick.blockID, 1, 3));
        }
        
        if(Loader.isModLoaded("Railcraft"))
        {
            // Iron nugget smelting recipe
            if(flags[5])
            {
                FurnaceRecipes.smelting().addSmelting(ItemRegistry.getItem("nugget.iron", 1).itemID, 0, new ItemStack(ItemRegistry.getItem("nugget.steel", 1).itemID, 1, 1), 0.1F);
            }
            
            // Reactor vent steel recipe
            if(Loader.isModLoaded("IC2") && flags[6])
            {
                Ic2Recipes.addCraftingRecipe(new ItemStack(Items.getItem("reactorVent").itemID, 1, 0),
                "xyx",
                "y y",
                "xyx",
                'x', new ItemStack(ItemRegistry.getItem("part.ingot.steel", 1).itemID, 1, 0),
                'y', new ItemStack(Block.fenceIron.blockID, 1, 0));
            }
        }
                
        if(Loader.isModLoaded("IC2"))
        {
            // Slimeball recipe
            if(flags[7])
            {
                Ic2Recipes.addExtractorRecipe(new ItemStack(Block.sapling.blockID, 8, 0), new ItemStack(Item.slimeBall.itemID, 1, 0));
                Ic2Recipes.addExtractorRecipe(new ItemStack(Block.sapling.blockID, 8, 1), new ItemStack(Item.slimeBall.itemID, 1, 0));
                Ic2Recipes.addExtractorRecipe(new ItemStack(Block.sapling.blockID, 8, 2), new ItemStack(Item.slimeBall.itemID, 1, 0));
                Ic2Recipes.addExtractorRecipe(new ItemStack(Block.sapling.blockID, 8, 3), new ItemStack(Item.slimeBall.itemID, 1, 0));
            }

            // Leather recipe
            if(flags[8])
            {
                Ic2Recipes.addCompressorRecipe(new ItemStack(Block.wood.blockID, 1, 0), new ItemStack(Item.leather.itemID, 1, 0));
                Ic2Recipes.addCompressorRecipe(new ItemStack(Block.wood.blockID, 1, 1), new ItemStack(Item.leather.itemID, 1, 0));
                Ic2Recipes.addCompressorRecipe(new ItemStack(Block.wood.blockID, 1, 2), new ItemStack(Item.leather.itemID, 1, 0));
                Ic2Recipes.addCompressorRecipe(new ItemStack(Block.wood.blockID, 1, 3), new ItemStack(Item.leather.itemID, 1, 0));
            }

            // Grinpowder recipe
            if(flags[9])
            {
                Ic2Recipes.addExtractorRecipe(new ItemStack(Item.poisonousPotato.itemID, 1, 0), new ItemStack(Items.getItem("grinPowder").itemID, 2, 0));
            }
            
            // Scrapbox smelting recipe
            if(flags[10])
            {
                FurnaceRecipes.smelting().addSmelting(Items.getItem("scrapBox").itemID, new ItemStack(Item.coal.itemID, 2, 1), 0.1F);
            }
            
            if(flags[35])
            {
                dcpFix();
            }
        }
        
        if(Loader.isModLoaded("Forestry"))
        {
            // Fertilizer recipe
            if(flags[16])
            {
                GameRegistry.addRecipe(new ItemStack(ItemInterface.getItem("fertilizerCompound").itemID, 2, 0), 
                "xxx",
                "xyx",
                "xxx",
                'x', new ItemStack(ItemInterface.getItem("ash").itemID, 1, 0),
                'y', new ItemStack(Block.sand.blockID, 1, 0));
            }
            
            if(flags[36])
            {
                RecipeManagers.carpenterManager.addRecipe(40, LiquidDictionary.getLiquid("Lava", LiquidContainerRegistry.BUCKET_VOLUME), null, new ItemStack(Block.netherrack.blockID, 1, 0), new Object[] {
                    "x  ",
                    "   ",
                    "   ", 'x', new ItemStack(Item.redstone.itemID, 1, 0)});
            }
            
            if(flags[37])
            {
                RecipeManagers.carpenterManager.addRecipe(40, LiquidDictionary.getLiquid("Water", LiquidContainerRegistry.BUCKET_VOLUME), null, new ItemStack(Block.slowSand.blockID, 1, 0), new Object[] {
                    "xy ",
                    "   ",
                    "   ", 'x', new ItemStack(Block.sand.blockID, 1, 0), 'y', new ItemStack(Block.mycelium.blockID, 1, 0)});
            }
        }        
    }
    
    public static void delRecipes(boolean[] flags)
    {
        // Remove UU recipes
        if(flags[11])
        {
            delMFRecipes();
        }
        
        // IC2 use steel recipe fix
        if(flags[12])
        {
            delICRecipes();
        }
        
        // RC2 use steel recipe fix
        if(flags[13])
        {
            delRCRecipes();
        }    
    }
    
    private static void delICRecipes()
    {
        FurnaceRecipes.smelting().getSmeltingList().remove(Item.ingotIron.itemID);
        rmICRecipe(new ItemStack(Items.getItem("refinedIronIngot").itemID, 8, 0));
        rmICRecipe(new ItemStack(Items.getItem("refinedIronIngot").itemID, 1, 0));
        rmICRecipe(new ItemStack(Items.getItem("reactorVent").itemID, 1, 0));
    }
    
    private static void delMFRecipes()
    {
        rmICRecipe(new ItemStack(Block.stone.blockID, 16, 0));
        rmICRecipe(new ItemStack(Block.glass.blockID, 32, 0));
        rmICRecipe(new ItemStack(Block.grass.blockID, 16, 0));
        rmICRecipe(new ItemStack(Block.cobblestoneMossy.blockID, 16, 0));
        rmICRecipe(new ItemStack(Block.sandStone.blockID, 16, 0));
        rmICRecipe(new ItemStack(Block.blockSnow.blockID, 4, 0));
        rmICRecipe(new ItemStack(Block.waterStill.blockID, 1, 0));
        rmICRecipe(new ItemStack(Block.lavaStill.blockID, 1, 0));
        rmICRecipe(new ItemStack(Block.oreIron.blockID, 2, 0));
        rmICRecipe(new ItemStack(Block.oreGold.blockID, 2, 0));
        rmICRecipe(new ItemStack(Block.obsidian.blockID, 12, 0));
        rmICRecipe(new ItemStack(Block.netherrack.blockID, 16, 0));
        rmICRecipe(new ItemStack(Block.glowStone.blockID, 8, 0));
        rmICRecipe(new ItemStack(Block.wood.blockID, 8, 0));
        rmICRecipe(new ItemStack(Block.cactus.blockID, 48, 0));
        rmICRecipe(new ItemStack(Block.vine.blockID, 24, 0));
        rmICRecipe(new ItemStack(Block.cloth.blockID, 12, 0));
        rmICRecipe(new ItemStack(Item.coal.itemID, 5, 0));
        rmICRecipe(new ItemStack(Item.diamond.itemID, 1, 0));
        rmICRecipe(new ItemStack(Item.redstone.itemID, 24, 0));
        rmICRecipe(new ItemStack(Item.dyePowder.itemID, 9, 4));
        rmICRecipe(new ItemStack(Item.feather.itemID, 32, 0));
        rmICRecipe(new ItemStack(Item.snowball.itemID, 16, 0));
        rmICRecipe(new ItemStack(Item.gunpowder.itemID, 15, 0));
        rmICRecipe(new ItemStack(Item.clay.itemID, 48, 0));
        rmICRecipe(new ItemStack(Item.dyePowder.itemID, 32, 3));
        rmICRecipe(new ItemStack(Item.dyePowder.itemID, 48, 0));
        rmICRecipe(new ItemStack(Item.reed.itemID, 48, 0));
        rmICRecipe(new ItemStack(Item.flint.itemID, 32, 0));
        rmICRecipe(new ItemStack(Item.bone.itemID, 32, 0));
        rmICRecipe(new ItemStack(Items.getItem("resin").itemID, 21, 0));
        rmICRecipe(new ItemStack(Items.getItem("iridiumOre").itemID, 1, 0));
        rmICRecipe(new ItemStack(Block.mycelium.blockID, 24, 0));
        rmICRecipe(new ItemStack(Block.stoneBrick.blockID, 48, 3));
        
        if(Items.getItem("copperOre") != null)
        {
            rmICRecipe(new ItemStack(Items.getItem("copperOre").itemID, 5, 0));
        }
        else
        {
            rmICRecipe(new ItemStack(Items.getItem("copperDust").itemID, 10, 0));
        }
        
        if(Items.getItem("tinOre") != null)
        {
            rmICRecipe(new ItemStack(Items.getItem("tinOre").itemID, 5, 0));
        }
        else
        {
            rmICRecipe(new ItemStack(Items.getItem("tinDust").itemID, 10, 0));
        }    
    }
    
    private static void delRCRecipes()
    {
        rmRCRecipe(new ItemStack(ItemRegistry.getItem("part.rebar", 1).itemID, 4, 0));
        rmRCRecipe(new ItemStack(ItemRegistry.getItem("part.rebar", 1).itemID, 4, 0));
        rmRCRecipe(new ItemStack(ItemRegistry.getItem("part.rebar", 1).itemID, 6, 0));
        
        rmRCRecipe(new ItemStack(ItemRegistry.getItem("post.metal", 1).itemID, 12, 2));
        rmRCRecipe(new ItemStack(ItemRegistry.getItem("post.metal", 1).itemID, 12, 2));
        rmRCRecipe(new ItemStack(ItemRegistry.getItem("post.metal", 1).itemID, 16, 2));
        rmRCRecipe(new ItemStack(ItemRegistry.getItem("post.metal", 1).itemID, 16, 2));
        rmRCRecipe(new ItemStack(ItemRegistry.getItem("post.metal", 1).itemID, 20, 2));
        rmRCRecipe(new ItemStack(ItemRegistry.getItem("post.metal", 1).itemID, 20, 2));
        rmRCRecipe(new ItemStack(ItemRegistry.getItem("post.metal", 1).itemID, 32, 2));   
    }
    
    private static void rmICRecipe(ItemStack is)
    {
        ArrayList rl = (ArrayList)CraftingManager.getInstance().getRecipeList();
        
        for(int i = 0; i < rl.size(); i++)
        {
            IRecipe ir = (IRecipe)rl.get(i);
            ItemStack ro = ir.getRecipeOutput();

            if(ir.getClass().getCanonicalName().equalsIgnoreCase("ic2.core.AdvRecipe") && ItemStack.areItemStacksEqual(is, ro))
            {
                rl.remove(i);
            }
        }
    }
    
    private static void rmRCRecipe(ItemStack is)
    {
        ArrayList rl = (ArrayList)RailcraftCraftingManager.rollingMachine.getRecipeList();
        
        for(int i = 0; i < rl.size(); i++)
        {
            IRecipe ir = (IRecipe)rl.get(i);
            ItemStack ro = ir.getRecipeOutput();

            if(ItemStack.areItemStacksEqual(is, ro))
            {
                rl.remove(i);
            }
        }
    }
    
    private static void dcpFix()
    {
        ItemStack dcp = new ItemStack(Items.getItem("denseCopperPlate").itemID, 1, 0);
        
        List recipes = Ic2Recipes.getCompressorRecipes();
        
        for(Iterator<Entry> iterator = recipes.iterator(); iterator.hasNext();)
        {
            Entry entry = iterator.next();
            
            ItemStack is = (ItemStack)entry.getValue();
            
            if(ItemStack.areItemStacksEqual(dcp, is))
            {
                iterator.remove();
            }
        }
        
        ArrayList<ItemStack> al = OreDictionary.getOres("blockCopper");
        
        for(ItemStack is : al)
        {
            Ic2Recipes.addCompressorRecipe(new ItemStack(is.itemID, 1, is.getItemDamage()), dcp);
        }
    }
}
