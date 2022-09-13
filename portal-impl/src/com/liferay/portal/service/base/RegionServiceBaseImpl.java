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

package com.liferay.portal.service.base;

import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBManagerUtil;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Region;
import com.liferay.portal.kernel.module.framework.service.IdentifiableOSGiService;
import com.liferay.portal.kernel.service.BaseServiceImpl;
import com.liferay.portal.kernel.service.RegionService;
import com.liferay.portal.kernel.service.RegionServiceUtil;
import com.liferay.portal.kernel.service.persistence.RegionLocalizationPersistence;
import com.liferay.portal.kernel.service.persistence.RegionPersistence;
import com.liferay.portal.kernel.util.PortalUtil;

import java.lang.reflect.Field;

import javax.sql.DataSource;

/**
 * Provides the base implementation for the region remote service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.portal.service.impl.RegionServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.portal.service.impl.RegionServiceImpl
 * @generated
 */
public abstract class RegionServiceBaseImpl
	extends BaseServiceImpl implements IdentifiableOSGiService, RegionService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Use <code>RegionService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>RegionServiceUtil</code>.
	 */

	/**
	 * Returns the region local service.
	 *
	 * @return the region local service
	 */
	public com.liferay.portal.kernel.service.RegionLocalService
		getRegionLocalService() {

		return regionLocalService;
	}

	/**
	 * Sets the region local service.
	 *
	 * @param regionLocalService the region local service
	 */
	public void setRegionLocalService(
		com.liferay.portal.kernel.service.RegionLocalService
			regionLocalService) {

		this.regionLocalService = regionLocalService;
	}

	/**
	 * Returns the region remote service.
	 *
	 * @return the region remote service
	 */
	public RegionService getRegionService() {
		return regionService;
	}

	/**
	 * Sets the region remote service.
	 *
	 * @param regionService the region remote service
	 */
	public void setRegionService(RegionService regionService) {
		this.regionService = regionService;
	}

	/**
	 * Returns the region persistence.
	 *
	 * @return the region persistence
	 */
	public RegionPersistence getRegionPersistence() {
		return regionPersistence;
	}

	/**
	 * Sets the region persistence.
	 *
	 * @param regionPersistence the region persistence
	 */
	public void setRegionPersistence(RegionPersistence regionPersistence) {
		this.regionPersistence = regionPersistence;
	}

	/**
	 * Returns the counter local service.
	 *
	 * @return the counter local service
	 */
	public com.liferay.counter.kernel.service.CounterLocalService
		getCounterLocalService() {

		return counterLocalService;
	}

	/**
	 * Sets the counter local service.
	 *
	 * @param counterLocalService the counter local service
	 */
	public void setCounterLocalService(
		com.liferay.counter.kernel.service.CounterLocalService
			counterLocalService) {

		this.counterLocalService = counterLocalService;
	}

	/**
	 * Returns the region localization persistence.
	 *
	 * @return the region localization persistence
	 */
	public RegionLocalizationPersistence getRegionLocalizationPersistence() {
		return regionLocalizationPersistence;
	}

	/**
	 * Sets the region localization persistence.
	 *
	 * @param regionLocalizationPersistence the region localization persistence
	 */
	public void setRegionLocalizationPersistence(
		RegionLocalizationPersistence regionLocalizationPersistence) {

		this.regionLocalizationPersistence = regionLocalizationPersistence;
	}

	public void afterPropertiesSet() {
		_setServiceUtilService(regionService);
	}

	public void destroy() {
		_setServiceUtilService(null);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return RegionService.class.getName();
	}

	protected Class<?> getModelClass() {
		return Region.class;
	}

	protected String getModelClassName() {
		return Region.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource = regionPersistence.getDataSource();

			DB db = DBManagerUtil.getDB();

			sql = db.buildSQL(sql);
			sql = PortalUtil.transformSQL(sql);

			SqlUpdate sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(
				dataSource, sql);

			sqlUpdate.update();
		}
		catch (Exception exception) {
			throw new SystemException(exception);
		}
	}

	private void _setServiceUtilService(RegionService regionService) {
		try {
			Field field = RegionServiceUtil.class.getDeclaredField("_service");

			field.setAccessible(true);

			field.set(null, regionService);
		}
		catch (ReflectiveOperationException reflectiveOperationException) {
			throw new RuntimeException(reflectiveOperationException);
		}
	}

	@BeanReference(
		type = com.liferay.portal.kernel.service.RegionLocalService.class
	)
	protected com.liferay.portal.kernel.service.RegionLocalService
		regionLocalService;

	@BeanReference(type = RegionService.class)
	protected RegionService regionService;

	@BeanReference(type = RegionPersistence.class)
	protected RegionPersistence regionPersistence;

	@BeanReference(
		type = com.liferay.counter.kernel.service.CounterLocalService.class
	)
	protected com.liferay.counter.kernel.service.CounterLocalService
		counterLocalService;

	@BeanReference(type = RegionLocalizationPersistence.class)
	protected RegionLocalizationPersistence regionLocalizationPersistence;

	private static final Log _log = LogFactoryUtil.getLog(
		RegionServiceBaseImpl.class);

}