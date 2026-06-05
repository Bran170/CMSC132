
package fazebook;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.LinkedList;

/* EWDGraph represents a directed, weighted graph stored in the form of an 
 * adjacency list. each instance of this class is a separate graph, and each 
 * graph is a generic type, and can store different object types as data in the 
 * vertices. This class has 2 fields, a comparator that is used to compare the 
 * data in 2 vertices to find out which one is larger or smaller. The other 
 * field is an ArrayList that stores all of the vertices and linked lists that 
 * store the neighbors of each vertex. This class also has multiple methods that 
 * perform various operations on a graph like creating and deleting new vertices
 * and edges, and also merging two vertices into a new vertex. All methods in 
 * this class throw an exception if null is passed into them as a parameter. 
 */

public class EWDGraph<V> {

    private ArrayList<Vertex> adjList; 
    private Comparator<V> comparator;
    
    // This inner class is used as Node in each linked list 
    // in the adjacency list. It stores both the data of the 
    // destination vertex of an edge, its weight, and a 
    // pointer to the next Node in the linked list. 
    private class Vertex 
    {
        V vertexData;
        int weight;
        Vertex next = null; 
        
        Vertex(V vertexData, int weight)  
        {
            this.vertexData = vertexData;
            this.weight = weight;
        }
    }
    
    
    public EWDGraph(Comparator<V> comparator) 
    {
        adjList = new ArrayList<Vertex>(); 
        this.comparator = comparator; 
    }

    // This method takes in a generic parameter that represents the data in 
    // a vertex and creates a new Vertex with that data in the current graph 
    // object that has no edges and returns true. However, if there is already 
    // a vertex with the same data, the method returns false and does nothing.  
    public boolean newEWDGraphVertex(V vertexData) 
    {
        // null check
        if (vertexData == null)
        {
            throw new IllegalArgumentException("null parameter");
        }
        
        // checks to see if vertex already exists
        for (int i = 0; i < adjList.size(); i++)
        {
            if (adjList.get(i).vertexData.equals(vertexData))
            {
                return false; 
            }
        }
        
        // adds new vertex and returns 
        Vertex newVertex = new Vertex(vertexData, -1);
        adjList.add(newVertex);
        
        return true; 
    }

    // This method takes in a generic parameter that represents the data in 
    // a vertex and returns true if a vertex in the current graph already 
    // exists with the same data, and false if not. 
    public boolean isEWDGraphVertex(V vertexData) 
    {
        if (vertexData == null)
        {
            throw new IllegalArgumentException("null parameter");
        }
        
        boolean result = false; 
        
        // attempts to search for vertex in the adjacency list 
        for (int i = 0; i < adjList.size(); i++)
        {
            // if found, set return to true
            if (adjList.get(i).vertexData.equals(vertexData))
            {
                result = true;; 
            }
        }
        
        return result; 
    }

    // This method has no parameters and creates and returns a LinkedList 
    // that contains all of the current vertices in the current graph object. 
    // However, if there are no vertices, an empty LinkedList will be returned. 
    public Collection<V> getEWDGraphVertices() 
    {
        LinkedList<V> vertices = new LinkedList<V>();
        
        // adds the vertices to the LinkedList
        for (int i = 0; i < adjList.size(); i++)
        {
            vertices.add(adjList.get(i).vertexData);           
        }
        
        return vertices; 
    }

    // This method takes in 2 generic parameters that represents the data in 
    // 2 vertices and an Integer parameter that represents the weight of an edge
    // creates a new directed, weighted edge starting from srcVert to destVert 
    // with the same weight as the parameter and returns true. However, if 
    // there is already an edge from srcVert to destVert, the this method will
    // simply change the weight to the new parameter weight. If the weight 
    // parameter is 0 or negative, or srcVert and destVrt are the same, the 
    // method will do nothing and return false. Finally, if one or both of the 
    // vertices do not yet exists, but the parameters are valid, then the 
    // vertices will be added before the edge is made. 
    public boolean newEWDGraphEdge(V srcVert, V destVert, int weight) 
    {
        if (srcVert == null || destVert == null)
        {
            throw new IllegalArgumentException("null parameter");
        }
        
        // checks for valid parameters 
        if (srcVert.equals(destVert) || weight <= 0)
        {
            return false; 
        }
        
        // makes sure both vertices actually exist
        newEWDGraphVertex(srcVert);
        newEWDGraphVertex(destVert);
              
        boolean found = false; 
        
        for (int i = 0; i < adjList.size(); i++)
        {
            // iterates through the adjacency list using 2 pointers 
            if (adjList.get(i).vertexData.equals(srcVert))
            {
                Vertex previous = null; 
                Vertex current = adjList.get(i);
                while (current != null)
                {
                    // if edge already exists, overwrite the weight 
                    if (current.vertexData.equals(destVert))
                    {
                        found = true; 
                        current.weight = weight;
                    }
                    previous = current; 
                    current = current.next;
                }
                // else, add the new edge 
                if (!found)
                {
                    previous.next = new Vertex(destVert, weight);
                }                          
            }
        }
        return true;        
    }

