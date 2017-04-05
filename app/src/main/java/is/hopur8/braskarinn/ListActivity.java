package is.hopur8.braskarinn;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

    private static final String TAG = "ListActivity";

    ListView mListView;
    private ArrayList<Post> mArraylistSectionPosts = new ArrayList<>();
    public static String clickedItem;

    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        mDatabase = FirebaseDatabase.getInstance().getReference("posts");

        mListView = (ListView) findViewById(R.id.postedList);
        //mListView.setStackFromBottom(true);

        final PostArrayAdapter arrayAdapterPosts = new PostArrayAdapter(this, mArraylistSectionPosts);
        mListView.setAdapter(arrayAdapterPosts);

        //Query query = mDatabase.orderByChild("_negativeTimeStamp");
        Query query = mDatabase.limitToLast(50);
        query.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Post newPost = dataSnapshot.getValue(Post.class);
                mArraylistSectionPosts.add(0, newPost);
                arrayAdapterPosts.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                Post changedPost = dataSnapshot.getValue(Post.class);
                for(int i = 0;i < mArraylistSectionPosts.size();i++) {
                    if(mArraylistSectionPosts.get(i).get_id().equals(changedPost.get_id())) {
                        mArraylistSectionPosts.set(i,changedPost);
                        arrayAdapterPosts.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                Post removedPost = dataSnapshot.getValue(Post.class);
                for(int i = 0;i < mArraylistSectionPosts.size();i++) {
                    if(mArraylistSectionPosts.get(i).get_id().equals(removedPost.get_id())) {
                        mArraylistSectionPosts.remove(i);
                        arrayAdapterPosts.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                //Things should not be moving, so don´t need this
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Log.i(TAG, String.valueOf(position));
                Log.i(TAG, String.valueOf(id));
                String test = mArraylistSectionPosts.get(position).get_id();

                Intent intent = new Intent(getApplicationContext(),ViewPostActivity.class);
                intent.putExtra("KEY", test);
                startActivity(intent);

            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
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

    public void openNewPost(View view) {
        Intent intent = new Intent(getApplicationContext(),NewPostActivity.class);
        startActivity(intent);
    }
}
