package com.rushdevo.keydroid.app;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.rushdevo.keydroid.app.db.UserDataSource;
import com.rushdevo.keydroid.app.db.model.User;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class RegisterActivity extends ActionBarActivity {

    private UserDataSource userDataSource;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initializeRegisterUI();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.register, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void initializeRegisterUI() {
        Button registerButton = (Button)findViewById(R.id.register_user_button);
        EditText nameField = (EditText)findViewById(R.id.keybase_id_field);
        registerButton.setVisibility(View.VISIBLE);
        nameField.setVisibility(View.VISIBLE);
    }

    public void saveUser() {
        User user = saveUserDB();
        Registration reg = new Registration();
    }

    private class Registration extends AsyncTask<Void,Void,String> {
        @Override
        protected String doInBackground(Void... params) {
            String msg = "";
            try {
                GoogleCloudMessaging gcm = GoogleCloudMessaging.getInstance(context);
                String regid = gcm.register("225850503170");
                msg = "Device registered, registration ID=" + regid;

                saveUserExternal(regid);

            } catch (IOException ex) {
                msg = "Error :" + ex.getMessage();
                // If there is an error, don't just keep trying to register.
                // Require the user to click a button again, or perform
                // exponential back-off.
            }
            return msg;
        }
    }



    private void saveUserExternal(String regid) {
        HttpClient httpClient = new DefaultHttpClient();
        String url = "http://localhost:3000/keydroid_users.json";
        // String url = "http://rushdevo.com/keydroid_users.json";
        HttpPost httpPost = new HttpPost(url);
        List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>(2);

        nameValuePair.add(new BasicNameValuePair("keydroid_id", "user@gmail.com"));
        nameValuePair.add(new BasicNameValuePair("keybase_id", null));

    }

    private User saveUserDB() {
        EditText nameField = (EditText)findViewById(R.id.keybase_id_field);
        String keybase_id = nameField.getText().toString();
        User user = new User(null,keybase_id);
        userDataSource = new UserDataSource(this);
        userDataSource.open();
        userDataSource.createUser(user);
        userDataSource.close();
        return user;
    }


}
