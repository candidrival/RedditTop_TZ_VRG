package com.candidrival.reddittop.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Items {

    @SerializedName("data")
    @Expose
    private RecyclerItem recyclerItem;

    public RecyclerItem getItems() {
        return recyclerItem;
    }

    public void setsItems(RecyclerItem recyclerItem) {
        this.recyclerItem = recyclerItem;
    }
}
