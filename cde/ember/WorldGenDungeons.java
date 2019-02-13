package cde.ember;

import forestry.api.core.BlockInterface;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.item.ItemStack;
import net.minecraft.src.ModLoader;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import net.minecraftforge.common.ChestGenHooks;

public class WorldGenDungeons extends WorldGenerator
{
    private static final String[] DUNGEON_MOBS = {"Skeleton", "Zombie", "Spider", "Creeper", "Enderman"};
    private final String loot;
    private final int floorBlockId,wallBlockId;
    
    public WorldGenDungeons(String loot, int floorBlockId, int wallBlockId)
    {
        this.loot = loot;
        this.floorBlockId = floorBlockId;
        this.wallBlockId = wallBlockId;
    }
    
    @Override
    public boolean generate(World par1World, Random par2Random, int par3, int par4, int par5)
    {
        byte var6 = 3;
        int var7 = par2Random.nextInt(2) + 2;
        int var8 = par2Random.nextInt(2) + 2;
        int var9 = 0;
        int var10;
        int var11;
        int var12;

        for (var10 = par3 - var7 - 1; var10 <= par3 + var7 + 1; ++var10)
        {
            for (var11 = par4 - 1; var11 <= par4 + var6 + 1; ++var11)
            {
                for (var12 = par5 - var8 - 1; var12 <= par5 + var8 + 1; ++var12)
                {
                    Material var13 = par1World.getBlockMaterial(var10, var11, var12);

                    if (var11 == par4 - 1 && !var13.isSolid())
                    {
                        return false;
                    }

                    if (var11 == par4 + var6 + 1 && !var13.isSolid())
                    {
                        return false;
                    }

                    if ((var10 == par3 - var7 - 1 || var10 == par3 + var7 + 1 || var12 == par5 - var8 - 1 || var12 == par5 + var8 + 1) && var11 == par4 && par1World.isAirBlock(var10, var11, var12) && par1World.isAirBlock(var10, var11 + 1, var12))
                    {
                        ++var9;
                    }
                }
            }
        }

        if (var9 >= 1 && var9 <= 5)
        {
            for (var10 = par3 - var7 - 1; var10 <= par3 + var7 + 1; ++var10)
            {
                for (var11 = par4 + var6; var11 >= par4 - 1; --var11)
                {
                    for (var12 = par5 - var8 - 1; var12 <= par5 + var8 + 1; ++var12)
                    {
                        if (var10 != par3 - var7 - 1 && var11 != par4 - 1 && var12 != par5 - var8 - 1 && var10 != par3 + var7 + 1 && var11 != par4 + var6 + 1 && var12 != par5 + var8 + 1)
                        {
                            par1World.setBlockWithNotify(var10, var11, var12, 0);
                        }
                        else if (var11 >= 0 && !par1World.getBlockMaterial(var10, var11 - 1, var12).isSolid())
                        {
                            par1World.setBlockWithNotify(var10, var11, var12, 0);
                        }
                        else if (par1World.getBlockMaterial(var10, var11, var12).isSolid())
                        {
                            if (var11 == par4 - 1)
                            {
                                par1World.setBlockWithNotify(var10, var11, var12, floorBlockId);
                            }
                            else
                            {
                                par1World.setBlockWithNotify(var10, var11, var12, wallBlockId);
                            }
                        }
                    }
                }
            }

            var10 = 0;

            while (var10 < 2)
            {
                var11 = 0;

                while (true)
                {
                    if (var11 < 3)
                    {
                        label210:
                        {
                            var12 = par3 + par2Random.nextInt(var7 * 2 + 1) - var7;
                            int var14 = par5 + par2Random.nextInt(var8 * 2 + 1) - var8;

                            if (par1World.isAirBlock(var12, par4, var14))
                            {
                                int var15 = 0;

                                if (par1World.getBlockMaterial(var12 - 1, par4, var14).isSolid())
                                {
                                    ++var15;
                                }

                                if (par1World.getBlockMaterial(var12 + 1, par4, var14).isSolid())
                                {
                                    ++var15;
                                }

                                if (par1World.getBlockMaterial(var12, par4, var14 - 1).isSolid())
                                {
                                    ++var15;
                                }

                                if (par1World.getBlockMaterial(var12, par4, var14 + 1).isSolid())
                                {
                                    ++var15;
                                }

                                if (var15 == 1)
                                {
                                    par1World.setBlockWithNotify(var12, par4, var14, Block.chest.blockID);
                                    TileEntityChest var16 = (TileEntityChest)par1World.getBlockTileEntity(var12, par4, var14);

                                    if (var16 != null)
                                    {
                                        ChestGenHooks info = ChestGenHooks.getInfo(loot);
                                        WeightedRandomChestContent.generateChestContents(par2Random, info.getItems(par2Random), var16, info.getCount(par2Random));
                                    }

                                    break label210;
                                }
                            }

                            ++var11;
                            continue;
                        }
                    }

                    ++var10;
                    break;
                }
            }

            if(loot.equals(ChestGenHooks.VILLAGE_BLACKSMITH))
            {
                if(ModLoader.isModLoaded("Forestry"))
                {
                    ItemStack beehives = BlockInterface.getBlock("beehives");
                    
                    if(beehives != null && par1World.getBlockMaterial(par3, par4 + 4, par5).isSolid())
                    {
                        switch(par2Random.nextInt(7))
                        {
                            case 0: par1World.setBlockAndMetadataWithNotify(par3, par4 + 3, par5, beehives.itemID, 1); break;
                            case 1: par1World.setBlockAndMetadataWithNotify(par3, par4 + 3, par5, beehives.itemID, 2); break;
                            case 2: par1World.setBlockAndMetadataWithNotify(par3, par4 + 3, par5, beehives.itemID, 3); break;
                            case 3: par1World.setBlockAndMetadataWithNotify(par3, par4 + 3, par5, beehives.itemID, 4); break;
                            case 4: par1World.setBlockAndMetadataWithNotify(par3, par4 + 3, par5, beehives.itemID, 5); break;
                            case 5: par1World.setBlockAndMetadataWithNotify(par3, par4 + 3, par5, beehives.itemID, 6); break;
                            case 6: par1World.setBlockAndMetadataWithNotify(par3, par4 + 3, par5, beehives.itemID, 7); break;
                        }
                    }
                }
                
                EntityAnimal animal;
                
                switch(par2Random.nextInt(4))
                {
                    case 0: animal = new EntityChicken(par1World); break;
                    case 1: animal = new EntityPig(par1World); break;
                    case 2: animal = new EntitySheep(par1World); break;
                    default: animal = new EntityCow(par1World); break;
                }
                
                animal.setPosition(par3, par4, par5);
                
                if(par1World.checkIfAABBIsClear(animal.boundingBox) && par1World.getCollidingBoundingBoxes(animal, animal.boundingBox).isEmpty() && !par1World.isAnyLiquid(animal.boundingBox))
                {
                    par1World.spawnEntityInWorld(animal);
                }
            }
            else
            {
                par1World.setBlockWithNotify(par3, par4, par5, Block.mobSpawner.blockID);
                TileEntityMobSpawner var19 = (TileEntityMobSpawner)par1World.getBlockTileEntity(par3, par4, par5);
                
                if(var19 != null)
                {
                    var19.setMobID(DUNGEON_MOBS[par2Random.nextInt(DUNGEON_MOBS.length)]);
                }
                else
                {
                    System.err.println("Failed to fetch mob spawner entity at (" + par3 + ", " + par4 + ", " + par5 + ")");
                }
            }
            
            return true;
        }
        else
        {
            return false;
        }
    }
}
