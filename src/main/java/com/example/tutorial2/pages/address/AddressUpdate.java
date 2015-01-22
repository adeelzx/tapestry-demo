package com.example.tutorial2.pages.address;

import javax.inject.Inject;
import javax.swing.JOptionPane;

import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.BeanEditForm;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.hibernate.Session;

import com.example.tutorial2.entities.Address;
import com.example.tutorial2.pages.Index;

public class AddressUpdate {

	@Property
    private Address address;
	
	@Property
    private Long addressId;
	
	@InjectPage
    private Index index;

	@InjectComponent
    private BeanEditForm form;
	
	@Inject
    private Session session;
	
	void onActivate(Long addressId) {
        this.addressId = addressId;
    }
	
	Long onPassivate() {
        return addressId;
    }
	
	void setupRender() {

        // We're doing this here instead of in onPrepareForRender() because person is used outside the form...

        // If fresh start, make sure there's a Person object available.

        if (form.isValid()) {
        	address = (Address) session.get(Address.class,  new Long(addressId));
            // Handle null person in the template.
        }

    }
	
	void onPrepareForSubmit() {

        // Get Person object for the form fields to overlay.
        address = (Address) session.get(Address.class, new Long(addressId));

        if (address == null) {
            form.recordError("Person has been deleted by another process.");
            // Instantiate an empty person to avoid NPE in the BeanEditForm.
            address = new Address();
        }
    }
	
	Object onCanceled() {
        return index;
    }
	
	@CommitAfter
    void onValidateFromForm() {

        if (form.getHasErrors()) {
            return;
        }

        try {
            session.saveOrUpdate(address);
        }
        catch (Exception e) {
            // Display the cause. In a real system we would try harder to get a user-friendly message.
            //form.recordError(ExceptionUtil.getRootCauseMessage(e));
        }
    }
    
    Object onSuccess() {
        return index;
    }
		
}
