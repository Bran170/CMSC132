
package BSPTree;

import java.util.ArrayList;

import java.util.Collection;

/* NonEmptyBSPTree represents an BST with 1 key/value pairs. This class has 
 * 4 fields, 2 generic fields that store the key and value for this BSP, and 
 * two generic BSP references that refer to the current BSP objects left
 * and right children. this class also implements all public methods in the 
 * BSPTree interface and creates the functionality needed for those methods 
 * to work appropriately for an nonEmptyBST. 
 */
@SuppressWarnings("unchecked")
public class NonemptyBSPTree<K extends Comparable<K>, V>
             implements BSPTree<K, V> 
{
    private K key;
    private V value; 
    private BSPTree<K, V> left; 
    private BSPTree<K, V> right; 

    public NonemptyBSPTree
    (K aKey, V aValue, BSPTree<K, V>  left, BSPTree<K, V>  right) 
    {
        key = aKey; 
        value = aValue; 
        this.left = left;
        this.right = right;
    }

    // This method has two parameters, generic types aKey and aValue. The 
    // method compares its current key to the parameter key and recursively
    // calls itself on its children if the comparison is not equal. The end 
    // result is that a new nonEmptyBSP object with the parameter key.value 
    // pair is created and inserted in the correct place in the BSP tree to 
    // preserve the properties of a BST. 
    // If they are equal, the parameter value replaces the current value. 
    // It returns the current object, and null if any parameter is null
    public NonemptyBSPTree<K, V> addKeyWithValue(K newKey, V newValue) 
    {
        if (newKey == null || newValue == null)
        {
            return null;
        }
        
        else if (key.compareTo(newKey) > 0) // greater, so go left
        {
            left= left.addKeyWithValue(newKey, newValue);
        }
        else if (key.compareTo(newKey) < 0) // smaller so go right
        {
            right = right.addKeyWithValue(newKey, newValue);
        }
        else // equal, so overwrite value with new parameter value 
        {
            value = newValue;     
        }
        return this;
    }

    // This method has no parameters, and returns the total number of key/value
    // pairs in the whole polymorphic BSPTree using recursion. 
    public int numKeyValuePairs() 
    {
         return 1 + left.numKeyValuePairs() + right.numKeyValuePairs(); 
    }

    // This method has one parameter, a generic type Key and returns the 
    // corresponding value if the parameter key matches any key currently 
    // in the whole BSPTree.  
    public V findValueForKey(K targetKey) 
    {
        if (targetKey == null)
        {
            return null;
        }
        
        if (key.compareTo(targetKey) == 0) // match found, so return the value
        {
            return value; 
        }
        else if (key.compareTo(targetKey) > 0) // greater, so go left
        {
            return left.findValueForKey(targetKey);
        }
        else // smaller so go right
        {
            return right.findValueForKey(targetKey);
        }
            
    }

    // This method has no parameters, and could possibly throw an Exception,
    // This method returns the maximum key found in the current whole BSPTree. 
    public K maximumKey() throws EmptyBSPTreeException 
    {
        try 
        {
            return right.maximumKey(); 
        }
        catch (EmptyBSPTreeException reachedEnd) 
        // if exception thrown, we have reached to the rightmost (largest)
        // NonemptyBSPTree so we return the current key 
        {
            return key; 
        }
    }

    // This method has no parameters, and could possibly throw an Exception,
    // This method returns the minimum key found in the current whole BSPTree. 
    public K minimumKey() throws EmptyBSPTreeException 
    {
        try 
        {
            return left.minimumKey();
        }
        catch (EmptyBSPTreeException reachedEnd)
        // if exception thrown, we have reached to the leftmost (smallest)
        // NonemptyBSPTree so we return the current key 
        {
            return key; 
        }
    }

    // This method has two parameters, generic types lowKey and highValue and 
    // returns a new BSPTree that contains all keys and their values that are 
    // between in value the 2 parameters, inclusive. 
    // It returns null if any parameter is null or if the lowKey parameter is 
    // greater than the highKey parameter. 
    public BSPTree<K, V> eltsBetween(K lowKey, K highKey) 
    {
        if (lowKey == null || highKey == null)
        {
            return null;
        }
        
        BSPTree<K, V> newTree = EmptyBSPTree.getInstance();
        
        if (key.compareTo(lowKey) >= 0 && key.compareTo(highKey) <= 0)
        {
            // in the range, so we add to the new returned BSPTree 
            newTree = new NonemptyBSPTree<K, V>
            (key, value, left.eltsBetween(lowKey, highKey),
                    right.eltsBetween(lowKey, highKey));
        }
        else if (key.compareTo(lowKey) < 0) // smaller so go right
        {
            return right.eltsBetween(lowKey, highKey);
        }
        else // greater, so go left
        {
            return left.eltsBetween(lowKey, highKey);          
        }
        return newTree; 
    }


    // This method has no parameters, and returns an ArrayList containing all 
    // of the keys in the current BSPTree. 
    public Collection<K> collectionOfKeys() 
    {
        ArrayList<K> collection = new ArrayList<K>();
        
        left.addToCollection(collection);
        collection.add(key);
        right.addToCollection(collection);
        
        return collection;        
    }
    
    // this helper method helps us add all of the keys to the ArrayList using 
    // recursive calls on the left and right references to iterate 
    // through the BSPTree 
    public void addToCollection(Collection<K> c)
    {
        left.addToCollection(c);
        c.add(key); 
        right.addToCollection(c);
    }

    // This method has one parameter, a generic type targetKey and removes 
    // the nonEmptyBSPTree containing the target key while maintaining the 
    // properties of a BST. The method returns null if the 
    // parameter is null. 
    public BSPTree<K, V> removeKeyWithValue(K targetKey) 
    {
        if (targetKey == null)
        {
            return null;
        }
        
        if (key.compareTo(targetKey) > 0) // greater so go left
        {
            left = left.removeKeyWithValue(targetKey);
        }
        else if (key.compareTo(targetKey) < 0) // smaller so go right 
        {
            right = right.removeKeyWithValue(targetKey);
        }
        else
        {
          
            try  // try to find minimum of right subtree
            {
                // set key/value to minimum of right subtree, then run 
                // remove on the minimum value of right subtree 
                key = right.minimumKey();
                value = right.findValueForKey(key);
                right = right.removeKeyWithValue(key);
            }
            catch (EmptyBSPTreeException noLeftMax)
            {
              
                try // try to find maximum of left subtree
                {
                    // set key/value to maximum of left subtree, then run 
                    // remove on the maximum value of left subtree 
                    key = left.maximumKey();
                    value = left.findValueForKey(key);
                    left = left.removeKeyWithValue(key);
                }
                catch (EmptyBSPTreeException noRightMin)
                {
                    // there are no minimum of right or 
                    // maximum of left subtrees so we return empty tree 
                    return EmptyBSPTree.getInstance();
                }
            }
            
        }
        return this; 
    }

    // this toString method prints out a String representation of both the 
    // left and right children as well as the key and value in the current
    // nonEmptyBSPTree object 
    public String toString() 
    {
        return left.toString() + key + "->" + value + " " + right.toString(); 
    }

}
