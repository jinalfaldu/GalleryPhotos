package hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.Jelly_Listeners;


public interface OnClickListener<T, P, A> {
    void onClickListener(T t, P p);

    void onClickMoreListener(T t, P p, A a2);

    void onLongClickListener(T t, P p);
}
