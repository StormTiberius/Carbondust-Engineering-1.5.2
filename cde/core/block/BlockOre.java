package cde.core.block;

import cde.CDECore;
import cde.api.Blocks;
import cde.api.Materials;
import cde.core.Defaults;
import java.util.ArrayList;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class BlockOre extends Block
{
    public BlockOre(int par1)
    {
        super(par1, Material.rock);
    }

    @Override
    public int idDropped(int meta, Random random, int fortune)
    {
        if(meta == Blocks.oreApatite.getItemDamage())
        {
            return CDECore.apatiteId;
        }
        else if(meta > 4)
        {
            return CDECore.materialsItem.itemID;
        }
        else
        {
            return blockID;
        }
    }
    
    @Override
    public int damageDropped(int meta)
    {
        switch(meta)
        {
            case 5: return Materials.dustSulfur.getItemDamage();
            case 6: return Materials.dustSaltpeter.getItemDamage();
            case 7: return Materials.gemQuartz.getItemDamage();
            case 8: return CDECore.apatiteMeta;
            case 9: return Materials.gemRuby.getItemDamage();
            case 10: return Materials.gemJade.getItemDamage();
            case 11: return Materials.gemSapphire.getItemDamage();
            default: return meta;
        }
    }
    
    @Override
    public int getDamageValue(World par1World, int par2, int par3, int par4)
    {
        return par1World.getBlockMetadata(par2, par3, par4);
    }
    
    @Override
    public int quantityDropped(int meta, int fortune, Random random)
    {
        switch(meta)
        {
            case 5: return 2 + random.nextInt(4) + random.nextInt(fortune + 1);
            case 6: return 1 + random.nextInt(2) + random.nextInt(fortune + 1);
            case 7: return 1 + Math.abs(random.nextInt()) % (1 + fortune);
            
            case 8:
                int fortmod = random.nextInt(fortune + 2) - 1;
            
                if(fortmod < 0)
                {
                    fortmod = 0;
                }

                int amount = (1 + random.nextInt(5)) * (fortmod + 1);

                if(amount > 0)
                {
                    return amount;
                }
            
            case 9:
            case 10:

            case 11:
                int i = random.nextInt(fortune + 2) - 1;
            
                if(i < 0)
                {
                    i = 0;
                }

                return i + 1;
                
            default: return quantityDroppedWithBonus(fortune, random);
        }
    }
    
    @Override
    public int quantityDropped(Random par1Random)
    {
        return 1;
    }

    @Override
    public int quantityDroppedWithBonus(int par1, Random par2Random)
    {
        if (par1 > 0 && this.blockID != this.idDropped(0, par2Random, par1))
        {
            int var3 = par2Random.nextInt(par1 + 2) - 1;

            if (var3 < 0)
            {
                var3 = 0;
            }

            return this.quantityDropped(par2Random) * (var3 + 1);
        }
        else
        {
            return this.quantityDropped(par2Random);
        }
    }

    @Override
    public ArrayList<ItemStack> getBlockDropped(World world, int x, int y, int z, int metadata, int fortune)
    {
        ArrayList<ItemStack> ret = super.getBlockDropped(world, x, y, z, metadata, fortune);

        if(metadata == Blocks.oreQuartz.getItemDamage())
        {
            int amount = Math.abs(world.rand.nextInt()) % (3 + fortune);

            if(amount > 0)
            {
                ret.add(new ItemStack(Materials.dustQuartz.itemID, amount, Materials.dustQuartz.getItemDamage()));
            }
        }
        
        return ret;
    }
        
    @Override
    public void dropBlockAsItemWithChance(World par1World, int par2, int par3, int par4, int par5, float par6, int par7)
    {
        super.dropBlockAsItemWithChance(par1World, par2, par3, par4, par5, par6, par7);
        
        if (par5 > 4)
        {
            int var8 = 0;

            switch(par5)
            {
                case 5:
                case 6: var8 = MathHelper.getRandomIntegerInRange(par1World.rand, 2, 5); break; // Sulfur & Saltpeter
                case 7: var8 = MathHelper.getRandomIntegerInRange(par1World.rand, 1, 5); break; // Quartz
                case 8: var8 = MathHelper.getRandomIntegerInRange(par1World.rand, 1, 4); break; // Apatite
                case 9:
                case 10:
                case 11: var8 = MathHelper.getRandomIntegerInRange(par1World.rand, 3, 7); break; // Gems // Nikolite 1 to 5
            }
            
            this.dropXpOnBlockBreak(par1World, par2, par3, par4, var8);
        }
    }
    
    @Override
    public int getBlockTextureFromSideAndMetadata(int side, int meta)
    {
        switch(meta)
        {
            case 0: return Defaults.TEXTURE_ORE_COPPER;
            case 1: return Defaults.TEXTURE_ORE_TIN;
            case 2: return Defaults.TEXTURE_ORE_SILVER;
            case 3: return Defaults.TEXTURE_ORE_LEAD;
            case 4: return Defaults.TEXTURE_ORE_URANIUM;
            case 5: return Defaults.TEXTURE_ORE_SULFUR;
            case 6: return Defaults.TEXTURE_ORE_SALTPETER;
            case 7: return Defaults.TEXTURE_ORE_QUARTZ;
            case 8: return Defaults.TEXTURE_ORE_APATITE;
            case 9: return Defaults.TEXTURE_ORE_RUBY;
            case 10: return Defaults.TEXTURE_ORE_JADE;
            case 11: return Defaults.TEXTURE_ORE_SAPPHIRE;
            default: return 0;
        }
    }
    
    @Override
    public String getTextureFile()
    {
        return CDECore.CDE_BLOCKS;
    }
}
