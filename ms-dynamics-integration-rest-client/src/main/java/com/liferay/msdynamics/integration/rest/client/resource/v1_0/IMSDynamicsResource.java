package com.liferay.msdynamics.integration.rest.client.resource.v1_0;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Generated;

import org.apache.commons.lang3.StringUtils;

import com.google.gson.Gson;
import com.liferay.msdynamics.integration.rest.client.data.MSAccount;
import com.liferay.msdynamics.integration.rest.client.dto.v1_0.MSDynamicsResponse;
import com.liferay.msdynamics.integration.rest.client.exception.RestException;
import com.liferay.msdynamics.integration.rest.client.http.HttpInvoker;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

/**
 * @author Filipe Afonso
 * @generated
 */
@Generated("")
public interface IMSDynamicsResource {

	public static Builder builder() {
		return new Builder();
	}

	public MSDynamicsResponse getMSDynamicsAccounts(String host, String token) throws IOException, RestException;

	public MSDynamicsResponse getMSDynamicsAccounts(String host, String token, String ownerId)
			throws IOException, RestException;

	public MSDynamicsResponse createMSDynamicsAccount(String host, String token, String name, String phone, String mail,
			String city) throws IOException, RestException;

//	public MSDynamicsResponse postIMediaResponse(MSDynamicsResponse iMediaResponse) throws Exception;
//
//	public HttpInvoker.HttpResponse postIMediaResponseHttpResponse(MSDynamicsResponse iMediaResponse) throws Exception;

	public static class Builder {

		public Builder authentication(String login, String password) {
			_login = login;
			_password = password;

			return this;
		}

		public Builder authentication(String token) {
			_token = token;

			return this;
		}

		public IMSDynamicsResource build() {
			return new MSDynamicsResourceImpl(this);
		}

		public Builder endpoint(String host, int port, String scheme) {
			_host = host;
			_port = port;
			_scheme = scheme;

			return this;
		}

		public Builder header(String key, String value) {
			_headers.put(key, value);

			return this;
		}

		public Builder locale(Locale locale) {
			_locale = locale;

			return this;
		}

		public Builder parameter(String key, String value) {
			_parameters.put(key, value);

			return this;
		}

		private Builder() {
		}

		private Map<String, String> _headers = new LinkedHashMap<>();
		private String _host = "";
		private Locale _locale;
		private String _login = "";
		private String _password = "";
		private String _token = "";
		private Map<String, String> _parameters = new LinkedHashMap<>();
		private int _port = 8080;
		private String _scheme = "https";

	}

	public static class MSDynamicsResourceImpl implements IMSDynamicsResource {

		private static final String API_ENDPOINT = "/api/data/v9.0/";
		private static final String RESOURCE_ACCOUNT = "accounts";
		private static final String RESOURCE_ACCOUNT_FILTER_BY_OWNER = "?$filter=_ownerid_value%20eq%20";

		@Override
		public MSDynamicsResponse createMSDynamicsAccount(String host, String token, String name, String phone,
				String mail, String city) throws IOException, RestException {
			
			StringBuilder url = new StringBuilder(host);
			url.append(API_ENDPOINT);
			url.append(RESOURCE_ACCOUNT);

			// Setup the request
			_builder.endpoint(url.toString(), -1, "https");

			// Set authoriazation
			_builder.authentication(token);

			// Set header content of post request.
			_builder.header("OData-MaxVersion", "4.0");
			_builder.header("OData-Version", "4.0");
			_builder.header("Host", host);
			_builder.header("Accept", "application/json");

			// Set body content of post request.
			MSAccount newAccount = new MSAccount(name, phone, city, mail);
			Gson gson = new Gson();

			HttpInvoker.HttpResponse httpResponse = getMSDynamicsHttpResponse(HttpInvoker.HttpMethod.POST, gson.toJson(newAccount), "application/json");

			if (_log.isDebugEnabled()) {
				_log.debug("HTTP response content: " + httpResponse.getContent());
				_log.debug("HTTP response message: " + httpResponse.getMessage());
				_log.debug("HTTP response status code: " + httpResponse.getStatusCode());
			}

			if (httpResponse.getStatusCode() > 299) {
				throw new RestException(
						"HTTP Status Code: " + httpResponse.getStatusCode() + ". HTTP Response Message: "
								+ httpResponse.getMessage() + ". Response Content: " + httpResponse.getContent());
			}

			MSDynamicsResponse msDynamicsResponse = new MSDynamicsResponse(httpResponse.getStatusCode(),
					httpResponse.getContent(), httpResponse.getMessage());
			return msDynamicsResponse;
		}

