package com.g24.authentication.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;

import java.util.Date;
import java.util.Calendar;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "tokens")
public class Token
{
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_gen_token")
	@SequenceGenerator(name = "seq_gen_token", sequenceName = "seq_token", initialValue = 500000000, allocationSize = 1)
	private Long id;

	@Column(nullable = false, unique = true)
	private String token;

	@Column(nullable = false)
	private String type;

	@OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
	@JoinColumn(nullable = false, name = "user_id")
	private User user;


	@Column(nullable = false)
	private Date expiryDate;

	public void setExpiryDate(int minutes)
	{
		Calendar now = Calendar.getInstance();
		now.add(Calendar.MINUTE, minutes);
		this.expiryDate = now.getTime();
	}

	public boolean isExpired() 
	{
		return new Date().after(this.expiryDate);
	}	
}
