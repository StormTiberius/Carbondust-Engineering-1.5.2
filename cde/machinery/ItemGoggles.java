/**
 *
 * @author StormTiberius
 */

package cde.machinery;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ic2.api.ElectricItem;
import ic2.api.IElectricItem;
import java.util.List;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemGoggles extends ItemArmorUtility implements IElectricItem
{
    private final String TEXTURE_FILE;
    private int counter = 240;
    
    public ItemGoggles(int itemId, int spriteIndex, int renderId, String textureFile)
    {
        super(itemId, spriteIndex, renderId, 0);
        TEXTURE_FILE = textureFile;
    }
    
    @Override
    public void onArmorTickUpdate(World world, EntityPlayer player, ItemStack itemStack)
    {
        if(!world.isRemote)
        {
            if(counter > 240)
            {
                counter--;
            }
            
            ElectricItem.chargeFromArmor(itemStack, player);

            if(ElectricItem.discharge(itemStack, 1, 1, true, false) >= 1)
            {
                if(counter == 240)
                {
                    counter = 259;
                    player.addPotionEffect(new PotionEffect(Potion.nightVision.id, counter, 0, true));     
                }
            }
        }
    }
    
    @Override
    public String getTextureFile()
    {
        return TEXTURE_FILE;
    }   
    
    @SideOnly(Side.CLIENT)
    @Override
    public void getSubItems(int par1, CreativeTabs par2CreativeTabs, List par3List)
    {
            ItemStack charged = new ItemStack(this, 1);
            ElectricItem.charge(charged, 0x7fffffff, 0x7fffffff, true, false);
            par3List.add(charged);

            par3List.add(new ItemStack(this, 1, getMaxDamage()));
    }
    
    // IElectricItem methods
    @Override
    public boolean canProvideEnergy()
    {
        return false;
    }

    @Override
    public int getChargedItemId()
    {
        return itemID;
    }

    @Override
    public int getEmptyItemId()
    {
        return itemID;
    }

    @Override
    public int getMaxCharge()
    {
        return 20000;
    }

    @Override
    public int getTier()
    {
        return 1;
    }

    @Override
    public int getTransferLimit()
    {
        return 200;
    }
}
