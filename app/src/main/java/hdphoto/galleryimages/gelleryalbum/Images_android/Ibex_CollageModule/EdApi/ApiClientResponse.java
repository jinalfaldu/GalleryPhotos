package hdphoto.galleryimages.gelleryalbum.Images_android.Ibex_CollageModule.EdApi;

import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.Response;


public final class ApiClientResponse implements Interceptor {
    public static final ApiClientResponse INSTANCE = new ApiClientResponse();

    private ApiClientResponse() {
    }

    @Override
    public final Response intercept(Interceptor.Chain chain) {
        try {
            return ApiClient.GetResponseOfApi(chain);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
