package com.ayan.recylerviewwithapi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView rv = (RecyclerView) findViewById(R.id.rv);
        Log.i("Volley onCreate","onCreate");
        StringRequest request = new StringRequest("https://api.github.com/users", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i("Volley response",response.toString());
                GsonBuilder gsonBuilder =  new GsonBuilder();
                Gson gson = gsonBuilder.create();
                List<ObjectModel> objList = convertArrayToList(gson.fromJson(response,ObjectModel[].class));
                Log.i("JSON",objList.toString());
                RecylerViewExpAdapter adapter = new RecylerViewExpAdapter(objList);
                rv.setAdapter(adapter);
                rv.setLayoutManager(new LinearLayoutManager(MainActivity.this));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("Volley Error",error.getLocalizedMessage());
                Toast.makeText(MainActivity.this,"Something Went Wrong",Toast.LENGTH_SHORT);
            }
        });
        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
        queue.add(request);
    }

    //Use this to convert any array to array list
    public static <T> List<T> convertArrayToList(T array[]) {
        List<T> list = Arrays.asList(array);
        return list;
    }

}