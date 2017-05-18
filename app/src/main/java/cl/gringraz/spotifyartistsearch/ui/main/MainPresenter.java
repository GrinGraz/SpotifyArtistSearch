package cl.gringraz.spotifyartistsearch.ui.main;

import javax.inject.Inject;

import cl.gringraz.spotifyartistsearch.data.DataManager;
import cl.gringraz.spotifyartistsearch.data.model.ArtistSearchResponsePayload;
import cl.gringraz.spotifyartistsearch.data.model.Artists;
import cl.gringraz.spotifyartistsearch.injection.ConfigPersistent;
import cl.gringraz.spotifyartistsearch.ui.base.BasePresenter;
import cl.gringraz.spotifyartistsearch.util.RxUtil;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import timber.log.Timber;

@ConfigPersistent
public class MainPresenter extends BasePresenter<MainMvpView> {

    private final DataManager  mDataManager;
    private       Subscription mSubscription;

    @Inject
    public MainPresenter(DataManager dataManager) {
        TAG = "Error in " + this.getClass().getSimpleName();
        mDataManager = dataManager;
    }

    @Override
    public void attachView(MainMvpView mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void detachView() {
        super.detachView();
        if (mSubscription != null) mSubscription.unsubscribe();
    }

    public void searchArtistByName(String artistName) {
        checkViewAttached();
        RxUtil.unsubscribe(mSubscription);
        mSubscription = mDataManager.searchArtistByName(artistName)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<ArtistSearchResponsePayload>() {
                    @Override
                    public void onCompleted() {
                        System.out.println("MainPresenter.onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Timber.e(e, TAG);
                        getMvpView().onError();
                    }

                    @Override
                    public void onNext(ArtistSearchResponsePayload artistSearchResponsePayload) {
                        getMvpView().onSearchOk(artistSearchResponsePayload.artists);
                    }
                });
    }
}