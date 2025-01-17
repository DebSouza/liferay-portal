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

package com.liferay.portal.workflow.kaleo.runtime.integration.internal.security.permission.resource;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermission;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.workflow.kaleo.model.KaleoDefinition;
import com.liferay.portal.workflow.kaleo.service.KaleoDefinitionLocalService;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Rafael Praxedes
 */
@Component(
	property = "model.class.name=com.liferay.portal.kernel.workflow.WorkflowDefinition",
	service = ModelResourcePermission.class
)
public class WorkflowDefinitionModelResourcePermission
	implements ModelResourcePermission<KaleoDefinition> {

	@Override
	public void check(
			PermissionChecker permissionChecker,
			KaleoDefinition kaleoDefinition, String actionId)
		throws PortalException {

		if (!contains(permissionChecker, kaleoDefinition, actionId)) {
			throw new PrincipalException.MustBeCompanyAdmin(
				permissionChecker.getUserId());
		}
	}

	@Override
	public void check(
			PermissionChecker permissionChecker, long primaryKey,
			String actionId)
		throws PortalException {

		check(
			permissionChecker,
			_kaleoDefinitionLocalService.getKaleoDefinition(primaryKey),
			actionId);
	}

	@Override
	public boolean contains(
			PermissionChecker permissionChecker,
			KaleoDefinition kaleoDefinition, String actionId)
		throws PortalException {

		return _portletResourcePermission.contains(
			permissionChecker, kaleoDefinition.getGroupId(), actionId);
	}

	@Override
	public boolean contains(
			PermissionChecker permissionChecker, long primaryKey,
			String actionId)
		throws PortalException {

		return contains(
			permissionChecker,
			_kaleoDefinitionLocalService.getKaleoDefinition(primaryKey),
			actionId);
	}

	@Override
	public String getModelName() {
		return KaleoDefinition.class.getName();
	}

	@Override
	public PortletResourcePermission getPortletResourcePermission() {
		return _portletResourcePermission;
	}

	@Reference
	private KaleoDefinitionLocalService _kaleoDefinitionLocalService;

	@Reference(
		target = "(resource.name=" + WorkflowConstants.RESOURCE_NAME + ")"
	)
	private PortletResourcePermission _portletResourcePermission;

}