package com.example.tutorial2.pages;

import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import org.apache.tapestry5.annotations.*;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.*;
import org.apache.tapestry5.corelib.components.*;
import org.apache.tapestry5.SymbolConstants;
import org.apache.tapestry5.alerts.AlertManager;
import org.hibernate.Session;

import com.example.tutorial2.entities.Address;

/**
 * Start page of application tutorial2.
 */
@Import (library="confirm.js")
public class Index
{
	@Property
	private Address address;
	
	@Inject
    private Session session;
	
	@InjectPage
	private Index index;

    public List<Address> getAddresses()
    {
        return session.createCriteria(Address.class).list();
    }
    
    @CommitAfter
    @Log
	Object onActionFromDelete(Address a)
    {
		session.delete(a);
		
		return index;
    }
    
    @CommitAfter
    void onDelete(Address a) {
    	session.delete(a);
		
		return;
    }
    
}
