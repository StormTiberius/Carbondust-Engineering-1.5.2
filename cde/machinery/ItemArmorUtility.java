/**
 *
 * @author StormTiberius
 */

package cde.machinery;

import cde.CDECore;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraftforge.common.ISpecialArmor;

public class ItemArmorUtility extends ItemArmor implements ISpecialArmor
{
    public ItemArmorUtility(int itemId, int spriteIndex, int renderId, int equipmentSlot)
    {
        super(itemId, EnumArmorMaterial.DIAMOND, renderId, equipmentSlot);
        iconIndex = spriteIndex;
    }

    @Override
    public int getItemEnchantability()
    {
        return 0;
    }
    
    @Override
    public boolean isRepairable()
    {
        return false;
    }
    
    @Override
    public boolean getIsRepairable(ItemStack par1ItemStack, ItemStack par2ItemStack)
    {
        return false;
    }
    
    @Override
    public String getTextureFile()
    {
        return CDECore.CDE_ITEMS;
    }
    
    @Override
    public ArmorProperties getProperties(EntityLiving player, ItemStack armor, DamageSource source, double damage, int slot)
    {
        return new ArmorProperties(0, 0.0D, 0);
    }
    
    @Override
    public int getArmorDisplay(EntityPlayer player, ItemStack armor, int slot)
    {
        return 0;
    }
    
    @Override
    public void damageArmor(EntityLiving entity, ItemStack stack, DamageSource source, int damage, int slot)
    {
        
    }
}
