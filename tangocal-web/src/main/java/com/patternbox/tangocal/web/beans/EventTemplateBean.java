package com.patternbox.tangocal.web.beans;

import java.io.Serializable;
import java.util.logging.Logger;

import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.patternbox.tangocalendar.command.CommandService;
import com.patternbox.tangocalendar.location.application.command.UpdateLocationAddressCommand;

/**
 * ...
 * 
 * @author <a href='http://www.patternbox.com'>D. Ehms, Patternbox</a>
 */
@SuppressWarnings("serial")
@Named("eventTemplate")
@ConversationScoped
public class EventTemplateBean implements Serializable {

	@Inject
	private Logger logger;

	@Inject
	private CommandService commandService;

	private String categoryCode;

	/**
	 * @return the categoryCode
	 */
	public String getCategoryCode() {
		return categoryCode;
	}

	/**
	 * @param categoryCode
	 *          the categoryCode to set
	 */
	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
		logger.warning("YYYYYYYYYYYYYYYYYYYYYYYY");
		UpdateLocationAddressCommand cmd = new UpdateLocationAddressCommand();
		commandService.execute(cmd);
	}
}
