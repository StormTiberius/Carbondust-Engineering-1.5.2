/**
 *
 * @author StormTiberius
 */

package cde.machinery;

import cde.CDECore;
import cde.core.Defaults;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockGrate extends Block
{
    public BlockGrate(int id)
    {
        super(id, Material.iron);
    }
    
    @Override
    public int getBlockTextureFromSide(int side)
    {
        switch(side)
        {
            case 0: return Defaults.TEXTURE_MACHINE_GRATE_BOTTOM_TOP;
            case 1: return Defaults.TEXTURE_MACHINE_GRATE_BOTTOM_TOP;
            case 2: return Defaults.TEXTURE_MACHINE_GRATE_SIDE;
            case 3: return Defaults.TEXTURE_MACHINE_GRATE_SIDE;
            case 4: return Defaults.TEXTURE_MACHINE_GRATE_SIDE;
            case 5: return Defaults.TEXTURE_MACHINE_GRATE_SIDE;
            default: return 0;
        }
    }
    
    @Override
    public String getTextureFile()
    {             
        return CDECore.CDE_BLOCKS;
    }
}
