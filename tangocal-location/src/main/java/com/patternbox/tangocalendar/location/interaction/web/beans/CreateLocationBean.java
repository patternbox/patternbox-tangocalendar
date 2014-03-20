package com.patternbox.tangocalendar.location.interaction.web.beans;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.patternbox.tangocalendar.core.command.CommandService;
import com.patternbox.tangocalendar.location.application.command.CreateLocationCommand;

/**
 * ...
 * 
 * @author <a href='http://www.patternbox.com'>D. Ehms, Patternbox</a>
 */
@Named("createLocation")
@RequestScoped
public class CreateLocationBean extends CreateLocationCommand implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private CommandService commandService;

	/**
	 * Default constructor to satisfy web container support.
	 */
	public CreateLocationBean() {
		super(null, new UpdateLocationAddressBean());
	}

	/**
	 * Execute command on command service.
	 * 
	 * @return "success"
	 */
	public String submit() {
		identifier = (Long) commandService.execute(this);
		// return "success";
		return "LocationDetails.xhtml?faces-redirect=true&id=" + identifier;
	}

	/**
	 * @param name
	 *          the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @see com.patternbox.tangocalendar.location.application.data.LocationData#getAddress()
	 */
	@Override
	public UpdateLocationAddressBean getAddress() {
		return (UpdateLocationAddressBean) super.getAddress();
	}
}
