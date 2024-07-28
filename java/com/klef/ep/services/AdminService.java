package com.klef.ep.services;

import java.util.List;

import javax.ejb.Remote;

import com.klef.ep.models.Admin;
import com.klef.ep.models.Book;
import com.klef.ep.models.Librarian;
import com.klef.ep.models.User;

@Remote
public interface AdminService 
{
	public Admin checkadminlogin(String username, String password);
	public void rejectlibrarian(int lid);
	public List<Librarian> viewalllibrarians();
	public List<Librarian> viewpendingregs();
	public long libcount();
	public void acceptlibrarian(int lid);
	public void AddBook(Book book);
	public List<Book> ViewAllBooks();
	public Book ViewBookByID(int id); 
	public void deletebook(int bid);
	public void updatebook(Book book);
	public void rejectuser(int uid);
	public List<User> viewallusers();
	public List<User> viewpendinguserregs();
	public long usercount();
	public void acceptuser(int uid);
}