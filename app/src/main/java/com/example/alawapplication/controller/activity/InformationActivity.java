package com.example.alawapplication.controller.activity;

import androidx.fragment.app.Fragment;

import com.example.alawapplication.controller.fragment.InformationFragment;

public class InformationActivity extends SingleFragmentActivity {

    @Override
    public Fragment createFragment() {
        return new InformationFragment();
    }


}