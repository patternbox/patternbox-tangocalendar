package com.patternbox.tangocal.web.beans;

import java.util.Map;

import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.patternbox.tangocalendar.event.infrastructure.persistence.JpaEventCategoryFinder;

/**
 * ...
 * 
 * @author <a href='http://www.patternbox.com'>D. Ehms, Patternbox</a>
 */
@Named("eventManagement")
@SessionScoped
public class EventManagementBean {

	@Inject
	JpaEventCategoryFinder eventCategoryFinder;

	public Map<String, String> getCategories() {
		return eventCategoryFinder.getEventCategories();
	}
}
