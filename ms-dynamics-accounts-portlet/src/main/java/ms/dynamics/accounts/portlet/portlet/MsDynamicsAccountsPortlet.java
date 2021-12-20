package ms.dynamics.accounts.portlet.portlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.apache.commons.lang3.StringUtils;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.liferay.msdynamics.integration.config.MSDynamicsConfiguration;
import com.liferay.msdynamics.integration.rest.client.data.MSAccount;
import com.liferay.msdynamics.integration.rest.client.dto.v1_0.MSDynamicsResponse;
import com.liferay.msdynamics.integration.rest.client.exception.RestException;
import com.liferay.msdynamics.integration.rest.client.resource.v1_0.IMSDynamicsResource;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.module.configuration.ConfigurationException;
import com.liferay.portal.kernel.module.configuration.ConfigurationProvider;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;

import ms.dynamics.accounts.portlet.constants.MsDynamicsAccountsPortletKeys;

/**
 * @author fafonso
 */
@Component(immediate = true, property = { "com.liferay.portlet.display-category=category.ms",
		"com.liferay.portlet.header-portlet-css=/css/main.css", "com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=MsDynamicsAccounts", "javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + MsDynamicsAccountsPortletKeys.MSDYNAMICSACCOUNTS,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user" }, service = Portlet.class)
public class MsDynamicsAccountsPortlet extends MVCPortlet {

	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {

		// Try to get MS Dynamic accounts
		MSDynamicsConfiguration msDynamicsConfiguration;
		MSDynamicsResponse msDynamicsResponse = null;
		try {
			msDynamicsConfiguration = _configurationProvider.getSystemConfiguration(MSDynamicsConfiguration.class);

			IMSDynamicsResource.Builder builder = IMSDynamicsResource.builder();
			IMSDynamicsResource imsDynamicsResource = builder.build();

			msDynamicsResponse = imsDynamicsResource.getMSDynamicsAccounts(
					msDynamicsConfiguration.microsoftDynamicsHost(),
					msDynamicsConfiguration.microsoftDynamicsOAuth2Token(),
					msDynamicsConfiguration.microsoftDynamicsAccountOwner());

		} catch (RestException | IOException | ConfigurationException e) {
			_log.error(e.getMessage(), e);
		}

		ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);

		List<MSAccount> accounts = Collections.emptyList();
		if (msDynamicsResponse != null) {
			accounts = parseMSDynamicsAccounts(msDynamicsResponse.getContent());

		}
		
		SearchContainer<MSAccount> searchContainer = new SearchContainer<MSAccount>(renderRequest,
				renderResponse.createRenderURL(), null, null);
		
		searchContainer.setEmptyResultsMessage("no-accounts-were-found");
		searchContainer.setResults(accounts);
		searchContainer.setTotal(accounts.size());
		
		renderRequest.setAttribute("accountsSearchContainer", searchContainer);

		
		super.doView(renderRequest, renderResponse);
	}

	private List<MSAccount> parseMSDynamicsAccounts(String content) {
		List<MSAccount> result = new ArrayList<MSAccount>();

		if (StringUtils.isEmpty(content)) {
			return result;
		}

		JsonParser parser = new JsonParser();
		JsonElement jsonTree = parser.parse(content);

		if (jsonTree.isJsonObject()) {
			JsonObject jsonObject = jsonTree.getAsJsonObject();

			JsonElement accounts = jsonObject.get("value");

			if (accounts.isJsonArray()) {
				for (JsonElement accountJsonElement : accounts.getAsJsonArray()) {
					MSAccount account = new MSAccount(accountJsonElement);

					if (_log.isDebugEnabled()) {
						_log.debug(account);
					}

					result.add(account);
				}
			}

		}

		return result;
	}

	@Reference
	protected ConfigurationProvider _configurationProvider;

	private static final Log _log = LogFactoryUtil.getLog(MsDynamicsAccountsPortlet.class);

}