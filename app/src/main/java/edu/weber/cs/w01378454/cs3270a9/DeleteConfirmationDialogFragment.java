package edu.weber.cs.w01378454.cs3270a9;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import edu.weber.cs.w01378454.cs3270a9.DB.AppDatabase;
import edu.weber.cs.w01378454.cs3270a9.DB.Course;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DeleteConfirmationDialogFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DeleteConfirmationDialogFragment extends DialogFragment
{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private View root;

    private Course course;



    public DeleteConfirmationDialogFragment(Course course) {

        this.course = course;
    }

    public DeleteConfirmationDialogFragment()
    {
        // Required empty public constructor
    }

    @Override
    public void onStart()
    {
        super.onStart();
    }

    @Override
    public void onResume()
    {
        super.onResume();
    }

    // TODO: Rename and change types and number of parameters
    public static DeleteConfirmationDialogFragment newInstance(String param1, String param2)
    {
        DeleteConfirmationDialogFragment fragment = new DeleteConfirmationDialogFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle(R.string.deletecourse);
        builder.setMessage(R.string.delete_warning)
                .setPositiveButton(getString(R.string.delete), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                          deleteCourse();
                    }
                })

                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

        return builder.create();
    }

    public void deleteCourse()
    {
        new Thread(new Runnable() {
            @Override
            public void run() {
                //Show db entities to debug console
                AppDatabase db = AppDatabase.getInstance(getContext());
                db.CourseDAO().DeleteSelectedCourse(course);
            }
        }).start();

        FragmentManager fm = getFragmentManager();
        fm.beginTransaction()
                .replace(R.id.CourseListFragmentHolder, new CourseListFragment(),"Course_List_Fragment" )
                .addToBackStack(null)
                .commit();
    }
}