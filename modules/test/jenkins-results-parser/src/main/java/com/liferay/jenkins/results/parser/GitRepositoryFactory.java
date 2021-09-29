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

package com.liferay.jenkins.results.parser;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;

import org.json.JSONObject;

/**
 * @author Peter Yoo
 */
public class GitRepositoryFactory {

	public static LocalGitRepository getLocalGitRepository(
		String repositoryName, String upstreamBranchName) {

		return new DefaultLocalGitRepository(
			repositoryName, upstreamBranchName);
	}

	public static RemoteGitRepository getRemoteGitRepository(
		GitRemote gitRemote) {

		String hostname = gitRemote.getHostname();

		if (hostname.equalsIgnoreCase("github.com")) {
			return new GitHubRemoteGitRepository(gitRemote);
		}

		return new DefaultRemoteGitRepository(gitRemote);
	}

	public static RemoteGitRepository getRemoteGitRepository(String remoteURL) {
		Matcher matcher = GitRemote.getRemoteURLMatcher(remoteURL);

		if ((matcher == null) || !matcher.find()) {
			throw new RuntimeException("Invalid remote URL " + remoteURL);
		}

		String patternString = String.valueOf(matcher.pattern());

		String username = "liferay";

		if (patternString.contains("(?<username>")) {
			username = matcher.group("username");
		}

		return getRemoteGitRepository(
			matcher.group("hostname"), matcher.group("gitRepositoryName"),
			username);
	}

	public static RemoteGitRepository getRemoteGitRepository(
		String hostname, String gitRepositoryName, String username) {

		if (hostname.equalsIgnoreCase("github.com")) {
			return new GitHubRemoteGitRepository(gitRepositoryName, username);
		}

		return new DefaultRemoteGitRepository(
			hostname, gitRepositoryName, username);
	}

	public static WorkspaceGitRepository getWorkspaceGitRepository(
		String gitDirectoryName) {

		WorkspaceGitRepository workspaceGitRepository =
			_workspaceGitRepositories.get(gitDirectoryName);

		if (workspaceGitRepository != null) {
			return workspaceGitRepository;
		}

		BuildDatabase buildDatabase = BuildDatabaseUtil.getBuildDatabase();

		if (buildDatabase.hasWorkspaceGitRepository(gitDirectoryName)) {
			workspaceGitRepository = buildDatabase.getWorkspaceGitRepository(
				gitDirectoryName);

			_workspaceGitRepositories.put(
				gitDirectoryName, workspaceGitRepository);

			return workspaceGitRepository;
		}

		String gitRepositoryName =
			JenkinsResultsParserUtil.getGitRepositoryName(gitDirectoryName);
		String gitUpstreamBranchName =
			JenkinsResultsParserUtil.getGitUpstreamBranchName(gitDirectoryName);

		if ((gitRepositoryName == null) || (gitUpstreamBranchName == null)) {
			throw new RuntimeException(
				"Could not find git directory name " + gitDirectoryName);
		}

		RemoteGitRef remoteGitRef = GitUtil.getRemoteGitRef(
			JenkinsResultsParserUtil.combine(
				"https://github.com/liferay/", gitRepositoryName, "/tree/",
				gitUpstreamBranchName));

		if (gitRepositoryName.matches("liferay-plugins(-ee)?")) {
			workspaceGitRepository = new PluginsWorkspaceGitRepository(
				remoteGitRef, gitUpstreamBranchName);
		}
		else if (gitRepositoryName.matches("liferay-portal(-ee)?")) {
			workspaceGitRepository = new PortalWorkspaceGitRepository(
				remoteGitRef, gitUpstreamBranchName);
		}
		else {
			workspaceGitRepository = new DefaultWorkspaceGitRepository(
				remoteGitRef, gitUpstreamBranchName);
		}

		_workspaceGitRepositories.put(gitDirectoryName, workspaceGitRepository);

		return workspaceGitRepository;
	}

	public static WorkspaceGitRepository getWorkspaceGitRepository(
		String repositoryName, String upstreamBranchName) {

		return getWorkspaceGitRepository(
			JenkinsResultsParserUtil.getGitDirectoryName(
				repositoryName, upstreamBranchName));
	}

	protected static WorkspaceGitRepository getWorkspaceGitRepository(
		JSONObject jsonObject) {

		String gitDirectoryName = jsonObject.optString("directory_name", null);

		if (gitDirectoryName == null) {
			throw new RuntimeException("Invalid JSONObject " + jsonObject);
		}

		WorkspaceGitRepository workspaceGitRepository =
			_workspaceGitRepositories.get(gitDirectoryName);

		if (workspaceGitRepository != null) {
			return workspaceGitRepository;
		}

		String repositoryName = jsonObject.optString("name", null);

		if (repositoryName == null) {
			throw new RuntimeException("Invalid JSONObject " + jsonObject);
		}
		else if (repositoryName.matches("liferay-plugins(-ee)?")) {
			workspaceGitRepository = new PluginsWorkspaceGitRepository(
				jsonObject);
		}
		else if (repositoryName.matches("liferay-portal(-ee)?")) {
			workspaceGitRepository = new PortalWorkspaceGitRepository(
				jsonObject);
		}
		else {
			workspaceGitRepository = new DefaultWorkspaceGitRepository(
				jsonObject);
		}

		workspaceGitRepository = new DefaultWorkspaceGitRepository(jsonObject);

		_workspaceGitRepositories.put(gitDirectoryName, workspaceGitRepository);

		return workspaceGitRepository;
	}

	private static final Map<String, WorkspaceGitRepository>
		_workspaceGitRepositories = new HashMap<>();

}