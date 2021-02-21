package com.example.alawapplication.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.alawapplication.model.InformationItems;
import com.example.alawapplication.repository.InformationRepository;

import java.util.List;

public class AlaaInformationViewModel extends ViewModel {
    private final InformationRepository mRepository;
    private LiveData<List<InformationItems>> mItemsLiveData;

    public LiveData<List<InformationItems>> getItemsLiveData() {
        return mItemsLiveData;
    }

    public AlaaInformationViewModel() {
        mRepository=new InformationRepository();
        mItemsLiveData=mRepository.getItemsLiveData();
    }

    public void fetchItemsAsync(){
        mRepository.fetchItemsAsync();
    }
}
