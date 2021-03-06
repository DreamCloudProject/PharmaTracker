package ru.dreamcloud.pharmatracker.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import ru.dreamcloud.pharmatracker.model.authentication.CommonUserInfo;

/*delimiter $$

CREATE TABLE `events` (
  `event_id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(1024) DEFAULT NULL,
  `description` varchar(1024) DEFAULT NULL,
  `date_time_start` datetime NOT NULL,
  `date_time_reg` datetime NOT NULL,
  `date_time_plan` datetime NOT NULL,
  `date_time_end` datetime NOT NULL,
  `notification_create_flag` varchar(5) DEFAULT NULL,
  `message_type` enum('TODO','IN_PROGRESS','DONE') DEFAULT NULL,
  `patient_history` int(11) DEFAULT NULL,
  `event_reason` int(11) DEFAULT NULL,
  `user_info` int(11) DEFAULT NULL,
  `posted_by_user` int(11) DEFAULT NULL,
  `event_number` int(11) DEFAULT NULL,
  PRIMARY KEY (`event_id`),
  UNIQUE KEY `event_id_UNIQUE` (`event_id`),
  KEY `fk_ph_event_idx` (`patient_history`),
  KEY `fk_er_event_idx` (`event_reason`),
  KEY `fk_user_event_idx` (`user_info`),
  KEY `fk_postedbyuser_event_idx` (`posted_by_user`),
  CONSTRAINT `fk_er_event` FOREIGN KEY (`event_reason`) REFERENCES `event_reasons` (`event_reason_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_ph_event` FOREIGN KEY (`patient_history`) REFERENCES `patient_histories` (`patient_histories_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_postedbyuser_event` FOREIGN KEY (`posted_by_user`) REFERENCES `common_user_info` (`user_info_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_event` FOREIGN KEY (`user_info`) REFERENCES `common_user_info` (`user_info_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8$$
*/

@Entity
@Table(name="events")
public class Event implements Serializable{
	
	@Id	@GeneratedValue(strategy = IDENTITY)
	@Column(name="event_id")
	private Integer eventId;
	
	private String title;
	
	private String description;
	
	@Column(name="date_time_start")
	private Timestamp dateTimeStart;
	
	@Column(name="date_time_reg")
	private Timestamp dateTimeReg;
	
	@Column(name="date_time_plan")
	private Timestamp dateTimePlan;
	
	@Column(name="date_time_end")
	private Timestamp dateTimeEnd;

	@ManyToOne(cascade={CascadeType.PERSIST},fetch=FetchType.LAZY)
    @JoinColumn(name = "patient_history")
	private PatientHistory patientHistory;
	
	@Column(name="event_number")
	private Integer eventNumber;
	
	@Column(name="notification_create_flag")
	private String notificationCreateFlag;
	
	@OneToMany(cascade={CascadeType.ALL}, mappedBy="event")
    private List<Document> documents;
	
	@Enumerated(EnumType.STRING)
	@Column(name="message_type")
	private MessageType messageType;
	
	@ManyToOne(cascade={CascadeType.PERSIST},fetch=FetchType.LAZY)
    @JoinColumn(name = "event_reason")
	private EventReason eventReason;
	
	@ManyToOne(cascade={CascadeType.PERSIST},fetch=FetchType.LAZY)
    @JoinColumn(name = "user_info")
	private CommonUserInfo userInfo;
	
	@ManyToOne(cascade={CascadeType.PERSIST},fetch=FetchType.LAZY)
    @JoinColumn(name = "posted_by_user")
	private CommonUserInfo postedByUser;
	
	@OneToMany(cascade={CascadeType.ALL}, mappedBy="event")
    private List<Notification> notifications;
	
	public Event() {
		// TODO Auto-generated constructor stub
	}

	public Integer getEventId() {
		return eventId;
	}

	public void setEventId(Integer eventId) {
		this.eventId = eventId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Timestamp getDateTimeStart() {
		return dateTimeStart;
	}

	public void setDateTimeStart(Timestamp dateTimeStart) {
		this.dateTimeStart = dateTimeStart;
	}	

	public Timestamp getDateTimeReg() {
		return dateTimeReg;
	}

	public void setDateTimeReg(Timestamp dateTimeReg) {
		this.dateTimeReg = dateTimeReg;
	}

	public Timestamp getDateTimePlan() {
		return dateTimePlan;
	}

	public void setDateTimePlan(Timestamp dateTimePlan) {
		this.dateTimePlan = dateTimePlan;
	}		

	public Timestamp getDateTimeEnd() {
		return dateTimeEnd;
	}

	public void setDateTimeEnd(Timestamp dateTimeEnd) {
		this.dateTimeEnd = dateTimeEnd;
	}

	public PatientHistory getPatientHistory() {
		return patientHistory;
	}

	public void setPatientHistory(PatientHistory patientHistory) {
		this.patientHistory = patientHistory;
	}	

	public Integer getEventNumber() {
		return eventNumber;
	}

	public void setEventNumber(Integer eventNumber) {
		this.eventNumber = eventNumber;
	}

	public String getNotificationCreateFlag() {
		return notificationCreateFlag;
	}

	public void setNotificationCreateFlag(String notificationCreateFlag) {
		this.notificationCreateFlag = notificationCreateFlag;
	}

	public List<Document> getDocuments() {
		return documents;
	}

	public void setDocuments(List<Document> documents) {
		this.documents = documents;
	}

	public MessageType getMessageType() {
		return messageType;
	}

	public void setMessageType(MessageType messageType) {
		this.messageType = messageType;
	}

	public EventReason getEventReason() {
		return eventReason;
	}

	public void setEventReason(EventReason eventReason) {
		this.eventReason = eventReason;
	}	

	public CommonUserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(CommonUserInfo userInfo) {
		this.userInfo = userInfo;
	}	

	public CommonUserInfo getPostedByUser() {
		return postedByUser;
	}

	public void setPostedByUser(CommonUserInfo postedByUser) {
		this.postedByUser = postedByUser;
	}

	public List<Notification> getNotifications() {
		return notifications;
	}

	public void setNotifications(List<Notification> notifications) {
		this.notifications = notifications;
	}
	
	
}
