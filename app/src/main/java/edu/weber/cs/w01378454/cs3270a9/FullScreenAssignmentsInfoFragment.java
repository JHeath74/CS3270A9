package edu.weber.cs.w01378454.cs3270a9;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

import edu.weber.cs.w01378454.cs3270a9.DB.Course;


public class FullScreenAssignmentsInfoFragment extends DialogFragment {

    private View root;
    private RecyclerView recyclerView;
    private CourseRecyclerAdapter adapter;
    private GetCanvasCourses task;

    private Activity activity;

    FragmentManager fm;


    public FullScreenAssignmentsInfoFragment(Course course)
    {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setStyle(FullScreenAssignmentsInfoFragment.STYLE_NORMAL, R.style.AppTheme_Dialog_FullScreen);

    }

    @Override
    public void onResume() {
        super.onResume();

        Context context = getContext();

        recyclerView = root.findViewById(R.id.assignmentListRecyclerView);
        adapter = new CourseRecyclerAdapter(new ArrayList<Course>(), activity, context);

        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(false);

        new ViewModelProvider(this)
                .get(AllCoursesViewModel.class)
                .getAllCoursesList(context)
                .observe(this, new Observer<List<Course>>() {

                    @Override
                    public void onChanged(List<Course> courses) {
                        if(courses != null)
                        {
                            adapter.setCourseList(courses);
                        }
                    }
                });

        updateFromCanvas();

    }

    void updateFromCanvas()
    {
        task = new GetCanvasCourses();
        task.setOnCourseListComplete(new GetCanvasCourses.OnCourseListComplete() {
            @Override
            public void processCourseList(Course[] courses) {
                if(courses != null)
                {
                    adapter.clear();
                    ArrayList<Course> courseList = new ArrayList<>();
                    for(Course course:courses)
                    {
                        courseList.add(course);
                    }
                    adapter.setCourseList(courseList);
                }
            }
        });
        task.execute("https://weber.instructure.com/api/v1/courses/:course_id/assignments");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        return root = inflater.inflate(R.layout.fragment_full_screen_assignments_info, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        requireDialog().getWindow().setWindowAnimations(R.style.AppTheme_DialogAnimation);

        Toolbar toolbar = view.findViewById(R.id.assignmentToolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        toolbar.inflateMenu(R.menu.fullscreenassignment);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                dismiss();

                return true;
            }
        });

    }
}