package com.example.alawapplication.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.alawapplication.R;
import com.example.alawapplication.adapter.InformationAdapter;
import com.example.alawapplication.databinding.FragmentInformationBinding;
import com.example.alawapplication.model.InformationItems;
import com.example.alawapplication.viewModel.AlaaInformationViewModel;

import java.util.List;


public class InformationFragment extends Fragment {

    private FragmentInformationBinding mBinding;
    public static final String TAG = "alaw";
    private AlaaInformationViewModel mInformationViewModel;



    public InformationFragment() {
        // Required empty public constructor
    }


    public static InformationFragment newInstance() {
        InformationFragment fragment = new InformationFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //InformationAdapter.setUpThumbnailDownloader();
        mInformationViewModel=new ViewModelProvider(this).get(AlaaInformationViewModel.class);
        Toast.makeText(getActivity(), "viewModel", Toast.LENGTH_SHORT).show();
        mInformationViewModel.fetchItemsAsync();
        mInformationViewModel.getItemsLiveData().observe(this, new Observer<List<InformationItems>>() {
            @Override
            public void onChanged(List<InformationItems> items) {
                setUpAdapter(mInformationViewModel.getItem());
            }
        });






    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding= DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_information,
                container,
                false);

        initViews();

        //setUpAdapter();
        return mBinding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        setUpAdapter(mInformationViewModel.getItem());
    }

    private void setUpAdapter(List<InformationItems> items) {
        InformationAdapter adapter = new InformationAdapter(getActivity(),items);
        mBinding.recyclerViewInformation.setAdapter(adapter);
    }

    private void initViews() {
        mBinding.recyclerViewInformation.setLayoutManager(new LinearLayoutManager(getActivity()));
    }



}