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

package com.liferay.layout.content.page.editor.web.internal.util;

import com.liferay.frontend.token.definition.FrontendTokenDefinition;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.json.JSONUtil;
import com.liferay.style.book.model.StyleBookEntry;
import com.liferay.style.book.service.StyleBookEntryLocalServiceUtil;

import java.util.Iterator;
import java.util.Locale;
import java.util.Objects;

/**
 * @author Eudaldo Alonso
 */
public class StyleBookEntryUtil {

	public static JSONArray getFrontendTokensValuesJSONArray(
			FrontendTokenDefinition frontendTokenDefinition, Locale locale,
			long styleBookEntryId)
		throws Exception {

		StyleBookEntry styleBookEntry =
			StyleBookEntryLocalServiceUtil.fetchStyleBookEntry(
				styleBookEntryId);

		JSONArray frontendTokensValuesJSONArray =
			JSONFactoryUtil.createJSONArray();

		JSONObject frontendTokenValuesJSONObject =
			JSONFactoryUtil.createJSONObject();

		if (styleBookEntry != null) {
			frontendTokenValuesJSONObject = JSONFactoryUtil.createJSONObject(
				styleBookEntry.getFrontendTokensValues());
		}

		JSONObject frontendTokenDefinitionJSONObject =
			JSONFactoryUtil.createJSONObject(
				frontendTokenDefinition.getJSON(locale));

		JSONArray frontendTokenCategoriesJSONArray =
			frontendTokenDefinitionJSONObject.getJSONArray(
				"frontendTokenCategories");

		for (int i = 0; i < frontendTokenCategoriesJSONArray.length(); i++) {
			JSONObject frontendTokenCategoryJSONObject =
				frontendTokenCategoriesJSONArray.getJSONObject(i);

			JSONArray frontendTokenSetsJSONArray =
				frontendTokenCategoryJSONObject.getJSONArray(
					"frontendTokenSets");

			for (int j = 0; j < frontendTokenSetsJSONArray.length(); j++) {
				JSONObject frontendTokenSetJSONObject =
					frontendTokenSetsJSONArray.getJSONObject(j);

				JSONArray frontendTokensJSONArray =
					frontendTokenSetJSONObject.getJSONArray("frontendTokens");

				for (int k = 0; k < frontendTokensJSONArray.length(); k++) {
					JSONObject frontendTokenJSONObject =
						frontendTokensJSONArray.getJSONObject(k);

					String name = frontendTokenJSONObject.getString("name");

					JSONObject valueJSONObject =
						frontendTokenValuesJSONObject.getJSONObject(name);

					String value = StringPool.BLANK;

					if (valueJSONObject != null) {
						value = valueJSONObject.getString("value");
					}
					else {
						value = frontendTokenJSONObject.getString(
							"defaultValue");
					}

					JSONArray mappingsJSONArray =
						frontendTokenJSONObject.getJSONArray("mappings");
					String cssVariable = StringPool.BLANK;

					Iterator<?> iterator = mappingsJSONArray.iterator();

					while (iterator.hasNext()) {
						JSONObject mappingJSONObject =
							(JSONObject)iterator.next();

						if (Objects.equals(
								mappingJSONObject.getString("type"),
								"cssVariable")) {

							cssVariable = mappingJSONObject.getString("value");

							break;
						}
					}

					frontendTokensValuesJSONArray.put(
						JSONUtil.put(
							"cssVariable", cssVariable
						).put(
							"value", value
						));
				}
			}
		}

		return frontendTokensValuesJSONArray;
	}

}