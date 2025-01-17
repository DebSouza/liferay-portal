<%--
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
--%>

<%@ include file="/init.jsp" %>

<%
CookiesBannerDisplayContext cookiesBannerDisplayContext = (CookiesBannerDisplayContext)request.getAttribute(CookiesBannerWebKeys.COOKIES_BANNER_DISPLAY_CONTEXT);
%>

<clay:container-fluid
	cssClass="container-view"
>
	<clay:row>
		<clay:content-row
			noGutters="true"
			verticalAlign="center"
		>
			<clay:content-col
				expand="<%= true %>"
			>
				<span>
					<%= cookiesBannerDisplayContext.getContent(locale) %>

					<clay:link
						href="<%= cookiesBannerDisplayContext.getPrivacyPolicyLink() %>"
						label="<%= cookiesBannerDisplayContext.getLinkDisplayText(locale) %>"
					/>
				</span>
			</clay:content-col>

			<clay:content-col>
				<clay:button
					displayType="link"
					id='<%= liferayPortletResponse.getNamespace() + "configurationButton" %>'
					label='<%= LanguageUtil.get(request, "configuration") %>'
					small="<%= true %>"
				/>
			</clay:content-col>

			<clay:content-col>
				<clay:button
					displayType="secondary"
					id='<%= liferayPortletResponse.getNamespace() + "acceptAllButton" %>'
					label='<%= LanguageUtil.get(request, "accept-all") %>'
					small="<%= true %>"
				/>
			</clay:content-col>

			<c:if test="<%= cookiesBannerDisplayContext.isIncludeDeclineAllButton() %>">
				<clay:content-col>
					<clay:button
						displayType="secondary"
						id='<%= liferayPortletResponse.getNamespace() + "declineAllButton" %>'
						label='<%= LanguageUtil.get(request, "decline-all") %>'
						small="<%= true %>"
					/>
				</clay:content-col>
			</c:if>
		</clay:content-row>
	</clay:row>
</clay:container-fluid>

<liferay-frontend:component
	componentId="CookiesBanner"
	context="<%= cookiesBannerDisplayContext.getContext(locale) %>"
	module="cookies_banner/js/CookiesBanner"
/>