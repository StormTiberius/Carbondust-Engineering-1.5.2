/**
 *
 * @author StormTiberius
 */

package cde.core.util;

import net.minecraft.item.ItemStack;

public class Utils
{
    public static ItemStack getNewItemStackWithQuantity(ItemStack is, int qty)
    {
        return new ItemStack(is.itemID, qty, is.getItemDamage());
    }
}
