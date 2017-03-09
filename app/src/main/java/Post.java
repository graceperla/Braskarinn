/**
 * Created by anna on 08-Mar-17.
 */

public class Post {
    private int _id;
    private int _userId;
    private String _content;
    private int _category;

    public int get_id() {
        return _id;
    }

    public int get_userId() {
        return _userId;
    }

    public String get_content() {
        return _content;
    }

    public int get_category() {
        return _category;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public void set_userId(int _userId) {
        this._userId = _userId;
    }

    public void set_content(String _content) {
        this._content = _content;
    }

    public void set_category(int _category) {
        this._category = _category;
    }

    public Post() {
    }
}
