package com.klef.ep.services;

import java.util.List;

import javax.ejb.Remote;

import com.klef.ep.models.Book;
import com.klef.ep.models.BookIssue;
import com.klef.ep.models.RejectedBooks;
import com.klef.ep.models.User;

@Remote
public interface UserService 
{
	public void adduser(User user);
	public User checkuserlogin(String email, String password);
	public List<BookIssue> viewreqbooks(String name);
	public List<RejectedBooks> viewrejbooks(String name);
	public List<Book> ViewAllBooks();
}