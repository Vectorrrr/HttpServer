package server.session;

import model.User;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Gladush Ivan
 * @since 30.03.16.
 */
public class Session {

    private static AtomicInteger countSession =new AtomicInteger(-2);
    private int id;
    private User user;

    public Session(User user) {
        this.user = user;
        this.id = countSession.addAndGet(1);
    }

    Session(int id){
        this.id=id;
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
    public boolean equals(Object o){
        if(o==null)
            return false;
        if(!(o instanceof Session))
            return false;
        return ((Session) o).getId()==id;

    }
    @Override
    public String toString(){
        return user.toString()+" ID: "+id;
    }
}
