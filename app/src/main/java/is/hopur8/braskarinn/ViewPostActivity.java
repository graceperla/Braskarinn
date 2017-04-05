package is.hopur8.braskarinn;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class ViewPostActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;

    private TextView postTitle;
    private TextView postContent;
    private TextView postUser;

    private Post currentPost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_post);

        postTitle = (TextView) findViewById(R.id.postTitle);
        postContent = (TextView) findViewById(R.id.postContent);
        postUser = (TextView) findViewById(R.id.postUser);



        Bundle bundle = getIntent().getExtras();
        String itemKey = "";
        if(bundle != null)
            itemKey = bundle.getString("KEY");

        mDatabase = FirebaseDatabase.getInstance().getReference("posts").child(itemKey);
        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                currentPost = dataSnapshot.getValue(Post.class);
                postTitle.setText(currentPost.get_title());
                postContent.setText(currentPost.get_content());
                FindPostedUser();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

    private void FindPostedUser() {
        mDatabase = FirebaseDatabase.getInstance().getReference("users");
        Query query = mDatabase.child(currentPost.get_userId());
        //mDatabase = FirebaseDatabase.getInstance().getReference("users").child(currentPost.get_userId());
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User createdBy = dataSnapshot.getValue(User.class);
                if(createdBy != null)
                    postUser.setText(createdBy.get_email());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
