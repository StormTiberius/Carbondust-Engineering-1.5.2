/**
 *
 * @author StormTiberius
 */

package cde.ember;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManagerHell;

public class WorldChunkManagerEmber extends WorldChunkManagerHell
{
    private final List biomesToSpawnIn;
    
    public WorldChunkManagerEmber(BiomeGenBase biome, float temp, float rain)
    {
        super(biome, temp, rain);
        biomesToSpawnIn = new ArrayList();
        biomesToSpawnIn.add(biome);
    }
    
    @Override
    public List getBiomesToSpawnIn()
    {
        return biomesToSpawnIn;
    }
}
