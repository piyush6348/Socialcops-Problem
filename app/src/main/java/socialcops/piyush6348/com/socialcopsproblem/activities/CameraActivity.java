package socialcops.piyush6348.com.socialcopsproblem.activities;

import android.app.Activity;
import android.os.Bundle;

import socialcops.piyush6348.com.socialcopsproblem.R;
import socialcops.piyush6348.com.socialcopsproblem.fragments.Camera2BasicFragment;

public class CameraActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getFragmentManager().beginTransaction()
                .replace(R.id.container, Camera2BasicFragment.newInstance())
                .commit();

    }
}
