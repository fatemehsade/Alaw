package com.example.alawapplication.view.activity;

import androidx.fragment.app.Fragment;

import com.example.alawapplication.view.fragment.InformationFragment;

public class InformationActivity extends SingleFragmentActivity {

    @Override
    public Fragment createFragment() {
        return new InformationFragment();
    }


}