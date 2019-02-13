/**
 *
 * @author StormTiberius
 */

package cde.ember;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenLiquids;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;
import static net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.LAKE;
import static net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.SHROOM;
import net.minecraftforge.event.terraingen.OreGenEvent;
import static net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable.EventType.COAL;
import static net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable.EventType.DIAMOND;
import static net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable.EventType.DIRT;
import static net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable.EventType.GOLD;
import static net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable.EventType.GRAVEL;
import static net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable.EventType.IRON;
import static net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable.EventType.LAPIS;
import static net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable.EventType.REDSTONE;
import net.minecraftforge.event.terraingen.TerrainGen;

public class BiomeDecoratorEmber extends BiomeDecorator
{
    public BiomeDecoratorEmber(BiomeGenBase biome)
    {
        super(biome);
    }
    
    @Override
    protected void decorate()
    {
        MinecraftForge.EVENT_BUS.post(new DecorateBiomeEvent.Pre(currentWorld, randomGenerator, chunk_X, chunk_Z));
        
        generateOres();

        int var2,var3,var4,var7;

        boolean doGen = TerrainGen.decorate(currentWorld, randomGenerator, chunk_X, chunk_Z, SHROOM);
        if(doGen && randomGenerator.nextInt(2) == 0)
        {
            var2 = chunk_X + randomGenerator.nextInt(16) + 8;
            var3 = randomGenerator.nextInt(256);
            var4 = chunk_Z + randomGenerator.nextInt(16) + 8;

            mushroomBrownGen.generate(currentWorld, randomGenerator, var2, var3, var4);
        }

        if(doGen && randomGenerator.nextInt(4) == 0)
        {
            var2 = chunk_X + randomGenerator.nextInt(16) + 8;
            var3 = randomGenerator.nextInt(256);
            var4 = chunk_Z + randomGenerator.nextInt(16) + 8;

            mushroomRedGen.generate(currentWorld, randomGenerator, var2, var3, var4);
        }

        doGen = TerrainGen.decorate(currentWorld, randomGenerator, chunk_X, chunk_Z, LAKE);
        if(doGen && generateLakes)
        {
            for(var2 = 0; var2 < 100; ++var2)
            {
                var3 = chunk_X + randomGenerator.nextInt(16) + 8;
                var4 = randomGenerator.nextInt(randomGenerator.nextInt(248) + 8);
                var7 = chunk_Z + randomGenerator.nextInt(16) + 8;
                
                (new WorldGenLiquids(Block.waterMoving.blockID)).generate(currentWorld, randomGenerator, var3, var4, var7);
            }

            for(var2 = 0; var2 < 40; ++var2)
            {
                var3 = chunk_X + randomGenerator.nextInt(16) + 8;
                var4 = randomGenerator.nextInt(randomGenerator.nextInt(randomGenerator.nextInt(240) + 8) + 8);
                var7 = chunk_Z + randomGenerator.nextInt(16) + 8;
                
                (new WorldGenLiquids(Block.lavaMoving.blockID)).generate(currentWorld, randomGenerator, var3, var4, var7);
            }
        }

        MinecraftForge.EVENT_BUS.post(new DecorateBiomeEvent.Post(currentWorld, randomGenerator, chunk_X, chunk_Z));
    }
    
    @Override
    protected void generateOres()
    {
        MinecraftForge.ORE_GEN_BUS.post(new OreGenEvent.Pre(currentWorld, randomGenerator, chunk_X, chunk_Z));
        
        if(TerrainGen.generateOre(currentWorld, randomGenerator, dirtGen, chunk_X, chunk_Z, DIRT))
        {
            genStandardOre1(40, dirtGen, 0, 256);
        }
        
        if(TerrainGen.generateOre(currentWorld, randomGenerator, gravelGen, chunk_X, chunk_Z, GRAVEL))
        {
            genStandardOre1(20, gravelGen, 0, 256);
        }
        
        if(TerrainGen.generateOre(currentWorld, randomGenerator, coalGen, chunk_X, chunk_Z, COAL))
        {
            genStandardOre1(40, coalGen, 0, 256);
        }
        
        if(TerrainGen.generateOre(currentWorld, randomGenerator, ironGen, chunk_X, chunk_Z, IRON))
        {
            genStandardOre1(40, ironGen, 0, 128);
        }
        
        if(TerrainGen.generateOre(currentWorld, randomGenerator, goldGen, chunk_X, chunk_Z, GOLD))
        {
            genStandardOre1(4, goldGen, 0, 64);
        }
        
        if(TerrainGen.generateOre(currentWorld, randomGenerator, redstoneGen, chunk_X, chunk_Z, REDSTONE))
        {
            genStandardOre1(16, redstoneGen, 0, 32);
        }
        
        if(TerrainGen.generateOre(currentWorld, randomGenerator, diamondGen, chunk_X, chunk_Z, DIAMOND))
        {
            genStandardOre1(2, diamondGen, 0, 32);
        }
        
        if(TerrainGen.generateOre(currentWorld, randomGenerator, lapisGen, chunk_X, chunk_Z, LAPIS))
        {
            genStandardOre2(2, lapisGen, 32, 32);
        }
        
        MinecraftForge.ORE_GEN_BUS.post(new OreGenEvent.Post(currentWorld, randomGenerator, chunk_X, chunk_Z));
    }
}
