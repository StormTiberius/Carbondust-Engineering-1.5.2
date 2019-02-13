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
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileEntityTransformer extends TileEntityMachine implements IEnergySink
{
    private int euBuffer;
    private final int BUFFER_SIZE = 4000;
    private final String BATTERY_BOX = "com.eloraam.redpower.machine.TileBatteryBox";
    private TileEntity te;
    private IInventory ii;
    private int batteryEmptyId,batteryFullId;
    
    public TileEntityTransformer()
    {
        batteryEmptyId = MachineryCore.getItemId(0);
        batteryFullId = MachineryCore.getItemId(1);
    }
        
    @Override
    protected void doWorkCycle()
    {
        if(euBuffer >= BUFFER_SIZE && isValid())
        {
            if(ii.getStackInSlot(1) != null && ii.getStackInSlot(1).itemID == batteryEmptyId)
            {
                ii.setInventorySlotContents(1, new ItemStack(batteryFullId, 1, 0));
                euBuffer -= BUFFER_SIZE;
            }
        }
    }
    
    private boolean isValid()
    {
        te = worldObj.getBlockTileEntity(xCoord, yCoord + 1, zCoord);
        
        if(te instanceof IInventory && te.getClass().getCanonicalName().equalsIgnoreCase(BATTERY_BOX))
        {
            ii = (IInventory)te;

            return true;
        }
        
        return false;
    }
    
    @Override
    public String useWrench(boolean flag)
    {
        return "Transformer by CDE";
    }
    
    @Override
    public void readFromNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.readFromNBT(par1NBTTagCompound);
        euBuffer = par1NBTTagCompound.getInteger("euBuffer");
        batteryEmptyId = par1NBTTagCompound.getInteger("batteryEmptyId");
        batteryFullId = par1NBTTagCompound.getInteger("batteryFullId");
    }

    @Override
    public void writeToNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.writeToNBT(par1NBTTagCompound);
        par1NBTTagCompound.setInteger("euBuffer", euBuffer);
        par1NBTTagCompound.setInteger("batteryEmptyId", batteryEmptyId);
        par1NBTTagCompound.setInteger("batteryFullId", batteryFullId);
    }
    
    @Override
    protected boolean isPowered()
    {
        return !isRedstonePowered;
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
        return "fluorescent.wav";
    }
    
    @Override
    public float getVolume()
    {
        return 1.0F / 100 * MachineryCore.transformerVolume;
    }
    
    @Override
    public float getPitch()
    {
        return 1.0F / 100 * MachineryCore.transformerPitch;
    }
}
