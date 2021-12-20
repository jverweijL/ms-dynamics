package com.liferay.msdynamics.integration.config;

import com.liferay.configuration.admin.category.ConfigurationCategory;

import org.osgi.service.component.annotations.Component;

@Component
public class MSDynamicsConfigurationCategory
	implements ConfigurationCategory {

	@Override
	public String getCategoryIcon() {
		return _CATEGORY_ICON;
	}

	@Override
	public String getCategoryKey() {
		return _CATEGORY_KEY;
	}

	@Override
	public String getCategorySection() {
		return _CATEGORY_SECTION;
	}

	private static final String _CATEGORY_ICON = "forms";

	public static final String _CATEGORY_KEY = "microsoft-ext";

	private static final String _CATEGORY_SECTION = "other";

}