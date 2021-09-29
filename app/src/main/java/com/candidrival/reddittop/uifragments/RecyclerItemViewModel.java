package com.candidrival.reddittop.uifragments;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import com.candidrival.reddittop.apicommon.BaseConstants;
import com.candidrival.reddittop.manager.DataManager;
import com.candidrival.reddittop.network.ResponseCallback;
import com.candidrival.reddittop.model.Items;

public class RecyclerItemViewModel extends ViewModel implements ResponseCallback {

    private MutableLiveData<List<Items>> data;

    public LiveData<List<Items>> getData() {
        if (data == null) {
            data = new MutableLiveData<>();
            DataManager.newInstance(this).getResponse();
            Log.e(BaseConstants.TAG, "1");
        }
        Log.e(BaseConstants.TAG, "2");
        return data;
    }

    @Override
    public void onResponse(List<Items> itemsList) {
        data.setValue(itemsList);
    }

}
