package com.example.nghia.listviewintro.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.nghia.listviewintro.R;
import com.example.nghia.listviewintro.models.Student;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFragment extends Fragment {
    private static final String TAG = DetailFragment.class.toString();


    private Student student;
    private EditText tvStudentName;
    private EditText tvStudentAge;
    private EditText tvStudentGender;
    private Button btnSave;
    OnStudentUpdateListener onStudentUpdateListener;

    private int operation;
    private int position;

    public DetailFragment() {
        // Required empty public constructor
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public void setOperation(int operation) {
        this.operation = operation;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detail, container, false);

        getReferrences(view);
        setupUI();
        addListengers();
        return view;

    }

    public void setOnStudentUpdateListener(OnStudentUpdateListener onStudentUpdateListener) {
        this.onStudentUpdateListener = onStudentUpdateListener;
    }

    private void addListengers() {
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d(TAG, "btnSave.onClicked");
//                if (operation == Student.OP_UPDATE) {

                student.setName(tvStudentName.getText().toString());
                try {

                    student.setAge(Integer.parseInt(tvStudentAge.getText().toString()));
                } catch (Exception ex) {
                    student.setAge(0);
                }


                student.setGender(tvStudentGender.getText().toString());

                Log.d(TAG, String.format("onClick: Student: %s", student));
                Log.d(TAG, String.format("onClick: Name(position) : %s", Student.names.get(position)));

                if (onStudentUpdateListener != null) {
                    onStudentUpdateListener.onUpdate();
                }
//                }
            }
        });
    }

    private void setupUI() {
        if (operation == Student.OP_UPDATE) {
            tvStudentName.setText(String.format("%s", student.getName()));
            tvStudentAge.setText(String.format("%s", student.getAge()));
            tvStudentGender.setText(String.format("%s", student.getGender()));

        } else {
            tvStudentName.setText("");
            tvStudentAge.setText("");
            tvStudentGender.setText("");
        }

    }

    private void getReferrences(View view) {
        tvStudentName = (EditText) view.findViewById(R.id.tv_name);
        tvStudentAge = (EditText) view.findViewById(R.id.tv_age);
        tvStudentGender = (EditText) view.findViewById(R.id.tv_gender);
        btnSave = (Button) view.findViewById(R.id.btn_save);
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public static DetailFragment create(int position, int operation) {
        DetailFragment detailFragment = new DetailFragment();

        detailFragment.setPosition(position);
        detailFragment.setStudent(Student.names.get(position));
        detailFragment.setOperation(operation);
        return detailFragment;
    }

}
