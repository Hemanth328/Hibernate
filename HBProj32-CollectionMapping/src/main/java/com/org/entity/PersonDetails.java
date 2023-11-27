package com.org.entity;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.annotations.ListIndexBase;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.MapKeyColumn;
import jakarta.persistence.OrderColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "HB_INHR_COLMAP_PERSONDETAILS")
public class PersonDetails {
	
	@Id
	@GeneratedValue
	private Integer pid;
	private String pname;
	private String paddrs;
	@ElementCollection
	@CollectionTable(name = "HB_NICKNAMES_COLL", joinColumns = @JoinColumn(name="PRSN_ID", referencedColumnName = "PID"))
	@Column(name="NICKNAME")
	@OrderColumn(name = "NNAME_INDEX")
	@ListIndexBase(value = 1 )
	private List<String> nickNames;
	
	@ElementCollection
	@CollectionTable(name = "HB_FRIENDS_COLL", joinColumns = @JoinColumn(name="PRSN_ID", referencedColumnName = "PID"))
	@Column(name = "FRIEND")
	private List<String> friends;
	
	@ElementCollection
	@CollectionTable(name = "HB_CONTACTNUM_COLL", joinColumns =	@JoinColumn(name="PRSN_ID", referencedColumnName = "PID"))
	@Column(name = "CONTACT_NUMBER", length = 13)
	private Set<Long> contactNumbers;
	
	@ElementCollection
	@CollectionTable(name = "HB_IDDETAILS_COLL", joinColumns = @JoinColumn(name="PRSN_ID", referencedColumnName = "PID"))
	@MapKeyColumn(name = "IDTYPE", length = 15)
	@Column(name = "ID_NUMBER", length = 18)
	private Map<String, Long> idDetails;
	
}

