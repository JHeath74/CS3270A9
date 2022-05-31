package edu.weber.cs.w01378454.cs3270a9;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import edu.weber.cs.w01378454.cs3270a9.DB.Course;

public class CourseListFragment extends Fragment{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private View root;
    private RecyclerView recyclerView;
    private CourseRecyclerAdapter adapter;
    private GetCanvasCourses task;

    private Activity activity;

    MenuItem update;


    FragmentManager fm = getFragmentManager();

    public CourseListFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static CourseListFragment newInstance(String param1, String param2) {
        CourseListFragment fragment = new CourseListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Activity activity) {
        super.onAttach(activity);

        this.activity = activity;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        setHasOptionsMenu(true);

    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        update = menu.add("Update");
        update.setIcon(R.drawable.ic_baseline_download_24);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {


        switch (item.getItemId())
        {
            case R.id.updatefromcanvas:
                updateFromCanvas();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

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
        task.execute("");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return root = inflater.inflate(R.layout.fragment_course_list, container, false);
    }

    @Override
    public String toString() {
        return "CourseListFragment{" +
                "mParam1='" + mParam1 + '\'' +
                ", mParam2='" + mParam2 + '\'' +
                ", root=" + root +
                ", recyclerView=" + recyclerView +
                ", adapter=" + adapter +
                ", activity=" + activity +
                ", fm=" + fm +
                '}';
    }

    @Override
    public void onResume() {
        super.onResume();

        FloatingActionButton CourseListFab = root.findViewById(R.id.CourseListFab);
        CourseListFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fm = getFragmentManager();
                fm.beginTransaction()
                        .replace(R.id.CourseListFragmentHolder, new CourseViewFragment(),"Course_View_Fragment" )
                        .addToBackStack(null)
                        .commit();
            }
        });


        Context context = getContext();

        recyclerView = root.findViewById(R.id.courseListRecyclerView);
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


}