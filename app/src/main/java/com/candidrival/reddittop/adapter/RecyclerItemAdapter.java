package com.candidrival.reddittop.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import com.candidrival.reddittop.R;

import com.candidrival.reddittop.uifragments.ImageDownload;
import com.candidrival.reddittop.model.RecyclerItem;
import com.candidrival.reddittop.model.Items;
import com.candidrival.reddittop.utils.DateManager;

public class RecyclerItemAdapter extends RecyclerView.Adapter<RecyclerItemAdapter.RecyclerItemViewHolder> {

    private List<Items> itemsList = new ArrayList<>();

    public void setArticles(List<Items> itemsList) {
        this.itemsList = itemsList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_item, parent, false);
        return new RecyclerItemViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(RecyclerItemAdapter.RecyclerItemViewHolder holder, int position) {
        RecyclerItem recyclerItemItem = itemsList.get(position).getItems();
        holder.bind(recyclerItemItem);
        holder.mImage.setOnClickListener(v -> {
            AppCompatActivity activity = (AppCompatActivity) v.getContext();
            activity.getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container_main,
                            ImageDownload.newInstance((recyclerItemItem.getThumbnail())))
                    .addToBackStack(null)
                    .commit();
        });
    }

    @Override
    public int getItemCount() {

        return itemsList.size();

    }

    class RecyclerItemViewHolder extends RecyclerView.ViewHolder {

        private TextView mAuthor, mTitle, mNumComments, mPublished;
        private ImageView mImage;

        RecyclerItemViewHolder(View itemView) {
            super(itemView);

            mAuthor = itemView.findViewById(R.id.tvAuthor);
            mTitle = itemView.findViewById(R.id.tvTitle);
            mNumComments = itemView.findViewById(R.id.tvComments);
            mPublished = itemView.findViewById(R.id.tvPublished);
            mImage = itemView.findViewById(R.id.ivImg);

        }

        public void bind(RecyclerItem recyclerItem){
            mAuthor.setText(nullCheck("Author: " + recyclerItem.getAuthor()));
            mTitle.setText(nullCheck(recyclerItem.getTitle()));
            mNumComments.setText(nullCheck(recyclerItem.getNumComments()
                    + itemView.getResources().getString(R.string.num_comments)));
            Picasso.with(itemView.getContext()).load(recyclerItem.getThumbnail()).into(mImage);

            try {
                mPublished.setText(checkDate(recyclerItem.getCreatedUtc()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        private String nullCheck(String str){
            return (str == null || str.equals("")) ? "" : str;
        }

        /**
         * Processes and returns the result in the form of 'just now or hours/minutes ago'
         * @param x - expected created_utc value
         * @return - result in the form of 'just now or hours/minutes ago'
         */

        private String checkDate(long x) throws ParseException{
            String result, difference;
            difference = DateManager.getInstance().getDifference(x);
            String[] separated = difference.split(":");
            if (separated[0].equals("0") && separated[1].equals("0")){
                result = itemView.getContext().getResources().getString(R.string.just_now);
            } else if (separated[0].equals("1") && separated[1].equals("0")){
                result = separated[0] + itemView.getContext().getResources().getString(R.string.hour);
            } else if (separated[1].equals("1") && separated[0].equals("0")) {
                result = itemView.getContext().getResources().getString(R.string.just_now);
            } else if (separated[0].equals("0")){
                result = separated[1] + itemView.getContext().getResources().getString(R.string.mins);
            }
            else {
                result = separated[0] + itemView.getContext().getResources().getString(R.string.hours);
            }

            return  result;

        }

    }

}
