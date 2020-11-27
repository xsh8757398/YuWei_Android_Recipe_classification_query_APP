package user;

/**
 * AbstractUser entity provides the base persistence definition of the User
 * entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractUser implements java.io.Serializable {

	// Fields

	private String userId;
	private String userName;
	private String userPsd;

	// Constructors

	/** default constructor */
	public AbstractUser() {
	}

	/** full constructor */
	public AbstractUser(String userName, String userPsd) {
		this.userName = userName;
		this.userPsd = userPsd;
	}

	// Property accessors

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPsd() {
		return this.userPsd;
	}

	public void setUserPsd(String userPsd) {
		this.userPsd = userPsd;
	}

}