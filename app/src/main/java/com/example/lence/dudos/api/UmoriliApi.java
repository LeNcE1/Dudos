package com.example.lence.dudos.api;




import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface UmoriliApi {
    @GET("/users")
    Call<ResponseBody> get();             //полдучения\ постов


    @GET("/posts/{id}")
    Call<ResponseBody> post(@Path("id") String id);

}
