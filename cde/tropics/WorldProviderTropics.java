/**
 *
 * @author StormTiberius
 */

package cde.tropics;

import cde.TropicsCore;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.storage.WorldInfo;

public class WorldProviderTropics extends WorldProvider
{    
    private long time;
    
    @Override
    public String getDimensionName()
    {
        return "Tropics";
    }
    
    @Override
    protected void registerWorldChunkManager()
    {
        worldChunkMgr = new WorldChunkManagerTropics(worldObj);
        dimensionId = TropicsCore.getDimensionId();
    }
    
    @Override
    public IChunkProvider createChunkGenerator()
    {
        return new ChunkProviderTropics(worldObj, worldObj.getSeed(), worldObj.getWorldInfo().isMapFeaturesEnabled());
    }
    
    @Override
    public float calculateCelestialAngle(long par1, float par3)
    {
        int var4 = (int)(par1 % (24000L * TropicsCore.getDayCycleDurationMultiplier()));
        float var5 = ((float)var4 + par3) / (24000.0F * TropicsCore.getDayCycleDurationMultiplier()) - 0.25F;

        if (var5 < 0.0F)
        {
            ++var5;
        }

        if (var5 > 1.0F)
        {
            --var5;
        }

        float var6 = var5;
        var5 = 1.0F - (float)((Math.cos((double)var5 * Math.PI) + 1.0D) / 2.0D);
        var5 = var6 + (var5 - var6) / 3.0F;
        return var5;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public int getMoonPhase(long par1, float par3)
    {
        return (int)(par1 / (24000L * TropicsCore.getDayCycleDurationMultiplier())) % 8;
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
    public int getRespawnDimension(EntityPlayerMP player)
    {
        return dimensionId;
    }

    @Override
    public void updateWeather()
    {   
        if(worldObj.isRemote)
        {
            super.updateWeather();
        }
        else
        {
            WorldInfo worldInfo = worldObj.getWorldInfo();

            time = worldInfo.getWorldTime();

            if (!this.hasNoSky)
            {
                int var1 = worldInfo.getThunderTime();

                if (var1 <= 0)
                {
                    if (worldInfo.isThundering())
                    {
                        worldInfo.setThunderTime(worldObj.rand.nextInt(TropicsCore.getWeatherDuration(0)) + TropicsCore.getWeatherDuration(1));
                    }
                    else
                    {
                        worldInfo.setThunderTime(worldObj.rand.nextInt(TropicsCore.getWeatherDuration(2)) + TropicsCore.getWeatherDuration(3));
                    }
                }
                else
                {
                    --var1;
                    worldInfo.setThunderTime(var1);

                    if (var1 <= 0)
                    {
                        worldInfo.setThundering(!worldInfo.isThundering());
                    }
                }

                int var2 = worldInfo.getRainTime();

                if (var2 <= 0)
                {
                    if (worldInfo.isRaining())
                    {
                        worldInfo.setRainTime(worldObj.rand.nextInt(TropicsCore.getWeatherDuration(4)) + TropicsCore.getWeatherDuration(5));
                    }
                    else
                    {
                        worldInfo.setRainTime(worldObj.rand.nextInt(TropicsCore.getWeatherDuration(6)) + TropicsCore.getWeatherDuration(7));
                    }
                }
                else
                {
                    --var2;
                    worldInfo.setRainTime(var2);

                    if (var2 <= 0)
                    {
                        worldInfo.setRaining(!worldInfo.isRaining());
                    }
                }

                worldObj.prevRainingStrength = worldObj.rainingStrength;

                if (worldInfo.isRaining())
                {
                    worldObj.rainingStrength = (float)((double)worldObj.rainingStrength + 0.01D);
                }
                else
                {
                    worldObj.rainingStrength = (float)((double)worldObj.rainingStrength - 0.01D);
                }

                if (worldObj.rainingStrength < 0.0F)
                {
                    worldObj.rainingStrength = 0.0F;
                }

                if (worldObj.rainingStrength > 1.0F)
                {
                    worldObj.rainingStrength = 1.0F;
                }

                worldObj.prevThunderingStrength = worldObj.thunderingStrength;

                if (worldInfo.isThundering())
                {
                    worldObj.thunderingStrength = (float)((double)worldObj.thunderingStrength + 0.01D);
                }
                else
                {
                    worldObj.thunderingStrength = (float)((double)worldObj.thunderingStrength - 0.01D);
                }

                if (worldObj.thunderingStrength < 0.0F)
                {
                    worldObj.thunderingStrength = 0.0F;
                }

                if (worldObj.thunderingStrength > 1.0F)
                {
                    worldObj.thunderingStrength = 1.0F;
                }
            }
        }
    }
    
    @Override
    public void resetRainAndThunder()
    {
        if(!worldObj.isRemote)
        {
            WorldInfo worldInfo = worldObj.getWorldInfo();

            if(time != worldInfo.getWorldTime())
            {
                long var2 = time + (24000L * TropicsCore.getDayCycleDurationMultiplier());
                worldInfo.setWorldTime(var2 - var2 % (24000L * TropicsCore.getDayCycleDurationMultiplier()));
            }
        }
        
        super.resetRainAndThunder();
    }
}
