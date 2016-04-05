import model.User;
import org.junit.Test;
import server.session.Session;
import server.session.SessionHolder;

import static junit.framework.TestCase.assertEquals;

/**
 * @author Gladush Ivan
 * @since 31.03.16.
 */
public class SessionTester {

    @Test
    public void session_holder_remove_session_test1(){
        SessionHolder.addSession(new User("Vanya","Gladush"));
        assertEquals(SessionHolder.sessionAmount(),1);
        Session session=SessionHolder.getSession("HTTP 1.1/Cookie: session=0;");
        assertEquals("Vanya",session.userName());
        assertEquals("Gladush",session.userSurname());
        SessionHolder.removeSession(0);
        session=SessionHolder.getSession("HTTP 1.1/Cookie: session=0;");
        assertEquals("anonim",session.userName());
        assertEquals("anonim",session.userSurname());
    }
}
