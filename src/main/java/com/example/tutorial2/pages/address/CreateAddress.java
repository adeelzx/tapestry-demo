package com.example.tutorial2.pages.address;

import javax.inject.Inject;

import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.hibernate.Session;

import com.example.tutorial2.entities.Address;
import com.example.tutorial2.pages.Index;

public class CreateAddress {
	
	@Property
    private Address address;
 
    @Inject
    private Session session;
 
    @InjectPage
    private Index index;
 
    @CommitAfter
    Object onSuccess()
    {
        session.persist(address);
 
        return index;
    }
}
