package hdphoto.galleryimages.gelleryalbum.Alofi_AdsAds;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiInterface {

    @FormUrlEncoded
    @POST("hdphoto.galleryimages.gelleryalbum.php")
    Call<Object> getid(@Field("keyid") String str);

}
