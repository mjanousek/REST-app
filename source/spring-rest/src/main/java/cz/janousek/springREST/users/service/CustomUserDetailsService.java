/**
 * author: Martin Janousek
 */
package cz.janousek.springREST.users.service;

import cz.janousek.springREST.model.dao.UserDAO;
import java.util.Collection;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailsService")
public class CustomUserDetailsService implements UserDetailsService {


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                System.out.println("CustomUserDetailsService: loadByUserName: " + username);
		cz.janousek.springREST.model.pojo.User user = UserDAO.findByUserName(username);
		if (user == null) {
			throw new UsernameNotFoundException(String.format("User %s does not exist!", username));
		}
                List<GrantedAuthority> authorities = buildUserAuthority(user.makeUserRole());
//		return new UserRepositoryUserDetails(user);
                return buildUserForAuthentication(user, authorities);
	}

        private org.springframework.security.core.userdetails.User buildUserForAuthentication(cz.janousek.springREST.model.pojo.User user, List<GrantedAuthority> authorities) {
            return new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(), user.isEnabled(), true, true, true, authorities);
        }
        
        private List<GrantedAuthority> buildUserAuthority(Set<String> userRoles) {

            Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();

            // Build user's authorities
            for (String userRole : userRoles) {
                setAuths.add(new SimpleGrantedAuthority(userRole));
            }

            List<GrantedAuthority> Result = new ArrayList<GrantedAuthority>(setAuths);

            return Result;
        }

}
