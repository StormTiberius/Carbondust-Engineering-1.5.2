/**
 *
 * @author StormTiberius
 */

package cde.machinery;

import cde.core.Defaults;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

public class BlockMachineAlpha extends BlockMachine
{   
    public BlockMachineAlpha(int par1)
    {
        super(par1);
    }
    
    @Override
    public int damageDropped(int i)
    {
        return i;
    }
    
    @Override
    public TileEntity createNewTileEntity(World var1)
    {
        return null;
    }
    
    @Override
    public TileEntity createNewTileEntity(World world, int metadata)
    {
        switch(metadata)
        {
            case 0: return new TileEntityGenerator();
            case 1: return new TileEntityTurbine();
            case 2: return new TileEntityHeater();
            case 3: return new TileEntityPump();
            case 4: return new TileEntityMixer();
            case 5: return new TileEntitySolarPanel();
            case 6: return new TileEntityTransformer();
            default: return null;
        }
    }
    
    @SideOnly(Side.CLIENT)
    @Override
    public int getBlockTexture(IBlockAccess par1IBlockAccess, int x, int y, int z, int side)
    {
        int meta = par1IBlockAccess.getBlockMetadata(x, y, z);
        
        switch(meta)
        {
            case 0: return getGeneratorTexture(par1IBlockAccess, x, y, z, side);
            case 1: return getTurbineTexture(par1IBlockAccess, x, y, z, side);
            case 2: return getHeaterTexture(par1IBlockAccess, x, y, z, side);
            case 3: return getPumpTexture(par1IBlockAccess, x, y, z, side);
            case 4: return getMixerTexture(par1IBlockAccess, x, y, z, side);
            case 5: return getSolarPanelTexture(par1IBlockAccess, x, y, z, side);
            case 6: return getTransformerTexture(par1IBlockAccess, x, y, z, side);
            default: return 0;
        }
    }
    
    @Override
    public int getBlockTextureFromSideAndMetadata(int side, int meta)
    {
        switch(meta)
        {
            case 0: return getGeneratorTextureFromSide(side);
            case 1: return getTurbineTextureFromSide(side);
            case 2: return getHeaterTextureFromSide(side);
            case 3: return getPumpTextureFromSide(side);
            case 4: return getMixerTextureFromSide(side);
            case 5: return getSolarPanelTextureFromSide(side);
            case 6: return getTransformerTextureFromSide(side);
            default: return 0;
        }
    }
    
    private int getGeneratorTextureFromSide(int side)
    {
        switch(side)
        {
            case 0: return Defaults.TEXTURE_MACHINE_BOTTOM;
            case 1: return Defaults.TEXTURE_MACHINE_SIDE;
            case 2: return Defaults.TEXTURE_MACHINE_GENERATOR_SIDE_OFF;
            case 3: return Defaults.TEXTURE_MACHINE_GENERATOR_SIDE_OFF;
            case 4: return Defaults.TEXTURE_MACHINE_GENERATOR_SIDE_OFF;
            case 5: return Defaults.TEXTURE_MACHINE_GENERATOR_SIDE_OFF;
            default: return 0;
        }
    }
    
    private int getTurbineTextureFromSide(int side)
    {
        switch(side)
        {
            case 0: return Defaults.TEXTURE_MACHINE_BOTTOM;
            case 1: return Defaults.TEXTURE_MACHINE_TURBINE_TOP_OFF;
            case 2: return Defaults.TEXTURE_MACHINE_TURBINE_SIDE;
            case 3: return Defaults.TEXTURE_MACHINE_TURBINE_SIDE;
            case 4: return Defaults.TEXTURE_MACHINE_TURBINE_SIDE;
            case 5: return Defaults.TEXTURE_MACHINE_TURBINE_SIDE;
            default: return 0;
        }
    }
    
    private int getHeaterTextureFromSide(int side)
    {
        switch(side)
        {
            case 0: return Defaults.TEXTURE_MACHINE_BOTTOM;
            case 1: return Defaults.TEXTURE_MACHINE_HEATER_TOP;
            case 2: return Defaults.TEXTURE_MACHINE_SIDE;
            case 3: return Defaults.TEXTURE_MACHINE_SIDE;
            case 4: return Defaults.TEXTURE_MACHINE_SIDE;
            case 5: return Defaults.TEXTURE_MACHINE_SIDE;
            default: return 0;
        }
    }
    
    private int getPumpTextureFromSide(int side)
    {
        switch(side)
        {
            case 0: return Defaults.TEXTURE_MACHINE_BOTTOM;
            case 1: return Defaults.TEXTURE_MACHINE_SIDE;
            case 2: return Defaults.TEXTURE_MACHINE_PUMP_SIDE_OFF;
            case 3: return Defaults.TEXTURE_MACHINE_PUMP_SIDE_OFF;
            case 4: return Defaults.TEXTURE_MACHINE_PUMP_SIDE_OFF;
            case 5: return Defaults.TEXTURE_MACHINE_PUMP_SIDE_OFF;
            default: return 0;
        }
    }
    
