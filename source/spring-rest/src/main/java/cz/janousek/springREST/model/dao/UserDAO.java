/**
 * author: Martin Janousek
 */
package cz.janousek.springREST.model.dao;

import cz.janousek.springREST.model.pojo.User;
import cz.janousek.springREST.model.utils.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author martin
 */
public class UserDAO {
    public static List<User> users() {
        //list users, ktery bude vracen
        List<User> lst = null;

        try {
            //otevreme si session, HibernateUtil je trida vytvorena v model.util
            Session session = HibernateUtil.getSessionFactory().openSession();

            //ze ktere tabulky budeme vybirat
            String hql = "from  User";
            //votvoreni query
            Query query = session.createQuery(hql);
            //vykonani a navraceni hodnoty
            lst = query.list();

            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return lst;
}
        @SuppressWarnings("unchecked")
	public static User findByUserName(String username) {
		
            List<User> users = new ArrayList<User>();

            try {
                Session session = HibernateUtil.getSessionFactory().openSession();

                System.out.println("Username  = " + username);

                users = session.createQuery("from User where name=?").setParameter(0, username)
                                .list();
                session.close();

                System.out.println("User a login = " + users);

            } catch (Exception e) {
            e.printStackTrace();
        }
		
            if (users.size() > 0) {
                    return users.get(0);
            } else {
                    return null;
            }
		

	}
}
