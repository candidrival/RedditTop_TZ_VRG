package com.candidrival.reddittop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.candidrival.reddittop.uifragments.RecyclerItemFragment;


public class MainActivity extends AppCompatActivity  {

    private RecyclerItemFragment mRecyclerItemFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        if (savedInstanceState != null) {
            mRecyclerItemFragment = (RecyclerItemFragment)
                    getSupportFragmentManager().findFragmentByTag(RecyclerItemFragment.class.getSimpleName());

        } else if (mRecyclerItemFragment == null) {
            mRecyclerItemFragment = new RecyclerItemFragment();
            showArticles();
        }
    }

    public void showArticles() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.container_main, mRecyclerItemFragment, RecyclerItemFragment.class.getSimpleName());
        ft.commit();
    }

}