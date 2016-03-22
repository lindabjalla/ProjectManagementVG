package se.grouprich.projectmanagement.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AbstractEntity
{
	@Id
	@GeneratedValue
	private Long id;

	@Column(nullable = false, unique = true)
	private Long controlNumber;
	
	@Column(name = "createdBy")
	@CreatedBy
	private String createdBy;

	@Column
	@CreatedDate
	private Date createdDate;
	
	@Column
	@LastModifiedBy
	private String lastModifiedBy;
	
	@Column
	@LastModifiedDate
	private Date lastModifiedDate;

	protected AbstractEntity()
	{
		setControlNumber();
	}

	public Long getId()
	{
		return id;
	}

	public Long getControlNumber()
	{
		return controlNumber;
	}

	public void setControlNumber()
	{
		Date now = new Date();
		Long time = now.getTime();
		controlNumber = time;
	}
}
