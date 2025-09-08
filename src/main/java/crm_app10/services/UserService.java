package crm_app10.services;

import java.util.List;

import crm_app10.repository.UserRepository;
import entity.Users;

public class UserService {
	private UserRepository userRepository = new UserRepository();
	public List<Users> findAll() {
		return userRepository.findAll();
	}
	
	public Users findUserById(int id) {
	    return userRepository.findUserById(id);
	}
	
	public Users findUserByEmail(String email) {
	    return userRepository.findUserByEmail(email);
	}
	
	public boolean deleteUser(int id) {
		return userRepository.deleteById(id) > 0;
	}
	
	public boolean addUser(String email, String password, String fullName, int roleId) {
		return userRepository.addUser(email, password, fullName, roleId);
	}
	
	public boolean updateUser(int id, String email, String password, String fullName, int roleId) {
	    return userRepository.updateUser(id, email, password, fullName, roleId);
	}
}
