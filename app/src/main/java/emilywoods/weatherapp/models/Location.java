package emilywoods.weatherapp.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Location implements Parcelable {
    private int id;
    private String name;
    private float longitude;
    private float latitude;

    public Location(int id, String name, float longitude, float latitude) {
        this.id = id;
        this.name = name;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    protected Location(Parcel in) {
        id = in.readInt();
        name = in.readString();
        longitude = in.readFloat();
        latitude = in.readFloat();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeFloat(longitude);
        dest.writeFloat(latitude);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Location> CREATOR = new Creator<Location>() {
        @Override
        public Location createFromParcel(Parcel in) {
            return new Location(in);
        }

        @Override
        public Location[] newArray(int size) {
            return new Location[size];
        }
    };

    public int getId() { return id; }

    public String getName() {
        return name;
    }

    public float getLongitude() { return longitude; }

    public float getLatitude() {
        return latitude;
    }
}
