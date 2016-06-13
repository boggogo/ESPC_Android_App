package xdesign.georgi.espc;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.common.collect.ImmutableMap;
import com.strongloop.android.loopback.Model;
import com.strongloop.android.loopback.ModelRepository;
import com.strongloop.android.loopback.RestAdapter;
import com.strongloop.android.loopback.callbacks.ListCallback;

import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = LoginActivity.class.getSimpleName();
    // UI references.
    private ProgressBar mProgressBar;
    private EditText mNamelView;
    private EditText mPasswordView;
    private Button mLoginButton;
    private RestAdapter adapter;
    private ModelRepository usersRepository;
    private Model user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Set up the login form.
        mNamelView = (EditText) findViewById(R.id.name);

        mPasswordView = (EditText) findViewById(R.id.password);

        mLoginButton = (Button) findViewById(R.id.log_in_button);

        mProgressBar = (ProgressBar) findViewById(R.id.login_progress);


        mLoginButton.setOnClickListener(this);

        // Set up Loopback's adapter and tell it how to connect to the backend api
        adapter = new RestAdapter(getApplicationContext(), "http://10.0.2.2:3000/api");
        // Create and initialize the Feature model
        usersRepository = adapter.createRepository("User_ESPC");

//        user = usersRepository.createObject(ImmutableMap.of("name","Testor Testov", "password","123"));
//        user.save(this);



    }


    @Override
    public void onClick(View v) {
        mProgressBar.setVisibility(View.VISIBLE);

        final String name = mNamelView.getText().toString().trim();
        final String password = mPasswordView.getText().toString().trim();



        usersRepository.findAll(new ListCallback<Model>() {
            @Override
            public void onSuccess(List<Model> models) {
                mProgressBar.setVisibility(View.GONE);
                Log.e(TAG,"onSuccess - findAll");

                for(Model m: models){


                    if((m.get("name").toString().equals(name))  && (m.get("password").toString().equals(password))    ){
                        Log.e(TAG,"USER EXISTS!");
                        startActivity(new Intent(LoginActivity.this,MainActivity.class));
                        Toast.makeText(LoginActivity.this,"Loged in as " + name , Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(LoginActivity.this,"Wrong user credentials!", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onError(Throwable t) {
                mProgressBar.setVisibility(View.GONE);
                Log.e(TAG,"onError - findAll" + t.toString());
            }
        });
    }
}

