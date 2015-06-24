/**
 * author: Martin Janousek
 */
package cz.janousek.springREST;

import cz.janousek.springREST.model.pojo.User;
import cz.janousek.springREST.model.utils.HibernateUtil;
import org.hibernate.Session;
import org.springframework.security.crypto.bcrypt.BCrypt;

/**
 *
 * @author martin
 */
public class App {
    public static void main(String[] args) {
    System.out.println("Maven + Hibernate + Oracle");
    Session session = HibernateUtil.getSessionFactory().openSession();

    session.beginTransaction();  //pocatek databazove transakce

        User user = new User(); // vytvoreni uzivatele, ktereho budeme ukladat
        user.setName("John");   // nastaveni jmena 

        BCrypt bc = new BCrypt(); // BCrypt pro zahesovani hesla

        user.setPassword(bc.hashpw("123456", bc.gensalt())); // ulozeni hesla s pouzitim BCrypt

        user.setEmail("example@email");  //nastaveni emailu

    session.save(user);   // ulozeni uzivatele
    session.getTransaction().commit();   // potvrzeni zmen
    }
}
