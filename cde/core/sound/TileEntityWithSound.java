/**
 *
 * @author StormTiberius
 */

package cde.core.sound;

import cde.CDECore;
import java.net.URL;
import net.minecraft.tileentity.TileEntity;
import paulscode.sound.SoundSystemConfig;

public abstract class TileEntityWithSound extends TileEntity
{
    protected abstract boolean isWorking();
    protected abstract String getSoundFileName();
    protected abstract float getVolume();
    protected abstract float getPitch();
    
    private boolean init,playing,active,flag;
    
    public boolean isPlaying()
    {
        return playing;
    }
        
    public void setPlaying(boolean flag)
    {
        playing = flag;
    }
    
    private void init()
    {
        if(worldObj.isRemote)
        {
            if(tileSoundsEnabled())
            {
                SoundHelper.addSource(this);
            }
        }
        
        init = true;
    }
    
    @Override
    public void updateEntity()
    {
        if(init)
        {   
            if(worldObj.isRemote)
            {
                if(tileSoundsEnabled())
                {
                    flag = isWorking();

                    if(active != flag)
                    {
                        active = flag;
                        SoundHelper.updateTileSound(this, active);
                    }
                }
            }
        }
        else
        {
            init();
        }
    }

    @Override
    public void invalidate()
    {
        if(worldObj.isRemote)
        {
            if(tileSoundsEnabled())
            {
                SoundHelper.removeSource(this);
            }
        }
                
        super.invalidate();
    }

    @Override
    public void onChunkUnload()
    {
        if(worldObj.isRemote)
        {
            if(tileSoundsEnabled())
            {
                SoundHelper.removeSource(this);
            }
        }
        
        super.onChunkUnload();
    }
    
    private boolean tileSoundsEnabled()
    {
        return CDECore.playSounds() && getVolume() > 0.0F;
    }
    
    protected String getSourceName()
    {
        return "cde_x" + xCoord + "_y" + yCoord + "_z" + zCoord;
    }
    
    protected URL getUrl()
    {
        return CDECore.class.getResource((new StringBuilder()).append("/cde/sounds/machine/").append(getSoundFileName()).toString());
    }
    
    protected int getAttModel()
    {
        return SoundSystemConfig.ATTENUATION_LINEAR;
    }
    
    protected float getDistOrRoll()
    {
        return 16.0F;
    }
    
    public SoundSource getSoundSource()
    {
        return new SoundSource(true, getSourceName(), getUrl(), getSoundFileName(), true, 0.5F + xCoord, 0.5F + yCoord, 0.5F + zCoord, getAttModel(), getDistOrRoll());
    }
}
