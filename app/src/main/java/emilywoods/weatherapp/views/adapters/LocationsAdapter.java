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
import emilywoods.weatherapp.models.Locations;

/**
 * Created by emilywoods on 10/11/2016.
 */

public class LocationsAdapter extends RecyclerView.Adapter<LocationsAdapter.myViewHolder> {

    private List<Locations> locationsList;

    static class myViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.latitude)
        TextView latitude;
        @BindView(R.id.longitude)
        TextView longitude;

        public myViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public LocationsAdapter() {
        this.locationsList = new ArrayList<>();
    }

    public void setLocationsList(List<Locations> locationsList) {
        this.locationsList = locationsList;
        notifyDataSetChanged();
    }

    @Override
    public myViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.locations_list, parent, false);
        return new myViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(myViewHolder holder, int position) {
        Locations locations = locationsList.get(position);
        holder.name.setText(locations.getName());
        holder.latitude.setText(locations.getLatitude());
        holder.longitude.setText(locations.getLongitude());

    }

    @Override
    public int getItemCount() {
        return locationsList.size();
    }

}
