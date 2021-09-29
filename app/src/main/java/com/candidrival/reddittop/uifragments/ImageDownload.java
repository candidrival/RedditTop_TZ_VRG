package com.candidrival.reddittop.uifragments;

import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.squareup.picasso.Picasso;


import com.candidrival.reddittop.R;

public class ImageDownload extends Fragment {

    private static final String ARGUMENT_ITEM_URL = "itemUrl";
    private static final int PERMISSION_REQUEST_CODE = 1;

    private ImageView mImageView;

    public static ImageDownload newInstance(String itemUrl) {

        Bundle args = new Bundle();

        args.putString(ARGUMENT_ITEM_URL, itemUrl);

        ImageDownload fragment = new ImageDownload();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.image, container, false);

        setHasOptionsMenu(true);

        mImageView = view.findViewById(R.id.ivThumbnail);
        Picasso.with(getActivity()).load(getImageUrl()).into(mImageView);

        return view;
    }

    private void saveImageToGallery() {
        long time= System.currentTimeMillis();
        mImageView.setDrawingCacheEnabled(true);
        Bitmap bitmap = mImageView.getDrawingCache();
        MediaStore.Images.Media.insertImage(getActivity().getContentResolver(), bitmap,
                String.valueOf(time), String.valueOf(time));
        Toast.makeText(getActivity(), getResources().getString(R.string.saved_success),
                Toast.LENGTH_LONG).show();
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(getActivity(), new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
    }

    private boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(getActivity(), android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.image_download_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.file && checkPermission()) {
            saveImageToGallery();
            return true;
        } else if (item.getItemId() == R.id.file && !checkPermission()){
            requestPermission();
            return true;
        }
        return onOptionsItemSelected(item);
    }

    private String getImageUrl() {
        Bundle args = getArguments();
        return args.getString(ARGUMENT_ITEM_URL);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.e("value", "Permission Granted, Now you can use local drive .");
                } else {
                    Log.e("value", "Permission Denied, You cannot use local drive .");
                }
                break;
        }
    }
}

