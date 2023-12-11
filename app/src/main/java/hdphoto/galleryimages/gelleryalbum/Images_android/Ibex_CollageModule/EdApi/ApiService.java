package hdphoto.galleryimages.gelleryalbum.Images_android.Ibex_CollageModule.EdApi;

import io.reactivex.Single;
import retrofit2.http.GET;


public interface ApiService {
    @GET("emojis.php")
    Single<EmojisResponse> getEmojis();

    @GET("patterns.php")
    Single<PatternsResponse> getPatterns();
}
