package crm_app10.services;

import java.util.List;

import crm_app10.repository.UserRepository;
import entity.Users;

public class UserServices {
	private UserRepository userRepository = new UserRepository();
	public List<Users> findAll() {
		return userRepository.findAll();
	}
}
