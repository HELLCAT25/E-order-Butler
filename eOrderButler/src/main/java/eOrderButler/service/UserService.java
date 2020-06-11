package eOrderButler.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import eOrderButler.dao.UserDao;
import eOrderButler.model.User;

@Service
public class UserService implements UserDetailsService {
	
	@Autowired
	private UserDao userDao;
	
	
	public void addUser(User user) {
		userDao.addUser(user);
	}
	
	public User getUserByUserEmail(String userEmail) {
		return userDao.getUserByUserEmail(userEmail);
	}

	@Override
	public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {

		User user = userDao.getUserByUserEmail(userEmail);
		return CustomUserDetails.build(user);
	}
}
