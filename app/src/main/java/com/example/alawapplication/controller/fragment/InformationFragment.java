package com.example.alawapplication.controller.fragment;

import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.alawapplication.R;
import com.example.alawapplication.model.InformationItems;
import com.example.alawapplication.netWork.InformationAlaw;
import com.example.alawapplication.repository.InformationRepository;
import com.example.alawapplication.services.ThumbnailDownloader;

import java.util.List;


public class InformationFragment extends Fragment {

    public static final String TAG = "alaw";
    private RecyclerView mRecyclerView;
    private InformationRepository mRepository;
    private ThumbnailDownloader<InformationHolder> mThumbnailDownloader;
    private Handler mHandlerUi;


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
        mRepository = new InformationRepository();

        mHandlerUi = new Handler();
        mThumbnailDownloader = new ThumbnailDownloader(mHandlerUi);

        mThumbnailDownloader.start(); //after start(start thread in massageLoop) actually call of getLooper;
        mThumbnailDownloader.getLooper();//wake up of looper
        mThumbnailDownloader.setListener(new ThumbnailDownloader.ThumbnailDownloaderListener<InformationHolder>() {
            @Override
            public void onThumbnailDownloader(InformationHolder target, Bitmap bitmap) {
                target.bindBitmap(bitmap);
            }


        });


        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                InformationAlaw alaw = new InformationAlaw();
                //String response=alaw.getUrlString("https://alaatv.com/api/v2/home");
                List<InformationItems> items = mRepository.fetchItems();

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        setUpAdapter(items);

                    }
                });
                //Log.d(TAG, response);
            }
        });
        thread.start();


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mThumbnailDownloader.quit();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mThumbnailDownloader.clearQueue();
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
        InformationAdapter adapter = new InformationAdapter(items);
        mRecyclerView.setAdapter(adapter);
    }

    private void initViews() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    private void findViews(View view) {
        mRecyclerView = view.findViewById(R.id.recycler_view_information);
    }

    private class InformationAdapter extends RecyclerView.Adapter<InformationHolder> {

        private List<InformationItems> mItems;

        public List<InformationItems> getItems() {
            return mItems;
        }

        public void setItems(List<InformationItems> mItems) {
            this.mItems = mItems;
        }

        public InformationAdapter(List<InformationItems> mItems) {
            this.mItems = mItems;
        }

        @NonNull
        @Override
        public InformationHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getActivity()).inflate(
                    R.layout.list_item_information,
                    parent,
                    false);
            return new InformationHolder(view);
        }

        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @Override
        public void onBindViewHolder(@NonNull InformationHolder holder, int position) {
            InformationItems item = mItems.get(position);

            holder.bindInformation(item);

        }

        @Override
        public int getItemCount() {
            return mItems.size();
        }
    }

    public class InformationHolder extends RecyclerView.ViewHolder {
        private TextView textView;
        private ImageView mImageViewInfo;
        private InformationItems mItem;

        public InformationHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.txt_id);
            mImageViewInfo = itemView.findViewById(R.id.image_info);
        }

        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        public void bindInformation(InformationItems items) {
            mItem = items;
            textView.setText(items.getmTitle());
            mImageViewInfo.setImageDrawable(getActivity().getDrawable(R.drawable.ic_launcher_background));
            mThumbnailDownloader.queueThumbnail(this, items.getmUrl());
        }

        public void bindBitmap(Bitmap bitmap) {
            mImageViewInfo.setImageBitmap(bitmap);

        }


    }
}