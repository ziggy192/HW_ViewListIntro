package com.example.nghia.listviewintro.activities;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.nghia.listviewintro.R;
import com.example.nghia.listviewintro.fragments.DetailFragment;
import com.example.nghia.listviewintro.fragments.OnStudentUpdateListener;
import com.example.nghia.listviewintro.models.Student;

public class DetailActivity extends BaseActivity implements OnStudentUpdateListener {
    private static final String TAG = DetailActivity.class.toString();

    public static final String POSITION_KEY = "position";
    public static final String STUDENT_KEY = "student";


    public static final String OPERATION_KEY = "operation";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        int position = intent.getIntExtra(POSITION_KEY, -1);
        int operation = intent.getIntExtra(OPERATION_KEY, -1);

        switch (operation) {
            case Student.OP_UPDATE:
                if (position != -1) {
                    Student student = Student.names.get(position);

                    //1: Create fragment
                    DetailFragment detailFragment = DetailFragment.create(position, operation);
                    detailFragment.setOnStudentUpdateListener(DetailActivity.this);

                    //3: Get Fragment Manager
                    changeFragment(R.id.fl_detail, detailFragment, false);
                }

//        Student student = (Student) intent.getSerializableExtra(STUDENT_KEY);
//        if (student != null) {
//            DetailFragment detailFragment = DetailFragment.create(student, operation, position);
//            detailFragment.setOnStudentUpdateListener(this);
//            changeFragment(R.id.fl_detail, detailFragment, false);
//        }
                break;

            case (Student.OP_ADD):
                Student.names.add(new Student());
                position = Student.names.size()-1;
                DetailFragment detailFragment = DetailFragment.create(position, Student.OP_ADD);
                detailFragment.setOnStudentUpdateListener(this);

                changeFragment(R.id.fl_detail, detailFragment, false);

                break;
        }


    }


    @Override
    public void onUpdate() {

        Log.d(TAG, "onUpdate");
        finish();
    }
}
