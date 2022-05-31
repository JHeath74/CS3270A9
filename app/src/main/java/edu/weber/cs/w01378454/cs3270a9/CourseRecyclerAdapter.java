package edu.weber.cs.w01378454.cs3270a9;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import edu.weber.cs.w01378454.cs3270a9.DB.Course;

public class CourseRecyclerAdapter extends RecyclerView.Adapter<CourseRecyclerAdapter.AllCourseViewHolder>
{
    private List<Course> courseList;
    private OnListChange mCallback;
    private GetCanvasCourses task;
    private FragmentManager fm;
    private Context context;

    interface OnListChange
    {
        void OnListUpdate(Course course);
    }


    public CourseRecyclerAdapter(List<Course> courseList, Activity activity, Context context) {
        this.courseList = courseList;
        this.mCallback = (OnListChange) activity;
        this.context = context;
    }

    public void setCourseList(List<Course> courses)
    {
        courseList.clear();
        courseList.addAll(courses);
        notifyDataSetChanged();
    }

    public void clear()
    {
        this.courseList.clear();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public AllCourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.courseview,parent,false);

        return new AllCourseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AllCourseViewHolder holder, int position)
    {
       Course course = courseList.get(position);

       if(course != null)
       {
           holder.item = course;
           holder.tvName.setText(course.getName());



           holder.itemRoot.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {

                  mCallback.OnListUpdate(course);
                   Log.d("Adapter Course Info: ", course.toString());


               }
           });
       }

       if(course != null)
       { //I need to display assignment information.
        /*   holder.item = course;
           holder.tvName.setText(course.getName());*/

           holder.itemRoot.setOnLongClickListener(new View.OnLongClickListener() {
               @Override
               public boolean onLongClick(View v) {

                   FragmentActivity activity = (FragmentActivity)(context);
                   FragmentManager fm = activity.getSupportFragmentManager();
                   FullScreenAssignmentsInfoFragment alertDialog = new FullScreenAssignmentsInfoFragment(course);
                   alertDialog.show(fm, "fragment_course_info");

                   return false;
               }
           });

       }

    }

    @Override
    public int getItemCount() {
        return courseList.size();

    }

    //ViewHolders Hold the ui of an individual item in the list.
    public class AllCourseViewHolder extends RecyclerView.ViewHolder
    {
        public View itemRoot;
        public TextView tvName;
        public Course item;

        public AllCourseViewHolder(View view)
        {
            super(view);
            itemRoot = view;

            tvName = itemRoot.findViewById(R.id.tvName);

        }

    }


}
