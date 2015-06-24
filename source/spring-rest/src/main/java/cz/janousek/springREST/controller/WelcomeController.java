/**
 * author: Martin Janousek
 */

package cz.janousek.springREST.controller;

import ch.qos.logback.classic.util.ContextInitializer;
import cz.janousek.springREST.model.pojo.User;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.security.core.Authentication;

import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {

	private static final String template = "Welcome, %s!";
	
	private final AtomicLong counter = new AtomicLong();

	@RequestMapping("/greeting")
	public Welcome greeting(Authentication user) {
            System.out.println("user " + user.getName());
            return new Welcome(counter.incrementAndGet(), String.format(template, user.getName()));
	}

}
