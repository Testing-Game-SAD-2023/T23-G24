package com.g24.authentication.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity

@Table(name = "users")

public class User
{
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_gen_user")
	@SequenceGenerator(name = "seq_gen_user", sequenceName = "seq_user", initialValue = 99999999, allocationSize = 1)
	private Long id;

	@Column(nullable = false)
	private boolean enabled;


	@Column(nullable = false)
	private String firstName;

	@Column(nullable = false)
	private String LastName;

	@Column(nullable = false, unique = true)
	private String email;

	@Column(nullable = false)
	private String password;

	private String degreeCourse;

	private String university;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinTable(
			name = "users_roles",
			joinColumns = {@JoinColumn(name = "USER_ID", referencedColumnName="ID")},
			inverseJoinColumns = {@JoinColumn(name = "ROLE_ID", referencedColumnName = "ID")})
	private List<Role> roles = new ArrayList<>();

}
