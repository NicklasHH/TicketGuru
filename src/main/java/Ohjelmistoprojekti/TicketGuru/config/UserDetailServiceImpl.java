package Ohjelmistoprojekti.TicketGuru.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import Ohjelmistoprojekti.TicketGuru.AppUser.AppUser;
import Ohjelmistoprojekti.TicketGuru.AppUser.AppUserRepository;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
	private final AppUserRepository repository;

	@Autowired
	public UserDetailServiceImpl(AppUserRepository userRepository) {
		this.repository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		AppUser curruser = repository.findByUsername2(username);
		System.out.println("Käyttäjän " + username + " rooli: " + curruser.getRole().getRoleName());
		UserDetails user = new org.springframework.security.core.userdetails.User(username, curruser.getPassword(),
				AuthorityUtils.createAuthorityList(curruser.getRole().getRoleName()));

		return user;
	}
}