package eOrderButler.model;
import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
 
@Entity
@Table(name = "users")
public class User implements Serializable {

	private static final long serialVersionUID = 2681531852204068105L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String emailId;
	private String password;
	private String userId;
	
	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserId() {
		
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}

}
