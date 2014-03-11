package com.patternbox.tangocalendar.event.application.handler;

import java.util.logging.Logger;

import javax.inject.Inject;
import javax.inject.Named;

import com.patternbox.tangocalendar.core.command.CommandHandler;
import com.patternbox.tangocalendar.event.application.command.CreateEventTemplateCommand;

/**
 * ...
 * 
 * @author <a href='http://www.patternbox.com'>D. Ehms, Patternbox</a>
 */
@Named
public class CreateEventTemplateCmdHandler implements
		CommandHandler<CreateEventTemplateCommand, Void> {

	@Inject
	private Logger logger;

	/**
	 * @see com.patternbox.tangocalendar.core.command.CommandHandler#handle(java.lang.Object)
	 */
	@Override
	public Void handle(CreateEventTemplateCommand command) {
		logger.warning("Create_Event_Template_Command: " + command.getEventCategory());
		return null;
	}
}
