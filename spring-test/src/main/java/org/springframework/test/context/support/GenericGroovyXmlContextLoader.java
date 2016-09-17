/*
 * Copyright 2002-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.test.context.support;

import org.springframework.beans.factory.groovy.GroovyBeanDefinitionReader;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.test.context.MergedContextConfiguration;
import org.springframework.util.StringUtils;

/**
 * Concrete implementation of {@link AbstractGenericContextLoader} that reads
 * bean definitions from Groovy scripts <em>and</em> XML configuration files.
 *
 * <p>Default resource locations are detected using the suffixes
 * {@code "-context.xml"} and {@code "Context.groovy"}.
 *
 * @author Sam Brannen
 * @since 4.1
 * @see GroovyBeanDefinitionReader
 * @see GenericXmlContextLoader
 * @see AnnotationConfigContextLoader
 */
public class GenericGroovyXmlContextLoader extends GenericXmlContextLoader {

	/**
	 * Load bean definitions into the supplied {@link GenericApplicationContext context}
	 * from the locations in the supplied {@code MergedContextConfiguration}.
	 *
	 * <p>If a location ends with the suffix {@code ".xml"}, bean definitions
	 * will be loaded from that location using an {@link XmlBeanDefinitionReader};
	 * otherwise, a {@link GroovyBeanDefinitionReader} will be used.
	 *
	 * @param context the context into which the bean definitions should be loaded
	 * @param mergedConfig the merged context configuration
	 * @see org.springframework.test.context.support.AbstractGenericContextLoader#loadBeanDefinitions
	 */
	@Override
	protected void loadBeanDefinitions(GenericApplicationContext context, MergedContextConfiguration mergedConfig) {
		XmlBeanDefinitionReader xmlReader = new XmlBeanDefinitionReader(context);
		GroovyBeanDefinitionReader groovyReader = new GroovyBeanDefinitionReader(context);

		for (String location : mergedConfig.getLocations()) {
			if (StringUtils.endsWithIgnoreCase(location, ".xml")) {
				xmlReader.loadBeanDefinitions(location);
			}
			else {
				groovyReader.loadBeanDefinitions(location);
			}
		}
	}

	/**
	 * Returns {@code "-context.xml" and "Context.groovy"} in order to
	 * support detection of a default XML config file or Groovy script.
	 */
	@Override
	protected String[] getResourceSuffixes() {
		return new String[] { super.getResourceSuffix(), "Context.groovy" };
	}

	/**
	 * {@code GenericGroovyXmlContextLoader} supports both Groovy and XML
	 * resource types for detection of defaults. Consequently, this method
	 * is not supported.
	 * @see #getResourceSuffixes()
	 * @throws UnsupportedOperationException
	 */
	@Override
	protected String getResourceSuffix() {
		throw new UnsupportedOperationException(
			"GenericGroovyXmlContextLoader does not support the getResourceSuffix() method");
	}

}
