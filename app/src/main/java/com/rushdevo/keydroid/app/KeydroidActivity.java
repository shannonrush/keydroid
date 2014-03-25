package com.rushdevo.keydroid.app;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.rushdevo.keydroid.app.db.KeydroidDatabaseHelper;
import com.rushdevo.keydroid.app.db.UserDataSource;
import com.rushdevo.keydroid.app.db.model.User;


public class KeydroidActivity extends ActionBarActivity {

    private KeydroidDatabaseHelper keydroidDatabaseHelper;
    private UserDataSource userDataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keydroid);
        keydroidDatabaseHelper = new KeydroidDatabaseHelper(this);
        initializeUI();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void initializeUI() {
        Button registerButton = (Button)findViewById(R.id.register_user_button);
        EditText nameField = (EditText)findViewById(R.id.keybase_id_field);
        registerButton.setVisibility(View.VISIBLE);
        nameField.setVisibility(View.VISIBLE);
    }

    public void saveUser(View view) {
        // create user in db
        EditText nameField = (EditText)findViewById(R.id.keybase_id_field);

        String keybase_id = nameField.getText().toString();
        User user = new User(null,keybase_id);
        userDataSource = new UserDataSource(this);
        userDataSource.open();
        userDataSource.createUser(user);

        // save keybase_id and keydroid_id in AWS DB
    }

//    public void registerDevice(View v) {
//        EditText nameField = (EditText)findViewById(R.id.device_name_field);
//        DEVICE_NAME = nameField.getText().toString();
//        GCMRegistrar.checkDevice(this);
//        GCMRegistrar.checkManifest(this);
//        final String regId = GCMRegistrar.getRegistrationId(this);
//        if (regId.equals("")) {
//            GCMRegistrar.register(this, GCM_PROJECT_ID);
//        } else {
//            Log.v("CamCamActivity", "Already registered with GCM");
//        }
//    }

}
