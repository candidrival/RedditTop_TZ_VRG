package com.candidrival.reddittop.uifragments;

import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import com.candidrival.reddittop.R;
import com.candidrival.reddittop.adapter.RecyclerItemAdapter;
import com.candidrival.reddittop.apicommon.BaseConstants;
import com.candidrival.reddittop.model.Items;

public class RecyclerItemFragment extends Fragment {

    private ProgressBar mProgressBar;
    private RecyclerItemAdapter mRecyclerItemAdapter;
    private RecyclerView mRecyclerView;
    private Drawable mDivider;
    private LiveData<List<Items>> itemsDataList;

    private int savedPosition;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRecyclerItemAdapter = new RecyclerItemAdapter();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recycler, container, false);

        mRecyclerView = view.findViewById(R.id.recycler_View);
        mProgressBar = view.findViewById(R.id.progress_Bar);
        mDivider = ContextCompat.getDrawable(getContext(), R.drawable.divider);

        showProgressBar();

        RecyclerItemViewModel model = new ViewModelProvider(requireActivity())
                .get(RecyclerItemViewModel.class);

        itemsDataList = model.getData();
        itemsDataList.observe(getViewLifecycleOwner(), items -> {
            mRecyclerItemAdapter.setArticles(items);
            dismissProgressBar();
            restoreListPosition();
        });

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());

        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setLayoutManager(layoutManager);
        DividerItemDecoration divider = new DividerItemDecoration(getContext(),
                DividerItemDecoration.VERTICAL);
        divider.setDrawable(mDivider);
        mRecyclerView.addItemDecoration(divider);

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                savedPosition = ((LinearLayoutManager)recyclerView.getLayoutManager())
                        .findFirstVisibleItemPosition();
            }
        });

        mRecyclerView.setAdapter(mRecyclerItemAdapter);


        return view;
    }

    public void showProgressBar() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    public void dismissProgressBar() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void onPause() {
        super.onPause();
        saveListPosition();
    }

    private void saveListPosition(){
        SharedPreferences getPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        SharedPreferences.Editor e = getPreferences.edit();
        e.putInt(BaseConstants.TAG, savedPosition);
        e.apply();
    }

    private void restoreListPosition(){
        SharedPreferences getPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        savedPosition = getPreferences.getInt(BaseConstants.TAG, 0);
        mRecyclerView.scrollToPosition(savedPosition);
    }

    @Nullable
    @Override
    public Object getSharedElementEnterTransition() {
        return super.getSharedElementEnterTransition();
    }

}
