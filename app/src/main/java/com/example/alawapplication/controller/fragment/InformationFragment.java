package com.example.alawapplication.controller.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.alawapplication.R;
import com.example.alawapplication.model.InformationItems;
import com.example.alawapplication.netWork.InformationAlaw;
import com.example.alawapplication.repository.InformationRepository;

import java.io.IOException;
import java.util.List;


public class InformationFragment extends Fragment {

    public static final String TAG = "alaw";
    private RecyclerView mRecyclerView;
    private InformationRepository mRepository;
    private TextView mTxtResponse;



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
        mRepository=new InformationRepository();
        Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {
                InformationAlaw alaw=new InformationAlaw();
                try {
                    String response=alaw.getUrlString("https://alaatv.com/api/v2/home");
                    Log.d(TAG, response);
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mTxtResponse.setText(response);
                        }
                    });
                } catch (IOException e) {
                    Log.e(TAG, e.getMessage(),e );
                }
            }
        });
        thread.start();


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
        List<InformationItems> items=mRepository.getItems();
        InformationAdapter adapter=new InformationAdapter(items);
        mRecyclerView.setAdapter(adapter);
    }

    private void initViews() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    private void findViews(View view) {
        mRecyclerView=view.findViewById(R.id.recycler_view_information);
        mTxtResponse=view.findViewById(R.id.txt_view_responce);
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