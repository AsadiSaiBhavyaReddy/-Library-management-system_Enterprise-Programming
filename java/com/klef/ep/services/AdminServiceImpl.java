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
import com.klef.ep.models.Librarian;
import com.klef.ep.models.User;

@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class AdminServiceImpl implements AdminService
{

  @Override
  public Admin checkadminlogin(String username, String password) 
  {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
    EntityManager em = emf.createEntityManager();
    
    // a is an alias of Admin Class
    Query qry = em.createQuery("select a from Admin a where a.username=? and a.password=?  ");
    qry.setParameter(1, username);
    qry.setParameter(2, password);
    
        Admin admin = null;
        
        if(qry.getResultList().size()>0)
        {
          admin = (Admin) qry.getSingleResult();
        }
    em.close();
    emf.close();
    
    return admin;
  }

@Override
public void rejectlibrarian(int lid) 
{
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
	EntityManager em = emf.createEntityManager();
	
	em.getTransaction().begin();
	
	Librarian l = em.find(Librarian.class, lid);
	em.remove(l);
	em.getTransaction().commit();
	
	em.close();
	emf.close();
	
}

@Override
public List<Librarian> viewalllibrarians() 
{
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
	EntityManager em = emf.createEntityManager();
	
	Query qry = em.createQuery("select l from Librarian l");
	// e is an alias of Employee Class
	List<Librarian> liblist = qry.getResultList();
	
    em.close();
    emf.close();
    
    return liblist;
}

@Override
public List<Librarian> viewpendingregs() 
{
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
	EntityManager em = emf.createEntityManager();
	
	Query qry = em.createQuery("select l from Librarian l where l.approved=?");
	qry.setParameter(1, "false");
	// e is an alias of Employee Class
	List<Librarian> liblist = qry.getResultList();
	
	if(liblist==null) {
		em.close();
	    emf.close();
		return null;
	}
	else {
    em.close();
    emf.close();
    
    return liblist;
	}
}

@Override
public long libcount() 
{
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
    EntityManager em = emf.createEntityManager();
	
    Query qry = em.createQuery("select count(*) from Librarian");
    List list = qry.getResultList();
    long count = (long) list.get(0);
    return count;
}

@Override
public void acceptlibrarian(int lid) 
{
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
	EntityManager em = emf.createEntityManager();
	
	em.getTransaction().begin();
	
	Librarian l = em.find(Librarian.class, lid);
	l.setApproved("true");
	em.getTransaction().commit();
	
	em.close();
	emf.close();
	
}

@Override
public void AddBook(Book book) 
{
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
 	  EntityManager em = emf.createEntityManager();
 	  
 	  em.getTransaction().begin();
 	  em.persist(book);     
 	  em.getTransaction().commit();
 	  
 	  em.close();
 	  emf.close();
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

@Override
public Book ViewBookByID(int id) 
{
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
 	 EntityManager em = emf.createEntityManager();
 	  
    em.getTransaction().begin();
    
    Query qry = em.createQuery("  select b from Book b where id=?  "); // atmost one object (0 or 1)
    qry.setParameter(1, id);
    
    Book b = null;
    
    if(qry.getResultList().size() > 0) 
    {
   	    b = (Book) qry.getSingleResult();
    }
    
    em.close();
    emf.close();
  
    return b;
}

@Override
public void deletebook(int bid) 
{
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
	EntityManager em = emf.createEntityManager();
	
	em.getTransaction().begin();
	
	Book b = em.find(Book.class, bid);
	em.remove(b);
	em.getTransaction().commit();
	
	em.close();
	emf.close();
	
}

@Override
public void updatebook(Book book) 
{
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
	EntityManager em = emf.createEntityManager();
	
	em.getTransaction().begin();
		
	Book b = em.find(Book.class, book.getId());
	b.setTitle(book.getTitle());
	b.setCategory(book.getCategory());
	b.setAuthor(book.getAuthor());
	b.setDescription(book.getDescription());
	b.setYear(book.getYear());
	b.setImagedata(book.getImagedata());
	
	em.getTransaction().commit();
	em.close();
	emf.close();
	
}

@Override
public void rejectuser(int uid) 
{
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
	EntityManager em = emf.createEntityManager();
	
	em.getTransaction().begin();
	
	User u = em.find(User.class, uid);
	em.remove(u);
	em.getTransaction().commit();
	
	em.close();
	emf.close();
	
}

@Override
public List<User> viewallusers() 
{
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
	EntityManager em = emf.createEntityManager();
	
	Query qry = em.createQuery("select u from User u");
	// e is an alias of Employee Class
	List<User> userlist = qry.getResultList();
	
    em.close();
    emf.close();
    
    return userlist;
}

@Override
public List<User> viewpendinguserregs() 
{
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
	EntityManager em = emf.createEntityManager();
	
	Query qry = em.createQuery("select u from User u where u.approved=?");
	qry.setParameter(1, "Pending");
	// e is an alias of Employee Class
	List<User> userlist = qry.getResultList();
	
	if(userlist==null) {
		em.close();
	    emf.close();
		return null;
	}
	else {
    em.close();
    emf.close();
    
    return userlist;
	}
}

@Override
public long usercount() 
{
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
    EntityManager em = emf.createEntityManager();
	
    Query qry = em.createQuery("select count(*) from User");
    List list = qry.getResultList();
    long count = (long) list.get(0);
    return count;
}

@Override
public void acceptuser(int uid) 
{
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
	EntityManager em = emf.createEntityManager();
	
	em.getTransaction().begin();
	
	User u = em.find(User.class, uid);
	u.setApproved("Approved");
	em.getTransaction().commit();
	
	em.close();
	emf.close();
	
}


}