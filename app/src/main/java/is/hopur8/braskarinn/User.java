package is.hopur8.braskarinn;

/**
 * Created by anna on 09-Mar-17.
 */

public class User {
    private String _id;
    private String _name;
    private String _email;
    private String _phone;
    private int _rating;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public String get_email() {
        return _email;
    }

    public void set_email(String _email) {
        this._email = _email;
    }

    public String get_phone() {
        return _phone;
    }

    public void set_phone(String _phone) {
        this._phone = _phone;
    }

    public int get_rating() {
        return _rating;
    }

    public void set_rating(int _rating) {
        this._rating = _rating;
    }

    public User() {
    }

    public User(String id, String email) {
        this._id = id;
        this._email = email;
    }
}
