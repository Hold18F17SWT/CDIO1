package dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class UserDTO implements Serializable{

	private static final long serialVersionUID = 4545864587995944260L;
	private int	userId;                     
	private String userName;                
	private String ini;
	private String cpr;
	private String password;
	private List<String> roles;
	
	public UserDTO(int userId, String userName, String ini, String cpr) {
		this.userId = userId;
		this.userName = userName;
		this.ini = ini;
		this.cpr = cpr;
		this.password = generateRandomPassword();
		this.roles = new ArrayList<>();
	}
	
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getIni() {
		return ini;
	}

	public void setIni(String ini) {
		this.ini = ini;
	}

    public String getCpr() {
        return cpr;
    }

    public void setCpr(String cpr) {
        this.cpr = cpr;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}
	
	public void addRole(String role){
		this.roles.add(role);
	}
	/**
	 * 
	 * @param role
	 * @return true if role existed, false if not
	 */
	public boolean removeRole(String role){
		return this.roles.remove(role);
	}

	@Override
	public String toString() {
		return "UserDTO [userId=" + userId + ", userName=" + userName + ", ini=" + ini + ", cpr=" + cpr + ", password=" + password + ", roles=" + roles + "]";
	}

	// mindst 6 tegn
	// brug mindst 3 af de fire kategorier
	private String generateRandomPassword(){
		char[] upper = (new String("abcdefghijklmnopqrstuvwxyzæøå")).toCharArray();
		char[] lower = (new String("ABCDEFGHIJKLMNOPQRSTUVWXYZÆØÅ")).toCharArray();
		char[] numbers = (new String("0123456789")).toCharArray();
		char[] special = (new String(".-_+!?=")).toCharArray();

		String password = "";

		for (int i = 0; i <= 4; i++) {
			password+=upper[(int) (Math.random()*upper.length)];
			password+=lower[(int) (Math.random()*lower.length)];
			password+=numbers[(int) (Math.random()*numbers.length)];
			password+=special[(int) (Math.random()*special.length)];
		}

		return password;
	}
}
