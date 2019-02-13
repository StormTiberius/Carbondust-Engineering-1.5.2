/**
 *
 * @author StormTiberius
 */

package cde.tweak;

import ic2.api.Items;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.src.ModLoader;
import net.minecraftforge.common.MinecraftForge;

public class PlantSeedGrass
{
    public static void init(boolean[] flags)
    {
        if(flags[17])
        {
            MinecraftForge.addGrassSeed(new ItemStack(Block.mushroomBrown.blockID, 1, 0), 1);
        }
        
        if(flags[18])
        {
            MinecraftForge.addGrassSeed(new ItemStack(Block.mushroomRed.blockID, 1, 0), 1);
        }
                        
        if(flags[19])
        {
            MinecraftForge.addGrassSeed(new ItemStack(Block.netherStalk.blockID, 1, 0), 1);
        }
        
        if(flags[20])
        {
            MinecraftForge.addGrassSeed(new ItemStack(Item.pumpkinSeeds.itemID, 1, 0), 1);
        }
        
        if(flags[21])
        {
            MinecraftForge.addGrassSeed(new ItemStack(Block.waterlily.blockID, 1, 0), 1);
        }
        
        if(flags[22])
        {
            MinecraftForge.addGrassSeed(new ItemStack(Item.melonSeeds.itemID, 1, 0), 1);
        }
        
        if(flags[23])
        {
            MinecraftForge.addGrassSeed(new ItemStack(Block.deadBush.blockID, 1, 0), 1);
        }
        
        if(flags[24])
        {
            MinecraftForge.addGrassSeed(new ItemStack(Block.cactus.blockID, 1, 0), 1);
        }
            
        if(flags[25])
        {
            MinecraftForge.addGrassSeed(new ItemStack(Block.vine.blockID, 1, 0), 1);
        }
        
        if(flags[26])
        {
            MinecraftForge.addGrassSeed(new ItemStack(Item.reed.itemID, 1, 0), 1);
        }
        
        if(flags[27])
        {
            MinecraftForge.addGrassPlant(Block.carrot, 0, 1);
        }
        
        if(flags[28])
        {
            MinecraftForge.addGrassPlant(Block.potato, 0, 1);
        }
        
        if(flags[29])
        {
            MinecraftForge.addGrassPlant(Block.sapling, 0, 1);
        }
                
        if(flags[30])
        {
            MinecraftForge.addGrassPlant(Block.sapling, 1, 1);
        }
                
        if(flags[31])
        {
            MinecraftForge.addGrassPlant(Block.sapling, 2, 1);
        }
                
        if(flags[32])
        {
            MinecraftForge.addGrassPlant(Block.sapling, 3, 1);
        }
        
        if(ModLoader.isModLoaded("IC2"))
        {
            if(flags[33])
            {
                ItemStack is = Items.getItem("rubberSapling");

                if(is != null)
                {
                    MinecraftForge.addGrassPlant(Block.blocksList[is.itemID], 0, 1);
                }
            }
        }
    }
}
