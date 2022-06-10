/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.info.exception;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;

import java.util.Locale;

/**
 * @author Rubén Pulido
 */
public class InfoFormException extends PortalException {

	public InfoFormException(String msg) {
		super(msg);
	}

	public InfoFormException(String msg, Throwable throwable) {
		super(msg, throwable);
	}

	public InfoFormException(Throwable throwable) {
		super(throwable);
	}

	public String getLocalizedMessage(Locale locale) {
		return LanguageUtil.get(
			locale,
			"an-error-has-occurred-and-the-form-could-not-be-sent.-please-" +
				"try-again");
	}

}