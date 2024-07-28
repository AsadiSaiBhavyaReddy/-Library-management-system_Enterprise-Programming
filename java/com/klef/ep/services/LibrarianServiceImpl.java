package com.klef.ep.services;

import java.util.List;    


import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.klef.ep.models.Book;
import com.klef.ep.models.BookIssue;
import com.klef.ep.models.Librarian;


@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class LibrarianServiceImpl implements LibrarianService
{
	@Override
	public String addlibrarian(Librarian librarian) 
	{
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		em.persist(librarian); 
		em.getTransaction().commit();
		
		em.close();
		emf.close();
		
		return "Librarian Inserted Successfully";
	}
	
	@Override
	public Librarian checkliblogin(String email, String password) 
	{
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
	    EntityManager em = emf.createEntityManager();
	    
	    // a is an alias of Admin Class
	    Query qry = em.createQuery("select l from Librarian l where l.email=? and l.password=?   ");
	    qry.setParameter(1, email);
	    qry.setParameter(2, password);
	    //qry.setParameter(3, "false");
	    
	        Librarian librarian = null;
	        
	        if(qry.getResultList().size()>0)
	        {
	          librarian = (Librarian) qry.getSingleResult();
	        }
	    em.close();
	    emf.close();
	    
	    return librarian;
	}

	@Override
	public void acceptrequest(int bid) 
	{
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		
		BookIssue bi = em.find(BookIssue.class, bid);
		bi.setIssue_status("Accepted");
		em.getTransaction().commit();
		
		em.close();
		emf.close();
		
	}

	@Override
	public void rejectrequest(int bid) 
	{
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		
		BookIssue bi = em.find(BookIssue.class, bid);
		
		Query qry = em.createNativeQuery("insert into rejectedbooks_table(title,name,status) values(:title,:name,:status)");
		qry.setParameter("title", bi.getTitle());
		qry.setParameter("name", bi.getName());
		qry.setParameter("status", "Rejected");
		qry.executeUpdate();
		
		em.remove(bi);
		em.getTransaction().commit();
		
		em.close();
		emf.close();
	}

	@Override
	public List<BookIssue> viewpendingreqs() 
	{
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
		EntityManager em = emf.createEntityManager();
		
		Query qry = em.createQuery("select bi from BookIssue bi where bi.issue_status=?");
		qry.setParameter(1, "Pending");
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