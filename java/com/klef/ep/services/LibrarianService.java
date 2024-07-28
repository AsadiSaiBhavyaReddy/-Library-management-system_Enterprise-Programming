package com.klef.ep.services;

import java.util.List;



import javax.ejb.Remote;

import com.klef.ep.models.Admin;
import com.klef.ep.models.Book;
import com.klef.ep.models.BookIssue;
import com.klef.ep.models.Librarian;


@Remote
public interface LibrarianService 
{
	
	// CRUD Operations
  public String addlibrarian(Librarian librarian); //Registration
  public Librarian checkliblogin(String email, String password);
  public void acceptrequest(int bid);
  public void rejectrequest(int bid);
  public List<BookIssue> viewpendingreqs();
  public List<Book> ViewAllBooks();
  
}