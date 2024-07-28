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

import com.klef.ep.models.Admin;
import com.klef.ep.models.Book;
import com.klef.ep.models.Librarian;
import com.klef.ep.models.User;
import com.klef.ep.services.AdminService;

@ManagedBean(name="adminbean",eager = true)
@SessionScoped
public class AdminBean 
{
  
  @EJB(lookup="java:global/LMSPROJ/AdminServiceImpl!com.klef.ep.services.AdminService")
  AdminService adminService;
  
  private Admin admin;
  private String username;
  private String password;
  private List<Librarian> liblist;
  private List<Librarian> pendinglist;
  private List<Book> booklist;
  private static int id;
  private Book viewbook;
  private List<User> userlist;
  private List<User> pendingusers;
  
public List<Librarian> getPendinglist() 
{
	
	return adminService.viewpendingregs();
}
public void setPendinglist(List<Librarian> pendinglist) 
{
	this.pendinglist = pendinglist;
}
public String getUsername() 
{
  return username;
}
@Override
public String toString() 
{
  return "AdminBean [username=" + username + ", password=" + password + "]";
}
public void setUsername(String username) 
{
  this.username = username;
}
public String getPassword() 
{
  return password;
}
public void setPassword(String password) 
{
  this.password = password;
}

  public List<Librarian> getLiblist() 
  {
	  
	return adminService.viewalllibrarians();
	  
}
public void setLiblist(List<Librarian> liblist) 
{
	this.liblist = liblist;
}
public List<Book> getBooklist() 
{
	return adminService.ViewAllBooks();
}
public void setBooklist(List<Book> booklist) 
{
	this.booklist = booklist;
}
public Book getViewbook() 
{
	return viewbook;
}
public void setViewbook(Book viewbook) 
{
	this.viewbook = viewbook;
}


public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public Admin getAdmin() {
	return admin;
}
public void setAdmin(Admin admin) {
	this.admin = admin;
}
public List<User> getUserlist() {
	return adminService.viewallusers();
}
public void setUserlist(List<User> userlist) {
	this.userlist = userlist;
}
public List<User> getPendingusers() {
	return adminService.viewpendinguserregs();
}
public void setPendingusers(List<User> pendingusers) {
	this.pendingusers = pendingusers;
}


public void validateadminlogin() throws IOException
  {
   FacesContext facesContext = FacesContext.getCurrentInstance();
     ExternalContext externalContext = facesContext.getExternalContext();
  
     HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
   HttpServletResponse response = (HttpServletResponse) externalContext.getResponse();
  
   Admin admin = adminService.checkadminlogin(username, password);
   
   if(admin!=null)
   {
	   HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
     session.setAttribute("admin", admin);
    this.admin = admin;
    FacesContext.getCurrentInstance().getExternalContext().redirect("adminhome.jsf");
   }
   else
   {
	   FacesContext.getCurrentInstance().getExternalContext().redirect("adminloginfail.jsf");
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

  
  public String reject(int lid)
	{
		adminService.rejectlibrarian(lid);
		
		return "pendingregs.jsf";
	}
  
  public String accept(int lid)
	{
		adminService.acceptlibrarian(lid);
		
		return "pendingregs.jsf";
	}
  
  public String delete(int lid)
	{
		adminService.rejectlibrarian(lid);
		
		return "viewalllibrarians.jsf";
	}
  public String rejectuser(int uid)
	{
		adminService.rejectuser(uid);
		
		return "pendingusers.jsf";
	}

public String acceptuser(int uid)
	{
		adminService.acceptuser(uid);
		
		return "pendingusers.jsf";
	}

public String deleteuser(int uid)
	{
		adminService.rejectuser(uid);
		
		return "viewallusers.jsf";
	}
  public String deletebook(int bid)
	{
		adminService.deletebook(bid);
		
		return "viewallbooksad.jsp";
	}
  public String viewbookbyid(int id)
	{
		viewbook = adminService.ViewBookByID(id);
		return "displaybook.jsf";
		
	}
  public String updatebook(int bid)
  {
	  this.id = bid;
	  return "updatebook.jsp";
  }

}