package com.klef.ep.beans;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import com.klef.ep.models.BookIssue;
import com.klef.ep.services.BookIssueService;

@ManagedBean(name = "bookbean",eager = true)
public class BookIssueBean 
{
	@EJB(lookup = "java:global/LMSPROJ/BookIssueServiceImpl!com.klef.ep.services.BookIssueService")
	BookIssueService issueservice;
	
	private int id;
	private String title;
	private String name;
	private String issue_status;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIssue_status() {
		return issue_status;
	}
	public void setIssue_status(String issue_status) {
		this.issue_status = issue_status;
	}
	
	public void bookissue(int id,String title,String name)
	{
		BookIssue bi = new BookIssue();
		bi.setTitle(title);
		bi.setName(name);
		bi.setIssue_status("Pending");
		
		issueservice.addbooksrequested(bi);
		//return "mybooks.jsf";
	}
}