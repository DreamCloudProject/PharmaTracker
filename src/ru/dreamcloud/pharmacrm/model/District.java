package ru.dreamcloud.pharmacrm.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="districts")
public class District implements Serializable{
	
	@Id	@GeneratedValue(strategy = IDENTITY)
	@Column(name="district_id")
	private Integer districtId;	
	private String title;
	private String description;
	
	@OneToMany(cascade={CascadeType.ALL}, mappedBy="district")
    private List<Region> regions;
	
	public Integer getDistrictId() {
		return districtId;
	}
	
	public void setDistrictId(Integer districtId) {
		this.districtId = districtId;
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

	public List<Region> getRegions() {
		return regions;
	}

	public void setRegions(List<Region> regions) {
		this.regions = regions;
	}
	
}
