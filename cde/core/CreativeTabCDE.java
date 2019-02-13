package cde.core;

import cde.api.Materials;
import cde.core.util.Utils;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class CreativeTabCDE extends CreativeTabs
{
    public CreativeTabCDE(String s)
    {
        super(s);
    }

    @Override
    public ItemStack getIconItemStack()
    {
        return Utils.getNewItemStackWithQuantity(Materials.dustCoal, 1);
    }
	
    @Override
    public String getTranslatedTabLabel()
    {
        return "Carbondust Engineering";
    }
}
