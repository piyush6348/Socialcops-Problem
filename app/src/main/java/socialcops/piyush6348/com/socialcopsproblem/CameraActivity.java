package socialcops.piyush6348.com.socialcopsproblem;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class CameraActivity extends Activity {
    Button recordVideo,clickPicture;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //recordVideo=(Button)findViewById(R.id.record_video);
        //clickPicture=(Button)findViewById(R.id.click_picture);

        /*recordVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //if (null == savedInstanceState) {
                    getFragmentManager().beginTransaction()
                            .replace(R.id.container, Camera2VideoFragment.newInstance())
                            .commit();
                //}
            }
        });*/

        /*clickPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });*/

        getFragmentManager().beginTransaction()
                .replace(R.id.container, Camera2BasicFragment.newInstance())
                .commit();

    }
}
