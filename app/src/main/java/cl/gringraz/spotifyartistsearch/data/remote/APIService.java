package cl.gringraz.spotifyartistsearch.data.remote;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import cl.gringraz.spotifyartistsearch.BuildConfig;
import cl.gringraz.spotifyartistsearch.data.model.ArtistSearchResponsePayload;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;
import timber.log.Timber;

public interface APIService {

    String ENDPOINT = "https://api.spotify.com/v1/";

    @GET("search?type=artist")
    Observable<ArtistSearchResponsePayload> searchArtistByName(@Query("q") String artistName);

    class Creator {
        public static APIService newAPIService() {

            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BASIC);
            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(logging)
                    .build();

            Gson gson = new GsonBuilder()
                    .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
                    .create();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(APIService.ENDPOINT)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .client(client)
                    .build();
            return retrofit.create(APIService.class);
        }
    }
}
