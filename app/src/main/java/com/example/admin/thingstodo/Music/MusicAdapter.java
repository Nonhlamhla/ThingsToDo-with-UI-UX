package com.example.admin.thingstodo.Music;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.admin.thingstodo.Classes.CatalogClass;
import com.example.admin.thingstodo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 2/7/2018.
 */

public class MusicAdapter extends RecyclerView.Adapter<MusicAdapter.MyViewHolder> implements Filterable {

    private Activity context;
    List<CatalogClass> catalogList, catalogss;
    private Activity applicationContext;
    private CatalogClass catalog;

    public MusicAdapter(Activity context, List<CatalogClass> catalogList) {
        this.context = context;
        this.catalogList = catalogList;
        this.catalogss= catalogList;
    }


    @Override
    public MusicAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_model,null);

        MusicAdapter.MyViewHolder myViewHolder = new MusicAdapter.MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MusicAdapter.MyViewHolder holder, int position) {
        final CatalogClass catalogClass = catalogList.get(position);

        holder.tvDate.setText(catalogClass.getDate());
        holder.tvArea.setText(catalogClass.getLocation());
        holder.tvEventTitle.setText(catalogClass.getEventTitle());
        holder.tvDescription.setText(catalogClass.getDescription());
        holder.tvPrice.setText(catalogClass.getPrice());
        holder.tvDiscount.setText(catalogClass.getDiscount());


        Glide.with(context)
                .load(catalogClass.getImageurl())
                .into(holder.ibImage);

    }

    @Override
    public int getItemCount() {
        return (null != catalogList ? catalogList.size() : 0);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvDate, tvArea, tvEventTitle, tvDescription, tvRating, tvPrice, tvDiscount;
        ImageButton ibImage, ibSave;
        Button btn_Add;


        public MyViewHolder(View itemView) {
            super(itemView);
            tvDate = itemView.findViewById(R.id.tvDate);
            tvArea = itemView.findViewById(R.id.tvArea);
            tvEventTitle = itemView.findViewById(R.id.tvEventTitle);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            tvRating = itemView.findViewById(R.id.tvRating);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            tvDiscount = itemView.findViewById(R.id.tvDiscount);
            btn_Add = itemView.findViewById(R.id.btn_Add);
            ibImage = itemView.findViewById(R.id.ibImage);
            ibSave = itemView.findViewById(R.id.ibSave);


            /**
             * FUEL ICON COLOR CHANGE
             */


        }
    }



    @Override
    public Filter getFilter() {


        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {

                String charString = charSequence.toString();

                if (charString.isEmpty()) {

                    catalogList = catalogss;
                } else {

                    ArrayList<CatalogClass> filteredList = new ArrayList<>();

                    for (CatalogClass androidVersion : catalogss) {

                        /**
                         * SEARCHES USING LOCATION/
                         */
                        if (androidVersion.getLocation().toUpperCase().contains(charString) ) {

                            filteredList.add(androidVersion);
                        }
                    }

                    catalogList = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = catalogList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                catalogList = (ArrayList<CatalogClass>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

}
