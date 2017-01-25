package socialcops.piyush6348.com.socialcopsproblem.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.io.File;
import java.io.FilenameFilter;

import socialcops.piyush6348.com.socialcopsproblem.R;
import socialcops.piyush6348.com.socialcopsproblem.adapter.ImageAdapter;

public class UploadedData extends AppCompatActivity {
    public static String path = "/storage/emulated/0/Android/data/";
    private GridView gridView;
    private ImageAdapter imageAdapter;
    private File[] fList;
    private String[] fPath;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uploaded_data);

        path = path + "socialcops.piyush6348.com.socialcopsproblem" + "/files/";
        defineView();
        fList = loadData();

        if(fList!=null)
        {
            imageAdapter = new ImageAdapter(fPath, UploadedData.this);
            gridView.setAdapter(imageAdapter);
        }
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (fPath[i].endsWith(".jpg")) {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setDataAndType(Uri.parse(fPath[i]), "image/*");
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(fPath[i]));
                    intent.setDataAndType(Uri.parse(fPath[i]), "video/mp4");
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onPause() {
        super.onPause();
        fList = loadData();

        if(fList!=null)
        {
            imageAdapter = new ImageAdapter(fPath, UploadedData.this);
            gridView.setAdapter(imageAdapter);
        }
    }

    private File[] loadData() {

        final String[] EXTENSIONS = new String[]{
                "jpg", "mp4" // and other formats you need
        };
        // filter to identify images based on their extensions
        final FilenameFilter IMAGE_FILTER = new FilenameFilter() {

            @Override
            public boolean accept(final File dir, final String name) {
                for (final String ext : EXTENSIONS) {
                    if (name.endsWith("." + ext)) {
                        {
                            Log.e("accept: ", name);
                            return (true);
                        }
                    }
                }
                return (false);
            }
        };

        File dir = new File(path);
        Log.e("loadData: ", dir.getAbsolutePath() + "hi");
        File[] filelist = dir.listFiles(IMAGE_FILTER);

        if(filelist!=null)
        {
            fPath=new String[filelist.length];
            for(int i=0;i<filelist.length;i++)
                fPath[i]=filelist[i].getAbsolutePath();

        }
        return filelist;
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
        gridView = (GridView) findViewById(R.id.grid_view);
    }
}
