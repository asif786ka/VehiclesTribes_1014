package vehicles.com.roomormcars.di;

import android.app.Application;
import android.arch.persistence.room.Room;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import vehicles.com.roomormcars.data.local.VehiclesDatabase;
import vehicles.com.roomormcars.data.local.dao.VehiclesDao;
import vehicles.com.roomormcars.data.remote.ApiConstants;
import vehicles.com.roomormcars.data.remote.VehiclesDBService;
import vehicles.com.roomormcars.data.remote.RequestInterceptor;
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
        //okHttpClient.addInterceptor(new RequestInterceptor());
        return okHttpClient.build();
    }

    @Provides
    @Singleton
    VehiclesDBService provideRetrofit(OkHttpClient okHttpClient) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiConstants.ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        return retrofit.create(VehiclesDBService.class);
    }


    @Provides
    @Singleton
    VehiclesDatabase provideVehicleDatabase(Application application) {
        return Room.databaseBuilder(application, VehiclesDatabase.class, "aa.db").build();
    }

    @Provides
    @Singleton
    VehiclesDao provideVehicleDao(VehiclesDatabase vehiclesDatabase) {
        return vehiclesDatabase.vehiclesDao();
    }



}
