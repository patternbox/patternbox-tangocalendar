package com.patternbox.tangocalendar.location.application.handler;

import java.util.logging.Logger;

import javax.inject.Inject;
import javax.inject.Named;

import com.patternbox.tangocalendar.core.command.CommandHandler;
import com.patternbox.tangocalendar.location.application.command.CreateLocationCommand;
import com.patternbox.tangocalendar.location.application.data.LocationDataConverter;
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
	private LocationDataConverter converter;

	@Inject
	private LocationRepository repository;

	/**
	 * @see com.patternbox.tangocalendar.core.command.CommandHandler#handle(java.lang.Object)
	 */
	@Override
	public Long handle(CreateLocationCommand command) {
		Location location = new Location(command.getName());
		Address addr = converter.convertAddress(command.getAddress());
		location.updateAddress(addr);
		repository.storeLocation(location);
		logger.info("New Location created, id: " + location.getIdentifer() + ", name: "
				+ command.getName());
		return location.getIdentifer();
	}
}
