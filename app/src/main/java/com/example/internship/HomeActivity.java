package com.example.internship;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import com.example.internship.databinding.ActivityHomeBinding;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    private ActivityHomeBinding binding;

    int i;
    private ArrayList<String> ids;
    private ArrayList<String> url;
    private ArrayList<String> fullID;
    private ArrayList<String> tittle;
    GridAdapter gridAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ProgressBar progressBar = binding.progress;
        TextView om = binding.om;

        String APIKEY = this.getResources().getString(R.string.API_KEY);


        //Volley
        String C1 = "https://youtube.googleapis.com/youtube/v3/channels?part=snippet%2CcontentDetails%2Cstatistics&id=UCRUAdVm9ZOF4JheOd8qIQHA&key="+APIKEY;
        String C2 = "https://youtube.googleapis.com/youtube/v3/channels?part=snippet%2CcontentDetails%2Cstatistics&id=UCDe0DwkMVFfSIoiYdQUPQmQ&key="+APIKEY;
        String C3 = "https://youtube.googleapis.com/youtube/v3/channels?part=snippet%2CcontentDetails%2Cstatistics&id=UCJeQx6mAyNdPUc9sJA866Xw&key="+APIKEY;
        String C4 = "https://youtube.googleapis.com/youtube/v3/channels?part=snippet%2CcontentDetails%2Cstatistics&id=UCSzOZ97LOpU-_AVlGfmD4rQ&key="+APIKEY;
        String C5 = "https://youtube.googleapis.com/youtube/v3/channels?part=snippet%2CcontentDetails%2Cstatistics&id=UCHq7ZxlzRRXimaBmk5QAxSQ&key="+APIKEY;
        String C6 = "https://youtube.googleapis.com/youtube/v3/channels?part=snippet%2CcontentDetails%2Cstatistics&id=UCUUIz69kK3Ib5bD4hWLKAwA&key="+APIKEY;
        String C7 = "https://youtube.googleapis.com/youtube/v3/channels?part=snippet%2CcontentDetails%2Cstatistics&id=UC8Igqo3g1U40n66BLb-xHuQ&key="+APIKEY;
        String C8 = "https://youtube.googleapis.com/youtube/v3/channels?part=snippet%2CcontentDetails%2Cstatistics&id=UCCR5eciEJIMvX2o1tiYOUKQ&key="+APIKEY;
        String C9 = "https://youtube.googleapis.com/youtube/v3/channels?part=snippet%2CcontentDetails%2Cstatistics&id=UCfwa_zKl8-zC9rQDWIEixgg&key="+APIKEY;
        String C10 = "https://youtube.googleapis.com/youtube/v3/channels?part=snippet%2CcontentDetails%2Cstatistics&id=UCT_QwW7Tbew5qrYNb2auqAQ&key="+APIKEY;
        String C11 = "https://youtube.googleapis.com/youtube/v3/channels?part=snippet%2CcontentDetails%2Cstatistics&id=UC04m8d9t8UeWZ5DuvQVnqiw&key="+APIKEY;
        String C12 = "https://youtube.googleapis.com/youtube/v3/channels?part=snippet%2CcontentDetails%2Cstatistics&id=UC6vQRTCxutg6fJLUGkDKynQ&key="+APIKEY;
        String C13 = "https://youtube.googleapis.com/youtube/v3/channels?part=snippet%2CcontentDetails%2Cstatistics&id=UCOizxR3GwY7dmehMCAdvv9g&key="+APIKEY;
        String C14 = "https://youtube.googleapis.com/youtube/v3/channels?part=snippet%2CcontentDetails%2Cstatistics&id=UCyIkg79GpPVF77qYKoAINtw&key="+APIKEY;
        String C15 = "https://youtube.googleapis.com/youtube/v3/channels?part=snippet%2CcontentDetails%2Cstatistics&id=UCDqkux3AH7P9hRjmunoUeAQ&key="+APIKEY;
        String C16 = "https://youtube.googleapis.com/youtube/v3/channels?part=snippet%2CcontentDetails%2Cstatistics&id=UC7ZivIYRB0fMSGh-THcTYbw&key="+APIKEY;
        String C17 = "https://youtube.googleapis.com/youtube/v3/channels?part=snippet%2CcontentDetails%2Cstatistics&id=UCaayLD9i5x4MmIoVZxXSv_g&key="+APIKEY;
        String C18 = "https://youtube.googleapis.com/youtube/v3/channels?part=snippet%2CcontentDetails%2Cstatistics&id=UCHKGDg0GJKBsA9mFraDOLHA&key="+APIKEY;

        String[] keys = {C1,C2,C3,C4,C5,C6,C7,C8,C9,C10,C11,C12,C13,C14,C15,C16,C17,C18};
        ids = new ArrayList<>();
        tittle = new ArrayList<>();
        url = new ArrayList<>();
        fullID = new ArrayList<>();

        progressBar.setVisibility(View.VISIBLE);
        om.setVisibility(View.VISIBLE);

        for(i=0;i<keys.length;i++) {

            JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, keys[i], null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                //items
                                JSONArray items = response.getJSONArray("items");
                                //snippet
                                JSONObject result1 = items.getJSONObject(0);
                                String id = result1.getString("id");
                                ids.add(id);
                                JSONObject snippet = result1.getJSONObject("snippet");
                                //tittle
                                String title = snippet.getString("title");
                                tittle.add(title);
                                //thumbnails
                                JSONObject thumbnails = snippet.getJSONObject("thumbnails");
                                //default
                                JSONObject default1 = thumbnails.getJSONObject("default");
                                //url
                                String urll = default1.getString("url");
                                url.add(urll);

                            } catch (Exception e) {

                            }

                            if(ids.size()==18)
                            {
                                Log.d("ALLISWELL", "IDS : "+ids);
                                Log.d("ALLISWELL", "url : "+url);
                                Log.d("ALLISWELL", "title : "+tittle);
                                gridAdapter = new GridAdapter(HomeActivity.this,url,tittle,ids);
                                binding.gridView.setAdapter(gridAdapter);

                                progressBar.setVisibility(View.GONE);
                                om.setVisibility(View.GONE);

                                binding.gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                                        Intent intent = new Intent(getApplicationContext(),YoutubeActivity.class);

                                        intent.putExtra("id",ids.get(position));
                                        intent.putExtra("title",tittle.get(position));

                                        startActivity(intent);

                                    }
                                });


                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });

            Volley.newRequestQueue(this).add(request);

        }

    }
}