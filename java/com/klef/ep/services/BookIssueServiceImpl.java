package com.klef.ep.services;

import javax.ejb.Stateless;

import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.klef.ep.models.BookIssue;

@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class BookIssueServiceImpl implements BookIssueService
{

	@Override
	public void addbooksrequested(BookIssue bookreq) 
	{
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		em.persist(bookreq); 
		em.getTransaction().commit();
		
		em.close();
		emf.close();
		
	}

}
