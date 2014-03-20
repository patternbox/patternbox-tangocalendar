package com.patternbox.tangocalendar.location.application.handler;

import java.util.logging.Logger;

import javax.inject.Inject;
import javax.inject.Named;

import com.patternbox.tangocalendar.core.command.CommandHandler;
import com.patternbox.tangocalendar.location.application.command.UpdateLocationAddressCommand;
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
public class UpdateLocationAddressCmdHandler implements
		CommandHandler<UpdateLocationAddressCommand, Void> {

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
	public Void handle(UpdateLocationAddressCommand command) {
		Location location = repository.findLocation(command.getLocationId());
		Address addr = converter.convertAddress(command);
		location.updateAddress(addr);
		logger.info("Location address updated, id: " + location.getIdentifer() + ", address: ");
		return null;
	}
}
