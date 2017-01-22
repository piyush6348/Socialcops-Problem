package socialcops.piyush6348.com.socialcopsproblem.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;

import com.kinvey.android.AsyncAppData;
import com.kinvey.android.callback.KinveyListCallback;
import com.kinvey.java.Query;

import socialcops.piyush6348.com.socialcopsproblem.MainApplication;
import socialcops.piyush6348.com.socialcopsproblem.R;

public class UploadedData extends AppCompatActivity {

    private GridView gridView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uploaded_data);
        defineView();
        loadData();
    }

    private void loadData() {
        /*
        Query q=MainApplication.getClient().query();
        q.equals("_acl.creator",MainApplication.getClient().user().getId());
        final AsyncAppData<> imgVid= MainApplication.getClient().appData("_downloadURL",);
        imgVid.get(q, new KinveyListCallback() {
            @Override
            public void onSuccess(Object[] objects) {

            }

            @Override
            public void onFailure(Throwable throwable) {

            }

            @Override
            public void onSuccess(Object o) {

            }
        });*/
    }

    private void defineView() {
        gridView=(GridView)findViewById(R.id.grid_view);
    }
}
