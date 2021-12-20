package com.liferay.msdynamics.command;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.liferay.msdynamics.integration.config.MSDynamicsConfiguration;
import com.liferay.msdynamics.integration.rest.client.dto.v1_0.MSDynamicsResponse;
import com.liferay.msdynamics.integration.rest.client.resource.v1_0.IMSDynamicsResource;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.module.configuration.ConfigurationException;
import com.liferay.portal.kernel.module.configuration.ConfigurationProvider;

/**
 * @author fafonso
 */
@Component(
	property = {
		"osgi.command.function=msgetaccounts", "osgi.command.scope=ms"
	},
	service = Object.class
)
public class MSGetAccountsCommand {

	public void msgetaccounts() {

		System.out.println("---------------Getting MS Dynamics Accounts using the rest client---------------");
		
		MSDynamicsConfiguration msDynamicsConfiguration;
		try {
			msDynamicsConfiguration = _configurationProvider.getSystemConfiguration(MSDynamicsConfiguration.class);
		} catch (ConfigurationException e) {
			e.printStackTrace();
			return;
		}
		
		System.out.println("TOKEN: " + msDynamicsConfiguration.microsoftDynamicsOAuth2Token());
		System.out.println("HOST: " + msDynamicsConfiguration.microsoftDynamicsHost());
		
		IMSDynamicsResource.Builder builder = IMSDynamicsResource.builder();
		IMSDynamicsResource imsDynamicsResource = builder.build();
		MSDynamicsResponse msDynamicsResponse;
		
		try {

			msDynamicsResponse = imsDynamicsResource.getMSDynamicsAccounts(msDynamicsConfiguration.microsoftDynamicsHost(), msDynamicsConfiguration.microsoftDynamicsOAuth2Token());
			System.out.println(msDynamicsResponse.getContent());

		} catch (Exception e) {
			_log.error(e.getMessage());
			_log.error(e);
		}
		
		
	}
	
	
	@Reference
	protected ConfigurationProvider _configurationProvider;

	private static final Log _log = LogFactoryUtil.getLog(MSGetAccountsCommand.class);
}