    // This method takes in 2 generic parameters that represents the data in 
    // 2 vertices and returns the Integer weight of the edge going from srcVert 
    // to destVert. However, if either vertex does not exists or the edge does 
    // not exists, the method just returns -1.  
    public int getEWDGraphEdge(V srcVert, V destVert) 
    {
        int edge = -1; 
        
        if (srcVert == null || destVert == null)
        {
            throw new IllegalArgumentException("null parameter");
        }
        
        for (int i = 0; i < adjList.size(); i++)
        {
            // iterates through the adjacency list to find the edge if it exists 
            if (adjList.get(i).vertexData.equals(srcVert))
            {
                Vertex current = adjList.get(i).next;
                while (current != null)
                {
                    // found 
                    if (current.vertexData.equals(destVert))
                    {
                        edge = current.weight;
                    }
                    current = current.next;
                }    
            }
        } 
        
        return edge; 
    }

    // This method takes in 2 generic parameters that represents the data in 
    // 2 vertices and removes the edge going from srcVert to destVert if it 
    // exists and returns true. However, if either vertex does not exists or 
    // the edge does not exists, the method just returns false. 
    public boolean removeEWDGraphEdge(V srcVert, V destVert) 
    {
        boolean result = false; 
        
        if (srcVert == null || destVert == null)
        {
            throw new IllegalArgumentException("null parameter");
        }
        
        for (int i = 0; i < adjList.size(); i++)
        {
            // iterates through the adjacency list to find the edge if it exists
            if (adjList.get(i).vertexData.equals(srcVert))
            {
                Vertex previous = null; 
                Vertex current = adjList.get(i);
                while (current!= null)
                {
                    if (current.vertexData.equals(destVert))
                    {
                        // removes the edge by changing the next pointer 
                        previous.next = current.next; 
                        result = true;
                    }
                    previous = current; 
                    current = current.next;
                }
            }
        }
        
        return result; 
    }

    // This method takes in a generic parameter that represents the data in 
    // a vertex and removes that vertex and all edges associated with that 
    // vertex from the graph and returns true.  However, if the vertex does not 
    // exists or the method just returns false. 
    public boolean removeEWDGraphVertex(V vertexData) 
    {
        if (vertexData == null)
        {
            throw new IllegalArgumentException("null parameter");
        }
        
        boolean result = false; 
        
        for (int i = 0; i < adjList.size(); i++)
        {
         // iterates through the adjacency list to find the vertex if it exists
            if (adjList.get(i).vertexData.equals(vertexData))
            {
                // removes the vertex
                Vertex remove = adjList.get(i);
                adjList.remove(remove);
                result = true; 
            }                               
        }
        
        for (int i = 0; i < adjList.size(); i++)
        {
            // remove all edges associated with the vertex 
            removeEWDGraphEdge(adjList.get(i).vertexData, vertexData);
        }
        
        return result; 
       
    }

    // This method has no parameters and creates and returns a LinkedList 
    // that contains all of the neighbors of the parameter vertex. However, if 
    // there are no neighbors, an empty LinkedList will be returned, and if
    // the vertex does not exists, the method will return null. 
    public Collection<V> getNeighborsOfVertex(V vertexData) 
    {
        if (vertexData == null)
        {
            throw new IllegalArgumentException("null parameter");
        }
        
        LinkedList<V> neighbors = new LinkedList<V>();
        
        if (isEWDGraphVertex(vertexData))
        {
            // searches for vertex
            for (int i = 0; i < adjList.size(); i++)
            {
                // vertex found 
                if (adjList.get(i).vertexData.equals(vertexData))
                {
                    // iterates through neighbors and adds to LinkedLIst
                    Vertex current = adjList.get(i).next;
                    while (current!= null)
                    {
                        neighbors.add(current.vertexData);
                        current = current.next;
                    }               
                }              
            }
            
            return neighbors; 
        }
        // vertex does not exist
        else
        {
            return null; 
        }       
    }

