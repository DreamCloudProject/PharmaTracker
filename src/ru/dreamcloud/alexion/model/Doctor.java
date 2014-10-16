package ru.dreamcloud.alexion.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;


/*
delimiter $$

CREATE TABLE `doctors` (
  `doctor_id` int(11) NOT NULL AUTO_INCREMENT,
  `firstname` varchar(255) DEFAULT NULL,
  `middlename` varchar(255) DEFAULT NULL,
  `lastname` varchar(255) DEFAULT NULL,
  `contact_info` int(11) DEFAULT NULL,
  PRIMARY KEY (`doctor_id`),
  KEY `fk_contact_info_doctor_idx` (`contact_info`),
  CONSTRAINT `fk_contact_info_doctor` FOREIGN KEY (`contact_info`) REFERENCES `contact_info` (`contact_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8$$
*/
@Entity
@Table(name="doctors")
public class Doctor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="doctor_id")
	private int doctorId;

	@OneToOne(cascade={CascadeType.ALL},fetch=FetchType.LAZY)
	@JoinColumn(name="contact_info")
	private ContactInfo contactInfo;

	private String firstname;

	private String lastname;

	private String middlename;
	
	@OneToMany(cascade={CascadeType.PERSIST}, mappedBy="doctor")    
	private List<PatientHistory> patientHistories;

	public Doctor() {
	}

	public int getDoctorId() {
		return this.doctorId;
	}

	public void setDoctorId(int doctorId) {
		this.doctorId = doctorId;
	}

	public ContactInfo getContactInfo() {
		return this.contactInfo;
	}

	public void setContactInfo(ContactInfo contactInfo) {
		this.contactInfo = contactInfo;
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

	public String getMiddlename() {
		return this.middlename;
	}

	public void setMiddlename(String middlename) {
		this.middlename = middlename;
	}	
	
	public List<PatientHistory> getPatientHistories() {
		return patientHistories;
	}

	public void setPatientHistories(List<PatientHistory> patientHistories) {
		this.patientHistories = patientHistories;
	}

	@Transient
	public String getFullname(){
		return lastname + " " + firstname + " " + middlename;
	}

}