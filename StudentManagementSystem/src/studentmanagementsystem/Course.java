/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package studentmanagementsystem;

/**
 *
 * @author EliteBook
 */
public class Course {
    private int courseId;
    private String courseName;
    private int creditCourse;
    private String courseInstuctor;
    public Course(int courseId,String courseName,int creditCourse , String courseInstructor)
    {
        this.courseId=courseId;
        this.courseName=courseName;
        this.creditCourse=creditCourse;
        this.courseInstuctor=courseInstructor;
    }
    
    public int getCourseId()
    {
        return courseId;
    }
    public void setCourseId(int id)
    {
        this.courseId=id;
    }
    
    public String getCourseName()
    {
        return courseName;
    }
    public void setCourseName(String courseName)
    {
        this.courseName=courseName;
    }
    public int getCreditHours()
    {
        return creditCourse;
    }
    public void setCreditHourse(int creditHourse)
    {
        this.creditCourse=creditHourse;
    }
    public String getCourseInstructor()
    {
        return courseInstuctor;
    }
    public void setCourseInstructor(String courseInstructor)
    {
        this.courseInstuctor=courseInstructor;
    }
    
    
}
