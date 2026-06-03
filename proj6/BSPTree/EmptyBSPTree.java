
package BSPTree;

import java.util.ArrayList;
import java.util.Collection;


/* EmptyBSPTree represents an empty BST with no key/value pairs. Due to the 
 * fact that this generic polymorphic BST implementation uses the singleton 
 * design, this class has only 1 field, a private static shared instance of 
 * itself that used whenever appropriate, like at the end of a BST object 
 * in lieu of null. This classes' constructor is also private, and not 
 * accessible by other classes. 
 * This class implements all public methods in the BSPTree interface and 
 * creates the functionality needed for those methods to work appropriately 
 * for an empty BST. 
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public class EmptyBSPTree<K extends Comparable<K>, V>
             implements BSPTree<K, V> 
{
    private static EmptyBSPTree EmptyBSPTree = new EmptyBSPTree();
    
    private EmptyBSPTree()
    {        
    }
    
    // This method has no parameters and returns a reference to the single 
    // instance of this class so that other classes and methods 
    // might be able to use it. 
    public static EmptyBSPTree getInstance() 
    {
        return EmptyBSPTree; 
    }

    // This method has two parameters, generic types aKey and aValue and 
    // creates and returns a new NonemptyBSPTree with those parameters whose 
    // left and right references refer to the single instance of this class. 
    // It returns null if any parameter is null
    public NonemptyBSPTree<K, V> addKeyWithValue(K aKey, V aValue) 
    {
        if (aKey == null || aValue == null)
        {
            return null;
        }
        
        return new NonemptyBSPTree(aKey, aValue, EmptyBSPTree, EmptyBSPTree);
    }

    // This method has no parameters, and returns the number of key value 
    // pairs in this EmptyBSPTree. Because an EmptyBSPTree is empty, 
    // it returns 0. 
    public int numKeyValuePairs() 
    {
        return 0; 
    }

    // This method has one parameter,a generic type Key and returns the 
    // corresponding value if the
    // parameter key matches any key in the object. Thus it returns null 
    // because an EmptyBSPTree cannot contain contain any keys ( and values).  
    public V findValueForKey(K targetKey) 
    {
        return null; 
    }

    // This method has no parameters, and throws an Exception,
    // Signifying that the maximum key has been reached when searching for 
    // the maximum key. 
    public K maximumKey() throws EmptyBSPTreeException 
    {
        throw new EmptyBSPTreeException();
    }

    // This method has no parameters, and throws an Exception,
    // Signifying that the minimum key has been reached when searching for 
    // the minimum key. 
    public K minimumKey() throws EmptyBSPTreeException 
    {
        throw new EmptyBSPTreeException();
    }

    // This method has two parameters, generic types lowKey and highValue and 
    // returns the single instance of this class because it signifies that all 
    // the elements needed to be added to the subTree have been added.
    // It returns null if any parameter is null
    public BSPTree<K, V> eltsBetween(K lowKey, K highKey) 
    {
        if (lowKey == null || highKey == null)
        {
            return null;
        }
        
        return EmptyBSPTree; 
    }

    // This method has no parameters, and returns an empty ArrayList as there 
    // are no keys in a EmptyBSPTree object. 
    public Collection<K> collectionOfKeys() 
    {
        return new ArrayList<K>();
    }
    
    // this helper method represents that the base case has been reached when 
    // adding all of the keys in a BSPTree to an ArrayList, and thus has no 
    // Parameters and just returns 
    public void addToCollection(Collection<K> c)
    {
        return; 
    }

    // This method has one parameter, a generic type targetKey and returns the 
    // single instance of this class as it represents that the whole BSP has 
    // been searched and the key that matches the parameter has been 
    // removed if it is in the BSPTree. The method returns null if the 
    // parameter is null. 
    public BSPTree<K, V> removeKeyWithValue(K targetKey) 
    {
        if (targetKey == null)
        {
            return null;
        }
        
        return EmptyBSPTree; 
    }

    // this toString method returns an empty string to represent an empty BSP 
    public String toString() 
    {
        return ""; 
    }

}
