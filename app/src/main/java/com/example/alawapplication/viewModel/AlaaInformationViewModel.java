package com.example.alawapplication.viewModel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.alawapplication.model.InformationItems;
import com.example.alawapplication.repository.InformationRepository;

import java.util.List;

public class AlaaInformationViewModel extends AndroidViewModel {
    private InformationRepository mRepository;
    private LiveData<List<InformationItems>> mItemsLiveData;



    public LiveData<List<InformationItems>> getItemsLiveData() {
        return mItemsLiveData;
    }

    public AlaaInformationViewModel(Application application) {
        super(application);
        mRepository=new InformationRepository(getApplication());
        mItemsLiveData=mRepository.getItemsLiveData();
    }


    public void fetchItemsAsync(){
        mRepository.fetchItemsAsync();
    }

    public void insert(InformationItems items){
        mRepository.insert(items);
    }

    public List<InformationItems> getItem(){
        return mRepository.getItems();
    }
}
