package hdphoto.galleryimages.gelleryalbum.Images_android.Ibex_CollageModule.EdApi;

import hdphoto.galleryimages.gelleryalbum.Images_android.Ibex_CollageModule.Cashier_MirrorUtils.Cashier_Constants;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ApiClient {
    static OkHttpClient okHttpClient;
    static Retrofit retrofit;

    public static Retrofit GetClient() {
        if (okHttpClient == null) {
            LoadOkHttp();
        }
        if (retrofit == null) {
            retrofit = new Retrofit.Builder().baseUrl(Cashier_Constants.BASE_URL).client(okHttpClient).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;
    }

    private static void LoadOkHttp() {
        OkHttpClient.Builder writeTimeout = new OkHttpClient().newBuilder().connectTimeout(30L, TimeUnit.SECONDS).readTimeout(30L, TimeUnit.SECONDS).writeTimeout(30L, TimeUnit.SECONDS);
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        writeTimeout.addInterceptor(httpLoggingInterceptor);
        writeTimeout.addInterceptor(ApiClientResponse.INSTANCE);
        okHttpClient = writeTimeout.build();
    }

    public static Response GetResponseOfApi(Interceptor.Chain chain) throws IOException {
        Request.Builder addHeader = chain.request().newBuilder().addHeader("Accept", "application/json").addHeader("Content-Type", "application/json");
        addHeader.addHeader("Auth-Token", Cashier_Constants.API_TOKEN);
        return chain.proceed(addHeader.build());
    }
}
