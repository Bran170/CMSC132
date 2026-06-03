
package simplList;

public class SLRM 
{

    public static <T extends Comparable<T>>
           int numOutOfOrder(SimplList<T> list) 
    {
        if (list == null)
        {
            throw new IllegalArgumentException("list is null"); 
        }
        return numOutOfOrder(list, 0);       
    }
    
    private static <T extends Comparable<T>> int numOutOfOrder
    (SimplList<T> list, int index)
    {
        if (index == list.size() -1 || list.size() == 0)
        {
            return 0; 
        }
        if (list.get(index).compareTo(list.get(index + 1)) > 0)
        {
            return 1 + numOutOfOrder(list, index + 1);
        }
        else
        {
            return  numOutOfOrder(list, index + 1);
        }
    }

    public static int compareValuePos(SimplList<Integer> list) 
    {
        if (list == null)
        {
            throw new IllegalArgumentException("list is null"); 
        }
        return compareValuePos(list, 0);
    }
    
    private static int compareValuePos(SimplList<Integer> list, int index)
    {
        if (index == list.size())
        {
            return 0;
        }
        if (list.get(index).compareTo(index) >= 0)
        {
            return 1 + compareValuePos(list, index +1);
        }
        else
        {
            return compareValuePos(list, index +1);
        }
    }

    public static <T> SimplList<T> replaceWithElts(SimplList<T> list, 
            T element, SimplList<T> list2) 
    {
        if (list == null || list2 == null || element == null)
        {
            throw new IllegalArgumentException("null parameter"); 
        }
        SimplList<T> newList = new SimplList<T>(); 
        replaceWithElts(list, element, list2, 0, 0, newList);
        return newList; 
    }
    
    private static <T> void replaceWithElts(SimplList<T> list, T element,
            SimplList<T> list2, int index, int current, SimplList<T> newList)
    { 
        if (index < list.size())
        {
            if (list.get(index).equals(element)) 
            {             
                if (current < list2.size()) 
                {                     
                    newList.append(list2.get(current++)); 
                } 
                else 
                {                   
                    newList.append(list.get(index));                   
                }               
            }
            else 
            {               
                newList.append(list.get(index));                         
            }
            replaceWithElts(list, element, list2, index +1, current, newList);
        }
    }
        
    public static <T> SimplList<T> filter(SimplList<T> list,
                                          Predicate<T> pred) 
    {
        if (list == null || pred == null)
        {
            throw new IllegalArgumentException("null parameter"); 
        }
        SimplList<T> newList = new SimplList<T>(); 
        filter(list, pred, 0, newList);
        return newList; 
    }
    
    private static <T> void filter(SimplList<T> list,
            Predicate<T> pred, int index, SimplList<T> newList)
    {
        if (index < list.size())
        {
            if (pred.choose(list.get(index)))
            {
                newList.append(list.get(index)); 
            }
            filter(list, pred, index +1, newList); 
        }      
    }
}
