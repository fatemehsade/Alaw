package com.example.alawapplication.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.alawapplication.R;
import com.example.alawapplication.adapter.InformationAdapter;
import com.example.alawapplication.model.InformationItems;
import com.example.alawapplication.viewModel.AlaaInformationViewModel;

import java.util.List;


public class InformationFragment extends Fragment {

    public static final String TAG = "alaw";
    private RecyclerView mRecyclerView;
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
                setUpAdapter(items);
            }
        });




    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_information, container, false);
        findViews(view);
        initViews();
        //setUpAdapter();
        return view;
    }



    private void setUpAdapter(List<InformationItems> items) {
        InformationAdapter adapter = new InformationAdapter(getActivity(),items);
        mRecyclerView.setAdapter(adapter);
    }

    private void initViews() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    private void findViews(View view) {
        mRecyclerView = view.findViewById(R.id.recycler_view_information);
    }


}