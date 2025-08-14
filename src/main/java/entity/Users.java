package entity;

public class Users {
	private int id;
	private String email;
	private String password;
	private String fullName;
	private String avatar;
	private int roleId;
	private String roleDescription;
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
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	
	public String getRoleDescription() {
		return roleDescription;
	}
	
	public void setRoleDescription(String roleDescription) {
		this.roleDescription = roleDescription;
	}

	public Users() {

	}

	public Users(int id, String email, String password, String fullName, String avatar, int roleId,
			String roleDescription) {
		this.id = id;
		this.email = email;
		this.password = password;
		this.fullName = fullName;
		this.avatar = avatar;
		this.roleId = roleId;
		this.roleDescription = roleDescription;
	}
	
	
}
