package xdesign.georgi.espc;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.common.collect.ImmutableBiMap;
import com.google.common.collect.ImmutableMap;
import com.strongloop.android.loopback.Model;
import com.strongloop.android.loopback.ModelRepository;
import com.strongloop.android.loopback.RestAdapter;
import com.strongloop.android.loopback.callbacks.ListCallback;
import com.strongloop.android.loopback.callbacks.VoidCallback;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements VoidCallback {

    private static final String TAG = MainActivity.class.getSimpleName();
    private ModelRepository featuresRepository;
    private RestAdapter adapter;
    private TextView mOutputTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Set up Loopback's adapter and tell it how to connect to the backend api
        adapter = new RestAdapter(getApplicationContext(), "http://10.0.2.2:3000/api");
        // Create and initialize the Feature model
        featuresRepository = adapter.createRepository("Feature");

        mOutputTextView = (TextView)findViewById(R.id.output);


        featuresRepository.findAll(new ListCallback<Model>() {
            @Override
            public void onSuccess(List<Model> models) {

                Log.e(TAG,"onSuccess - findAll. List size: " + models.size());

                for(Model m: models){
                    Log.e(TAG,"Feature name:" + m.get("name").toString() + " ID:"+m.get("id").toString() + " roomID: " + m.get("roomID").toString() + "\n");
                    mOutputTextView.append("Feature name:" + m.get("name").toString() + " ID:"+m.get("id").toString() + " roomID: " + m.get("roomID").toString() + "\n");
                }
            }

            @Override
            public void onError(Throwable t) {
                Log.e(TAG,"onError - findAll" + t.toString());
            }
        });




    }













    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSuccess() {
        Log.e(TAG,"onSuccess");
    }

    @Override
    public void onError(Throwable t) {
        Log.e(TAG,"onError - " + t.toString());
    }
}
