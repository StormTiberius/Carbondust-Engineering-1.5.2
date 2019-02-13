/**
 *
 * @author StormTiberius
 */

package cde.core.item;

import cde.core.Namings;
import java.util.List;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemStorage extends ItemBlock
{    
    public ItemStorage(int id)
    {
        super(id);
    }
    
    @Override
    public int getMaxDamage()
    {
        return 0;
    }
    
    @Override
    public boolean getHasSubtypes()
    {
        return true;
    }
    
    @Override
    public int getMetadata(int meta)
    {
        return meta;
    }

    @Override
    public String getItemNameIS(ItemStack is)
    {
        if(is.getItemDamage() > Namings.INTERNAL_STORAGE_BLOCK_NAMES.length - 1)
        {
            return "NONAME";
        }
        
        return Namings.INTERNAL_STORAGE_BLOCK_NAMES[is.getItemDamage()];
    }

    @Override
    public void getSubItems(int par1, CreativeTabs par2CreativeTabs, List par3List)
    {
        for (int i = 0; i < Namings.INTERNAL_STORAGE_BLOCK_NAMES.length; i++)
        {   
            par3List.add(new ItemStack(par1, 1, i));
        }
    }
}
