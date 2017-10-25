package android.adrielsoares.github.com.pancs;
import android.provider.ContactsContract;
import android.util.Log;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Converter;
import retrofit2.Converter.Factory;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

class PancsDAO {
    private static final String TAG = PancsDAO.class.getSimpleName();

    private static final String BASE_URL = "https://notes-5aa5.restdb.io/rest/";

    private PancsService service;

    public PancsDAO() {
        Factory jsonFactory = GsonConverterFactory.create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(jsonFactory)
                .build();

        this.service = retrofit.create(PancsService.class);
    }

    public List<pancs> readAllPancs() {
        Call<List<pancs>> call = this.service.listPlantas();
        List<pancs> panc = null;

        try {
            Response<List<pancs>> res = call.execute();

            if (res.isSuccessful()) {
                panc = res.body();
            } else {
                String errorBody = res.errorBody().string();
                int errorCode = res.code();
                String errorMessage = res.message();

                Log.e(TAG, "Request not successful - "
                        + errorBody + ": "
                        + errorCode
                        + "(" + errorMessage + ")");
            }
        } catch (IOException exc){
            Log.e(TAG, "IO error during REST operation.", exc);
        }
        return panc;
    }

    public pancs createNote(pancs pancs) {
        Call<pancs> call = this.service.createPancs(pancs);
        pancs newPanc = null;

        try {
            Response<pancs> res = call.execute();

            if (res.isSuccessful()) {
                newPanc = res.body();
            } else {
                String errorBody = res.errorBody().string();
                int errorCode = res.code();
                String errorMessage = res.message();

                Log.e(TAG, "Request not successful - "
                        + errorBody + ": "
                        + errorCode
                        + " (" + errorMessage + ")");
            }
        } catch (IOException exc) {
            Log.e(TAG, "IO error during REST operation.", exc);
        }
        return newPanc;
    }
}
