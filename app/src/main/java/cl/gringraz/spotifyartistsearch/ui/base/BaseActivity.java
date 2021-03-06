package cl.gringraz.spotifyartistsearch.ui.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import cl.gringraz.spotifyartistsearch.SpotifyArtistSearchApplication;
import cl.gringraz.spotifyartistsearch.injection.component.ActivityComponent;
import cl.gringraz.spotifyartistsearch.injection.component.ConfigPersistentComponent;
import cl.gringraz.spotifyartistsearch.injection.component.DaggerConfigPersistentComponent;
import cl.gringraz.spotifyartistsearch.injection.module.ActivityModule;
import timber.log.Timber;


public class BaseActivity extends AppCompatActivity {

    private static final String                               KEY_ACTIVITY_ID = "KEY_ACTIVITY_ID";
    private static final AtomicLong                           NEXT_ID         = new AtomicLong(0);
    private static final Map<Long, ConfigPersistentComponent> sComponentsMap  = new HashMap<>();

    private ActivityComponent mActivityComponent;
    private long              mActivityId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mActivityId = savedInstanceState != null ?
                savedInstanceState.getLong(KEY_ACTIVITY_ID) : NEXT_ID.getAndIncrement();
        ConfigPersistentComponent configPersistentComponent;
        if (!sComponentsMap.containsKey(mActivityId)) {
            Timber.i("Creating new ConfigPersistentComponent id=%d", mActivityId);
            configPersistentComponent = DaggerConfigPersistentComponent.builder()
                    .applicationComponent(SpotifyArtistSearchApplication.get(this).getComponent())
                    .build();
            sComponentsMap.put(mActivityId, configPersistentComponent);
        } else {
            Timber.i("Reusing ConfigPersistentComponent id=%d", mActivityId);
            configPersistentComponent = sComponentsMap.get(mActivityId);
        }
        mActivityComponent = configPersistentComponent.activityComponent(new ActivityModule(this));
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putLong(KEY_ACTIVITY_ID, mActivityId);
    }

    @Override
    protected void onDestroy() {
        if (!isChangingConfigurations()) {
            Timber.i("Clearing ConfigPersistentComponent id=%d", mActivityId);
            sComponentsMap.remove(mActivityId);
        }
        super.onDestroy();
    }

    public ActivityComponent activityComponent() {
        return mActivityComponent;
    }

}
