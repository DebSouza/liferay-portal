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

package com.liferay.layout.content.page.editor.web.internal.portlet.action.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.document.library.kernel.service.DLAppLocalService;
import com.liferay.document.library.util.DLURLHelper;
import com.liferay.dynamic.data.mapping.model.DDMForm;
import com.liferay.dynamic.data.mapping.model.DDMFormField;
import com.liferay.dynamic.data.mapping.model.DDMStructure;
import com.liferay.dynamic.data.mapping.storage.DDMFormValues;
import com.liferay.dynamic.data.mapping.test.util.DDMFormTestUtil;
import com.liferay.dynamic.data.mapping.test.util.DDMFormValuesTestUtil;
import com.liferay.dynamic.data.mapping.test.util.DDMStructureTestUtil;
import com.liferay.dynamic.data.mapping.util.DDM;
import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.service.JournalArticleLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.security.permission.PermissionThreadLocal;
import com.liferay.portal.kernel.service.CompanyLocalService;
import com.liferay.portal.kernel.test.portlet.MockLiferayPortletActionRequest;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.rule.DeleteAfterTestRun;
import com.liferay.portal.kernel.test.util.GroupTestUtil;
import com.liferay.portal.kernel.test.util.TestPropsValues;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.PermissionCheckerMethodTestRule;

import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;

import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.runner.RunWith;

import org.springframework.mock.web.MockHttpServletRequest;

/**
 * @author Rubén Pulido
 */
@RunWith(Arquillian.class)
public class AddStructuredContentMVCActionCommandTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
			new LiferayIntegrationTestRule(),
			PermissionCheckerMethodTestRule.INSTANCE);

	@Before
	public void setUp() throws Exception {
		_group = GroupTestUtil.addGroup();

		_company = _companyLocalService.getCompany(_group.getCompanyId());
	}

	private DDMStructure _addDDMStructure(DDMFormField... ddmFormFields)
		throws Exception {

		DDMForm ddmForm = new DDMForm();

		ddmForm.addAvailableLocale(LocaleUtil.US);

		for (DDMFormField ddmFormField : ddmFormFields) {
			ddmForm.addDDMFormField(ddmFormField);
		}

		ddmForm.setDefaultLocale(LocaleUtil.US);

		DDMFormTestUtil.setIndexTypeProperty(ddmForm, "none");

		return DDMStructureTestUtil.addStructure(
			_group.getGroupId(), JournalArticle.class.getName(), ddmForm);
	}

	private byte[] _getBytes(String fileName) throws Exception {
		Class<?> clazz = getClass();

		InputStream inputStream = clazz.getResourceAsStream(
			"dependencies/" + fileName);

		return FileUtil.getBytes(inputStream);
	}

	private DDMFormValues _getDDMFormValues(
		DDMStructure ddmStructure, String fieldName, String fieldValue) {

		DDMFormValues ddmFormValues = DDMFormValuesTestUtil.createDDMFormValues(
			ddmStructure.getDDMForm());

		ddmFormValues.addDDMFormFieldValue(
			DDMFormValuesTestUtil.createUnlocalizedDDMFormFieldValue(
				fieldName, fieldValue));

		return ddmFormValues;
	}

	private MockActionRequest _getMockActionRequest(
			DDMFormValues ddmFormValues, long ddmStructureId, String title)
		throws PortalException {

		MockActionRequest mockActionRequest = new MockActionRequest();

		mockActionRequest.setAttribute(
			WebKeys.THEME_DISPLAY, _getThemeDisplay());

		mockActionRequest.addParameter(
			"ddmFormValues", _ddm.getDDMFormValuesJSONString(ddmFormValues));

		mockActionRequest.addParameter(
			"ddmStructureId", String.valueOf(ddmStructureId));

		mockActionRequest.addParameter(
			"groupId", String.valueOf(_group.getGroupId()));

		mockActionRequest.addParameter("title", title);

		return mockActionRequest;
	}

	private ThemeDisplay _getThemeDisplay() throws PortalException {
		ThemeDisplay themeDisplay = new ThemeDisplay();

		themeDisplay.setCompany(_company);
		themeDisplay.setPermissionChecker(
			PermissionThreadLocal.getPermissionChecker());
		themeDisplay.setScopeGroupId(_group.getGroupId());
		themeDisplay.setSiteGroupId(_group.getGroupId());
		themeDisplay.setUser(TestPropsValues.getUser());

		return themeDisplay;
	}

	private String _read(String fileName) throws Exception {
		Class<?> clazz = getClass();

		InputStream inputStream = clazz.getResourceAsStream(
			"dependencies/" + fileName);

		return StringUtil.read(inputStream);
	}

	private Company _company;

	@Inject
	private CompanyLocalService _companyLocalService;

	@Inject
	private DDM _ddm;

	@Inject
	private DLAppLocalService _dlAppLocalService;

	@Inject
	private DLURLHelper _dlURLHelper;

	@DeleteAfterTestRun
	private Group _group;

	@Inject
	private JournalArticleLocalService _journalArticleLocalService;

	@Inject(filter = "mvc.command.name=/content_layout/add_structured_content")
	private MVCActionCommand _mvcActionCommand;

	@Inject
	private Portal _portal;

	private static class MockActionRequest
		extends MockLiferayPortletActionRequest {

		@Override
		public HttpServletRequest getHttpServletRequest() {
			return new MockHttpServletRequest();
		}

	}

}