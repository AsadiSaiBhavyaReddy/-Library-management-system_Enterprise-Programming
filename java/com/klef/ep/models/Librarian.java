package com.klef.ep.models;

import java.io.Serializable;  

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="librarian_table")
public class Librarian implements Serializable
{
	@Id  // primary key = unique + not null
	@Column(name="l_id")
	private int id;
	@Column(name="l_name",nullable = false,length = 50)
	private String name;
	@Column(name="l_gender",nullable = false,length = 10)
	private String gender;
	@Column(name="l_email",nullable = false,length = 50,unique = true)
	private String email;
	@Column(name="l_password",nullable = false,length = 20)
	private String password;
	@Column(name="l_contact",nullable = false,length = 20,unique = true)
	private String contact;
	@Column(name = "approved", nullable = false,length = 10)
	private String approved;
	public int getId() 
	{
		return id;
	}
	public void setId(int id) 
	{
		this.id = id;
	}
	public String getName() 
	{
		return name;
	}
	public void setName(String name) 
	{
		this.name = name;
	}
	public String getGender() 
	{
		return gender;
	}
	public void setGender(String gender) 
	{
		this.gender = gender;
	}
	public String getEmail() 
	{
		return email;
	}
	public void setEmail(String email) 
	{
		this.email = email;
	}
	public String getPassword() 
	{
		return password;
	}
	public void setPassword(String password) 
	{
		this.password = password;
	}
	public String getContact() 
	{
		return contact;
	}
	public void setContact(String contact) 
	{
		this.contact = contact;
	}
	public String getApproved() 
	{
		return approved;
	}
	public void setApproved(String approved) 
	{
		this.approved = approved;
	}
}
