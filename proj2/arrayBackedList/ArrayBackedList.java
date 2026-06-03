package arrayBackedList;

/* ArrayBackedList is a class that represents a data structure that can store 
 * elements of various types and also change size if all the indexes are full.
 * This class has 1 fields, an Object array
 * that holds the data that we will store in the ArrayBackedList. 
 * This class has certain public methods that add elements and returns data 
 * about the ArrayBackedLIst. 
 */
public class ArrayBackedList 
{
    private Object[] arrayBackedList;
    private int increment; 
       
    public ArrayBackedList(int increment) 
    {
        if (increment < 1)
        {
            arrayBackedList = new Object[1];
            this.increment = 1; 
        }
        else
        {
            arrayBackedList = new Object[increment];  
            this.increment = increment; 
        }
    }
    
    // this method adds a new element to the ArrayBackedList, but only if the 
    // parameter that contains the new element is valid. if we do not have any 
    // more space, we will adjust the ArraybackedList by doubling its capacity. 
    public boolean add(Object newElement) 
    {
        boolean temp = false; 
        
        if (newElement != null)
        {
            for (int i = 0; i < arrayBackedList.length; i ++)
            {
                // checks if there is space, and if there is the new element is 
                // added 
                if (arrayBackedList[i] == null && temp == false)
                {
                    arrayBackedList[i] = newElement;
                    
                    temp = true; 
                }
            }
            
            // if there is no space, the capacity is doubled 
            if (temp == false)
            {
                Object[] copy = new Object[arrayBackedList.length + increment];
                
                for (int i = 0; i < arrayBackedList.length; i ++)
                {
                    copy[i] = arrayBackedList[i];
                }
                // the new element is added to the larger ArraybackedList 
                arrayBackedList = copy;
                temp = add(newElement);
            }
        }
        return temp; 
    }

    // this method returns the total number of indexes where data is actually 
    // being stored, how many items we are storing in the ArraybackedList in 
    // the form of an Integer 
    public int getSize() 
    {
        int temp = 0;
        // iterates through the array field and counts the number of non null 
        // indices 
        for (int i = 0; i < arrayBackedList.length; i ++)
        {
            if (arrayBackedList[i] != null)
            {
                temp ++;
            }
        }
        return temp; 
        
    }

    // this method returns the total capacity of the ArraybackedList, 
    // how many elements it can hold in total in the form of an Integer 
    public int getCapacity() 
    {
        return arrayBackedList.length; 
    }

    // this method returns the Object stored at the position indicated by the 
    // Integer parameter. The returned object will be in an Object reference.  
    public Object get(int pos) 
    {
        Object temp = null;        
        if (pos >= 0 && pos < arrayBackedList.length)
        {
            if (arrayBackedList[pos] != null)
            {
                temp = arrayBackedList[pos];
            }
        }
        return temp; 
    }

}
