package entity;

public class Users {
	private int id;
	private String email;
	private String password;
	private String fullName;
	private String avatar;
	private Role role;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public int getRoleId() {
		return role != null ? role.getId() : 0;
	}
	
	public void setRoleId(int roleId) {
		if (role == null) {
			role = new Role();
		}
		role.setId(roleId);
	}
	
	public Role getRole() {
		return role;
	}
	
	public void setRole(Role role) {
		this.role = role;
	}

	public Users() {

	}

	public Users(int id, String email, String password, String fullName, String avatar, int roleId) {
		this.id = id;
		this.email = email;
		this.password = password;
		this.fullName = fullName;
		this.avatar = avatar;
		this.role = new Role();
		this.role.setId(roleId);
	}
	
	
}
