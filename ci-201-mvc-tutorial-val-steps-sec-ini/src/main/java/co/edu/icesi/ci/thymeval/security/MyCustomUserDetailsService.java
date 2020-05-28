package co.edu.icesi.ci.thymeval.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import co.edu.icesi.ci.thymeval.model.UserApp;
import co.edu.icesi.ci.thymeval.repository.UserRepository;

@Service
public class MyCustomUserDetailsService implements UserDetailsService {
	
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserApp userApp = null;
		if (userApp != null) {
			User.UserBuilder builder = User.withUsername(username);
			return builder.build();
		} else {
			throw new UsernameNotFoundException("User not found.");
		}
	}
}