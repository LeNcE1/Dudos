package com.example.lence.dudos.view;


import android.util.Log;

import com.example.lence.dudos.api.App;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Presenter {
    MVP mvp;

    public Presenter(MVP MVP) {
        mvp = MVP;
    }

    public void loadGet() {

        final List<String> getList = new ArrayList<>();
        App.getApi().get().enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                String json = null;
                JSONArray jsonArray;
                try {
                    json = response.body().string();

                    jsonArray = new JSONArray(json);
                    JSONObject jsonObject;
                    for (int i =0; i<jsonArray.length();i++) {

                        jsonObject = jsonArray.getJSONObject(i);
                        getList.add(jsonObject.getString("name"));

                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                mvp.showGet(getList);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("get", "error: " + t);
            }


        });

    }

    public void loadPost(int k) {

        final List<String> getList = new ArrayList<>();

    App.getApi().post(k).enqueue(new Callback<ResponseBody>() {
        @Override
        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
            String json = null;
            JSONObject jsonObject;

            try {
                json = response.body().string();

                jsonObject = new JSONObject(json);


                getList.add(jsonObject.getString("title"));
                } catch (IOException e1) {
                e1.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            Log.e("post", getList.toString());
            mvp.showPost(getList);

        }

        @Override
        public void onFailure(Call<ResponseBody> call, Throwable t) {
            Log.e("get", "error: " + t);
        }


    });


    }
}
