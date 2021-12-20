package ms.dynamics.accounts.portlet.commands;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.liferay.msdynamics.integration.config.MSDynamicsConfiguration;
import com.liferay.msdynamics.integration.rest.client.exception.RestException;
import com.liferay.msdynamics.integration.rest.client.resource.v1_0.IMSDynamicsResource;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.module.configuration.ConfigurationProvider;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.ParamUtil;

import ms.dynamics.accounts.portlet.constants.MsDynamicsAccountsPortletKeys;

/**
 *
 * @author Filipe Afonso
 *
 *
 */
@Component(immediate = true, property = { "javax.portlet.name=" + MsDynamicsAccountsPortletKeys.MSDYNAMICSACCOUNTS,
		"mvc.command.name=addMSAccount" }, service = MVCActionCommand.class)
public class AddMSAccountMVCActionCommand extends BaseMVCActionCommand {

	@Override
	protected void doProcessAction(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {

		if (_log.isDebugEnabled()) {
			_log.debug("About to create a new MS Account");
		}

		String msAccountPhone = ParamUtil.getString(actionRequest, "msAccountPhone");
		String msAccountName = ParamUtil.getString(actionRequest, "msAccountName");
		String msAccountCity = ParamUtil.getString(actionRequest, "msAccountCity");
		String msAccountMail = ParamUtil.getString(actionRequest, "msAccountMail");

		try {

			if (_log.isDebugEnabled()) {
				_log.debug("Creating: " + msAccountName + " | " + msAccountPhone + " | " + msAccountCity + " | "
						+ msAccountMail);
			}

			// Try to get MS Dynamic accounts
			MSDynamicsConfiguration msDynamicsConfiguration;
			msDynamicsConfiguration = _configurationProvider.getSystemConfiguration(MSDynamicsConfiguration.class);

			IMSDynamicsResource.Builder builder = IMSDynamicsResource.builder();
			IMSDynamicsResource imsDynamicsResource = builder.build();

			imsDynamicsResource.createMSDynamicsAccount(msDynamicsConfiguration.microsoftDynamicsHost(),
					msDynamicsConfiguration.microsoftDynamicsOAuth2Token(), msAccountName, msAccountPhone,
					msAccountMail, msAccountCity);
			
			String redirect = ParamUtil.getString(actionRequest, "backURL");
			sendRedirect(actionRequest, actionResponse, redirect);
		}

		catch (RestException e) {
			_log.error(e.getMessage(), e);
			hideDefaultErrorMessage(actionRequest);
			hideDefaultSuccessMessage(actionRequest);

			// PortalUtil.copyRequestParameters(actionRequest, actionResponse);

			SessionErrors.add(actionRequest, e.getClass());

			String redirect = ParamUtil.getString(actionRequest, "redirect");

			if (_log.isDebugEnabled()) {
				_log.debug("Redirecting to : " + redirect);
			}

			sendRedirect(actionRequest, actionResponse, redirect);
		}

	}

	private static final Log _log = LogFactoryUtil.getLog(AddMSAccountMVCActionCommand.class);

	@Reference
	protected ConfigurationProvider _configurationProvider;

}