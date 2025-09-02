package crm_app10.services;

import java.util.List;

import crm_app10.repository.RoleRepository;
import entity.Role;

public class RoleService {
	private RoleRepository roleRepository = new RoleRepository();

	public List<Role> findAllRole() {
		return roleRepository.findAllRole();
	}
	
	public boolean insertRole (String name, String desc) {
		return roleRepository.addRole(name, desc) > 0;
	}
	
	public boolean deleteRole (int id) {
		return roleRepository.deleteRole(id);
	}

	public Role findRoleById(int id) {
		return roleRepository.findById(id);
	}

	public boolean updateRole(int id, String roleName, String description) {
		return roleRepository.updateRole(id, roleName, description);
	}
}
