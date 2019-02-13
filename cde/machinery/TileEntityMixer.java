/**
 *
 * @author StormTiberius
 */

package cde.machinery;

import cde.MachineryCore;
import ic2.api.Direction;
import ic2.api.energy.tile.IEnergySink;
import ic2.api.energy.tile.IEnergyTile;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;

public class TileEntityMixer extends TileEntityMachine implements IEnergySink
{
    private int euBuffer;
    private final int BUFFER_SIZE = 4000;
    private int index,counter,indigoDyeId;
    private TileEntity te;
    private IInventory ii;  
    private ItemStack is;
    private ForgeDirection direction;
    
    public TileEntityMixer()
    {
        indigoDyeId = MachineryCore.getItemId(2);
    }
    
    @Override
    protected void doWorkCycle()
    {
        if(counter >= 20)
        {
            counter = 0;
            
            if(euBuffer >= BUFFER_SIZE && hasOutput())
            {
                is = ii.getStackInSlot(index);

                if(index != 4 || indigoDyeId == 0)
                {
                    if(is == null)
                    {
                        ii.setInventorySlotContents(index, new ItemStack(Item.dyePowder.itemID, 1, index));
                        euBuffer -= BUFFER_SIZE;
                    }
                    else if(is.itemID == Item.dyePowder.itemID && is.getItemDamage() == index && is.stackSize < is.getMaxStackSize())
                    {
                        is.stackSize++;
                        euBuffer -= BUFFER_SIZE;
                    }
                }
                else
                {
                    if(is == null)
                    {
                        ii.setInventorySlotContents(index, new ItemStack(indigoDyeId, 1, 0));
                        euBuffer -= BUFFER_SIZE;
                    }
                    else if(is.itemID == indigoDyeId && is.stackSize < is.getMaxStackSize())
                    {
                        is.stackSize++;
                        euBuffer -= BUFFER_SIZE;
                    }
                }
                
                index++;
                
                if(index >= 16)
                {
                    index = 0;
                }
            }
        }
        
        counter++;
    }
    
    private boolean hasOutput()
    {
        for(int i = 0; i < 6; i++)
        {
            direction = ForgeDirection.getOrientation(i);
            te = worldObj.getBlockTileEntity(xCoord + direction.offsetX, yCoord + direction.offsetY, zCoord + direction.offsetZ);

            if(te instanceof IInventory)
            {
                ii = (IInventory)te;
                
                return ii.getSizeInventory() >= 16;
            }
        }
        
        return false;
    }
    
    @Override
    public String useWrench(boolean flag)
    {
        return "Dye Mixer by CDE";
    }
    
    @Override
    public void readFromNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.readFromNBT(par1NBTTagCompound);
        euBuffer = par1NBTTagCompound.getInteger("euBuffer");
        counter = par1NBTTagCompound.getInteger("counter");
        index = par1NBTTagCompound.getInteger("index");
        indigoDyeId = par1NBTTagCompound.getInteger("indigoDyeId");
    }

    @Override
    public void writeToNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.writeToNBT(par1NBTTagCompound);
        par1NBTTagCompound.setInteger("euBuffer", euBuffer);
        par1NBTTagCompound.setInteger("counter", counter);
        par1NBTTagCompound.setInteger("index", index);
        par1NBTTagCompound.setInteger("indigoDyeId", indigoDyeId);
    }
    
    // IC2 Methods
    @Override
    public boolean acceptsEnergyFrom(TileEntity emitter, Direction direction)
    {
        return emitter instanceof IEnergyTile;
    }
    
    @Override
    public int demandsEnergy()
    {
        if(isPowered() && euBuffer < BUFFER_SIZE)
        {
            return BUFFER_SIZE - euBuffer;
        }
        
        return 0;
    }
    
    @Override
    public int injectEnergy(Direction directionFrom, int amount)
    {
        int uncharged = BUFFER_SIZE - euBuffer;
        
        if(amount > uncharged)
        {
            int excess = amount - uncharged;
            
            euBuffer += amount - excess;
            
            return excess;
        }
        
        euBuffer += amount;
        
        return 0;
    }

    @Override
    public int getMaxSafeInput()
    {
        return 2048;
    }
        
    // Ambient Sounds
    @Override
    public boolean isWorking()
    {
        return isPowered();
    }
    
    @Override
    public String getSoundFileName()
    {
        return "boomer.wav";
    }
    
    @Override
    public float getVolume()
    {
        return 1.0F / 100 * MachineryCore.mixerVolume;
    }
    
    @Override
    public float getPitch()
    {
        return 1.0F / 100 * MachineryCore.mixerPitch;
    }
}
