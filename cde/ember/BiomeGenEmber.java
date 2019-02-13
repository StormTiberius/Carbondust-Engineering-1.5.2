/**
 *
 * @author StormTiberius
 */

package cde.ember;

import cde.EmberCore;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.SpawnListEntry;

public class BiomeGenEmber extends BiomeGenBase
{
    public BiomeGenEmber(int id)
    {
        super(id);
        
        theBiomeDecorator = new BiomeDecoratorEmber(this);
        
        spawnableCreatureList.clear();
        spawnableWaterCreatureList.clear();
        field_82914_M.clear();
        
        spawnableWaterCreatureList.add(new SpawnListEntry(EntitySquidEmber.class, 10, 4, 4));
        field_82914_M.add(new SpawnListEntry(EntityBatEmber.class, 10, 8, 8));
    }
    
    @Override
    public void decorate(World par1World, Random par2Random, int par3, int par4)
    {
        super.decorate(par1World, par2Random, par3, par4);
        
        int var5 = 6 + par2Random.nextInt(11);
        int var6;
        int var7;
        int var8;

        for(var6 = 0; var6 < var5; ++var6)
        {
            var7 = par3 + par2Random.nextInt(16);
            var8 = par2Random.nextInt(56) + 8;
            int var9 = par4 + par2Random.nextInt(16);
            int var10 = par1World.getBlockId(var7, var8, var9);

            if(var10 == Block.stone.blockID)
            {
                par1World.setBlock(var7, var8, var9, Block.oreEmerald.blockID);
            }
        }
        
        liquidPopulate(par1World, par2Random, par3, par4);
    }
    
    public void liquidPopulate(World world, Random random, int xPos, int zPos)
    {
        boolean mediumDeposit = random.nextDouble() <= (0.15 / 100.0);
        boolean largeDeposit = random.nextDouble() <= (0.005 / 100.0);

        if(mediumDeposit || largeDeposit)
        {
            int yPos = 40 + random.nextInt(20);

            int r = 0;

            if(mediumDeposit)
            {
                r = 4 + random.nextInt(4);
            }
            else if(largeDeposit)
            {
                r = 8 + random.nextInt(9);
            }

            int b = r * r;

            for(int x = -r; x <= r; x++)
            {
                for(int y = -r; y <= r; y++)
                {
                    for(int z = -r; z <= r; z++)
                    {
                        int a = x * x + y * y + z * z;

                        if(a <= b)
                        {
                            world.setBlockWithNotify(xPos + x, yPos + y, zPos + z, EmberCore.getLiquidId());
                        }
                    }
                }
            }
        }
    }
}
