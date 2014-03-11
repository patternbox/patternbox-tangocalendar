package com.patternbox.tangocal.web.beans;

import java.io.Serializable;
import java.util.Date;
import java.util.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.patternbox.tangocalendar.core.command.CommandService;
import com.patternbox.tangocalendar.event.application.command.CreateEventTemplateCommand;

/**
 * ...
 * 
 * @author <a href='http://www.patternbox.com'>D. Ehms, Patternbox</a>
 */
@SuppressWarnings("serial")
@Named("eventTemplate")
// @ConversationScoped
@RequestScoped
public class EventTemplateBean implements Serializable {

	@Inject
	private Logger logger;

	@Inject
	private CommandService commandService;

	private String categoryCode = "TANGO";

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
		logger.warning("Category_Code: " + categoryCode);
		CreateEventTemplateCommand cmd = new CreateEventTemplateCommand(new Date(), new Date(), 0,
				categoryCode, null);
		commandService.execute(cmd);
	}
}
