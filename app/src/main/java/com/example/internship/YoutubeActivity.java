package com.example.internship;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import com.example.internship.databinding.ActivityYoutubeBinding;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class YoutubeActivity extends AppCompatActivity {

    private ActivityYoutubeBinding binding;
    String id;
    String title;
    ArrayList<String> videoidlive;
    ArrayList<String> thumbnaillive;
    ArrayList<String> videotitlelive;
    ArrayList<String> videoid;
    ArrayList<String> videothumbnail;
    ArrayList<String> videotitle;
    RecyclerView recyclerView;
    RecyclerView recyclerView2;
    YoutubeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityYoutubeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        String APIKEY = this.getResources().getString(R.string.API_KEY);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
             id = extras.getString("id");
             title = extras.getString("title");
            Log.d("fff", "ID = "+id);
            Log.d("fff", "title = "+title);
        }

        String linklivestreams ="https://www.googleapis.com/youtube/v3/search?part=snippet&channelId="+id+"+&eventType=live&type=video&key="+APIKEY;
        String linkmostwatched ="https://www.googleapis.com/youtube/v3/search?key="+APIKEY+"&channelId="+id+"&part=snippet,id&order=viewcount&maxResults=10";
        String l = linkmostwatched;

        videotitle = new ArrayList<>();
        videothumbnail = new ArrayList<>();
        videoid = new ArrayList<>();

        videotitlelive = new ArrayList<>();
        thumbnaillive = new ArrayList<>();
        videoidlive = new ArrayList<>();

        recyclerView = binding.recyclerviewmostview;
        ProgressBar PL = binding.progresslive;
        ProgressBar PM = binding.progressmost;
        TextView omlive = binding.omlive;
        TextView ommost = binding.ommost;

        PL.setVisibility(View.VISIBLE);
        PM.setVisibility(View.VISIBLE);
        omlive.setVisibility(View.VISIBLE);
        ommost.setVisibility(View.VISIBLE);


        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, linklivestreams, null,
                new Response.Listener<JSONObject>() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            //items
                            JSONArray items = response.getJSONArray("items");

                            for(int i=0;i<items.length();i++)
                            {

                                JSONObject ii = items.getJSONObject(i);
                                Log.d("tttt", i+"= "+items.get(i));

                                //videoID
                                JSONObject id = ii.getJSONObject("id");
                                String videoId = id.getString("videoId");
                                videoidlive.add(videoId);


                                //title

                                JSONObject snippet = ii.getJSONObject("snippet");
                                String title = snippet.getString("title");
                                videotitlelive.add(title);


                                //thumbnail

                                JSONObject thumbnail = snippet.getJSONObject("thumbnails");
                                JSONObject defaultt = thumbnail.getJSONObject("high");
                                String thumbnaill = defaultt.getString("url");
                                thumbnaillive.add(thumbnaill);


                            }



                        } catch (Exception e) {

                        }

                        if(videoidlive.size()==0)
                        {
                            binding.recyclerviewlive.setVisibility(View.GONE);
                            binding.noStream.setVisibility(View.VISIBLE);

                            RelativeLayout layout = binding.layout1;

                            ViewGroup.LayoutParams params = layout.getLayoutParams();

                            params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
                            layout.setLayoutParams(params);


                        }
                        else {
                            binding.recyclerviewlive.setVisibility(View.VISIBLE);
                            binding.noStream.setVisibility(View.GONE);



                            recyclerView = binding.recyclerviewlive;
                            adapter = new YoutubeAdapter(videoidlive, thumbnaillive, videotitlelive, 1,getApplicationContext());
                            recyclerView.setAdapter(adapter);
                            recyclerView.setLayoutManager(new LinearLayoutManager(YoutubeActivity.this));
                        }

                        PL.setVisibility(View.GONE);
                        omlive.setVisibility(View.GONE);



                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        Volley.newRequestQueue(this).add(request);

        JsonObjectRequest request1 = new JsonObjectRequest(Request.Method.GET, l, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            //items
                            JSONArray items = response.getJSONArray("items");

                            for(int i=0;i<items.length();i++)
                            {

                                JSONObject ii = items.getJSONObject(i);
                                Log.d("tttt", i+"= "+items.get(i));

                                //videoID
                                JSONObject id = ii.getJSONObject("id");
                                String videoId = id.getString("videoId");
                                videoid.add(videoId);


                                //title

                                JSONObject snippet = ii.getJSONObject("snippet");
                                String title = snippet.getString("title");
                                videotitle.add(title);


                                //thumbnail

                                JSONObject thumbnail = snippet.getJSONObject("thumbnails");
                                JSONObject defaultt = thumbnail.getJSONObject("high");
                                String thumbnaill = defaultt.getString("url");
                                videothumbnail.add(thumbnaill);


                            }
                            Log.d("ggg", "Title: "+videotitle);
                            Log.d("ggg", "ID: "+videoid);
                            Log.d("ggg", "thumbail: "+videothumbnail);


                        } catch (Exception e) {

                        }
                        recyclerView2 = binding.recyclerviewmostview;
                        adapter = new YoutubeAdapter(videoid, videothumbnail,videotitle,0,getApplicationContext());
                        recyclerView2.setAdapter(adapter);
                        recyclerView2.setLayoutManager(new LinearLayoutManager(YoutubeActivity.this));

                        PM.setVisibility(View.GONE);
                        ommost.setVisibility(View.GONE);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        Volley.newRequestQueue(this).add(request1);

    }


}