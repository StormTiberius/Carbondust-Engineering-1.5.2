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

public class TileEntityHeater extends TileEntityMachine implements IEnergySink
{
    private int euBuffer,counter;
    private final int BUFFER_SIZE = 4000;
    private final String BLAST_FURNACE = "railcraft.common.blocks.machine.alpha.TileBlastFurnace";
    private TileEntity te;
    private IInventory ii;
    
    @Override
    protected void doWorkCycle()
    {
        if(counter >= 20)
        {
            counter = 0;
            
            if(euBuffer >= BUFFER_SIZE && isValid())
            {
                if(ii.getStackInSlot(1) == null)
                {
                    ii.setInventorySlotContents(1, new ItemStack(Item.coal.itemID, 1, 1));
                    euBuffer -= BUFFER_SIZE;
                }
            }
        }
        
        counter++;
    }
    
    private boolean isValid()
    {
        te = worldObj.getBlockTileEntity(xCoord, yCoord + 1, zCoord);
        
        if(te instanceof IInventory && te.getClass().getCanonicalName().equalsIgnoreCase(BLAST_FURNACE))
        {
            ii = (IInventory)te;

            return true;
        }
        
        return false;
    }
    
    @Override
    public String useWrench(boolean flag)
    {
        return "Heater by CDE";
    }
    
    @Override
    public void readFromNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.readFromNBT(par1NBTTagCompound);
        euBuffer = par1NBTTagCompound.getInteger("euBuffer");
        counter = par1NBTTagCompound.getInteger("counter");
    }

    @Override
    public void writeToNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.writeToNBT(par1NBTTagCompound);
        par1NBTTagCompound.setInteger("euBuffer", euBuffer);
        par1NBTTagCompound.setInteger("counter", counter);
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
        return "burning3.wav";
    }
    
    @Override
    public float getVolume()
    {
        return 1.0F / 100 * MachineryCore.heaterVolume;
    }
    
    @Override
    public float getPitch()
    {
        return 1.0F / 100 * MachineryCore.heaterPitch;
    }
}
