package android.adrielsoares.github.com.pancs;

import android.provider.ContactsContract;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by adriel on 24/10/17.
 */

public interface PancsService {
    @GET("pancs")
    @Headers("x-apikey: ")
    Call<List<pancs>> listPlantas();

    @POST("pancs")
    @Headers("x-apikey: ")
    Call<pancs> createPancs(@Body pancs panc);
}
