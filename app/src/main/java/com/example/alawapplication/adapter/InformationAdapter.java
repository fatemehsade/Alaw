package com.example.alawapplication.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.alawapplication.R;
import com.example.alawapplication.model.InformationItems;
import com.example.alawapplication.services.ThumbnailDownloader;

import java.util.List;

public class InformationAdapter extends RecyclerView.Adapter<InformationAdapter.InformationHolder> {

    private ThumbnailDownloader<InformationHolder> mThumbnailDownloader;

    private List<InformationItems> mItems;
    private Context mContext;

    public List<InformationItems> getItems() {
        return mItems;
    }

    public void setItems(List<InformationItems> mItems) {
        this.mItems = mItems;
    }

    public InformationAdapter(Context context, List<InformationItems> mItems) {
        this.mItems = mItems;
        mContext = context;
    }

    @NonNull
    @Override
    public InformationHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(
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
            mImageViewInfo.setImageDrawable(mContext.getDrawable(R.drawable.ic_launcher_background));
            //mThumbnailDownloader.queueThumbnail(this, items.getmUrl());
        }

        public void bindBitmap(Bitmap bitmap) {
            mImageViewInfo.setImageBitmap(bitmap);

        }


    }

   /* public static void setUpThumbnailDownloader() {
        Handler mHandlerUi;

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
    }

    */
}
