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

package com.liferay.object.model;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.exception.LocaleException;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.LocalizedModel;
import com.liferay.portal.kernel.model.MVCCModel;
import com.liferay.portal.kernel.model.ShardedModel;
import com.liferay.portal.kernel.model.StagedAuditedModel;

import java.util.Date;
import java.util.Locale;
import java.util.Map;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model interface for the ObjectField service. Represents a row in the &quot;ObjectField&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.liferay.object.model.impl.ObjectFieldModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.liferay.object.model.impl.ObjectFieldImpl</code>.
 * </p>
 *
 * @author Marco Leo
 * @see ObjectField
 * @generated
 */
@ProviderType
public interface ObjectFieldModel
	extends BaseModel<ObjectField>, LocalizedModel, MVCCModel, ShardedModel,
			StagedAuditedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a object field model instance should use the {@link ObjectField} interface instead.
	 */

	/**
	 * Returns the primary key of this object field.
	 *
	 * @return the primary key of this object field
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this object field.
	 *
	 * @param primaryKey the primary key of this object field
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the mvcc version of this object field.
	 *
	 * @return the mvcc version of this object field
	 */
	@Override
	public long getMvccVersion();

	/**
	 * Sets the mvcc version of this object field.
	 *
	 * @param mvccVersion the mvcc version of this object field
	 */
	@Override
	public void setMvccVersion(long mvccVersion);

	/**
	 * Returns the uuid of this object field.
	 *
	 * @return the uuid of this object field
	 */
	@AutoEscape
	@Override
	public String getUuid();

	/**
	 * Sets the uuid of this object field.
	 *
	 * @param uuid the uuid of this object field
	 */
	@Override
	public void setUuid(String uuid);

	/**
	 * Returns the object field ID of this object field.
	 *
	 * @return the object field ID of this object field
	 */
	public long getObjectFieldId();

	/**
	 * Sets the object field ID of this object field.
	 *
	 * @param objectFieldId the object field ID of this object field
	 */
	public void setObjectFieldId(long objectFieldId);

	/**
	 * Returns the company ID of this object field.
	 *
	 * @return the company ID of this object field
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this object field.
	 *
	 * @param companyId the company ID of this object field
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this object field.
	 *
	 * @return the user ID of this object field
	 */
	@Override
	public long getUserId();

	/**
	 * Sets the user ID of this object field.
	 *
	 * @param userId the user ID of this object field
	 */
	@Override
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this object field.
	 *
	 * @return the user uuid of this object field
	 */
	@Override
	public String getUserUuid();

	/**
	 * Sets the user uuid of this object field.
	 *
	 * @param userUuid the user uuid of this object field
	 */
	@Override
	public void setUserUuid(String userUuid);

	/**
	 * Returns the user name of this object field.
	 *
	 * @return the user name of this object field
	 */
	@AutoEscape
	@Override
	public String getUserName();

	/**
	 * Sets the user name of this object field.
	 *
	 * @param userName the user name of this object field
	 */
	@Override
	public void setUserName(String userName);

	/**
	 * Returns the create date of this object field.
	 *
	 * @return the create date of this object field
	 */
	@Override
	public Date getCreateDate();

	/**
	 * Sets the create date of this object field.
	 *
	 * @param createDate the create date of this object field
	 */
	@Override
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this object field.
	 *
	 * @return the modified date of this object field
	 */
	@Override
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this object field.
	 *
	 * @param modifiedDate the modified date of this object field
	 */
	@Override
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the list type definition ID of this object field.
	 *
	 * @return the list type definition ID of this object field
	 */
	public long getListTypeDefinitionId();

	/**
	 * Sets the list type definition ID of this object field.
	 *
	 * @param listTypeDefinitionId the list type definition ID of this object field
	 */
	public void setListTypeDefinitionId(long listTypeDefinitionId);

	/**
	 * Returns the object definition ID of this object field.
	 *
	 * @return the object definition ID of this object field
	 */
	public long getObjectDefinitionId();

	/**
	 * Sets the object definition ID of this object field.
	 *
	 * @param objectDefinitionId the object definition ID of this object field
	 */
	public void setObjectDefinitionId(long objectDefinitionId);

	/**
	 * Returns the business type of this object field.
	 *
	 * @return the business type of this object field
	 */
	@AutoEscape
	public String getBusinessType();

	/**
	 * Sets the business type of this object field.
	 *
	 * @param businessType the business type of this object field
	 */
	public void setBusinessType(String businessType);

	/**
	 * Returns the db column name of this object field.
	 *
	 * @return the db column name of this object field
	 */
	@AutoEscape
	public String getDBColumnName();

	/**
	 * Sets the db column name of this object field.
	 *
	 * @param dbColumnName the db column name of this object field
	 */
	public void setDBColumnName(String dbColumnName);

	/**
	 * Returns the db table name of this object field.
	 *
	 * @return the db table name of this object field
	 */
	@AutoEscape
	public String getDBTableName();

	/**
	 * Sets the db table name of this object field.
	 *
	 * @param dbTableName the db table name of this object field
	 */
	public void setDBTableName(String dbTableName);

	/**
	 * Returns the db type of this object field.
	 *
	 * @return the db type of this object field
	 */
	@AutoEscape
	public String getDBType();

	/**
	 * Sets the db type of this object field.
	 *
	 * @param dbType the db type of this object field
	 */
	public void setDBType(String dbType);

	/**
	 * Returns the indexed of this object field.
	 *
	 * @return the indexed of this object field
	 */
	public boolean getIndexed();

	/**
	 * Returns <code>true</code> if this object field is indexed.
	 *
	 * @return <code>true</code> if this object field is indexed; <code>false</code> otherwise
	 */
	public boolean isIndexed();

	/**
	 * Sets whether this object field is indexed.
	 *
	 * @param indexed the indexed of this object field
	 */
	public void setIndexed(boolean indexed);

	/**
	 * Returns the indexed as keyword of this object field.
	 *
	 * @return the indexed as keyword of this object field
	 */
	public boolean getIndexedAsKeyword();

	/**
	 * Returns <code>true</code> if this object field is indexed as keyword.
	 *
	 * @return <code>true</code> if this object field is indexed as keyword; <code>false</code> otherwise
	 */
	public boolean isIndexedAsKeyword();

	/**
	 * Sets whether this object field is indexed as keyword.
	 *
	 * @param indexedAsKeyword the indexed as keyword of this object field
	 */
	public void setIndexedAsKeyword(boolean indexedAsKeyword);

	/**
	 * Returns the indexed language ID of this object field.
	 *
	 * @return the indexed language ID of this object field
	 */
	@AutoEscape
	public String getIndexedLanguageId();

	/**
	 * Sets the indexed language ID of this object field.
	 *
	 * @param indexedLanguageId the indexed language ID of this object field
	 */
	public void setIndexedLanguageId(String indexedLanguageId);

	/**
	 * Returns the label of this object field.
	 *
	 * @return the label of this object field
	 */
	public String getLabel();

	/**
	 * Returns the localized label of this object field in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param locale the locale of the language
	 * @return the localized label of this object field
	 */
	@AutoEscape
	public String getLabel(Locale locale);

	/**
	 * Returns the localized label of this object field in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param locale the local of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized label of this object field. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	 */
	@AutoEscape
	public String getLabel(Locale locale, boolean useDefault);

	/**
	 * Returns the localized label of this object field in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @return the localized label of this object field
	 */
	@AutoEscape
	public String getLabel(String languageId);

	/**
	 * Returns the localized label of this object field in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized label of this object field
	 */
	@AutoEscape
	public String getLabel(String languageId, boolean useDefault);

	@AutoEscape
	public String getLabelCurrentLanguageId();

	@AutoEscape
	public String getLabelCurrentValue();

	/**
	 * Returns a map of the locales and localized labels of this object field.
	 *
	 * @return the locales and localized labels of this object field
	 */
	public Map<Locale, String> getLabelMap();

	/**
	 * Sets the label of this object field.
	 *
	 * @param label the label of this object field
	 */
	public void setLabel(String label);

	/**
	 * Sets the localized label of this object field in the language.
	 *
	 * @param label the localized label of this object field
	 * @param locale the locale of the language
	 */
	public void setLabel(String label, Locale locale);

	/**
	 * Sets the localized label of this object field in the language, and sets the default locale.
	 *
	 * @param label the localized label of this object field
	 * @param locale the locale of the language
	 * @param defaultLocale the default locale
	 */
	public void setLabel(String label, Locale locale, Locale defaultLocale);

	public void setLabelCurrentLanguageId(String languageId);

	/**
	 * Sets the localized labels of this object field from the map of locales and localized labels.
	 *
	 * @param labelMap the locales and localized labels of this object field
	 */
	public void setLabelMap(Map<Locale, String> labelMap);

	/**
	 * Sets the localized labels of this object field from the map of locales and localized labels, and sets the default locale.
	 *
	 * @param labelMap the locales and localized labels of this object field
	 * @param defaultLocale the default locale
	 */
	public void setLabelMap(Map<Locale, String> labelMap, Locale defaultLocale);

	/**
	 * Returns the name of this object field.
	 *
	 * @return the name of this object field
	 */
	@AutoEscape
	public String getName();

	/**
	 * Sets the name of this object field.
	 *
	 * @param name the name of this object field
	 */
	public void setName(String name);

	/**
	 * Returns the relationship type of this object field.
	 *
	 * @return the relationship type of this object field
	 */
	@AutoEscape
	public String getRelationshipType();

	/**
	 * Sets the relationship type of this object field.
	 *
	 * @param relationshipType the relationship type of this object field
	 */
	public void setRelationshipType(String relationshipType);

	/**
	 * Returns the required of this object field.
	 *
	 * @return the required of this object field
	 */
	public boolean getRequired();

	/**
	 * Returns <code>true</code> if this object field is required.
	 *
	 * @return <code>true</code> if this object field is required; <code>false</code> otherwise
	 */
	public boolean isRequired();

	/**
	 * Sets whether this object field is required.
	 *
	 * @param required the required of this object field
	 */
	public void setRequired(boolean required);

	/**
	 * Returns the system of this object field.
	 *
	 * @return the system of this object field
	 */
	public boolean getSystem();

	/**
	 * Returns <code>true</code> if this object field is system.
	 *
	 * @return <code>true</code> if this object field is system; <code>false</code> otherwise
	 */
	public boolean isSystem();

	/**
	 * Sets whether this object field is system.
	 *
	 * @param system the system of this object field
	 */
	public void setSystem(boolean system);

	@Override
	public String[] getAvailableLanguageIds();

	@Override
	public String getDefaultLanguageId();

	@Override
	public void prepareLocalizedFieldsForImport() throws LocaleException;

	@Override
	public void prepareLocalizedFieldsForImport(Locale defaultImportLocale)
		throws LocaleException;

	@Override
	public ObjectField cloneWithOriginalValues();

}