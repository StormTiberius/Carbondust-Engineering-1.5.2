/**
 *
 * @author StormTiberius
 */

package cde.core.worldgen;

import cpw.mods.fml.common.IWorldGenerator;
import java.util.Random;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.OreGenEvent;
import static net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable.EventType;
import net.minecraftforge.event.terraingen.TerrainGen;

public class WorldGenManager implements IWorldGenerator
{
    private final WorldGenerator copper,tin,silver,lead,uranium,sulfur,saltpeter,quartz,apatite,ruby,jade,sapphire;
    private final int[][] cfg;
    
    public WorldGenManager(int id, int[][] config)
    {   
        cfg = config;
        
        copper = new WorldGenMinable(id, 0, cfg[0][1]);
        tin = new WorldGenMinable(id, 1, cfg[1][1]);
        silver = new WorldGenMinable(id, 2, cfg[2][1]);
        lead = new WorldGenMinable(id, 3, cfg[3][1]);
        uranium = new WorldGenMinable(id, 4, cfg[4][1]);
        sulfur = new WorldGenMinable(id, 5, cfg[5][1]);
        saltpeter = new WorldGenMinable(id, 6, cfg[6][1]);
        quartz = new WorldGenMinable(id, 7, cfg[7][1]);
        apatite = new WorldGenMinable(id, 8, cfg[8][1]);
        ruby = new WorldGenMinable(id, 9, cfg[9][1]);
        jade = new WorldGenMinable(id, 10, cfg[10][1]);
        sapphire = new WorldGenMinable(id, 11, cfg[11][1]);
    }
    
    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider)
    {
        int xCoord = chunkX * 16;
        int zCoord = chunkZ * 16;
        
        MinecraftForge.ORE_GEN_BUS.post(new OreGenEvent.Pre(world, random, xCoord, zCoord));
        
        int xPos,yPos,zPos;
        
        if(cfg[0][0] == 1 && TerrainGen.generateOre(world, random, copper, xCoord, zCoord, EventType.CUSTOM))
        {
            for(int i = 0; i < cfg[0][2]; i++)
            {
                xPos = xCoord + random.nextInt(16);
                yPos = cfg[0][3] + random.nextInt(cfg[0][4] - cfg[0][3]);
                zPos = zCoord + random.nextInt(16);

                copper.generate(world, random, xPos, yPos, zPos);
            }
        }
        
        if(cfg[1][0] == 1 && TerrainGen.generateOre(world, random, tin, xCoord, zCoord, EventType.CUSTOM))
        {
            for(int i = 0; i < cfg[1][2]; i++)
            {
                xPos = xCoord + random.nextInt(16);
                yPos = cfg[1][3] + random.nextInt(cfg[1][4] - cfg[1][3]);
                zPos = zCoord + random.nextInt(16);

                tin.generate(world, random, xPos, yPos, zPos);
            }
        }
                
        if(cfg[2][0] == 1 && TerrainGen.generateOre(world, random, silver, xCoord, zCoord, EventType.CUSTOM))
        {
            for(int i = 0; i < cfg[2][2]; i++)
            {
                xPos = xCoord + random.nextInt(16);
                yPos = cfg[2][3] + random.nextInt(cfg[2][4] - cfg[2][3]);
                zPos = zCoord + random.nextInt(16);

                silver.generate(world, random, xPos, yPos, zPos);
            }
        }
        
        if(cfg[3][0] == 1 && TerrainGen.generateOre(world, random, lead, xCoord, zCoord, EventType.CUSTOM))
        {
            for(int i = 0; i < cfg[3][2]; i++)
            {
                xPos = xCoord + random.nextInt(16);
                yPos = cfg[3][3] + random.nextInt(cfg[3][4] - cfg[3][3]);
                zPos = zCoord + random.nextInt(16);

                lead.generate(world, random, xPos, yPos, zPos);
            }
        }
                        
        if(cfg[4][0] == 1 && TerrainGen.generateOre(world, random, uranium, xCoord, zCoord, EventType.CUSTOM))
        {
            for(int i = 0; i < cfg[4][2]; i++)
            {
                xPos = xCoord + random.nextInt(16);
                yPos = cfg[4][3] + random.nextInt(cfg[4][4] - cfg[4][3]);
                zPos = zCoord + random.nextInt(16);

                uranium.generate(world, random, xPos, yPos, zPos);
            }
        }
        
        if(cfg[5][0] == 1 && TerrainGen.generateOre(world, random, sulfur, xCoord, zCoord, EventType.CUSTOM))
        {
            for(int i = 0; i < cfg[5][2]; i++)
            {
                xPos = xCoord + random.nextInt(16);
                yPos = cfg[5][3] + random.nextInt(cfg[5][4] - cfg[5][3]);
                zPos = zCoord + random.nextInt(16);

                sulfur.generate(world, random, xPos, yPos, zPos);
            }
        }
        
        if(cfg[6][0] == 1 && TerrainGen.generateOre(world, random, saltpeter, xCoord, zCoord, EventType.CUSTOM))
        {
            for(int i = 0; i < cfg[6][2]; i++)
            {
                xPos = xCoord + random.nextInt(16);
                yPos = cfg[6][3] + random.nextInt(cfg[6][4] - cfg[6][3]);
                zPos = zCoord + random.nextInt(16);

                saltpeter.generate(world, random, xPos, yPos, zPos);
            }
        }
                        
        if(cfg[7][0] == 1 && TerrainGen.generateOre(world, random, quartz, xCoord, zCoord, EventType.CUSTOM))
        {
            for(int i = 0; i < cfg[7][2]; i++)
            {
                xPos = xCoord + random.nextInt(16);
                yPos = cfg[7][3] + random.nextInt(cfg[7][4] - cfg[7][3]);
                zPos = zCoord + random.nextInt(16);

                quartz.generate(world, random, xPos, yPos, zPos);
            }
        }
        
        if(cfg[8][0] == 1 && TerrainGen.generateOre(world, random, apatite, xCoord, zCoord, EventType.CUSTOM))
        {
            for(int i = 0; i < cfg[8][2]; i++)
            {
                xPos = xCoord + random.nextInt(16);
                yPos = cfg[8][3] + random.nextInt(cfg[8][4] - cfg[8][3]);
                zPos = zCoord + random.nextInt(16);

                apatite.generate(world, random, xPos, yPos, zPos);
            }
        }
                                                                        
        if(cfg[9][0] == 1 && TerrainGen.generateOre(world, random, ruby, xCoord, zCoord, EventType.CUSTOM))
        {
            for(int i = 0; i < cfg[9][2]; i++)
            {
                xPos = xCoord + random.nextInt(16);
                yPos = cfg[9][3] + random.nextInt(cfg[9][4] - cfg[9][3]);
                zPos = zCoord + random.nextInt(16);

                ruby.generate(world, random, xPos, yPos, zPos);
            }
        }
        
        if(cfg[10][0] == 1 && TerrainGen.generateOre(world, random, jade, xCoord, zCoord, EventType.CUSTOM))
        {
            for(int i = 0; i < cfg[10][2]; i++)
            {
                xPos = xCoord + random.nextInt(16);
                yPos = cfg[10][3] + random.nextInt(cfg[10][4] - cfg[10][3]);
                zPos = zCoord + random.nextInt(16);

                jade.generate(world, random, xPos, yPos, zPos);
            }
        }
        
        if(cfg[11][0] == 1 && TerrainGen.generateOre(world, random, sapphire, xCoord, zCoord, EventType.CUSTOM))
        {
            for(int i = 0; i < cfg[11][2]; i++)
            {
                xPos = xCoord + random.nextInt(16);
                yPos = cfg[11][3] + random.nextInt(cfg[11][4] - cfg[11][3]);
                zPos = zCoord + random.nextInt(16);

                sapphire.generate(world, random, xPos, yPos, zPos);
            }
        }
        
        MinecraftForge.ORE_GEN_BUS.post(new OreGenEvent.Post(world, random, xCoord, zCoord));
    }
}
