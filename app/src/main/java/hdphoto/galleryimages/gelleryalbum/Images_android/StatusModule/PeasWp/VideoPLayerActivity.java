package hdphoto.galleryimages.gelleryalbum.Images_android.StatusModule.PeasWp;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import hdphoto.galleryimages.gelleryalbum.Alofi_AdsAds.OnBackPressed;
import hdphoto.galleryimages.gelleryalbum.R;
import hdphoto.galleryimages.gelleryalbum.Images_android.Bongo_PhotoModule.Lotus_Activity.PreviewActivity;

import java.io.File;


public class VideoPLayerActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_video_player);
        getWindow().setFlags(1024, 1024);
        VideoView videoView = (VideoView) findViewById(R.id.videoView1);
        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);
        ((ImageView) findViewById(R.id.backbutton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        ((TextView) findViewById(R.id.titlename)).setText(String.valueOf(new File(new File(PreviewActivity.ImgPath).getName())));
        Uri parse = Uri.parse(PreviewActivity.ImgPath);
        videoView.setMediaController(mediaController);
        videoView.setVideoURI(parse);
        videoView.requestFocus();
        videoView.start();
    }

    @Override 
    public void onBackPressed() {
        OnBackPressed.onBackPressed(this, true);

    }
}
