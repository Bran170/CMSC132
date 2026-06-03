
package studentCourseGrades;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class StudentCourseGrades {
   
    private Map<String, Map<String, Character>> courseGrades;
    
    public StudentCourseGrades()
    {
        courseGrades = new LinkedHashMap<String, Map<String, Character>>();
    }
    
    public boolean addGrade(String studentName, String courseName,
                            char grade) 
    {
        if (studentName == null || courseName == null)
        {
            throw new IllegalArgumentException(" null parameter");
        }
        else if (!(grade == 'A' ||
                grade == 'B' || grade == 'C' || grade == 'D' || 
                grade == 'F'))
        {
            throw new IllegalArgumentException(" invalid parameter");
        }
        boolean result = false; 
        Set<String> keySet = courseGrades.keySet();
        if (keySet.contains(studentName))
        {
            if (!courseGrades.get(studentName).containsKey(courseName))
            {
                Map<String, Character> grades = courseGrades.get(studentName);
                grades.put(courseName, grade);
                result = true; 
            }
            else
            {
                result = false; 
            }               
        }
        else
        {
            LinkedHashMap<String, Character> grades = 
                    new LinkedHashMap<String, Character>();
            grades.put(courseName, grade);
            courseGrades.put(studentName, grades); 
            result = true; 
        }
        
        return result; 
        
    }

    public int numStudents() 
    {
        return courseGrades.size();
    }

    public int numCourses() 
    {
        Set<String> keySet = new HashSet<String>();
        for (String studentName : courseGrades.keySet()) 
        {
            for (String courseName: courseGrades.get(studentName).keySet())
            {
                if (!keySet.contains(courseName))
                {
                    keySet.add(courseName);
                }
            }
        }
        return keySet.size(); 
    }

    public int numCoursesTaking(String studentName) 
    {
        int num = 0; 
        if (studentName == null)
        {
            throw new IllegalArgumentException(" null parameter");
        }
        for (String name: courseGrades.keySet())
        {
            if (name.equals(studentName))
            {
                Map<String, Character> grades = courseGrades.get(studentName);
                num = grades.size();
            }
        }
        return num;       
    }

    public int numInCourse(String courseName) 
    {
        if (courseName == null)
        {
            throw new IllegalArgumentException(" null parameter");
        }
        int sum = 0;
        for (String studentName : courseGrades.keySet()) 
        {
            for (String name: courseGrades.get(studentName).keySet())
            {
                if (name.equals(courseName))
                {
                    sum ++; 
                }
            }
        }
        return sum; 
    }

    public double getGPA(String studentName) 
    {
        if (studentName == null)
        {
            throw new IllegalArgumentException(" null parameter");
        }
        double pointSum = -1.0; 
        for (String nameStudent : courseGrades.keySet()) 
        {
            if (nameStudent.equals(studentName))
            {
                pointSum = 0.0;
                Map<String, Character> grades = courseGrades.get(studentName);
                for (String courseName: grades.keySet())
                {                 
                    pointSum += getPointSum(
                            courseGrades.get(studentName), courseName); 
                }
            }            
        }          
        if (pointSum == -1.0)
        {
            return pointSum;
        }
        else
        {
            return pointSum / numCoursesTaking(studentName);
        }
    }

    public double averageCourseGPA(String courseName) 
    {
        if (courseName == null)
        {
            throw new IllegalArgumentException(" null parameter");
        }
        double pointSum = -1.0; 
        for (String studentName : courseGrades.keySet()) 
        {
            for (String name: courseGrades.get(studentName).keySet())
            {
                if (name.equals(courseName))
                {
                    if (pointSum == -1.0)
                    {
                        pointSum = 0.0;
                    }
                    pointSum += getPointSum(
                            courseGrades.get(studentName), courseName); 
                }
            }
        }
        if (pointSum == -1.0)
        {
            return pointSum;
        }
        else
        {
            return pointSum / numInCourse(courseName);
        }
    }
    
    private double getPointSum(Map<String, Character> grades, String courseName)
    {
        double pointSum = 0.0; 
        if (grades.get(courseName).equals('A'))
        {
            pointSum += 4;  
        }
        else if (grades.get(courseName).equals('B'))
        {
            pointSum += 3;
        }
        else if (grades.get(courseName).equals('C'))
        {
            pointSum += 2;
        }
        else if (grades.get(courseName).equals('D'))
        {
            pointSum += 1;
        }
        return pointSum; 
    }

}
