package cl.gringraz.spotifyartistsearch;

import android.app.Application;
import android.content.Context;

import cl.gringraz.spotifyartistsearch.injection.component.ApplicationComponent;
import cl.gringraz.spotifyartistsearch.injection.component.DaggerApplicationComponent;
import cl.gringraz.spotifyartistsearch.injection.module.ApplicationModule;
import timber.log.Timber;


public class SpotifyArtistSearchApplication extends Application  {

    ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }

    public static SpotifyArtistSearchApplication get(Context context) {
        return (SpotifyArtistSearchApplication) context.getApplicationContext();
    }

    public ApplicationComponent getComponent() {
        if (mApplicationComponent == null) {
            mApplicationComponent = DaggerApplicationComponent.builder()
                    .applicationModule(new ApplicationModule(this))
                    .build();
        }
        return mApplicationComponent;
    }

    public void setComponent(ApplicationComponent applicationComponent) {
        mApplicationComponent = applicationComponent;
    }
}
