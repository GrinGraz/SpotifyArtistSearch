package cl.gringraz.spotifyartistsearch.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ArtistSearchResponsePayload implements Parcelable{

    @SerializedName("artists")
    @Expose
    public Artists artists;

    protected ArtistSearchResponsePayload(Parcel in) {
        artists = in.readParcelable(Artists.class.getClassLoader());
    }

    public static final Creator<ArtistSearchResponsePayload> CREATOR = new Creator<ArtistSearchResponsePayload>() {
        @Override
        public ArtistSearchResponsePayload createFromParcel(Parcel in) {
            return new ArtistSearchResponsePayload(in);
        }

        @Override
        public ArtistSearchResponsePayload[] newArray(int size) {
            return new ArtistSearchResponsePayload[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(artists, flags);
    }
}
