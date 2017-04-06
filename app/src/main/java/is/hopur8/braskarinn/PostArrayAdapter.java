package is.hopur8.braskarinn;

/**
 * Created by anna on 03-Apr-17.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class PostArrayAdapter extends ArrayAdapter<Post> {
    private final Context context;
    private final ArrayList<Post> values;

    public PostArrayAdapter(Context context, ArrayList<Post> values) {
        super(context, -1, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.list_view_item, parent, false);

        TextView textView = (TextView) rowView.findViewById(R.id.text1);
        TextView textView2 = (TextView) rowView.findViewById(R.id.text2);
        Button removeButton = (Button) rowView.findViewById(R.id.profileRemovePost);

        final Post currentPost = values.get(position);
        textView.setText(currentPost.get_title());
        textView2.setText(currentPost.get_content());
        if(context.getClass() == ProfileActivity.class) {
            removeButton.setVisibility(View.VISIBLE);
            removeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ProfileActivity profile = (ProfileActivity)context;
                    profile.removeClick(currentPost.get_id());
                }
            });
        }

        return rowView;
    }
}
