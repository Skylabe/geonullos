package com.example.geonullos.Data;

import android.content.Context;
import android.widget.ImageView;

import com.example.geonullos.R;
import com.pixplicity.sharp.Sharp;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Utils {
    private static OkHttpClient httpClient;

    public static void fetchSvg(Context context, String url, final ImageView target) {
        if (httpClient == null) {
            // Use cache for performance and basic offline capability
            httpClient = new OkHttpClient.Builder()
                    .cache(new Cache(context.getCacheDir(), 5 * 1024 * 1014))
                    .build();
        }

        Request request = new Request.Builder().url(url).build();
        httpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                target.setImageResource(R.drawable.ic_launcher_background);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                InputStream stream = response.body().byteStream();
                try{
                    Sharp.loadInputStream(stream).into(target);
                } catch(Exception e){

                }

                stream.close();
            }
        });
    }

    public static boolean exists(Country c, List<Country> countries){
        for(Country c2 : countries){
            if(c.getName().equals(c2.getName())){
                return true;
            }
        }
        return false;
    }

    public static List<Country> remove(Country c, List<Country> cs){
        for(int i = 0 ; i < cs.size() ; i++){
            if(c.getName().equals(cs.get(i).getName())){
                cs.remove(i);
                return cs;
            }
        }
        return cs;
    }
}