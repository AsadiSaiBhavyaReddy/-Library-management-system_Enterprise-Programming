package com.klef.ep.services;

import javax.ejb.Remote;

import com.klef.ep.models.BookIssue;

@Remote
public interface BookIssueService 
{
	public void addbooksrequested(BookIssue bookreq);
}
