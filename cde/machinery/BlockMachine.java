/**
 *
 * @author StormTiberius
 */

package cde.machinery;

import buildcraft.api.tools.IToolWrench;
import cde.CDECore;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public abstract class BlockMachine extends BlockContainer
{   
    public BlockMachine(int par1)
    {
        super(par1, Material.iron);
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer entityplayer, int par6, float par7, float par8, float par9)
    {
        TileEntityMachine tem = (TileEntityMachine)world.getBlockTileEntity(x, y, z);

        if (entityplayer.isSneaking())
        {
            return false;
        }

        Item equipped = entityplayer.getCurrentEquippedItem() != null ? entityplayer.getCurrentEquippedItem().getItem() : null;
		
        if (equipped instanceof IToolWrench && ((IToolWrench) equipped).canWrench(entityplayer, x, y, z))
        {
            if(!world.isRemote && tem != null)
            {
                if(tem.isPowered())
                {
                    entityplayer.sendChatToPlayer(tem.useWrench(false));
                }
                else
                {
                    entityplayer.sendChatToPlayer(tem.useWrench(true));
                }
            }
            
            ((IToolWrench) equipped).wrenchUsed(entityplayer, x, y, z);
            
            return true;
        } 
        
        return false;
    }

    @Override
    public String getTextureFile()
    {             
        return CDECore.CDE_BLOCKS;
    }
}
