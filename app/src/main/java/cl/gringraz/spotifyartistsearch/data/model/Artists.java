package cl.gringraz.spotifyartistsearch.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Artists implements Parcelable{

    @SerializedName("href")
    @Expose
    public String href;
    @SerializedName("items")
    @Expose
    public List<Item> items = null;
    @SerializedName("limit")
    @Expose
    public int limit;
    @SerializedName("next")
    @Expose
    public Object next;
    @SerializedName("offset")
    @Expose
    public int offset;
    @SerializedName("previous")
    @Expose
    public Object previous;
    @SerializedName("total")
    @Expose
    public int total;

    protected Artists(Parcel in) {
        href = in.readString();
        items = in.createTypedArrayList(Item.CREATOR);
        limit = in.readInt();
        offset = in.readInt();
        total = in.readInt();
    }

    public static final Creator<Artists> CREATOR = new Creator<Artists>() {
        @Override
        public Artists createFromParcel(Parcel in) {
            return new Artists(in);
        }

        @Override
        public Artists[] newArray(int size) {
            return new Artists[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(href);
        dest.writeTypedList(items);
        dest.writeInt(limit);
        dest.writeInt(offset);
        dest.writeInt(total);
    }
}
