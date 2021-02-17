package com.example.alawapplication.controller.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.TextView;

import com.example.alawapplication.R;
import com.example.alawapplication.model.InformationItems;

import java.util.ArrayList;
import java.util.List;


public class InformationFragment extends Fragment {

    private RecyclerView mRecyclerView;



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

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_information, container, false);
        findViews(view);
        initViews();
        setUpAdapter();
        return view;
    }

    private void setUpAdapter() {
        InformationAdapter adapter=new InformationAdapter(new ArrayList<>());
        mRecyclerView.setAdapter(adapter);
    }

    private void initViews() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    private void findViews(View view) {
        mRecyclerView=view.findViewById(R.id.recycler_view_information);
    }

    private class InformationAdapter extends RecyclerView.Adapter<InformationHolder>{

        private List<InformationItems> mItems;

        public List<InformationItems> getmItems() {
            return mItems;
        }

        public void setmItems(List<InformationItems> mItems) {
            this.mItems = mItems;
        }

        public InformationAdapter(List<InformationItems> mItems) {
            this.mItems = mItems;
        }

        @NonNull
        @Override
        public InformationHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            TextView textView=new TextView(getContext());
            return new InformationHolder(textView);
        }

        @Override
        public void onBindViewHolder(@NonNull InformationHolder holder, int position) {

            holder.bindInformation(mItems.get(position));

        }

        @Override
        public int getItemCount() {
            return mItems.size();
        }
    }

    private class InformationHolder extends RecyclerView.ViewHolder{
        private  TextView textView;
        private InformationItems mItem;
        public InformationHolder(@NonNull View itemView) {
            super(itemView);
            textView= (TextView) itemView;
        }

        public void bindInformation(InformationItems items){
            mItem=items;
            textView.setText(items.getmTitle());
        }
    }
}