		public MSDynamicsResponse getMSDynamicsAccounts(String host, String token) throws IOException, RestException {
			return getMSDynamicsAccounts(host, token, null);
		}

		public MSDynamicsResponse getMSDynamicsAccounts(String host, String token, String ownerId)
				throws IOException, RestException {

			StringBuilder url = new StringBuilder(host);
			url.append(API_ENDPOINT);
			url.append(RESOURCE_ACCOUNT);

			if (!StringUtils.isEmpty(ownerId)) {
				url.append(RESOURCE_ACCOUNT_FILTER_BY_OWNER);
				url.append(ownerId);
			}

			// Setup the request
			_builder.endpoint(url.toString(), -1, "https");

			// Set authoriaztion
			_builder.authentication(token);

			// Set header content of post request.
			_builder.header("OData-MaxVersion", "4.0");
			_builder.header("OData-Version", "4.0");
			_builder.header("Content-Type", "application/json");
			_builder.header("Host", host);
			_builder.header("Accept", "application/json");

			// Set body content of post request.
			// No need to set info in the body for this case

			HttpInvoker.HttpResponse httpResponse = getMSDynamicsHttpResponse(HttpInvoker.HttpMethod.GET);

			if (_log.isDebugEnabled()) {
				_log.debug("HTTP response content: " + httpResponse.getContent());
				_log.debug("HTTP response message: " + httpResponse.getMessage());
				_log.debug("HTTP response status code: " + httpResponse.getStatusCode());
			}

			if (httpResponse.getStatusCode() != 200) {
				throw new RestException(
						"HTTP Status Code: " + httpResponse.getStatusCode() + ". HTTP Response Message: "
								+ httpResponse.getMessage() + ". Response Content: " + httpResponse.getContent());
			}

			MSDynamicsResponse msDynamicsResponse = new MSDynamicsResponse(httpResponse.getStatusCode(),
					httpResponse.getContent(), httpResponse.getMessage());
			return msDynamicsResponse;

		}

		private HttpInvoker.HttpResponse getMSDynamicsHttpResponse(HttpInvoker.HttpMethod httpMethod)
				throws IOException {
			return getMSDynamicsHttpResponse(httpMethod, null, null);
		}

		private HttpInvoker.HttpResponse getMSDynamicsHttpResponse(HttpInvoker.HttpMethod httpMethod, String body,
				String bodyContentType) throws IOException {

			HttpInvoker httpInvoker = HttpInvoker.newHttpInvoker();

			if (!StringUtils.isEmpty(body)) {
				httpInvoker.body(body, bodyContentType);
			}

			if (_builder._locale != null) {
				httpInvoker.header("Accept-Language", _builder._locale.toLanguageTag());
			}

			for (Map.Entry<String, String> entry : _builder._headers.entrySet()) {

				httpInvoker.header(entry.getKey(), entry.getValue());
			}

			for (Map.Entry<String, String> entry : _builder._parameters.entrySet()) {

				httpInvoker.parameter(entry.getKey(), entry.getValue());
			}

			httpInvoker.httpMethod(httpMethod);

			httpInvoker.path(_builder._scheme + "://" + _builder._host);

			httpInvoker.token(_builder._token);

			return httpInvoker.invoke();
		}

		private MSDynamicsResourceImpl(Builder builder) {
			_builder = builder;
		}

		private static final Log _log = LogFactoryUtil.getLog(MSDynamicsResourceImpl.class);

		private Builder _builder;

	}

}