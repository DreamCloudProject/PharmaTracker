package ru.dreamcloud.authentication.persistence.jpa;

import java.io.Serializable;
import javax.persistence.*;


/*delimiter $$

CREATE TABLE `common_user_info` (
  `user_info_id` int(11) NOT NULL AUTO_INCREMENT,
  `login` varchar(45) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `firstname` varchar(255) DEFAULT NULL,
  `middlename` varchar(255) DEFAULT NULL,
  `lastname` varchar(255) DEFAULT NULL,
  `sessionid` varchar(255) DEFAULT NULL,
  `role` int(11) DEFAULT NULL,
  PRIMARY KEY (`user_info_id`),
  KEY `fk_role_user_info_idx` (`role`),
  CONSTRAINT `fk_role_user_info` FOREIGN KEY (`role`) REFERENCES `common_roles` (`role_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8$$
 */
@Entity
@Table(name="common_user_info")
public class CommonUserInfo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="user_info_id")
	private int userInfoId;

	private String firstname;

	private String lastname;

	private String login;

	private String middlename;

	private String password;
	
	@ManyToOne(cascade={CascadeType.PERSIST},fetch=FetchType.LAZY)
	@JoinColumn(name="role")
	private CommonRole role;

	private String sessionid;

	public CommonUserInfo() {
	}

	public int getUserInfoId() {
		return this.userInfoId;
	}

	public void setUserInfoId(int userInfoId) {
		this.userInfoId = userInfoId;
	}

	public String getFirstname() {
		return this.firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return this.lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getMiddlename() {
		return this.middlename;
	}

	public void setMiddlename(String middlename) {
		this.middlename = middlename;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public CommonRole getRole() {
		return role;
	}

	public void setRole(CommonRole role) {
		this.role = role;
	}

	public String getSessionid() {
		return this.sessionid;
	}

	public void setSessionid(String sessionid) {
		this.sessionid = sessionid;
	}

}