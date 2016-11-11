package com.example.nghia.listviewintro.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.nghia.listviewintro.R;
import com.example.nghia.listviewintro.fragments.DetailFragment;
import com.example.nghia.listviewintro.fragments.OnStudentUpdateListener;
import com.example.nghia.listviewintro.models.Student;

import static com.example.nghia.listviewintro.activities.DetailActivity.OPERATION_KEY;
import static com.example.nghia.listviewintro.activities.DetailActivity.POSITION_KEY;
import static com.example.nghia.listviewintro.activities.DetailActivity.STUDENT_KEY;

public class MainActivity extends BaseActivity implements OnStudentUpdateListener {


    private ListView lvStudents;
    private String TAG = MainActivity.class.toString();
    ArrayAdapter<Student> studentArrayAdapter;

    private Button btnAdd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getReferences();
        setupUI();

    }

    private void setupUI() {
        // Create Adapter
        studentArrayAdapter = new
                ArrayAdapter<>(this,android.R.layout.simple_list_item_1,Student.names);
        lvStudents.setAdapter(studentArrayAdapter);

        lvStudents.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Student student = Student.names.get(position);
                Log.d(TAG, String.format("%s was tapped", student));



                if (findViewById(R.id.fl_detail)==null){
                    Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                    intent.putExtra(POSITION_KEY, position);
//                    intent.putExtra(STUDENT_KEY, student);
                    intent.putExtra(OPERATION_KEY, Student.OP_UPDATE);
                    startActivity(intent);

                }
                else{
                    DetailFragment detailFragment =  DetailFragment.create(
                            position,
                            Student.OP_UPDATE

                    );
                    detailFragment.setOnStudentUpdateListener(MainActivity.this);
                    changeFragment(R.id.fl_detail,detailFragment,true);

                }
            }
        });

        lvStudents.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Student name = Student.names.get(position);
                Log.d(TAG, String.format("%s was long clicked", name));
                Student.names.remove(position);
                studentArrayAdapter.notifyDataSetChanged();

//                return false;

                //when dont want next Listener to handle
                return  true;
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                intent.putExtra(OPERATION_KEY, Student.OP_ADD);
                startActivity(intent);
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onRestart() {

        Log.d(TAG, "onRestart");
        Student.printStudentList();


        studentArrayAdapter.notifyDataSetChanged();
        super.onRestart();
    }

    public void getReferences() {
        lvStudents = (ListView) findViewById(R.id.lv_student);
        btnAdd = (Button) findViewById(R.id.btn_add);
    }

    @Override
    public void onUpdate() {
        studentArrayAdapter.notifyDataSetChanged();
    }
}
