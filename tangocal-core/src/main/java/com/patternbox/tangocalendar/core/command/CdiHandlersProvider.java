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
package com.patternbox.tangocalendar.core.command;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * @author <a href='http://www.patternbox.com'>D. Ehms, Patternbox</a>
 */
@Singleton
public class CdiHandlersProvider {

	@Inject
	private BeanManager beanManager;

	private final Map<Class<?>, String> handlers = new HashMap<Class<?>, String>();

	@PostConstruct
	public void initHandlerMap() {
		for (Bean<?> bean : beanManager.getBeans(CommandHandler.class)) {
			Class<?> handlerClass = bean.getBeanClass();
			handlers.put(getHandledCommandType(handlerClass), bean.getName());
		}
	}

	@SuppressWarnings("unchecked")
	public CommandHandler<Object, Object> getHandler(Object command) {
		String beanName = handlers.get(command.getClass());
		if (beanName == null) {
			throw new IllegalStateException("No command handler registered for command "
					+ command.getClass().getName() + " Are you sure your handlers are @Named beans?");
		}
		Set<Bean<?>> beanset = beanManager.getBeans(beanName);
		if (beanset.size() > 1) {
			throw new IllegalStateException("Should never occur: name " + beanName
					+ " has more than one registered CDI bean!");
		}
		Bean<?> bean = beanset.iterator().next();
		CreationalContext<?> creationalContext = beanManager.createCreationalContext(bean);
		Object reference = beanManager.getReference(bean, CommandHandler.class, creationalContext);
		return (CommandHandler<Object, Object>) reference;
	}

	private Class<?> getHandledCommandType(Class<?> clazz) {
		Type[] genericInterfaces = clazz.getGenericInterfaces();
		ParameterizedType type = findByRawType(genericInterfaces, CommandHandler.class);
		return (Class<?>) type.getActualTypeArguments()[0];
	}

	private ParameterizedType findByRawType(Type[] genericInterfaces, Class<?> expectedRawType) {
		for (Type type : genericInterfaces) {
			if (type instanceof ParameterizedType) {
				ParameterizedType parametrized = (ParameterizedType) type;
				if (expectedRawType.equals(parametrized.getRawType())) {
					return parametrized;
				}
			}
		}
		throw new RuntimeException();
	}
}
