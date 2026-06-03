
package oLinkedList;

import java.lang.IllegalArgumentException;
import java.lang.IndexOutOfBoundsException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/* This class represents a generic, singly linked list that is ordered and can 
 * have duplicate values called OLinkedList. Each instance of this class 
 * represents a independent linked list that stores values in increasing order 
 * and can store any type of Object as long as the object implements the 
 * comparable interface. This class has a private static inner class called 
 * Node, which is where the data is actually stored and each Node has a generic
 * type field for the data, and a Node field that stores a reference to the 
 * next node in the list. The linked lost is made up of Node objects all 
 * pointing to the next Node in the list, with the last one pointing to null. 
 * This class has only 1 field, a Node field called head that stores a 
 * reference to the  first Node in the list, or null if the list is empty. 
 * This class also has public methods that can perform certain actions like 
 * creating a new shallow copy of itself, inserting a new element in order, 
 * searching for a specific value in the list, getting a reference to the 
 * object at a specific index, removing the first object with a specific value, 
 * and others.  
 */

public class OLinkedList<T extends Comparable<T>>
    implements Comparable<OLinkedList<T>> , Iterable<T>
{
    private Node<T> head;
    
    private static class Node<T>
    {
        T value;
        Node<T> next;

        private Node(T data) 
        {
            this.value = data;
            next = null;
        }
    }

    // This constructor creates an empty OLinkedLIst object 
    public OLinkedList() 
    {        
        head = null;
    }
    

    // This copy constructor creates a new OLinkedList with new independent 
    // Nodes that have the same references to Objects as the ones in the Nodes
    // of the original List in the same order. This method has one parameter, 
    // an OLinkedList that represents the original list, and the parameter is 
    // not modified in any way by the constructor.The constructor will throw 
    // an exception if the parameter is null.  
    public OLinkedList(OLinkedList<T> otherOLinkedList) 
    {
        if (otherOLinkedList == null)
        {
            throw new IllegalArgumentException("parameter is null"); 
        }
        
        head = otherOLinkedList.head; 
        
        if (otherOLinkedList.head != null) //checks if the original is empty
        {
            Node<T> temp = otherOLinkedList.head;
            Node<T> current = head;
            while (temp.next != null) 
            {
                temp = temp.next; //iterates through the original list 
                Node<T> newNode = new Node<T>(temp.value); 
                current.next = newNode; //adds each new Node with correct value
                current = current.next;
            }
            current.next = null; 
        }  
    }

    // This method takes in a generic type parameter of the new Object to be 
    // inserted and inserts the new Object in a New Node in the 
    // appropriate location to maintain the sorted nature of the list. 
    // This method has no return type and if the parameter is null, the method
    // does nothing and throws an exception.  
    public void listOrderedInsert(T newElement) 
    {
        if (newElement == null)
        {
            throw new IllegalArgumentException("parameter is null"); 
        }
        
        Node<T> newNode = new Node<T>(newElement);
        Node<T> current = head;
        
        if (head == null) // inserts in an empty list 
        {
            head = newNode;
            head.next = null; 
        }
        else if (head.value.compareTo(newElement) >= 0) 
            //inserts at the beginning of the list
        {
            newNode.next = head;
            head= newNode;          
        }
        else
        {
            // inserts in all other locations 
            while (current.next != null && 
                    current.next.value.compareTo(newElement) < 0)
            {
                current = current.next; 
            }
            newNode.next = current.next;
            current.next = newNode;           
        }
    }

    // This method has no parameters and returns the number of elements in the 
    // list, including any duplicates (objects with same value). 
    // If the list is empty, it returns 0. 
    public int listNumElements() 
    {
        int length = 0;
        
        Node<T> current = head;
        while (current != null)
        {
            length++;
            current = current.next; 
        }
        return length; 
    }

    // This method takes in a integer parameter that represents the index of 
    // the Object to be returned. If the parameter is less than 0 or greater 
    // than the number of elements in the list, an exception is thrown. 
    // This method returns a reference to the desired Object. 
    public T getEltAtPos(int index) throws IndexOutOfBoundsException 
    {
        T element; 
        
        Node<T> current = head;
        
        if (index < 0 || index > listNumElements() -1) 
            //checks for valid parameter 
        {
            throw new IndexOutOfBoundsException("invalid index");
        }
        
        else 
        {
            while (index != 0) 
                //searches for desired element using a local variable pointer 
            {
                current = current.next;
                index --; 
            }
            element = current.value; // gets the desired element when found 
        }
        return element;          
    }

    // This method takes in a generic type parameter of the Object that has 
    // the same value as the Object to be returned. If the element does not 
    // exist, then null is returned. If there are multiple Objects with the 
    // same value as the parameter in the list, the one closest to the end will 
    // be returned. A reference to the specified Object will be returned. 
    // If the parameter is null, the method throws an exception.  
    public T eltSearch(T element) 
    {
        if (element == null)
        {
            throw new IllegalArgumentException("parameter is null"); 
        }
        
        T elt = null;
        
        if (head != null)
        {
            Node<T> current = head;
            while (current.next != null)
            {
                // searches for the desired Object in the list 
                if (current.value.compareTo(element) == 0)
                {
                    elt = current.value; // gets the reference to the Object
                }
                current = current.next; 
            }
        }
        return elt; 
    }

    // This method has no parameters and returns a String representation of the 
    // list and its elements. In other words, a String containing the values 
    // of all objects in the list in order, with a space in between each. 
    // If the list is empty, an empty String will be returned. 
    public String toString() 
    {
        String output = "";
        
        Node<T> current = head; 
        
        if (head != null)
        {
            while (current.next!= null)
            {
                output = output + current.value.toString() + " ";
                current = current.next; 
            }  
            // gets the last element 
            output = output + current.value.toString();
        }
        return output; 
    }

    // This method takes in a generic type parameter and returns the integer 
    // index of the first element in the list that has an identical value to 
    // the parameter object. If no such element exists, then the method will 
    // return -1. If the parameter is null, the method throws an exception.
    public int getIndexOfElement(T element)  
    {
        if (element == null)
        {
            throw new IllegalArgumentException("parameter is null"); 
        }
        
        int index = 0; 
        Node<T> current = head; 
        
        while (current != null)
        {
            if (current.value.compareTo(element) == 0)
                // checks to see if the desired element has been found
            {  
                return index;
            }
            current = current.next; 
            index ++; 
        }
        return -1;           
    }

    // This method takes in a generic type parameter and removes the first Node 
    // containing the object with the same value as the parameter object and 
    // returns true. If no such element exists, then the method will 
    // return false. If the parameter is null, the method throws an exception.
    public boolean listEltRemove(T element) 
    {
        if (element == null)
        {
            throw new IllegalArgumentException("parameter is null"); 
        }
        
        boolean temp = false; 
        Node<T> current = head; 
        
        if (current != null && current.value.compareTo(element) == 0)
        {
            // removes the first node; 
            head = head.next; 
            current.next = null; 
            temp = true; 
        }
        else
        {
            while (current.next != null)
            {
                if (current.next.value.compareTo(element) == 0)
                {  
                    current.next = current.next.next; 
                    temp = true; 
                }
                current = current.next;            
            }  
        }
        return temp; 
    }

    // This method has no parameters and returns nothing, it just deletes all 
    // the Nodes with non-null values in the list and resets the list into an 
    // empty list. 
    public void reset() 
    {
        head = null; 
    }

    // This method takes in 2 generic type parameters and returns a new 
    // OLinkedList that contains elements with the same values as the current 
    // list starting at the first Node with an Object value equal to the 
    // value of firstElt and ending with the last Node with an Object value 
    // equal to lastElt, inclusive. If no Node object value of the current 
    // list is equal to either parameter Obejcts's value, an empty list will 
    // be returned. If the parameter Object values are equal, then the 
    // returned list will have 1 element. 
    // The method does not modify the current list in any way. 
    // If the parameter is null, the method throws an exception.
    public OLinkedList<T> subList(T firstElt, T lastElt) 
    {
        if (firstElt == null || lastElt == null)
        {
            throw new IllegalArgumentException("parameter is null"); 
        }
        
        OLinkedList<T> list = new OLinkedList<T>();
        Node<T> current = head; 
        Node<T> first = null; 
        Node<T> last = null; 
        
        // uses 2 pointers to find location of start and end elements 
        while (current != null)
        {
            if (current.value.compareTo(firstElt) == 0 && first == null)
            {
                first = current; 
            }
            if (current.value.compareTo(lastElt) == 0)
            {
                last = current; 
            }
            current = current.next; 
        }
        if (first != null && last != null && firstElt.compareTo(lastElt) == 0)
        {
            // creates the new list if parameter's object values are equal 
            list.listOrderedInsert(first.value);
        }
        else
        {
            if (first != null && last != null)
            {
                while (first != last)
                {
                    // creates the new list ( general case) 
                    list.listOrderedInsert(first.value);
                    first = first.next; 
                }
                list.listOrderedInsert(first.value);
            }        
        }
        return list; 
       
        
    }

    // Implemented compareTo method that takes in a OLinkedList parameter 
    // containing the list the current list is to be compared to. 
    // The method returns 0 if the 2 lists contain the same elements in 
    // the same order. Otherwise, the method compares the first elements in 
    // both lists that are not identical, and returns -1 if the element of the 
    // current list is less than the element of the other list, and 
    // returns 1 for the opposite. If the elements are equal, but the parameter
    // list is longer, than the method also returns -1, and 1 if the 
    // current list is longer. 
    // If the parameter is null, the method throws an exception.
    public int compareTo(OLinkedList<T> otherOLinkedList) 
    {
        if (otherOLinkedList == null)
        {
            throw new IllegalArgumentException("parameter is null"); 
        }
        
        Node<T> current = head;
        Node<T> temp = otherOLinkedList.head;
        int result; 
        
        while (current != null && temp != null) 
        {
            // uses 2 pointers to iterate through the 2 lists at same pace 
            result = current.value.compareTo(temp.value);
            if (result != 0)
            {
                // immediately returns the result if we find a pair of 
                // elements that are not equal by calling compareTo on the 
                // element's values 
                return result;
            }
            current = current.next;
            temp = temp.next;
        }
        if (current == null && temp == null) 
        {
            // lists are same length and identical 
            result = 0;
        } 
        else if (current == null) 
        {
            // parameter list is longer 
            result = -1;
        } 
        else 
        {
            // current list is longer 
            result = 1;
        }
        return result;      
    }


    public Iterator<T> iterator() 
    {
         return new OLLIterator();        
    }
    
    private class OLLIterator implements Iterator<T>
    {
        private Node<T> current = null;
        private Node<T> previous; 
        private boolean removable = false; 
        
        
        public boolean hasNext() 
        {
            if (current == null)
            {
                return head != null;
            }
            else
            {
                return current.next != null; 
            }
        }

 
        public T next() throws NoSuchElementException
        {
            if (!hasNext())
            {
                throw new NoSuchElementException("there are no more elements");
            }
            if (current == null) 
            {
                current = head;
            } 
            else 
            {
                previous = current;
                current = current.next;
            }
            removable = true; 
            return current.value;
        }

        public void remove() throws IllegalStateException
        {
            if (!removable)
            {
                throw new IllegalStateException();
            }
            if (current == head) 
            {
                head = head.next;
            } 
            else 
            {
                previous.next = current.next;
            }
            current = previous;
            removable = false; 
        }
    }
    
    
}
