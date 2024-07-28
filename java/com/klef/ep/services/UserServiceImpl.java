package com.klef.ep.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.klef.ep.models.Admin;
import com.klef.ep.models.Book;
import com.klef.ep.models.BookIssue;
import com.klef.ep.models.RejectedBooks;
import com.klef.ep.models.User;

@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class UserServiceImpl implements UserService
{

	@Override
	public void adduser(User user) 
	{
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		em.persist(user); 
		em.getTransaction().commit();
		
		em.close();
		emf.close();
		
	}

	@Override
	public User checkuserlogin(String email, String password) 
	{
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
	    EntityManager em = emf.createEntityManager();
	    
	    Query qry = em.createQuery("select u from User u where u.email=? and u.password=?  ");
	    qry.setParameter(1, email);
	    qry.setParameter(2, password);
	    
	        User user = null;
	        
	        if(qry.getResultList().size()>0)
	        {
	          user = (User) qry.getSingleResult();
	        }
	    em.close();
	    emf.close();
	    
	    return user;
	}

	@Override
	public List<BookIssue> viewreqbooks(String name) 
	{
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
		EntityManager em = emf.createEntityManager();
		
		Query qry = em.createQuery("select bi from BookIssue bi where bi.name=?");
		qry.setParameter(1, name);
		// e is an alias of Employee Class
		List<BookIssue> reqlist = qry.getResultList();
		
		if(reqlist==null) {
			em.close();
		    emf.close();
			return null;
		}
		else {
	    em.close();
	    emf.close();
	    
	    return reqlist;
		}
	}

	@Override
	public List<RejectedBooks> viewrejbooks(String name) 
	{
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
		EntityManager em = emf.createEntityManager();
		
		Query qry = em.createQuery("select bi from RejectedBooks bi where bi.name=?");
		qry.setParameter(1, name);
		// e is an alias of Employee Class
		List<RejectedBooks> rejlist = qry.getResultList();
		
		if(rejlist==null) {
			em.close();
		    emf.close();
			return null;
		}
		else {
	    em.close();
	    emf.close();
	    
	    return rejlist;
		}
	}

	@Override
	public List<Book> ViewAllBooks() 
	{
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
		 EntityManager em = emf.createEntityManager();
		  
	   em.getTransaction().begin();
	   
	   Query qry = em.createQuery("   select b from Book b "); 
	   List<Book> booklist = qry.getResultList();

	   em.close();
		    emf.close();
		    
		    return booklist;
	}

	

}