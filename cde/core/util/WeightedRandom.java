/**
 *
 * @author StormTiberius
 */

package cde.core.util;

import java.util.Collection;
import java.util.Iterator;
import java.util.Random;

public class WeightedRandom
{
    public static int getTotalWeightValue(WeightedObject[] array)
    {
        int weightValue = 0;
        
        for(WeightedObject object : array)
        {
            weightValue += object.objectWeight; 
        }
        
        return weightValue;
    }
    
    public static int getTotalWeightValue(Collection collection)
    {
        int weightValue = 0;
        WeightedObject object;
        
        for(Iterator iterator = collection.iterator(); iterator.hasNext(); weightValue += object.objectWeight)
        {
            object = (WeightedObject)iterator.next();
        }
        
        return weightValue;
    }

    public static WeightedObject getRandomObject(Random random, WeightedObject[] array, int weightValue)
    {
        int i = random.nextInt(weightValue);
        
        for(WeightedObject object : array)
        {
            i -= object.objectWeight;
            
            if(i < 0)
            {
                return object;
            }
        }
        
        return null;
    }
    
    public static WeightedObject getRandomObject(Random random, Collection collection, int weightValue)
    {
        int i = random.nextInt(weightValue);
        Iterator iterator = collection.iterator();
        WeightedObject object;
        
        do
        {   
            object = (WeightedObject)iterator.next();
            i -= object.objectWeight;
        }
        
        while(i >= 0);
        
        return object;
    }
    
    public static WeightedObject getRandomObject(Random random, WeightedObject[] array)
    {
        return getRandomObject(random, array, getTotalWeightValue(array));
    }
    
    public static WeightedObject getRandomObject(Random random, Collection collection)
    {
        return getRandomObject(random, collection, getTotalWeightValue(collection));
    }
}
