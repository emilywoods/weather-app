package emilywoods.weatherapp.views.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import emilywoods.weatherapp.R;
import emilywoods.weatherapp.models.Location;

/**
 * Created by emilywoods on 10/11/2016.
 */

public class LocationsAdapter extends RecyclerView.Adapter<LocationsAdapter.MyViewHolder> {

    private final LocationClickListener mLocationClickListener;
    private List<Location> locationsList;

    static class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.latitude)
        TextView latitude;
        @BindView(R.id.longitude)
        TextView longitude;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public LocationsAdapter(LocationClickListener locationClickListener) {
        mLocationClickListener = locationClickListener;
        this.locationsList = new ArrayList<>();
    }

    public void setLocationsList(List<Location> locationsList) {
        this.locationsList = locationsList;
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.locations_list, parent, false);

        final MyViewHolder holder = new MyViewHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        Location locations = locationsList.get(position);
        holder.name.setText(locations.getName());
        holder.latitude.setText(String.valueOf(locations.getLatitude()));
        holder.longitude.setText(String.valueOf(locations.getLongitude()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final int position = holder.getAdapterPosition();
                mLocationClickListener.onClicked(locationsList.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return locationsList.size();
    }

}
