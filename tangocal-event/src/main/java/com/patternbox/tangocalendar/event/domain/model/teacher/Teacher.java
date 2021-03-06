/**************************** Copyright notice ********************************

Copyright (C)2014 by D. Ehms, http://www.patternbox.com
All rights reserved.

Redistribution and use in source and binary forms, with or without
modification, are permitted provided that the following conditions
are met:
1. Redistributions of source code must retain the above copyright
notice, this list of conditions and the following disclaimer.
2. Redistributions in binary form must reproduce the above copyright
notice, this list of conditions and the following disclaimer in the
documentation and/or other materials provided with the distribution.
THIS SOFTWARE IS PROVIDED BY THE AUTHOR AND CONTRIBUTORS ``AS IS'' AND
ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
ARE DISCLAIMED. IN NO EVENT SHALL THE AUTHOR OR CONTRIBUTORS BE LIABLE
FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS
OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION)
HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT
LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY
OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
SUCH DAMAGE.
 ******************************************************************************/
package com.patternbox.tangocalendar.event.domain.model.teacher;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.patternbox.tangocalendar.core.types.Entity;

/**
 * @author <a href='http://www.patternbox.com'>D. Ehms, Patternbox</a>
 */
@javax.persistence.Entity
public class Teacher implements Entity<Teacher, Long> {

	public static final String FK_TEACHER = "Teacher_FK";

	@Id
	@GeneratedValue
	private Long identifier;

	@Column(unique = true, nullable = false)
	private String name;

	private String phone;

	private String email;

	/**
	 * @see com.patternbox.tangocalendar.core.types.Entity#sameIdentityAs(java.lang.Object)
	 */
	@Override
	public boolean sameIdentityAs(Teacher other) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * @see com.patternbox.tangocalendar.core.types.Entity#getIdentifer()
	 */
	@Override
	public Long getIdentifer() {
		return identifier;
	}
}
