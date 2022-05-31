package edu.weber.cs.w01378454.cs3270a9;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import edu.weber.cs.w01378454.cs3270a9.DB.AppDatabase;
import edu.weber.cs.w01378454.cs3270a9.DB.Course;

public class AllCoursesViewModel extends ViewModel
{
    private LiveData<List<Course>> AllCoursesList;

    public LiveData<List<Course>> getAllCoursesList(Context context) {

        AppDatabase db = AppDatabase.getInstance(context);

        AllCoursesList = db.CourseDAO().ListAllCourses();

        return AllCoursesList;

    }
}
