/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.search.experiences.blueprint.parameter;

import com.liferay.portal.kernel.json.JSONObject;

/**
 * @author Petteri Karttunen
 */
public class BooleanSXPParameter extends BaseSXPParameter {

	public BooleanSXPParameter(
		String name, boolean templateVariable, Boolean value) {

		super(name, templateVariable);

		_value = value;
	}

	@Override
	public boolean evaluateEquals(JSONObject jsonObject) {
		boolean value = jsonObject.getBoolean("value");

		if (_value.booleanValue() == value) {
			return true;
		}

		return false;
	}

	public Boolean getValue() {
		return _value;
	}

	private final Boolean _value;

}