
package searchTreeMap;

import BSPTree.BSPTree;
import BSPTree.EmptyBSPTree;
import BSPTree.NonemptyBSPTree;
import java.util.Collection;

/* SearchTreeMap is a simple version of a Map implementation using a 
 * polymorphic BST to store its data. Each instance of this class represents a 
 * separate object of a BST Map class. This class has only 1 field, an 
 * Generic BSPTree reference that contains a polymorphic BSPTree that 
 * stores the keys and values of the SearchTreeMap in a BST format. 
 * This class has certain public methods that allow functionality such as 
 * inserting new Key,value pairs, getting the size of the TreeMap, 
 * getting the value associated with a particular key, and resetting the 
 * TreeMap to hold no values or keys. 
 */
@SuppressWarnings("unchecked")
public class SearchTreeMap<K extends Comparable<K>, D> 
{
    private BSPTree<K, D> searchTreeMap;
    
    public SearchTreeMap()
    {
        searchTreeMap = EmptyBSPTree.getInstance();;
    }

    // This method has two parameters, generic types key and value and 
    // adds the new key value parameters into the current TreeMap and 
    // returns a reference to the current object. 
    // It returns null if any parameter is null
    public SearchTreeMap<K, D> put(K key, D data) 
    {
        if (key == null || data == null)
        {
            return null;
        }
        
        searchTreeMap = searchTreeMap.addKeyWithValue(key, data);
        return this; 
        
    }

    // This method has no parameters, and returns the total number of 
    // key/value pairs present in the current TreeMap. 
    public int size() 
    {
        return searchTreeMap.numKeyValuePairs();
    }

    // This method has one parameter, a generic type key and returns true if 
    // the current TreeMap contains the current Key, and false if it doesn't. 
    // It returns null if the parameter is null
    public Boolean hasKey(K key) 
    {
        boolean result = false; 
              
        if (key == null)
        {
            return null;
        }
        
        // checks to see if key is in the current TreeMap 
        if (searchTreeMap.findValueForKey(key) != null)
        {
            result = true; 
        }
        return result; 
    }

    // This method has one parameter, a generic type key and returns the value
    // that is associated with its parameter Key in the current TreeMap. 
    // It returns null if the parameter is null or if the parameter key 
    // doesn't match any keys in the current TreeMap. 
    public D get(K key) 
    {
        if (key == null)
        {
            return null;
        }
        
        return searchTreeMap.findValueForKey(key);       
    }

    // This method has no parameters, and no return value. 
    // Its only job is to reset the current treeMap and delete all 
    // key/value pairs that were in it. 
    public void clear() 
    {
        searchTreeMap = EmptyBSPTree.getInstance();
    }

    // This method has one parameter, a generic type key and removes the 
    // parameter Key and its value from the current TreeMap. 
    // It then returns a reference to the current object. 
    // It returns null if any parameter is null
    public SearchTreeMap<K, D> remove(K key) 
    {
        if (key == null)
        {
            return null;
        }
        
        searchTreeMap = searchTreeMap.removeKeyWithValue(key);
        return this;
    }

    // This method has no parameters, and returns an ArrayLIst containing all 
    // of the keys in the current TreeMap. 
    public Collection<K> getKeys() 
    {
        return searchTreeMap.collectionOfKeys();
    }

}
