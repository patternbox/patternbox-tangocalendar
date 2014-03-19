package com.patternbox.tangocalendar.location.application.handler;

import java.util.logging.Logger;

import javax.inject.Inject;
import javax.inject.Named;

import com.patternbox.tangocalendar.core.command.CommandHandler;
import com.patternbox.tangocalendar.location.application.command.CreateLocationCommand;
import com.patternbox.tangocalendar.location.domain.model.location.Address;
import com.patternbox.tangocalendar.location.domain.model.location.Location;
import com.patternbox.tangocalendar.location.domain.model.location.LocationRepository;

/**
 * ...
 * 
 * @author <a href='http://www.patternbox.com'>D. Ehms, Patternbox</a>
 */
@Named
public class CreateLocationCmdHandler implements CommandHandler<CreateLocationCommand, Long> {

	@Inject
	private Logger logger;

	@Inject
	private LocationRepository repository;

	/**
	 * @see com.patternbox.tangocalendar.core.command.CommandHandler#handle(java.lang.Object)
	 */
	@Override
	public Long handle(CreateLocationCommand command) {
		Location location = new Location(command.getName());
		Address addr = new Address(command.getCountry(), command.getState(), command.getTown(),
				command.getZipCode(), command.getStreet());
		location.updateAddress(addr);
		repository.storeLocation(location);
		return location.getIdentifer();
	}
}
