package is.hopur8.braskarinn;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import is.hopur8.braskarinn.Post;

public class NewPostActivity extends AppCompatActivity {

    private static final String TAG = "NewPostActivity";
    private static final String REQUIRED = "Required";

    private DatabaseReference mDatabase;

    private EditText mNewPostTitle;
    private EditText mNewPostContent;
    private Button mCreatePostButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_post);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        Log.i(TAG,"START");

        mNewPostTitle = (EditText) findViewById(R.id.newPostTitle);
        mNewPostContent = (EditText) findViewById(R.id.newPostContent);
        mCreatePostButton = (Button) findViewById(R.id.createPostButton);
    }



    public void submitPost(View view) {

        final String title = mNewPostTitle.getText().toString();
        final String body = mNewPostContent.getText().toString();

        // Title is required
        if (TextUtils.isEmpty(title)) {
            mNewPostTitle.setError(REQUIRED);
            return;
        }

        // Body is required
        if (TextUtils.isEmpty(body)) {
            mNewPostContent.setError(REQUIRED);
            return;
        }

        // Disable button so there are no multi-posts
        setEditingEnabled(false);
        Toast.makeText(this, "Posting...", Toast.LENGTH_SHORT).show();

        final String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();

        String key = mDatabase.child("posts").push().getKey();

        Post post = new Post(key, userId, body, title);

        /*HashMap<String, Object> result = new HashMap<>();
        result.put("uid", userId);
        result.put("title", title);
        result.put("body", body);

        Map<String, Object> childUpdates = new HashMap<>();

        childUpdates.put("/posts/" + key, result);

        mDatabase.updateChildren(childUpdates);*/

        mDatabase.child("posts").child(key).setValue(post);

        setEditingEnabled(true);
        finish();
    }

    private void setEditingEnabled(boolean enabled) {
        mNewPostTitle.setEnabled(enabled);
        mNewPostContent.setEnabled(enabled);
        if (enabled) {
            mCreatePostButton.setVisibility(View.VISIBLE);
        } else {
            mCreatePostButton.setVisibility(View.GONE);
        }
    }
}
