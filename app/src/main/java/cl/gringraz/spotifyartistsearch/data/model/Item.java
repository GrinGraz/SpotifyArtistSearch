package cl.gringraz.spotifyartistsearch.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Item implements Parcelable{

    @SerializedName("genres")
    @Expose
    public List<String> genres = null;
    @SerializedName("href")
    @Expose
    public String href;
    @SerializedName("id")
    @Expose
    public String id;
    @SerializedName("images")
    @Expose
    public List<Image> images = null;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("popularity")
    @Expose
    public int popularity;
    @SerializedName("type")
    @Expose
    public String type;
    @SerializedName("uri")
    @Expose
    public String uri;

    protected Item(Parcel in) {
        genres = in.createStringArrayList();
        href = in.readString();
        id = in.readString();
        name = in.readString();
        popularity = in.readInt();
        type = in.readString();
        uri = in.readString();
    }

    public static final Creator<Item> CREATOR = new Creator<Item>() {
        @Override
        public Item createFromParcel(Parcel in) {
            return new Item(in);
        }

        @Override
        public Item[] newArray(int size) {
            return new Item[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringList(genres);
        dest.writeString(href);
        dest.writeString(id);
        dest.writeString(name);
        dest.writeInt(popularity);
        dest.writeString(type);
        dest.writeString(uri);
    }
}
