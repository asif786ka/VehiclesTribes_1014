package shirts.com.roomormshirts.di;

import android.app.Application;
import android.arch.persistence.room.Room;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import shirts.com.roomormshirts.data.local.ShirtsDatabase;
import shirts.com.roomormshirts.data.local.dao.CartShirtDao;
import shirts.com.roomormshirts.data.local.dao.ShirtsDao;
import shirts.com.roomormshirts.data.remote.ApiConstants;
import shirts.com.roomormshirts.data.remote.CartShirtDBService;
import shirts.com.roomormshirts.data.remote.ShirtsDBService;
import shirts.com.roomormshirts.data.remote.RequestInterceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


@Module(includes = ViewModelModule.class)
public class AppModule {

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient() {
        OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();
        okHttpClient.connectTimeout(ApiConstants.TIMEOUT_IN_SEC, TimeUnit.SECONDS);
        okHttpClient.readTimeout(ApiConstants.TIMEOUT_IN_SEC, TimeUnit.SECONDS);
        okHttpClient.addInterceptor(new RequestInterceptor());
        return okHttpClient.build();
    }

    @Provides
    @Singleton
    ShirtsDBService provideRetrofit(OkHttpClient okHttpClient) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiConstants.ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        return retrofit.create(ShirtsDBService.class);
    }

    @Provides
    @Singleton
    CartShirtDBService provideCartRetrofit(OkHttpClient okHttpClient) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiConstants.ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        return retrofit.create(CartShirtDBService.class);
    }

    @Provides
    @Singleton
    ShirtsDatabase provideShirtDatabase(Application application) {
        return Room.databaseBuilder(application, ShirtsDatabase.class, "aa.db").build();
    }

    @Provides
    @Singleton
    ShirtsDao provideShirtDao(ShirtsDatabase shirtsDatabase) {
        return shirtsDatabase.shirtsDao();
    }

    @Provides
    @Singleton
    CartShirtDao provideCartShirtDao(ShirtsDatabase shirtsDatabase) {
        return shirtsDatabase.cartShirtsDao();
    }

}
