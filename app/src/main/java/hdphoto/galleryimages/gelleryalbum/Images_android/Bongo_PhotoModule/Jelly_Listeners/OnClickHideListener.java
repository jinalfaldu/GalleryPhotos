package hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.Jelly_Listeners;


public interface OnClickHideListener<T, P, B, A> {
    void onClickListener(T t, P p, B b, A a2);

    void onClickMoreListener(T t, P p, A a2, B b);

    void onLongClickListener(T t, P p, B b, A a2);
}
