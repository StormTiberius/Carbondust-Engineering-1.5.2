/**
 *
 * @author StormTiberius
 */

package cde.core.sound;

import java.util.EnumSet;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;

public class SoundTickHandler implements ITickHandler
{   
    @Override
    public void tickStart(EnumSet<TickType> type, Object... tickData)
    {
        SoundHelper.onUpdate();
    }

    @Override
    public void tickEnd(EnumSet<TickType> type, Object... tickData)
    {

    }

    @Override
    public EnumSet<TickType> ticks()
    {
        return EnumSet.of(TickType.CLIENT);
    }

    @Override
    public String getLabel()
    {
        return "CDE Sound";
    }
}
