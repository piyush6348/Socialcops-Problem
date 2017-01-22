package socialcops.piyush6348.com.socialcopsproblem;

import android.app.Application;
import android.util.Log;

import com.kinvey.android.Client;

import socialcops.piyush6348.com.socialcopsproblem.utils.Constants;

/**
 * Created by dell on 1/22/2017.
 */

public class MainApplication extends Application {
    private static Client mKinveyClient;
    @Override
    public void onCreate() {
        super.onCreate();
        defineClient();
    }

    private void defineClient() {
        Log.e( "defineClient: ","running" );
        mKinveyClient = new Client.Builder(Constants.APP_ID, Constants.APP_SECRET
                , getApplicationContext()).build();
    }
    public static Client getClient()
    {
        return mKinveyClient;
    }
}
