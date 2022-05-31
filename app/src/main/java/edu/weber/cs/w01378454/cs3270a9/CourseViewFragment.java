package edu.weber.cs.w01378454.cs3270a9;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;

import edu.weber.cs.w01378454.cs3270a9.DB.AppDatabase;
import edu.weber.cs.w01378454.cs3270a9.DB.Course;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CourseViewFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CourseViewFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private View root;

    private TextInputLayout Id;
    private TextInputLayout Name;
    private TextInputLayout CourseName;
    private TextInputLayout CourseCode;
    private TextInputLayout StartAt;
    private TextInputLayout EndAt;



    FragmentManager fm = getFragmentManager();

    public CourseViewFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static CourseViewFragment newInstance(String param1, String param2) {
        CourseViewFragment fragment = new CourseViewFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);


        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return root = inflater.inflate(R.layout.fragment_course_view, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();

        Id = root.findViewById(R.id.id);
        Name = root.findViewById(R.id.Name);
        CourseName = root.findViewById(R.id.Course_Name);
        CourseCode = root.findViewById(R.id.Course_Code);
        StartAt = root.findViewById(R.id.Start_At);
        EndAt = root.findViewById(R.id.End_At);

        FloatingActionButton CourseViewFab = root.findViewById(R.id.fabSaveCourseInfo);
        CourseViewFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String id = Id.getEditText().getText().toString();
                final String name  = Name.getEditText().getText().toString();
                final String course_name = CourseName.getEditText().getText().toString();
                final String course_code = CourseCode.getEditText().getText().toString();
                final String start_at = StartAt.getEditText().getText().toString();
                final String end_at = EndAt.getEditText().getText().toString();

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        //Background

                        AppDatabase db = AppDatabase.getInstance(getContext());
                        //Add a new course to the database (via the UI & in the DAO)
                        db.CourseDAO().AddACourse(new Course(id,name, course_name,course_code, start_at, end_at));

                    }
                }).start();

                Toast toast = Toast.makeText(getContext(), R.string.newcourseadded, Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();

                Id.getEditText().setText("");
                Name.getEditText().getText().toString();
                CourseName.getEditText().setText("");
                CourseCode.getEditText().setText("");
                StartAt.getEditText().setText("");
                EndAt.getEditText().setText("");
                AppDatabase db = AppDatabase.getInstance(getContext());

                fm = getFragmentManager();
                fm.beginTransaction()
                        .replace(R.id.CourseListFragmentHolder, new CourseListFragment(), "Course_List_Fragment")
                        .addToBackStack(null)
                        .commit();

            }
        });

    }
}