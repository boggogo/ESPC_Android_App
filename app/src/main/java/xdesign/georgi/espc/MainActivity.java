package xdesign.georgi.espc;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

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



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);






//        usersRepository.findAll(new ListCallback<Model>() {
//            @Override
//            public void onSuccess(List<Model> models) {
//                Log.e(TAG,"onSuccess - findAll");
//
//                for(Model m: models){
//                    Log.e(TAG,(((User_ESPC)m).getName()));
//                    Log.e(TAG,m.get("name") + "");
//                    Log.e(TAG,m.get("id") + "");
//                }
//            }
//
//            @Override
//            public void onError(Throwable t) {
//                Log.e(TAG,"onError - findAll");
//            }
//        });







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
