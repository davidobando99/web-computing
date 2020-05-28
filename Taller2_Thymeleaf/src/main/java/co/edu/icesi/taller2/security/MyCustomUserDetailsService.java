package co.edu.icesi.taller2.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import co.edu.icesi.taller2.model.TsscAdmin;
import co.edu.icesi.taller2.repository.AdminRepository;

@Service
public class MyCustomUserDetailsService implements UserDetailsService {
	
	@Autowired
	private AdminRepository adminRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		TsscAdmin tsscAdmin = adminRepository.findByUsername(username);
		if (tsscAdmin != null) {
			User.UserBuilder builder = User.withUsername(username).password(tsscAdmin.getPassword()).roles(tsscAdmin.getSuperAdmin());
			return builder.build();
		} else {
			throw new UsernameNotFoundException("User not found.");
		}
	}
}