    // This method takes in 2 generic parameters that represents the data in 
    // 2 vertices and merges those 2 vertices into a single vertex that 
    // contains the smaller vertexData and returns true. The method also merges 
    // all of the edges both going from and to either, vertex into the new 
    // vertex, and only keeps the smaller edge weight if both vertices have 
    // the same neighbor. This method returns false if either vertex does not 
    // exist, or there is no edge between them. 
    public boolean consolidateVertices(V vertex1, V vertex2) 
    {
        if (vertex1 == null || vertex2 == null)
        {
            throw new IllegalArgumentException("null parameter");
        }
        
        // checks if the merge is valid 
        if (!isEWDGraphVertex(vertex1) || !isEWDGraphVertex(vertex2) || 
                (getEWDGraphEdge(vertex1, vertex2) == -1 && 
                getEWDGraphEdge(vertex2, vertex1) == -1))
        {
            return false; 
        }
        
        // assigns which vertex is larger, which is smaller 
        V largerVertex; 
        V smallerVertex;       
        if (comparator.compare(vertex1, vertex2) < 0)
        {
            largerVertex = vertex2; 
            smallerVertex = vertex1; 
        }
        else
        {
            largerVertex = vertex1; 
            smallerVertex = vertex2; 
        }
                
        for (int i = 0; i < adjList.size(); i++)
        {
            Vertex current = adjList.get(i);
            // merges the neighbors of larger vertex into new vertex
            if (adjList.get(i).vertexData.equals(largerVertex))
            {
                while (current!= null)
                {
                    if (getEWDGraphEdge(smallerVertex, 
                            current.vertexData) != -1)
                    {
                        if (getEWDGraphEdge(smallerVertex, current.vertexData) 
                                > current.weight)
                        {
                            newEWDGraphEdge(smallerVertex, current.vertexData, 
                                    current.weight);
                        }          
                    }
                    else
                    {
                        newEWDGraphEdge(smallerVertex, current.vertexData, 
                                current.weight);
                    }
                    current = current.next; 
                }
            }
            else
            {
                // current vertex has edges to both merged vertices 
                if (getEWDGraphEdge(current.vertexData, largerVertex) != -1 && 
                       getEWDGraphEdge(current.vertexData, smallerVertex) != -1)
                {
                    int weight;
                    // getting the smaller weight and adjusting edge weight 
                    if (getEWDGraphEdge(current.vertexData, largerVertex) > 
                            getEWDGraphEdge(current.vertexData, smallerVertex))
                    {
                        weight = 
                             getEWDGraphEdge(current.vertexData, smallerVertex);                    
                    }
                    else
                    {
                        weight = 
                             getEWDGraphEdge(current.vertexData, largerVertex);
                    }
                    // remove old edges, and create new edge 
                    removeEWDGraphEdge(current.vertexData, largerVertex);
                    removeEWDGraphEdge(current.vertexData, smallerVertex);
                    newEWDGraphEdge(current.vertexData, smallerVertex, weight);                
                }
                // there is a edge to larger vertex that needs to be moved 
                else if (getEWDGraphEdge(current.vertexData, 
                        largerVertex) != -1)
                {
                    // move edge to new vertex
                    newEWDGraphEdge(current.vertexData, smallerVertex, 
                            getEWDGraphEdge(current.vertexData, largerVertex)); 
                    removeEWDGraphEdge(current.vertexData, largerVertex);
                }
            }         
        }
        // remove larger vertex and return 
        removeEWDGraphVertex(largerVertex);
        
        return true;        
    }
    
    public String toString()
    {
        String result = "";
        for (int i = 0; i < adjList.size(); i++)
        {
            Vertex current = adjList.get(i);
            while (current!= null)
            {
                result +=  " " + current.vertexData + " " + current.weight + " "; 
                current = current.next; 
            }
            
            
        }    
        return result; 
    }
    
}
