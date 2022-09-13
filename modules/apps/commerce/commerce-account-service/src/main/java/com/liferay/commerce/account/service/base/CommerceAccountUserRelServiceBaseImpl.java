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

package com.liferay.commerce.account.service.base;

import com.liferay.commerce.account.model.CommerceAccountUserRel;
import com.liferay.commerce.account.service.CommerceAccountUserRelService;
import com.liferay.commerce.account.service.CommerceAccountUserRelServiceUtil;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.module.framework.service.IdentifiableOSGiService;
import com.liferay.portal.kernel.service.BaseServiceImpl;

import java.lang.reflect.Field;

import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * Provides the base implementation for the commerce account user rel remote service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.commerce.account.service.impl.CommerceAccountUserRelServiceImpl}.
 * </p>
 *
 * @author Marco Leo
 * @see com.liferay.commerce.account.service.impl.CommerceAccountUserRelServiceImpl
 * @generated
 */
public abstract class CommerceAccountUserRelServiceBaseImpl
	extends BaseServiceImpl
	implements AopService, CommerceAccountUserRelService,
			   IdentifiableOSGiService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Use <code>CommerceAccountUserRelService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>CommerceAccountUserRelServiceUtil</code>.
	 */
	@Deactivate
	protected void deactivate() {
		_setServiceUtilService(null);
	}

	@Override
	public Class<?>[] getAopInterfaces() {
		return new Class<?>[] {
			CommerceAccountUserRelService.class, IdentifiableOSGiService.class
		};
	}

	@Override
	public void setAopProxy(Object aopProxy) {
		commerceAccountUserRelService = (CommerceAccountUserRelService)aopProxy;

		_setServiceUtilService(commerceAccountUserRelService);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return CommerceAccountUserRelService.class.getName();
	}

	protected Class<?> getModelClass() {
		return CommerceAccountUserRel.class;
	}

	protected String getModelClassName() {
		return CommerceAccountUserRel.class.getName();
	}

	private void _setServiceUtilService(
		CommerceAccountUserRelService commerceAccountUserRelService) {

		try {
			Field field =
				CommerceAccountUserRelServiceUtil.class.getDeclaredField(
					"_service");

			field.setAccessible(true);

			field.set(null, commerceAccountUserRelService);
		}
		catch (ReflectiveOperationException reflectiveOperationException) {
			throw new RuntimeException(reflectiveOperationException);
		}
	}

	@Reference
	protected
		com.liferay.commerce.account.service.CommerceAccountUserRelLocalService
			commerceAccountUserRelLocalService;

	protected CommerceAccountUserRelService commerceAccountUserRelService;

	@Reference
	protected com.liferay.counter.kernel.service.CounterLocalService
		counterLocalService;

	private static final Log _log = LogFactoryUtil.getLog(
		CommerceAccountUserRelServiceBaseImpl.class);

}