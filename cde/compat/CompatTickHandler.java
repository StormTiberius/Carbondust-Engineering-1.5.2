/**
 *
 * @author StormTiberius
 */

package cde.compat;

import java.util.EnumSet;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;

public class CompatTickHandler implements ITickHandler
{   
    @Override
    public void tickStart(EnumSet<TickType> type, Object... tickData)
    {
        WorldEventManager.clearList();
    }

    @Override
    public void tickEnd(EnumSet<TickType> type, Object... tickData)
    {

    }

    @Override
    public EnumSet<TickType> ticks()
    {
        return EnumSet.of(TickType.SERVER);
    }

    @Override
    public String getLabel()
    {
        return "CDE Compat";
    }
}
