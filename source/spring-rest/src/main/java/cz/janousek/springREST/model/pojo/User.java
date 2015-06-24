/**
 * author: Martin Janousek
 */
package cz.janousek.springREST.model.pojo;


import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * author: Martin Janousek
 */
@Entity
@Table(name="user"
    ,schema="public"
    , uniqueConstraints = @UniqueConstraint(columnNames="email") 
)
public class User  implements java.io.Serializable {


     private int idUser;
     private String email;
     private String name;
     private String password;
     private boolean isAdmin;
     private boolean enabled;
     
    public User() {
    }

	
    public User(int idUser, String email, String name, String password, boolean isAdmin, boolean enabled) {
        this.idUser = idUser;
        this.email = email;
        this.name = name;
        this.password = password;
        this.isAdmin = isAdmin;
        this.enabled = enabled;
    }
   
    @Id 
    @Column(name="id_user", unique=true, nullable=false)
    public int getIdUser() {
        return this.idUser;
    }
    
    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }


    
    @Column(name="email", unique=true, nullable=false)
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }

    
    @Column(name="name", nullable=false)
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    
    @Column(name="password", nullable=false, length=60)
    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }

    
    @Column(name="is_admin", nullable=false)
    public boolean isIsAdmin() {
        return this.isAdmin;
    }
    
    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    
    @Column(name="enabled", nullable=false)
    public boolean isEnabled() {
        return this.enabled;
    }
    
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Set<String> makeUserRole() {
        Set<String> roles = new HashSet<String>();
        if(isIsAdmin()){
            roles.add("ROLE_ADMIN");
        }
        roles.add("ROLE_USER");
        return roles;
    }


}


