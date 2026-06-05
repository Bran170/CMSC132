package fazebook;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

/* Fazebook represents a rudimentary social networking platform where people 
 * can join as new users and after they join, they can choose to friend other 
 * users on the platform. Each instance of this class represents a separate 
 * instance of the platform, with its own users and friendships. This class has
 * 2 fields, a comparator that is used to compare data, and an EWDGraph that 
 * stores all of the users as vertices and their friendships as edges. This 
 * class also has multiple methods that perform various operations on the 
 * social networking platform like creating new users, new friendships, and 
 * giving friendship recommendations.  
 */

public class Fazebook 
{
    private Comparator<String> comparator;
    private EWDGraph<String> fazebook;
           
    public Fazebook()
    {
        fazebook = new EWDGraph<String>(comparator);
    }
    
    // This inner class is used as to represent threads that are used we are 
    // reading files and adding new users and creating friendships based on 
    // their contents. Each instance of this class is a thread, and it is used 
    // when we need to concurrently do operations when reading from 
    // different files. 
    private class FileThread extends Thread
    {
        private String fileName;
        
        public FileThread(String fileName)
        {
            this.fileName = fileName; 
        }
        
        public void run()
        {
            if (fileName == null)
            {
                return; 
            }
            //creates file object
            File file = new File(fileName);
            
            try 
            {
                Scanner sc = new Scanner(file);
                
                while (sc.hasNext()) 
                {
                    String keyword = sc.next();   
                    // adds new friendship as specified from next 2 words
                    if (keyword.equals("addfriends"))
                    {
                        if (sc.hasNext())
                        {                         
                            String friend1 = sc.next();                            
                            if (sc.hasNext())
                            {                         
                                addFriends(friend1, sc.next());
                            }
                        }                                                                                                                                                   
                    }
                    // adds new user as specified in file 
                    else if (keyword.equals("adduser")) 
                    {
                        if (sc.hasNext())
                        {
                            addUser(sc.next());
                        }
                    }
                  
                }                                     
                sc.close();
            }
            
            catch (FileNotFoundException e) 
            {
                e.printStackTrace();
            }
            
        }
           
    }            
        
    // This method takes in a String parameter that represents the username
    // of a new user and creates a new user with that data in the current 
    // fazebook object with 0 friends and returns true. However, if there is 
    // already a user with the same username, the method returns false and 
    // does nothing. This method also returns false if the parameter is empty 
    // or null. 
    public boolean addUser(String userName) 
    {
        if (userName == null || userName.equals(""))
        {
            return false;
        }
        else
        {
            synchronized (this)
            {
                // uses the graph method to add new vertices ( user)
                return fazebook.newEWDGraphVertex(userName);
            }
                  
        }

    }

    // This method has no parameters and creates and returns a LinkedList 
    // that contains all of the usernames of all current user in the current 
    // fazebook object. However, if there are no users, an empty LinkedList 
    // will be returned. 
    public Collection<String> getAllUsers() 
    {
        return fazebook.getEWDGraphVertices();
    }

    // This method takes in 2 String parameters that represents the usernames 
    // of in 2 current users and creates a new friendship between the users and
    // returns true. A friendship is represented by edges going in both 
    // directions between the 2 users. However, If the 2 parameter strings are 
    // the same or the friendship already exists, the method will do nothing 
    //and return false. Finally, if one or both of the users do not yet exists, 
    // but the parameters are valid, then the users will be added before the 
    // friendship is made. 
    public boolean addFriends(String userName1, String userName2) 
    {
        if (userName1 == null || userName1.equals("") || userName2 == null 
                || userName2.equals(""))
        {
            return false;
        }
        else
        {
            synchronized (this)
            {
                // uses the graph methods for creating edges 
                return fazebook.newEWDGraphEdge(userName1, userName2, 1) && 
                        fazebook.newEWDGraphEdge(userName2, userName1, 1);
            }
          
        }
       
    }
    
    // This method takes in a string parameter that represents the username of 
    // a user in the current fazebook object. It returns a LinkedList
    // containing all of the friends of the specified user. 
    // However, if either the user does not exists the method just returns 
    // an empty LinkedList. 
    public Collection<String> getFriends(String userName) 
    {
        if (userName == null || userName.equals(""))
        {
            return null;
        }
        synchronized (this)
        {
            return fazebook.getNeighborsOfVertex(userName);
        }
       
    }

    // This method takes in 2 String parameters that represents the usernames
    // of 2 users and removes the friendship between them if it 
    // exists and returns true. However, if either user does not exists or 
    // the friendship does not exists, the method just returns false. 
    public boolean unfriend(String userName1, String userName2) 
    {
        if (userName1 == null || userName1.equals("") || userName2 == null 
                || userName2.equals(""))
        {
            return false;
        }
        synchronized(this)
        {
            // removes the edges in both directions 
            return fazebook.removeEWDGraphEdge(userName1, userName2) &&
                    fazebook.removeEWDGraphEdge(userName2, userName1);
        }
   
    }

    // This method takes in a string parameter that represents the username of 
    // a current user and returns a LinkedList of all users who are not 
    // currently friends of the user but friends of the friends of the user. 
    // However, if the user does not exists or has no current friends, it just
    // returns an empty LinkedList. 
    public Collection<String> peopleYouMayWannaKnow(String userName) 
    {
        if (userName == null || userName.equals(""))
        {
            return null;
        }
        
        Collection<String> peopleYouMayWannaKnow = new LinkedList<String>();
        Collection<String> friends = getFriends(userName);
        
        if (friends == null) // no friends so returns empty LinkedList 
        {
            return peopleYouMayWannaKnow;
        }
           
        // iterates through all of the users current friends 
        for (String friend: friends)
        {
            Collection<String> friendsOfFriends = getFriends(friend);
            for (String friendOfFriend: friendsOfFriends)
            {
                // if the user isn't already a friend, add to collection 
                if (!friends.contains(friendOfFriend) && 
                        !peopleYouMayWannaKnow.contains(friendOfFriend) && 
                        !friendOfFriend.equals(userName))
                {
                    peopleYouMayWannaKnow.add(friendOfFriend);
                }
            }
        }
        
        return peopleYouMayWannaKnow;
        
    }

    // This method takes in a collection of Strings that represent the names of 
    // files. It creates and runs a Thread for each file concurrently, 
    // extracting its contents and adding new users and friendships as 
    // specified. If the parameter is null it will return false, 
    // otherwise it returns true. 
    public boolean readSocialNetworkData(Collection<String> filenames) 
    {
        if (filenames == null)
        {
            return false; 
        }
        
        ArrayList<FileThread> threads = new ArrayList<FileThread>();

        for (String fileName : filenames) 
        {
            FileThread newThread = new FileThread(fileName); 
            newThread.start();
            threads.add(newThread);
        } 

        // join all threads so we wait for every file to finish loading
        for (FileThread thread : threads)
        {
            try 
            {
                thread.join();
            } 
            catch (InterruptedException e) 
            {
                Thread.currentThread().interrupt(); // preserve interrupt status
                e.printStackTrace();
            }
        }
           
            return true; 
        }
          
    
    public String toString()
    {
        return fazebook.toString();
    }

}
