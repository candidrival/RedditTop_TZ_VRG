package com.candidrival.reddittop.network;

import com.candidrival.reddittop.apicommon.BaseConstants;
import com.candidrival.reddittop.model.Reply;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET(BaseConstants.KEY)
    Call<Reply> getResponseAsJson();
}
