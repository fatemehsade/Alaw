package com.example.alawapplication.services;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.alawapplication.netWork.InformationAlaw;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

public class ThumbnailDownloader<T> extends HandlerThread {
    public static final String TAG="ThumbnailDownloader";
    public static final int WHAT_THUMBNAIL_DOWNLOAD = 1;
    private ConcurrentHashMap<T,String> mRequestMap=new ConcurrentHashMap<>();
    private ThumbnailDownloaderListener mListener;

    private Handler mHandlerResponse;
    private Handler mHandlerRequest;

    public ThumbnailDownloaderListener getListener() {
        return mListener;
    }

    public void setListener(ThumbnailDownloaderListener mListener) {
        this.mListener = mListener;
    }



    public ThumbnailDownloader(Handler uiHandler) {
        super(TAG);
        mHandlerResponse=uiHandler;
    }

    private void handleDownloadMessage(T target,String url) throws IOException {
            if (url==null)
                return;

            InformationAlaw information = new InformationAlaw();
            byte[] bitmapBytes = information.getUrlBytes(url);

            Bitmap bitmap = BitmapFactory.decodeByteArray(bitmapBytes, 0, bitmapBytes.length);
            mHandlerResponse.post(new Runnable() {
                @Override
                public void run() {
                    if (!mRequestMap.get(target).equals(url))
                        return;
                    mListener.onThumbnailDownloader(target,bitmap);
                    mRequestMap.remove(target);

                }
            });

        }


    public void queueThumbnail(T target,String url){
        mRequestMap.put(target,url);
        //create a massage and send it to looper
        mHandlerRequest.obtainMessage(WHAT_THUMBNAIL_DOWNLOAD,target).sendToTarget();
    }

    @Override
    protected void onLooperPrepared() {
        super.onLooperPrepared();
        mHandlerRequest=new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);


                //download url from net
                try {
                    if (msg.what == WHAT_THUMBNAIL_DOWNLOAD) {
                        if (msg.obj == null)
                            return;
                        T target = (T) msg.obj;
                        String url=mRequestMap.get(target);
                        handleDownloadMessage(target,url);


                    }
                } catch (IOException e) {
                    Log.e(TAG, e.getMessage(), e);
                }
            }
        };
    }

    public interface ThumbnailDownloaderListener<T>{
        void onThumbnailDownloader(T target,Bitmap bitmap);
    }

    public void clearQueue(){
        mHandlerRequest.removeMessages(WHAT_THUMBNAIL_DOWNLOAD);
    }
}
