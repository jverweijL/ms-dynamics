package com.liferay.msdynamics.integration.rest.client.dto.v1_0;

/**
 * @author Filipe Afonso
 */
public class MSDynamicsResponse {

	private int httpCode;
	private String content;
	private String message;
	
	public MSDynamicsResponse() {
		super();
	}

	public MSDynamicsResponse(int httpCode, String content, String message) {
		super();
		this.httpCode = httpCode;
		this.content = content;
		this.message = message;
	}

	public int getHttpCode() {
		return httpCode;
	}

	public void setHttpCode(int httpCode) {
		this.httpCode = httpCode;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result + httpCode;
		result = prime * result + ((message == null) ? 0 : message.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MSDynamicsResponse other = (MSDynamicsResponse) obj;
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		if (httpCode != other.httpCode)
			return false;
		if (message == null) {
			if (other.message != null)
				return false;
		} else if (!message.equals(other.message))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "MSDynamicsResponse [httpCode=" + httpCode + ", content=" + content + ", message=" + message + "]";
	}
	
	
	
}