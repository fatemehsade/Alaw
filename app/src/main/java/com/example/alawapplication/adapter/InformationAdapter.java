package com.example.alawapplication.adapter;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.alawapplication.R;
import com.example.alawapplication.databinding.ListItemInformationBinding;
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
        ListItemInformationBinding binding= DataBindingUtil.inflate(
                LayoutInflater.from(mContext),
                R.layout.list_item_information,
                parent,
                false);
        return new InformationHolder(binding);
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
        private ListItemInformationBinding mBinding;
        private InformationItems mItem;

        public InformationHolder(ListItemInformationBinding binding) {
            super(binding.getRoot());
            mBinding=binding;
        }

        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        public void bindInformation(InformationItems items) {
            mItem = items;
            mBinding.txtId.setText(items.getmTitle());
            mBinding.imageInfo.setImageDrawable(mContext.getDrawable(R.drawable.ic_launcher_background));
            //mThumbnailDownloader.queueThumbnail(this, items.getmUrl());
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
