package com.patternbox.tangocalendar.core.types;

import java.io.Serializable;

/**
 * A value object, as described in the DDD book.
 * 
 * @author <a href='http://www.patternbox.com'>D. Ehms, Patternbox</a>
 */
public interface ValueObject<T> extends Serializable {

	/**
	 * Value objects compare by the values of their attributes, they don't have an identity.
	 * 
	 * @param other
	 *          The other value object.
	 * @return <code>true</code> if the given value object's and this value object's attributes are
	 *         the same.
	 */
	boolean sameValueAs(T other);
}
