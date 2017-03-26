/**
 * Created by anna on 08-Mar-17.
 */

public class Post {
    private int _id;
    private int _userId;
    private String _content;
    private PostCategory _category;

    public int get_id() {
        return _id;
    }

    public int get_userId() {
        return _userId;
    }

    public String get_content() {
        return _content;
    }

    public PostCategory get_category() {
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

    public void set_category(PostCategory _category) {
        this._category = _category;
    }

    public Post() {
    }

    public static void createNewPost(int id, int userId, String content, int category) {

    }
}
