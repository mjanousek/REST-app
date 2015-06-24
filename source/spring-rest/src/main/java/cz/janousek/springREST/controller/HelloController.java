/**
 * author: Martin Janousek
 */
package cz.janousek.springREST.controller;

import cz.janousek.springREST.model.dao.UserDAO;
import cz.janousek.springREST.model.pojo.User;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author martin
 */
@Controller
public class HelloController {

	@RequestMapping(value = { "/welcome**" }, method = RequestMethod.GET)
	public ModelAndView welcomePage() {

		ModelAndView model = new ModelAndView();
		model.addObject("title", "Spring Security Custom Login Form");
		model.addObject("message", "This is welcome page!");
		model.setViewName("hello");
		return model;

	}
        @RequestMapping(value = "/admin**", method = RequestMethod.GET)
    public ModelAndView adminPage() {

        ModelAndView model = new ModelAndView();
        //prida promennou title s obsahem "Spring Security ..." 
        model.addObject("title", "Spring Security Custom Login Form");
        //prida promennou message a tak dale
        model.addObject("message", "This is protected page!");
                //bude vyvolavat sablonu admin.jsp
        model.setViewName("admin");

        try {
            //vypise vsechny uzivatele z databaze
            List<User> lst = UserDAO.users();
            //posle je do jsp sablony v promenne users
            model.addObject("users", lst);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return model;

    }    

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(@RequestParam(value = "error", required = false) String error,
            @RequestParam(value = "logout", required = false) String logout, HttpServletRequest request) {

        ModelAndView model = new ModelAndView();
        if (error != null) {
            model.addObject("error", getErrorMessage(request, "SPRING_SECURITY_LAST_EXCEPTION"));
        }

        if (logout != null) {
            model.addObject("msg", "You've been logged out successfully.");
        }
        model.setViewName("login");

        return model;

    }

    /**
    *   slouzi pro tisk chybovych hlaseni
    */
    private String getErrorMessage(HttpServletRequest request, String key) {

        Exception exception = (Exception) request.getSession().getAttribute(key);

        String error = "";
        if (exception instanceof BadCredentialsException) {
            error = "Zadali jste spatne prihlasovaci udaje!";
        } else if (exception instanceof LockedException) {
            error = exception.getMessage();
        } else {
            error = "Zadali jste spatne prihlasovaci udaje!";
        }

        return error;
    }

    // zamitnut pristup
    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public ModelAndView accesssDenied() {

        ModelAndView model = new ModelAndView();

        // kontrola uzivatele
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetail = (UserDetails) auth.getPrincipal();
            System.out.println(userDetail);

            model.addObject("username", userDetail.getUsername());

        }

        model.setViewName("403");
        return model;

    }
    
    /**
     * rest
     */
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public @ResponseBody String test() {

            String str = "ahoj";
            return str;

    }

    @RequestMapping(value = "/testuser", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody User testUsers() {
            User u = new User();
            u.setName("Martin");
            u.setEmail("email");
            return u;
    }
}
