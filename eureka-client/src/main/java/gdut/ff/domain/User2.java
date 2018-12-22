package gdut.ff.domain;

public class User2 {

    private String message;

    private int id;

    public User2(int id,String message) {
        this.id = id;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "User{" +
                "message='" + message + '\'' +
                ", id=" + id +
                '}';
    }
}

