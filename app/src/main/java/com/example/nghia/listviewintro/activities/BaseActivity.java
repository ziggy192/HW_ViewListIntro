package com.example.nghia.listviewintro.activities;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.example.nghia.listviewintro.R;
import com.example.nghia.listviewintro.fragments.DetailFragment;

/**
 * Created by Nghia on 11/8/2016.
 */

public class BaseActivity extends AppCompatActivity {
    public void changeFragment(int fragId, Fragment detailFragment, boolean addToBackStack) {
        FragmentManager fragmentManager = this.getSupportFragmentManager();


        //4: Start replacing
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        //5:
        fragmentTransaction.replace(fragId, detailFragment);

        //6: (Optional)]
        if (addToBackStack) {

            fragmentTransaction.addToBackStack(null);
        }

        //7:
        fragmentTransaction.commit();
    }
}
