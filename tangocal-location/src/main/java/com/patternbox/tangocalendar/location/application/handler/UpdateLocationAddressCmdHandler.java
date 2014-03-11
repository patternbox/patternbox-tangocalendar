package com.patternbox.tangocalendar.location.application.handler;

import java.util.logging.Logger;

import javax.inject.Inject;
import javax.inject.Named;

import com.patternbox.tangocalendar.core.command.CommandHandler;
import com.patternbox.tangocalendar.location.application.command.UpdateLocationAddressCommand;

/**
 * ...
 * 
 * @author <a href='http://www.patternbox.com'>D. Ehms, Patternbox</a>
 */
@Named
public class UpdateLocationAddressCmdHandler implements
		CommandHandler<UpdateLocationAddressCommand, Void> {

	@Inject
	private Logger logger;

	/**
	 * @see com.patternbox.tangocalendar.core.command.CommandHandler#handle(java.lang.Object)
	 */
	@Override
	public Void handle(UpdateLocationAddressCommand command) {
		logger.warning("XXXXXXXXXXXXXXXXXXXXXXXXXXX");
		return null;
	}
}