    private int getMixerTextureFromSide(int side)
    {
        switch(side)
        {
            case 0: return Defaults.TEXTURE_MACHINE_BOTTOM;
            case 1: return Defaults.TEXTURE_MACHINE_SIDE;
            case 2: return Defaults.TEXTURE_MACHINE_MIXER_SIDE_OFF;
            case 3: return Defaults.TEXTURE_MACHINE_MIXER_SIDE_OFF;
            case 4: return Defaults.TEXTURE_MACHINE_MIXER_SIDE_OFF;
            case 5: return Defaults.TEXTURE_MACHINE_MIXER_SIDE_OFF;
            default: return 0;
        }
    }
    
    private int getSolarPanelTextureFromSide(int side)
    {
        switch(side)
        {
            case 0: return Defaults.TEXTURE_MACHINE_BOTTOM;
            case 1: return Defaults.TEXTURE_MACHINE_SOLAR_PANEL_TOP;
            case 2: return Defaults.TEXTURE_MACHINE_SIDE;
            case 3: return Defaults.TEXTURE_MACHINE_SIDE;
            case 4: return Defaults.TEXTURE_MACHINE_SIDE;
            case 5: return Defaults.TEXTURE_MACHINE_SIDE;
            default: return 0;
        }
    }
    
    private int getTransformerTextureFromSide(int side)
    {
        switch(side)
        {
            case 0: return Defaults.TEXTURE_MACHINE_BOTTOM;
            case 1: return Defaults.TEXTURE_MACHINE_TRANSFORMER_TOP_OFF;
            case 2: return Defaults.TEXTURE_MACHINE_SIDE;
            case 3: return Defaults.TEXTURE_MACHINE_SIDE;
            case 4: return Defaults.TEXTURE_MACHINE_SIDE;
            case 5: return Defaults.TEXTURE_MACHINE_SIDE;
            default: return 0;
        }
    }
    
    private int getGeneratorTexture(IBlockAccess par1IBlockAccess, int x, int y, int z, int side)
    {
        TileEntityMachine tem = (TileEntityMachine)par1IBlockAccess.getBlockTileEntity(x, y, z);
        
        if(tem != null)
        {
            if(tem.isConnected(ForgeDirection.getOrientation(side)))
            {
                return Defaults.TEXTURE_MACHINE_CONNECTED;
            }
            
            if(tem.isPowered())
            {
                switch(side)
                {
                    case 0: return Defaults.TEXTURE_MACHINE_BOTTOM;
                    case 1: return Defaults.TEXTURE_MACHINE_SIDE;
                    case 2: return Defaults.TEXTURE_MACHINE_GENERATOR_SIDE_ON;
                    case 3: return Defaults.TEXTURE_MACHINE_GENERATOR_SIDE_ON;
                    case 4: return Defaults.TEXTURE_MACHINE_GENERATOR_SIDE_ON;
                    case 5: return Defaults.TEXTURE_MACHINE_GENERATOR_SIDE_ON;
                    default: return 0;
                }
            }
        }
        
        return getGeneratorTextureFromSide(side);
    }
    
    private int getTurbineTexture(IBlockAccess par1IBlockAccess, int x, int y, int z, int side)
    {
        TileEntityMachine tem = (TileEntityMachine)par1IBlockAccess.getBlockTileEntity(x, y, z);
        
        if(tem != null)
        {
            if(tem.isConnected(ForgeDirection.getOrientation(side)))
            {
                return Defaults.TEXTURE_MACHINE_CONNECTED;
            }
            
            if(tem.isPowered())
            {
                switch(side)
                {
                    case 0: return Defaults.TEXTURE_MACHINE_BOTTOM;
                    case 1: return Defaults.TEXTURE_MACHINE_TURBINE_TOP_ON;
                    case 2: return Defaults.TEXTURE_MACHINE_TURBINE_SIDE;
                    case 3: return Defaults.TEXTURE_MACHINE_TURBINE_SIDE;
                    case 4: return Defaults.TEXTURE_MACHINE_TURBINE_SIDE;
                    case 5: return Defaults.TEXTURE_MACHINE_TURBINE_SIDE;
                    default: return 0;
                }
            }
        }
        
        return getTurbineTextureFromSide(side);
    }
    
    private int getHeaterTexture(IBlockAccess par1IBlockAccess, int x, int y, int z, int side)
    {
        TileEntityMachine tem = (TileEntityMachine)par1IBlockAccess.getBlockTileEntity(x, y, z);
        
        if(tem != null)
        {
            if(tem.isConnected(ForgeDirection.getOrientation(side)))
            {
                return Defaults.TEXTURE_MACHINE_CONNECTED;
            }
            
            if(tem.isPowered())
            {
                switch(side)
                {
                    case 0: return Defaults.TEXTURE_MACHINE_BOTTOM;
                    case 1: return Defaults.TEXTURE_MACHINE_HEATER_TOP;
                    case 2: return Defaults.TEXTURE_MACHINE_SIDE;
                    case 3: return Defaults.TEXTURE_MACHINE_SIDE;
                    case 4: return Defaults.TEXTURE_MACHINE_SIDE;
                    case 5: return Defaults.TEXTURE_MACHINE_SIDE;
                    default: return 0;
                }
            }
        }
        
        return getHeaterTextureFromSide(side);
    }
    
