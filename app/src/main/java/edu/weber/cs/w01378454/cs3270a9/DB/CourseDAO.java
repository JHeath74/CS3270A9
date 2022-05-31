package edu.weber.cs.w01378454.cs3270a9.DB;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface CourseDAO {


    //Retrieve a list of courses (in the DAO)
    @Query("Select * from Course")
    LiveData<List<Course>> ListAllCourses();

    //View the details of a selected course (in the DAO)
    @Query("Select * from Course WHERE id = :id LIMIT 1")
    Course getCourseByID(String id);

    @Update
    void EditASelectedCourse(Course courses); //Edit a selected course (in the DAO)

    //Delete a selected course (in the DAO)
    @Delete
    void DeleteSelectedCourse(Course courses); //Delete a selected course (in the DAO)

    //Add a new course to the database (via the UI & in the DAO)
    @Insert
    void AddACourse(Course courses); //Update Selected Course

}
