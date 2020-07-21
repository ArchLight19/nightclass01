package com.example.nightclass;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class CourseDetails extends AppCompatActivity {

    private Toolbar toolbar;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_details);

        setupUIViews();
        initToolbar();
        setupListView();
    }

    private void setupUIViews() {
        toolbar = findViewById(R.id.ToolbarCourseDetails);
        listView = findViewById(R.id.lvCourseDetails);
    }
    private void initToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Syllabus");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void setupListView()  {
        String course_selected = CourseActivity.coursePreferences.getString(CourseActivity.COURSE_PREF, null);

        String [] syllabus = new String[]{};
        String [] titles = getResources().getStringArray(R.array.titles);

        if (course_selected.equalsIgnoreCase("Mathematics")) {
            syllabus = getResources().getStringArray(R.array.Maths);
        } else if (course_selected.equalsIgnoreCase("Physics")) {
            syllabus = getResources().getStringArray(R.array.Phy);
        } else {
            syllabus = getResources().getStringArray(R.array.Chem);
        }

        CourseDetailsAdapter courseDetailsAdapter = new CourseDetailsAdapter(this,titles,syllabus);
        listView.setAdapter(courseDetailsAdapter);
    }

    public class CourseDetailsAdapter extends BaseAdapter {

        private Context mContext;
        private LayoutInflater layoutInflater;
        private TextView title, syllabus;
        private String[] titleArray;
        private String[] syllabusArray;

        public CourseDetailsAdapter(Context context, String[] title, String[] syllabus) {
            mContext = context;
            titleArray = title;
            syllabusArray = syllabus;
            layoutInflater = LayoutInflater.from(context);
        }


        @Override
        public int getCount() {
            return titleArray.length;
        }

        @Override
        public Object getItem(int position) {
            return titleArray[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = layoutInflater.inflate(R.layout.course_details_single_item, null);
            }

            title = convertView.findViewById(R.id.tvCourseTitle);
            syllabus = convertView.findViewById(R.id.tvSyllabus);

            title.setText(titleArray[position]);
            syllabus.setText(syllabusArray[position]);

            return convertView;
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
