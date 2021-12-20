package com.liferay.msdynamics.integration.config;

import org.osgi.service.component.annotations.Component;

import com.liferay.msdynamics.integration.config.MSDynamicsConfiguration;
import com.liferay.portal.kernel.settings.definition.ConfigurationBeanDeclaration;

@Component
public class MSDynamicsConfigurationBeanDeclaration
	implements ConfigurationBeanDeclaration {

	@Override
	public Class<?> getConfigurationBeanClass() {
		return MSDynamicsConfiguration.class;
	}

}