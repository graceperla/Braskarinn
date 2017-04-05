package is.hopur8.braskarinn;

import java.util.Date;

/**
 * Created by anna on 08-Mar-17.
 */

public class Post {
    private String _id;
    private String _userId;
    private String _content;
    private String _title;
    private long _negativeTimeStamp;
    //private is.hopur8.braskarinn.PostCategory _category;

    public String get_id() {
        return _id;
    }

    public String get_userId() {
        return _userId;
    }

    public String get_content() {
        return _content;
    }

    public String get_title() {
        return _title;
    }

    public long get_negativeTimeStamp() {
        return _negativeTimeStamp;
    }

    /*public is.hopur8.braskarinn.PostCategory get_category() {
        return _category;
    }*/

    public void set_id(String id) {
        this._id = id;
    }

    public void set_userId(String userId) {
        this._userId = userId;
    }

    public void set_content(String content) {
        this._content = content;
    }

    public void set_title(String title) {
        this._title = title;
    }

    public void set_negativeTimeStamp(long negativeTimeStamp) {
        this._negativeTimeStamp = negativeTimeStamp;
    }

    /*public void set_category(is.hopur8.braskarinn.PostCategory _category) {
        this._category = _category;
    }*/

    public Post() {
    }

    public Post(String id, String userId, String content, String title) {
        this._id = id;
        this._userId = userId;
        this._content = content;
        this._title = title;
        this._negativeTimeStamp = -1 * new Date().getTime();
    }
}
