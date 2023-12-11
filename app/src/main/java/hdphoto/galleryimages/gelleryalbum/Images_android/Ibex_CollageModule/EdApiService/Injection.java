package hdphoto.galleryimages.gelleryalbum.Images_android.Ibex_CollageModule.EdApiService;

import hdphoto.galleryimages.gelleryalbum.Images_android.Ibex_CollageModule.EdApi.ApiClient;
import hdphoto.galleryimages.gelleryalbum.Images_android.Ibex_CollageModule.EdApi.ApiService;


public class Injection {
    public static ApiService provideApiService() {
        return (ApiService) ApiClient.GetClient().create(ApiService.class);
    }
}
