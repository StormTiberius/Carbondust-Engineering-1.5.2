package cde.ember;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraft.world.World;
import net.minecraftforge.common.ChestGenHooks;

public class WorldGenSpawn
{   
    public void generate(World world, Random random, int chunkX, int chunkZ)
    {
        int spawnY = world.provider.getSpawnPoint().posY;
        
        int xPos,yPos,zPos;
        
        for(int x = 4; x < 13; x++)
        {
            for(int y = 0; y < 5; y++)
            {
                for(int z = 4; z < 13; z++)
                {
                    xPos = chunkX * 16 + x;
                    yPos = spawnY - 1 + y;
                    zPos = chunkZ * 16 + z;
                    
                    if(y == 0)
                    {
                        world.setBlock(xPos, yPos, zPos, Block.grass.blockID);
                    }
                    else if(x == 4 || x == 12 || z == 4 || z == 12)
                    {
                        if(!world.isAirBlock(xPos, yPos, zPos))
                        {
                            world.setBlock(xPos, yPos, zPos, Block.cobblestone.blockID);
                        }
                    }
                    else
                    {
                        world.setBlock(xPos, yPos, zPos, 0);
                    }
                }
            }
        }
        
        xPos = chunkX * 16 + 8;
        yPos = spawnY;
        zPos = chunkZ * 16 + 8;
        
        world.setBlockWithNotify(xPos + 1, yPos, zPos, Block.torchWood.blockID);
        world.setBlockWithNotify(xPos, yPos, zPos + 1, Block.torchWood.blockID);
        world.setBlockWithNotify(xPos - 1, yPos, zPos, Block.torchWood.blockID);
        world.setBlockWithNotify(xPos, yPos, zPos - 1, Block.torchWood.blockID);
        
        int[] ra = getRandomizedArray(20, random);
        
        int chests = 0;
        int first = 0;
        
        for(int i = 0; i < ra.length; i++)
        {
            if(chests == 0)
            {
                if(generateChest(world, random, chunkX, spawnY, chunkZ, ra[i]))
                {
                    first = ra[i];
                    chests++;
                }
            }
            else
            {
                if(!(isAdjacent(first, ra[i])))
                {
                    if(generateChest(world, random, chunkX, spawnY, chunkZ, ra[i]))
                    {
                        break;
                    }
                }
            }
        }
    }
        
    private boolean generateChest(World world, Random random, int chunkX, int yPos, int chunkZ, int spot)
    {
        ChunkCoordinates cc = getOffset(spot);
            
            int xPos = chunkX * 16 + cc.posX;
            int zPos = chunkZ * 16 + cc.posZ;
        
            if(world.getBlockMaterial(xPos, yPos - 1, zPos).isSolid() && world.isAirBlock(xPos, yPos, zPos))
            {
                world.setBlockWithNotify(xPos, yPos, zPos, Block.chest.blockID);

                TileEntityChest tec = (TileEntityChest)world.getBlockTileEntity(xPos, yPos, zPos);

                if (tec != null)
                {
                    ChestGenHooks info = ChestGenHooks.getInfo(ChestGenHooks.BONUS_CHEST);
                    WeightedRandomChestContent.generateChestContents(random, info.getItems(random), tec, info.getCount(random));
                    
                    return true;
                }
            }
            
            return false;
    }
    
    private boolean isAdjacent(int a, int b)
    {
        if(a == 4 && b == 5)
        {
            return false;
        }
        
        if(a == 5 && b == 4)
        {
            return false;
        }
        
        if(a == 9 && b == 10)
        {
            return false;
        }
        
        if(a == 10 && b == 9)
        {
            return false;
        }
        
        if(a == 14 && b == 15)
        {
            return false;
        }
        
        if(a == 15 && b == 14)
        {
            return false;
        }
        
        if(a == 19 && b == 0)
        {
            return false;
        }
        
        if(a == 0 && b == 19)
        {
            return false;
        }
        
        return a == b + 1 || a == b - 1;
    }
    
    private ChunkCoordinates getOffset(int spot)
    {
        ChunkCoordinates cc = new ChunkCoordinates();
        
        switch(spot)
        {
            case 0: cc.set(6,0,5); return cc;
            case 1: cc.set(7,0,5); return cc;
            case 2: cc.set(8,0,5); return cc;
            case 3: cc.set(9,0,5); return cc;
            case 4: cc.set(10,0,5); return cc;
            case 5: cc.set(11,0,6); return cc;
            case 6: cc.set(11,0,7); return cc;
            case 7: cc.set(11,0,8); return cc;
            case 8: cc.set(11,0,9); return cc;
            case 9: cc.set(11,0,10); return cc;
            case 10: cc.set(10,0,11); return cc;
            case 11: cc.set(9,0,11); return cc;
            case 12: cc.set(8,0,11); return cc;
            case 13: cc.set(7,0,11); return cc;
            case 14: cc.set(6,0,11); return cc;
            case 15: cc.set(5,0,10); return cc;
            case 16: cc.set(5,0,9); return cc;
            case 17: cc.set(5,0,8); return cc;
            case 18: cc.set(5,0,7); return cc;
            case 19: cc.set(5,0,6); return cc;
            default: return cc;
        }
    }
    
    private int[] getRandomizedArray(int size, Random r)
    {
        int[] array = new int[size];
        
        for(int i = 0; i < size; i++)
        {
            array[i] = i; 
        }
        
        for(int i = 0; i < size; i++)
        {
            int temp = array[i];
            
            int index = r.nextInt(size);
            
            array[i] = array[index];
            
            array[index] = temp;
        }
        
        return array;
    }
}
