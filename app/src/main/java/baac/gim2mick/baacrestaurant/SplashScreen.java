package baac.gim2mick.baacrestaurant;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import static baac.gim2mick.baacrestaurant.R.id.imvMonkey;

public class SplashScreen extends AppCompatActivity {

    //Explicit
    private ImageView monkeyImageView;
    private AnimationDrawable objAnimationDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Handler objHandler = new Handler();
        objHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashScreen.this, MainActivity.class));
                finish();
            }
        }, 3000);

        //Sound Effect
        MediaPlayer introPlayer = MediaPlayer.create(getBaseContext(),R.raw.cat);
        introPlayer.start();

        //show animate
        /*
        monkeyImageView = (ImageView)findViewById(R.id.imvMonkey);
        monkeyImageView.setBackgroundResource(android.R.anim.monkey);
        objAnimationDrawable = monkeyImageView.getBackground();
        objAnimationDrawable.start();
        */

    } //On Create

}//Main Class
