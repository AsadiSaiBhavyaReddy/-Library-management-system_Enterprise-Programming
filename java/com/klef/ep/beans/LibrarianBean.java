package com.klef.ep.beans;


import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.klef.ep.models.Book;
import com.klef.ep.models.BookIssue;
import com.klef.ep.models.Librarian;
import com.klef.ep.services.LibrarianService;


@ManagedBean(name = "libbean", eager = true)
@SessionScoped
public class LibrarianBean 
{
	@EJB(lookup = "java:global/LMSPROJ/LibrarianServiceImpl!com.klef.ep.services.LibrarianService")
	LibrarianService service;
	private Librarian librarian;
	private int id;
	private String name;
	private String gender;
	private String email;
	private String password;
	private String contact;
	private String approved;
	private List<BookIssue> pendingreqs;
	private List<Book> booklist;
	
	public List<BookIssue> getPendingreqs() 
	{
		return service.viewpendingreqs();
	}
	public void setPendingreqs(List<BookIssue> pendingreqs) 
	{
		this.pendingreqs = pendingreqs;
	}
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
	public Librarian getLibrarian() {
		return librarian;
	}
	public void setLibrarian(Librarian librarian) {
		this.librarian = librarian;
	}
	public List<Book> getBooklist() {
		return service.ViewAllBooks();
	}
	public void setBooklist(List<Book> booklist) {
		this.booklist = booklist;
	}
	
	
	public String add()
	{
		Librarian l = new Librarian();
		l.setId(id);
		l.setName(name);
		l.setGender(gender);
		l.setEmail(email);
		l.setPassword(password);
		l.setContact(contact);
		l.setApproved("false");
		
		service.addlibrarian(l);
		
		//it will reset the form after redirection
		return "insertsuccess.jsf";
		
	}
	
	public void validateliblogin() throws IOException
	  {
	   FacesContext facesContext = FacesContext.getCurrentInstance();
	     ExternalContext externalContext = facesContext.getExternalContext();
	  
	     HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
	   HttpServletResponse response = (HttpServletResponse) externalContext.getResponse();
	  
	   Librarian librarian = service.checkliblogin(email, password);
	   
	   if(librarian==null)
	   {
		   response.sendRedirect("requestfail.html");
	   }
	   else if(librarian!=null && "false".equals(librarian.getApproved()))
	   {
			   response.sendRedirect("pendingreq.html");
		   
	   }
	   else {
		   HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
           session.setAttribute("librarian", librarian);
           this.librarian = librarian;

           FacesContext.getCurrentInstance().getExternalContext().redirect("libhome.jsf");
	   }
	  }
	
	public String logout() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "index.jsf";
    }
	
	public void reject(int bid)
	{
		service.rejectrequest(bid);
	}
  
  public void accept(int bid)
	{
		service.acceptrequest(bid);
	}
	
}