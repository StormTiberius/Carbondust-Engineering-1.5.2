/**
 *
 * @author StormTiberius
 */

package cde.machinery;

import buildcraft.api.transport.IPipeConnection;
import cde.MachineryCore;
import ic2.api.Direction;
import ic2.api.energy.tile.IEnergyConductor;
import ic2.api.energy.tile.IEnergySink;
import ic2.api.energy.tile.IEnergyTile;
import net.minecraft.block.Block;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.liquids.ILiquidTank;
import net.minecraftforge.liquids.ITankContainer;
import net.minecraftforge.liquids.LiquidContainerRegistry;
import net.minecraftforge.liquids.LiquidDictionary;
import net.minecraftforge.liquids.LiquidStack;
import net.minecraftforge.liquids.LiquidTank;

public class TileEntityPump extends TileEntityMachine implements IEnergySink, ITankContainer
{
    private int euBuffer;
    private final int BUFFER_SIZE = 25;
    private int side;
    private TileEntity te;
    private ITankContainer itc; 
    private final int TANK_SIZE = LiquidContainerRegistry.BUCKET_VOLUME;
    private final LiquidTank TANK;
    private final LiquidStack WATER;
    private int counter, flags;
    
    public TileEntityPump()
    {
        TANK = new LiquidTank(TANK_SIZE);
        WATER = LiquidDictionary.getLiquid("Water", TANK_SIZE);
    }
    
    @Override
    protected void doWorkCycle()
    {
        if(euBuffer > 0 && hasOutput() && hasWater())
        {
            int pumpUnit = TANK_SIZE / BUFFER_SIZE; // 40  
            int offer = pumpUnit * euBuffer; // Cant offer more
            int request = itc.fill(ForgeDirection.OPPOSITES[side], WATER, false); // Cant fit more
            int amountToPump = Math.min(offer, request);
            
            itc.fill(ForgeDirection.OPPOSITES[side], LiquidDictionary.getLiquid("Water", amountToPump), true);
            
            if(amountToPump > pumpUnit)
            {
                euBuffer -= amountToPump / pumpUnit;
            }
            else if(amountToPump > 0)
            {
                euBuffer -= 1;
            }
        }
    }
    
    private boolean hasOutput()
    {
        for(int i = 1; i < 6; i++)
        {
            ForgeDirection direction = ForgeDirection.getOrientation(i);
            
            te = worldObj.getBlockTileEntity(xCoord + direction.offsetX, yCoord + direction.offsetY, zCoord + direction.offsetZ);
            
            if(te instanceof ITankContainer)
            {
                itc = (ITankContainer)te;
                side = i;
                return true;
            }
        }
        
        return false;
    }
    
    private boolean hasWater()
    {
        if(counter >= 40 || flags != 9)
        {
            counter = 0;
            flags = 0;
            
            if(worldObj.getBlockId(xCoord, yCoord - 1, zCoord) == MachineryCore.grate.blockID)
            {
                flags++;
            }

            if(worldObj.getBlockId(xCoord + 1, yCoord - 1, zCoord + 1) == Block.waterStill.blockID)
            {
                flags++;
            }

            if(worldObj.getBlockId(xCoord - 1, yCoord - 1, zCoord - 1) == Block.waterStill.blockID)
            {
                flags++;
            }

            if(worldObj.getBlockId(xCoord + 1, yCoord - 1, zCoord - 1) == Block.waterStill.blockID)
            {
                flags++;
            }

            if(worldObj.getBlockId(xCoord - 1, yCoord - 1, zCoord + 1) == Block.waterStill.blockID)
            {
                flags++;
            }

            if(worldObj.getBlockId(xCoord + 1, yCoord - 1, zCoord) == Block.waterStill.blockID)
            {
                flags++;
            }

            if(worldObj.getBlockId(xCoord, yCoord - 1, zCoord + 1) == Block.waterStill.blockID)
            {
                flags++;
            }

            if(worldObj.getBlockId(xCoord - 1, yCoord - 1, zCoord) == Block.waterStill.blockID)
            {
                flags++;
            }

            if(worldObj.getBlockId(xCoord, yCoord - 1, zCoord - 1) == Block.waterStill.blockID)
            {
                flags++;
            }
        }
        
        counter++;

        return flags == 9;
    }
    
    @Override
    public String useWrench(boolean flag)
    {
        return "Pump by CDE";
    }
    
    @Override
    public void readFromNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.readFromNBT(par1NBTTagCompound);
        euBuffer = par1NBTTagCompound.getInteger("euBuffer");
        counter = par1NBTTagCompound.getInteger("counter");
        flags = par1NBTTagCompound.getInteger("flags");
    }

    @Override
    public void writeToNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.writeToNBT(par1NBTTagCompound);
        par1NBTTagCompound.setInteger("euBuffer", euBuffer);
        par1NBTTagCompound.setInteger("counter", counter);
        par1NBTTagCompound.setInteger("flags", flags);
    }
    
    @Override    
    protected boolean isConnected(ForgeDirection side)
    {
        tec = worldObj.getBlockTileEntity(xCoord + side.offsetX, yCoord + side.offsetY, zCoord + side.offsetZ);
            
        if(tec instanceof IPipeConnection)
        {
            return ((IPipeConnection)tec).isPipeConnected(side.getOpposite());
        }

        if(tec != null && tec.getClass().getCanonicalName().equalsIgnoreCase("buildcraft.factory.TileTank"))
        {
            return side.equals(ForgeDirection.UP);
        }        
        
        return tec instanceof IEnergyConductor;
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
    
    // ITankContainer methods
    @Override
    public int fill(ForgeDirection from, LiquidStack resource, boolean doFill)
    {
        return 0;
    }

    @Override
    public int fill(int tankIndex, LiquidStack resource, boolean doFill)
    {
        return 0;
    }

    @Override
    public LiquidStack drain(ForgeDirection from, int maxDrain, boolean doDrain)
    {
        return drain(0, maxDrain, doDrain);
    }

    @Override
    public LiquidStack drain(int tankIndex, int maxDrain, boolean doDrain)
    {
        if (tankIndex == 0)
        {
            return TANK.drain(maxDrain, doDrain);
        }
                    
        return null;
    }

    @Override
    public ILiquidTank[] getTanks(ForgeDirection direction) 
    {
        return new ILiquidTank[] { TANK };
    }

    @Override
    public ILiquidTank getTank(ForgeDirection direction, LiquidStack type)
    {
        return null;
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
        return "pumper.wav";
    }
    
    @Override
    public float getVolume()
    {
        return 1.0F / 100 * MachineryCore.pumpVolume;
    }
    
    @Override
    public float getPitch()
    {
        return 1.0F / 100 * MachineryCore.pumpPitch;
    }
}
