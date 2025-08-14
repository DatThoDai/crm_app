package crm_app10.services;

import java.util.List;

import crm_app10.repository.RoleRepository;
import entity.Role;

public class RoleService {
	private RoleRepository roleRepository = new RoleRepository();

	public List<Role> findAllRole() {
		return roleRepository.findAllRole();
	}
}
