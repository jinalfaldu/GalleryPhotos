package hdphoto.galleryimages.gelleryalbum.Images_android.Ibex_CollageModule.Hertz_Activity.stickers;

import hdphoto.galleryimages.gelleryalbum.Images_android.Ibex_CollageModule.EdApi.ApiService;
import hdphoto.galleryimages.gelleryalbum.Images_android.Ibex_CollageModule.EdApi.EmojisResponse;
import hdphoto.galleryimages.gelleryalbum.Images_android.Ibex_CollageModule.Jackal_StickerLayout.Ledl_StickerItemModel;
import hdphoto.galleryimages.gelleryalbum.Images_android.Ibex_CollageModule.Jackal_StickerLayout.Ledl_StickerPack;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.List;


public class StickersDataPresenter implements StickersDataContract.Presenter {
    ApiService apiService;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    StickersDataContract.View view;

    public StickersDataPresenter(StickersDataContract.View view, ApiService apiService) {
        this.view = view;
        this.apiService = apiService;
    }

    @Override // hdphoto.galleryimages.gelleryalbum.android.CollageModule.CMActivity.stickers.StickersDataContract.Presenter
    public void loadStickers() {
        this.view.showProgress();
        this.compositeDisposable.add((Disposable) this.apiService.getEmojis().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DisposableSingleObserver<EmojisResponse>() { // from class: hdphoto.galleryimages.gelleryalbum.android.CollageModule.CMActivity.stickers.CMStickersDataPresenter.1
            @Override // io.reactivex.SingleObserver
            public void onSuccess(EmojisResponse emojisResponse) {
                StickersDataPresenter.this.view.hideProgress();
                if (emojisResponse != null && emojisResponse.getResultAsBoolean()) {
                    StickersDataPresenter.this.view.showStickers(StickersDataPresenter.this.ResponseToCustomModel(emojisResponse.getData().get(0).getEmojiPack()));
                } else if (emojisResponse != null) {
                    StickersDataPresenter.this.view.showError(emojisResponse.getMessage());
                }
            }

            @Override // io.reactivex.SingleObserver
            public void onError(Throwable th) {
                StickersDataPresenter.this.view.hideProgress();
                StickersDataPresenter.this.view.showError(th.getMessage());
            }
        }));
    }

    public List<Ledl_StickerPack> ResponseToCustomModel(List<EmojisResponse.EmojisPack> list) {
        ArrayList arrayList = new ArrayList();
        for (EmojisResponse.EmojisPack emojisPack : list) {
            Ledl_StickerPack cMStickerPack = new Ledl_StickerPack();
            cMStickerPack.setIconUrl(emojisPack.getTrayImageUri());
            cMStickerPack.setPackName(emojisPack.getName());
            cMStickerPack.setStickerItemModelArrayList(ResponseStickerPackToCustomModel(emojisPack.getEmojis()));
            arrayList.add(cMStickerPack);
        }
        return arrayList;
    }

    private ArrayList<Ledl_StickerItemModel> ResponseStickerPackToCustomModel(List<EmojisResponse.Emoji> list) {
        ArrayList<Ledl_StickerItemModel> arrayList = new ArrayList<>();
        for (EmojisResponse.Emoji emoji : list) {
            Ledl_StickerItemModel cMStickerItemModel = new Ledl_StickerItemModel();
            cMStickerItemModel.setStickerUrl(emoji.getUri());
            cMStickerItemModel.setSelected(false);
            arrayList.add(cMStickerItemModel);
        }
        return arrayList;
    }

    @Override // hdphoto.galleryimages.gelleryalbum.android.CollageModule.CMActivity.stickers.StickersDataContract.Presenter
    public void onDestroy() {
        this.compositeDisposable.clear();
    }
}
