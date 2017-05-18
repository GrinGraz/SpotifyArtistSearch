package cl.gringraz.spotifyartistsearch.data;

import javax.inject.Inject;
import javax.inject.Singleton;

import cl.gringraz.spotifyartistsearch.data.local.DatabaseHelper;
import cl.gringraz.spotifyartistsearch.data.local.PreferencesHelper;
import cl.gringraz.spotifyartistsearch.data.model.ArtistSearchResponsePayload;
import cl.gringraz.spotifyartistsearch.data.remote.APIService;
import rx.Observable;

@Singleton
public class DataManager {

    private final APIService        mAPIService;
    private final DatabaseHelper    mDatabaseHelper;
    private final PreferencesHelper mPreferencesHelper;

    @Inject
    public DataManager(APIService APIService, PreferencesHelper preferencesHelper,
                       DatabaseHelper databaseHelper) {
        mAPIService = APIService;
        mPreferencesHelper = preferencesHelper;
        mDatabaseHelper = databaseHelper;
    }

    public PreferencesHelper getPreferencesHelper() {
        return mPreferencesHelper;
    }

    public Observable<ArtistSearchResponsePayload> searchArtistByName(String artistName) {
        return mAPIService.searchArtistByName(artistName);
    }

}
