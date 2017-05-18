package cl.gringraz.spotifyartistsearch.ui.main;

import cl.gringraz.spotifyartistsearch.data.model.Artists;
import cl.gringraz.spotifyartistsearch.ui.base.MvpView;


public interface MainMvpView extends MvpView {

    void onSearchOk(Artists artists);

    void onError();
}
