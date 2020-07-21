package com.example.nightclass;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.nightclass.Utils.LetterImageView;

public class CourseActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ListView listView;
    private String[] courses;
    public static SharedPreferences coursePreferences;
    public static String COURSE_PREF;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);

        setupUIViews();
        initToolbar();
        setupListView();
    }

    private void setupUIViews() {
        toolbar = findViewById(R.id.ToolbarCourse);
        listView = findViewById(R.id.lvCourse);
        coursePreferences = getSharedPreferences("Courses",MODE_PRIVATE);
    }
    private void initToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Course");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void setupListView() {
        courses = getResources().getStringArray(R.array.Courses);

        CourseAdapter courseAdapter = new CourseAdapter(this,R.layout.course_single_item,courses);
        listView.setAdapter(courseAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:{
                        coursePreferences.edit().putString(COURSE_PREF, "Mathematics").apply();
                        Intent intent = new Intent(CourseActivity.this, CourseDetails.class);
                        startActivity(intent);
                        break;
                    }
                    case 1:{
                        coursePreferences.edit().putString(COURSE_PREF, "Physics").apply();
                        Intent intent = new Intent(CourseActivity.this, CourseDetails.class);
                        startActivity(intent);
                        break;
                    }
                    case 2:{
                        coursePreferences.edit().putString(COURSE_PREF, "Chemistry").apply();
                        Intent intent = new Intent(CourseActivity.this, CourseDetails.class);
                        startActivity(intent);
                        break;
                    }
                }

            }
        });
    }

    public class CourseAdapter extends ArrayAdapter {

        private int resource;
        private LayoutInflater layoutInflater;
        private String[] courses = new String[]{};

        public CourseAdapter(Context context, int resource, String[] objects) {
            super(context, resource, objects);
            this.resource = resource;
            this.courses = objects;
            layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder holder;
            if (convertView == null){
                holder = new ViewHolder();
                convertView = layoutInflater.inflate(resource,null);
                holder.ivLogo = convertView.findViewById(R.id.ivLetterCourse);
                holder.tvCourse = convertView.findViewById(R.id.tvCourse);
                convertView.setTag(holder);
            }
            else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.ivLogo.setOval(true);
            holder.ivLogo.setLetter(courses[position].charAt(0));
            holder.tvCourse.setText(courses[position]);

            return convertView;
        }

        class ViewHolder{
            private LetterImageView ivLogo;
            private TextView tvCourse;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home : {
                onBackPressed();
            }
        }
        return super.onOptionsItemSelected(item);
    }

}
