/**
 *
 * @author StormTiberius
 */

package cde.ember;

import cde.EmberCore;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.chunk.IChunkProvider;

public class WorldProviderEmber extends WorldProvider
{
    private static final ChunkCoordinates SPAWN = new ChunkCoordinates(264,20,264);
    
    @Override
    public String getDimensionName()
    {
        return "Ember";
    }
    
    @Override
    protected void registerWorldChunkManager()
    {
        worldChunkMgr = new WorldChunkManagerEmber(EmberCore.ember, 0.8F, 0.4F);
        dimensionId = EmberCore.getDimensionId();
        hasNoSky = true;
    }
    
    @Override
    public IChunkProvider createChunkGenerator()
    {
        return new ChunkProviderEmber(worldObj, worldObj.getSeed(), worldObj.getWorldInfo().isMapFeaturesEnabled());
    }
    
    @Override
    public boolean canCoordinateBeSpawn(int par1, int par2)
    {
        return true;
    }
    
    @Override
    public float calculateCelestialAngle(long par1, float par3)
    {
        return 0.5F;
    }
    
    @Override
    public boolean isSurfaceWorld()
    {
        return false;
    }
    
    @Override
    public int getAverageGroundLevel()
    {
        return 128;
    }
    
    @SideOnly(Side.CLIENT)
    @Override
    public boolean getWorldHasVoidParticles()
    {
        return false;
    }
    
    @SideOnly(Side.CLIENT)
    @Override
    public double getVoidFogYFactor()
    {
        return 1.0D;
    }
    
    @Override
    public ChunkCoordinates getRandomizedSpawnPoint()
    {
        return getSpawnPoint();
    }
    
    @Override
    public ChunkCoordinates getSpawnPoint()
    {
        return SPAWN;
    }
    
    @Override
    public void setSpawnPoint(int x, int y, int z)
    {
        worldObj.getWorldInfo().setSpawnPosition(SPAWN.posX, SPAWN.posY, SPAWN.posZ);
    }
    
    @Override
    public int getHeight()
    {
        return 256;
    }

    @Override
    public int getActualHeight()
    {
        return 256;
    }

    @Override
    public double getHorizon()
    {
        return 127.0D;
    }
}
