/**
 *
 * @author StormTiberius
 */

package cde.tropics;

import net.minecraft.block.Block;

public class BiomeGenTropicsOcean extends BiomeGenTropics
{
    public BiomeGenTropicsOcean(int id)
    {
        super(id);
        
        topBlock = (byte)Block.sand.blockID;
        fillerBlock = (byte)Block.sand.blockID;
        
        spawnableCreatureList.clear();
        
        theBiomeDecorator.treesPerChunk = -999;
    }
}
