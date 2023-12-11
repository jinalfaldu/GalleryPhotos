package hdphoto.galleryimages.gelleryalbum.Images_android.Ibex_CollageModule.Duff_Lib;

import hdphoto.galleryimages.gelleryalbum.Images_android.Ibex_CollageModule.EdApi.ApiService;
import hdphoto.galleryimages.gelleryalbum.Images_android.Ibex_CollageModule.EdApi.PatternsResponse;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

import java.util.ArrayList;


public class Eel_CollagePresenter implements CollageContract.Presenter {
    ApiService apiService;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    CollageContract.View view;

    public Eel_CollagePresenter(CollageContract.View view, ApiService apiService) {
        this.view = view;
        this.apiService = apiService;
    }

    @Override // hdphoto.galleryimages.gelleryalbum.android.CollageModule.CMLib.CollageContract.Presenter
    public void loadPatterns() {
        this.compositeDisposable.add((Disposable) this.apiService.getPatterns().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DisposableSingleObserver<PatternsResponse>() { // from class: hdphoto.galleryimages.gelleryalbum.android.CollageModule.CMLib.CMCollagePresenter.1
            @Override // io.reactivex.SingleObserver
            public void onError(Throwable th) {
            }

            @Override // io.reactivex.SingleObserver
            public void onSuccess(PatternsResponse patternsResponse) {
                if (patternsResponse == null || !patternsResponse.getResultAsBoolean()) {
                    return;
                }
                ArrayList arrayList = new ArrayList();
                for (PatternsResponse.PatternsCategory patternsCategory : patternsResponse.getData()) {
                    arrayList.addAll(patternsCategory.getPatternPack());
                }
                Eel_CollagePresenter.this.view.setPatterns(arrayList);
            }
        }));
    }

    @Override // hdphoto.galleryimages.gelleryalbum.android.CollageModule.CMLib.CollageContract.Presenter
    public void onDestroy() {
        this.compositeDisposable.clear();
    }
}