    private int getPumpTexture(IBlockAccess par1IBlockAccess, int x, int y, int z, int side)
    {
        TileEntityMachine tem = (TileEntityMachine)par1IBlockAccess.getBlockTileEntity(x, y, z);
        
        if(tem != null)
        {
            if(tem.isConnected(ForgeDirection.getOrientation(side)))
            {
                return Defaults.TEXTURE_MACHINE_CONNECTED;
            }
            
            if(tem.isPowered())
            {
                switch(side)
                {
                    case 0: return Defaults.TEXTURE_MACHINE_BOTTOM;
                    case 1: return Defaults.TEXTURE_MACHINE_SIDE;
                    case 2: return Defaults.TEXTURE_MACHINE_PUMP_SIDE_ON;
                    case 3: return Defaults.TEXTURE_MACHINE_PUMP_SIDE_ON;
                    case 4: return Defaults.TEXTURE_MACHINE_PUMP_SIDE_ON;
                    case 5: return Defaults.TEXTURE_MACHINE_PUMP_SIDE_ON;
                    default: return 0;
                }
            }
        }
        
        return getPumpTextureFromSide(side);
    }
    
    private int getMixerTexture(IBlockAccess par1IBlockAccess, int x, int y, int z, int side)
    {
        TileEntityMachine tem = (TileEntityMachine)par1IBlockAccess.getBlockTileEntity(x, y, z);
        
        if(tem != null)
        {
            if(tem.isConnected(ForgeDirection.getOrientation(side)))
            {
                return Defaults.TEXTURE_MACHINE_CONNECTED;
            }
            
            if(tem.isPowered())
            {
                switch(side)
                {
                    case 0: return Defaults.TEXTURE_MACHINE_BOTTOM;
                    case 1: return Defaults.TEXTURE_MACHINE_SIDE;
                    case 2: return Defaults.TEXTURE_MACHINE_MIXER_SIDE_ON;
                    case 3: return Defaults.TEXTURE_MACHINE_MIXER_SIDE_ON;
                    case 4: return Defaults.TEXTURE_MACHINE_MIXER_SIDE_ON;
                    case 5: return Defaults.TEXTURE_MACHINE_MIXER_SIDE_ON;
                    default: return 0;
                }
            }
        }
        
        return getMixerTextureFromSide(side);
    }
    
    private int getSolarPanelTexture(IBlockAccess par1IBlockAccess, int x, int y, int z, int side)
    {
        TileEntityMachine tem = (TileEntityMachine)par1IBlockAccess.getBlockTileEntity(x, y, z);
        
        if(tem != null)
        {
            if(!ForgeDirection.getOrientation(side).equals(ForgeDirection.UP) && tem.isConnected(ForgeDirection.getOrientation(side)))
            {
                return Defaults.TEXTURE_MACHINE_CONNECTED;
            }
            
            if(tem.isPowered())
            {
                switch(side)
                {
                    case 0: return Defaults.TEXTURE_MACHINE_BOTTOM;
                    case 1: return Defaults.TEXTURE_MACHINE_SOLAR_PANEL_TOP;
                    case 2: return Defaults.TEXTURE_MACHINE_SIDE;
                    case 3: return Defaults.TEXTURE_MACHINE_SIDE;
                    case 4: return Defaults.TEXTURE_MACHINE_SIDE;
                    case 5: return Defaults.TEXTURE_MACHINE_SIDE;
                    default: return 0;
                }
            }
        }
        
        return getSolarPanelTextureFromSide(side);
    }
    
    private int getTransformerTexture(IBlockAccess par1IBlockAccess, int x, int y, int z, int side)
    {
        TileEntityMachine tem = (TileEntityMachine)par1IBlockAccess.getBlockTileEntity(x, y, z);
        
        if(tem != null)
        {
            if(tem.isConnected(ForgeDirection.getOrientation(side)))
            {
                return Defaults.TEXTURE_MACHINE_CONNECTED;
            }
            
            if(tem.isPowered())
            {
                switch(side)
                {
                    case 0: return Defaults.TEXTURE_MACHINE_BOTTOM;
                    case 1: return Defaults.TEXTURE_MACHINE_TRANSFORMER_TOP_ON;
                    case 2: return Defaults.TEXTURE_MACHINE_SIDE;
                    case 3: return Defaults.TEXTURE_MACHINE_SIDE;
                    case 4: return Defaults.TEXTURE_MACHINE_SIDE;
                    case 5: return Defaults.TEXTURE_MACHINE_SIDE;
                    default: return 0;
                }
            }
        }
        
        return getTransformerTextureFromSide(side);
    }
}
