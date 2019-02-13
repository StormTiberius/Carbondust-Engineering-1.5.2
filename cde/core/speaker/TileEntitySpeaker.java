/**
 *
 * @author StormTiberius
 */

package cde.core.speaker;

import cde.core.sound.SoundHelper;
import cde.core.sound.TileEntityWithSound;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;

public class TileEntitySpeaker extends TileEntityWithSound
{
    private int index;
    
    protected String getSound()
    {
        return index + 1 + ". " + SpeakerModule.sounds[index]; 
    }
    
    protected String setSound()
    {
        worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
        
        return index + 1 + ". " + SpeakerModule.sounds[index];
    }
    
    protected String nextSound()
    {
        if(index >= SpeakerModule.sounds.length - 1 || index >= SpeakerModule.volumes.length - 1 || index >= SpeakerModule.pitchs.length - 1)
        {
            index = 0;
        }
        else
        {
            index++;
        }

        return index + 1 + ". " + SpeakerModule.sounds[index];
    }
    
    @Override
    public void onDataPacket(INetworkManager net, Packet132TileEntityData pkt)
    {
        if(worldObj.isRemote)
        {
            int i = index;
            
            readFromNBT(pkt.customParam1);
            
            if(i != index)
            {
                SoundHelper.removeSource(this);
                SoundHelper.addSource(this);
            }
        }
    }
    
    @Override
    public Packet getDescriptionPacket()
    {
        NBTTagCompound var1 = new NBTTagCompound();
        writeToNBT(var1);
        return new Packet132TileEntityData(xCoord, yCoord, zCoord, 4, var1);
    }
    
    @Override
    public void readFromNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.readFromNBT(par1NBTTagCompound);
        
        int i = par1NBTTagCompound.getInteger("index");
        
        if(i >= SpeakerModule.sounds.length || i >= SpeakerModule.volumes.length || i >= SpeakerModule.pitchs.length)
        {
            index = 0;
        }
        else
        {
            index = i;
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.writeToNBT(par1NBTTagCompound);
        par1NBTTagCompound.setInteger("index", index);
    }
    
    @Override
    public boolean isWorking()
    {
        return worldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord);
    }
    
    @Override
    public String getSoundFileName()
    {   
        return SpeakerModule.sounds[index];
    }
    
    @Override
    public float getVolume()
    {   
        return 1.0F / 100 * SpeakerModule.volumes[index];
    }
    
    @Override
    public float getPitch()
    {        
        return 1.0F / 100 * SpeakerModule.pitchs[index];
    }
}
