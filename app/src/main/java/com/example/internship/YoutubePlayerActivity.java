package com.example.internship;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.internship.databinding.ActivityYoutubePlayerBinding;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import org.w3c.dom.Text;

public class YoutubePlayerActivity extends AppCompatActivity {

    private ActivityYoutubePlayerBinding binding;
    String videoID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityYoutubePlayerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ProgressBar progressBar = binding.progress;
        TextView om = binding.ommost;

        progressBar.setVisibility(View.VISIBLE);
        om.setVisibility(View.VISIBLE);

        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            videoID = extras.getString("videoID");
        }
        YouTubePlayerView youTubePlayerView = binding.player;
        getLifecycle().addObserver(youTubePlayerView);

        youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                String videoId = videoID;
                youTubePlayer.loadVideo(videoId, 0);
                progressBar.setVisibility(View.GONE);
                om.setVisibility(View.GONE);

            }
        });

        binding.fullscreenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                youTubePlayerView.enterFullScreen();
            }
        });

    }
}