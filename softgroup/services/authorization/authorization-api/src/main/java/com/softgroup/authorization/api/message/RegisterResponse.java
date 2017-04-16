package com.softgroup.authorization.api.message;

import com.softgroup.common.protocol.ResponseData;

/**
 * @author odin
 * @since 20.02.17.
 */
public class RegisterResponse implements ResponseData {
	private static final long serialVersionUID = -5146888202653750948L;

	private String registrationID;
	private Integer registraionTimeOut;
	private String authCode;

	public RegisterResponse(String registrationID, Integer registraionTimeOut, String authCode) {
		this.registrationID = registrationID;
		this.registraionTimeOut = registraionTimeOut;
		this.authCode = authCode;
	}

	public String getRegistrationID() {
		return registrationID;
	}

	public void setRegistrationID(String registrationID) {
		this.registrationID = registrationID;
	}

	public Integer getRegistraionTimeOut() {
		return registraionTimeOut;
	}

	public void setRegistraionTimeOut(Integer registraionTimeOut) {
		this.registraionTimeOut = registraionTimeOut;
	}

	public String getAuthCode() {
		return authCode;
	}

	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}
}
