package com.candidrival.reddittop.network;

import java.util.List;

import com.candidrival.reddittop.model.Items;

/**
 * Callback responsible for data transfer into @ArticleViewModel
 */

public interface ResponseCallback {

    void onResponse(List<Items> itemsList);

}
