package com.example.gpaplanner;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class ListAdapter extends BaseAdapter {

    private List<Course> courseData;
    private Activity activity;
    private static LayoutInflater inflater=null;

    public ListAdapter(Activity a, List<Course> courseData) {
        activity = a;
        this.courseData = courseData;
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return courseData.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View vi =convertView;

        if(convertView==null)

            vi = inflater.inflate(R.layout.course_item,null);


        TextView tv_title= (TextView)vi.findViewById(R.id.et_courseName);
        TextView tv_lecturer = (TextView)vi.findViewById(R.id.et_courseTeacher);


        Course course = courseData.get(position);

        tv_title.setText(course.getCourseName());
        tv_lecturer.setText(course.getTeacher());


        return vi;
    }
}
