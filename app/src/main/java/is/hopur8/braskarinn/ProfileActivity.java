package is.hopur8.braskarinn;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = "Profile";

    private TextView mUsernameTextView;
    private TextView mEmailTextView;
    private EditText mUsernameField;

    private FirebaseAuth firebaseAuth;


    public ProfileActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        //Initialize views
        mUsernameField = (EditText) findViewById(R.id.newName);
        mUsernameTextView = (TextView) findViewById(R.id.userName);
        mEmailTextView = (TextView) findViewById(R.id.userEmail);

        //Initialize buttons
        findViewById(R.id.changeUsernameButton).setOnClickListener(this);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        updateUI(user);
    }

    public void changeDisplayName(String newName){

        if(!validateForm()){
            Toast.makeText(ProfileActivity.this, R.string.auth_failed,
                    Toast.LENGTH_SHORT).show();
            return;
        }

        FirebaseUser user = firebaseAuth.getInstance().getCurrentUser();
        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                .setDisplayName(newName)
               // .setPhotoUri(Uri.parse("https://example.com/jane-q-user/profile.jpg"))
                .build();

        user.updateProfile(profileUpdates)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "User profile updated.");
                        }
                    }
                });
    }


    private boolean validateForm() {
        boolean valid = true;

        String name = mUsernameField.getText().toString();
        if (TextUtils.isEmpty(name)) {
            mUsernameField.setError("Required.");
            valid = false;
        } else {
            mUsernameField.setError(null);
        }
        return valid;
    }

    private void updateUI(FirebaseUser user) {
        if (user != null) {
            mEmailTextView.setText(getString(R.string.profileEmail,
                    user.getEmail()));
            if(user.getDisplayName() != null) mUsernameTextView.setText(getString(R.string.profileUsername,
                    user.getDisplayName()));

        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_front, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        if (id == R.id.action_aboutus) {

            Intent intent = new Intent(this, AboutActivity.class);
            startActivity(intent);

            return true;
        }

        if (id == R.id.action_faq) {

            Intent intent = new Intent(this, FaqActivity.class);
            startActivity(intent);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.changeUsernameButton) {
            changeDisplayName(mUsernameField.getText().toString());//(mEmailField.getText().toString(), mPasswordField.getText().toString());
            updateUI(firebaseAuth.getInstance().getCurrentUser());
        }/* else if (i == R.id.email_sign_in_button) {
            signIn(mEmailField.getText().toString(), mPasswordField.getText().toString());
        } else if (i == R.id.sign_out_button) {
            signOut();
        } else if (i == R.id.verify_email_button) {
            sendEmailVerification();
        }*/
    }
}


