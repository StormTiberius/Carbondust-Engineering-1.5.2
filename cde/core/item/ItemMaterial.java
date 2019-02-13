/**
 *
 * @author StormTiberius
 */

package cde.core.item;

import cde.CDECore;
import cde.core.Defaults;
import cde.core.Namings;
import java.util.List;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemMaterial extends Item
{
    public ItemMaterial(int id)
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
        if(is.getItemDamage() > Namings.INTERNAL_PART_ITEM_NAMES.length - 1)
        {
            return "NONAME";
        }
        
        return Namings.INTERNAL_PART_ITEM_NAMES[is.getItemDamage()];
    }

    @Override
    public void getSubItems(int par1, CreativeTabs par2CreativeTabs, List par3List)
    {
        for (int i = 0; i < Namings.INTERNAL_PART_ITEM_NAMES.length; i++)
        {   
            par3List.add(new ItemStack(par1, 1, i));
        }
    }
    
    @Override
    public int getIconFromDamage(int i)
    {
        switch(i)
        {
            case 0: return Defaults.TEXTURE_MATERIAL_INGOT_COPPER;
            case 1: return Defaults.TEXTURE_MATERIAL_INGOT_TIN;
            case 2: return Defaults.TEXTURE_MATERIAL_INGOT_SILVER;
            case 3: return Defaults.TEXTURE_MATERIAL_INGOT_LEAD;
            case 4: return Defaults.TEXTURE_MATERIAL_INGOT_URANIUM;
            case 5: return Defaults.TEXTURE_MATERIAL_INGOT_ZINC;
            case 6: return Defaults.TEXTURE_MATERIAL_INGOT_BRONZE;
            case 7: return Defaults.TEXTURE_MATERIAL_INGOT_BRASS;
            case 8: return Defaults.TEXTURE_MATERIAL_INGOT_STEEL;
            case 9: return Defaults.TEXTURE_MATERIAL_INGOT_PEAT;
            case 10: return Defaults.TEXTURE_MATERIAL_INGOT_BITUMINOUS_PEAT;
            case 11: return Defaults.TEXTURE_MATERIAL_DUST_COAL;
            case 12: return Defaults.TEXTURE_MATERIAL_DUST_CHARCOAL;
            case 13: return Defaults.TEXTURE_MATERIAL_DUST_IRON;
            case 14: return Defaults.TEXTURE_MATERIAL_DUST_GOLD;
            case 15: return Defaults.TEXTURE_MATERIAL_DUST_COPPER;
            case 16: return Defaults.TEXTURE_MATERIAL_DUST_TIN;
            case 17: return Defaults.TEXTURE_MATERIAL_DUST_SILVER;
            case 18: return Defaults.TEXTURE_MATERIAL_DUST_LEAD;
            case 19: return Defaults.TEXTURE_MATERIAL_DUST_ZINC;
            case 20: return Defaults.TEXTURE_MATERIAL_DUST_BRONZE;
            case 21: return Defaults.TEXTURE_MATERIAL_DUST_BRASS;
            case 22: return Defaults.TEXTURE_MATERIAL_DUST_SULFUR;
            case 23: return Defaults.TEXTURE_MATERIAL_DUST_SALTPETER;
            case 24: return Defaults.TEXTURE_MATERIAL_DUST_QUARTZ;
            case 25: return Defaults.TEXTURE_MATERIAL_DUST_APATITE;
            case 26: return Defaults.TEXTURE_MATERIAL_DUST_YELLOWCAKE;
            case 27: return Defaults.TEXTURE_MATERIAL_NUGGET_IRON;
            case 28: return Defaults.TEXTURE_MATERIAL_NUGGET_COPPER;
            case 29: return Defaults.TEXTURE_MATERIAL_NUGGET_TIN;
            case 30: return Defaults.TEXTURE_MATERIAL_NUGGET_SILVER;
            case 31: return Defaults.TEXTURE_MATERIAL_NUGGET_LEAD;
            case 32: return Defaults.TEXTURE_MATERIAL_NUGGET_ZINC;
            case 33: return Defaults.TEXTURE_MATERIAL_NUGGET_BRONZE;
            case 34: return Defaults.TEXTURE_MATERIAL_NUGGET_BRASS;
            case 35: return Defaults.TEXTURE_MATERIAL_NUGGET_STEEL;
            case 36: return Defaults.TEXTURE_MATERIAL_WASHED_IRON;
            case 37: return Defaults.TEXTURE_MATERIAL_WASHED_GOLD;
            case 38: return Defaults.TEXTURE_MATERIAL_WASHED_COPPER;
            case 39: return Defaults.TEXTURE_MATERIAL_WASHED_TIN;
            case 40: return Defaults.TEXTURE_MATERIAL_WASHED_SILVER;
            case 41: return Defaults.TEXTURE_MATERIAL_WASHED_LEAD;
            case 42: return Defaults.TEXTURE_MATERIAL_WASHED_URANIUM;
            case 43: return Defaults.TEXTURE_MATERIAL_CRUSHED_IRON;
            case 44: return Defaults.TEXTURE_MATERIAL_CRUSHED_GOLD;
            case 45: return Defaults.TEXTURE_MATERIAL_CRUSHED_COPPER;
            case 46: return Defaults.TEXTURE_MATERIAL_CRUSHED_TIN;
            case 47: return Defaults.TEXTURE_MATERIAL_CRUSHED_SILVER;
            case 48: return Defaults.TEXTURE_MATERIAL_CRUSHED_LEAD;
            case 49: return Defaults.TEXTURE_MATERIAL_CRUSHED_URANIUM;
            case 50: return Defaults.TEXTURE_MATERIAL_GEM_QUARTZ;
            case 51: return Defaults.TEXTURE_MATERIAL_GEM_APATITE;
            case 52: return Defaults.TEXTURE_MATERIAL_GEM_RUBY;
            case 53: return Defaults.TEXTURE_MATERIAL_GEM_JADE;
            case 54: return Defaults.TEXTURE_MATERIAL_GEM_SAPPHIRE;
            case 55: return Defaults.TEXTURE_MATERIAL_FUEL_COKE;
            default: return 0;
        }
    }
    
    @Override
    public String getTextureFile()
    {
        return CDECore.CDE_ITEMS;
    }
}
