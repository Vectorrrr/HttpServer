package server.session;

import model.User;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Class session simulates a single person,
 * the session is valid as long as the user log out.
 * Sessions are considered equal if they are equal to id
 * @author Gladush Ivan
 * @since 30.03.16.
 */
public class Session {
    private static AtomicInteger countSession = new AtomicInteger(-2);
    private int id;
    private User user;

    public Session(User user) {
        this.user = user;
        this.id = countSession.addAndGet(1);
    }

    Session(int id) {
        this.id = id;
    }

    public int getId() {
        return countSession.get();
    }

    public String userName() {
        return user.getName();
    }

    public String userSurname() {
        return user.getSurname();
    }

    @Override
    public boolean equals(Object o) {
        return o != null && o instanceof Session && ((Session) o).getId() == id;

    }
}
