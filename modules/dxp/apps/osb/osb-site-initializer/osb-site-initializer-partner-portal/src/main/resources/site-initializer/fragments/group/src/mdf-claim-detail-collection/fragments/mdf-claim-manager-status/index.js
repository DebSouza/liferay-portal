/* eslint-disable no-undef */
/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 */

/* eslint-disable no-undef */
const findRequestIdUrl = (paramsUrl) => {
	const splitParamsUrl = paramsUrl.split('?');

	return splitParamsUrl[0];
};

const currentPath = Liferay.currentURL.split('/');
const mdfClaimId = findRequestIdUrl(currentPath.at(-1));

const updateStatusToApproved = fragmentElement.querySelector(
	'#status-approved'
);
const updateStatusToRequestMoreInfo = fragmentElement.querySelector(
	'#status-request'
);
const updateStatusToReject = fragmentElement.querySelector('#status-reject');

const updateStatus = async (status) => {
	// eslint-disable-next-line @liferay/portal/no-global-fetch
	const statusManagerResponse = await fetch(`/o/c/mdfclaims/${mdfClaimId}`, {
		body: `{"mdfClaimStatus": "${status}"}`,
		headers: {
			'content-type': 'application/json',
			'x-csrf-token': Liferay.authToken,
		},
		method: 'PUT',
	});

	if (statusManagerResponse.ok) {
		const data = await statusManagerResponse.json();

		document.getElementById(
			'mdf-claim-status-display'
		).innerHTML = `Status: ${Liferay.Util.escape(data.mdfClaimStatus)}`;

		getMDFClaimStatus();

		return;
	}

	Liferay.Util.openToast({
		message: 'An unexpected error occured.',
		type: 'danger',
	});
};

updateStatusToApproved.onclick = () =>
	Liferay.Util.openConfirmModal({
		message: 'Do you want to Approve this MDF?',
		onConfirm: (isConfirmed) => {
			if (isConfirmed) {
				updateStatus('approved');
			}
		},
	});

updateStatusToRequestMoreInfo.onclick = () =>
	Liferay.Util.openConfirmModal({
		message: 'Do you want to Request more info for this MDF?',
		onConfirm: (isConfirmed) => {
			if (isConfirmed) {
				updateStatus('moreInfoRequested');
			}
		},
	});

updateStatusToReject.onclick = () =>
	Liferay.Util.openConfirmModal({
		message: 'Do you want to Reject this MDF?',
		onConfirm: (isConfirmed) => {
			if (isConfirmed) {
				updateStatus('rejected');
			}
		},
	});

const getMDFClaimStatus = async () => {
	// eslint-disable-next-line @liferay/portal/no-global-fetch
	const statusResponse = await fetch(`/o/c/mdfclaims/${mdfClaimId}`, {
		headers: {
			'accept': 'application/json',
			'x-csrf-token': Liferay.authToken,
		},
	});

	if (statusResponse.ok) {
		const data = await statusResponse.json();

		fragmentElement.querySelector(
			'#mdf-claim-status-display'
		).innerHTML = `Status: ${Liferay.Util.escape(
			data.mdfClaimStatus.name
		)}`;

		return;
	}

	Liferay.Util.openToast({
		message: 'An unexpected error occured.',
		type: 'danger',
	});
};

if (layoutMode !== 'edit') {
	getMDFClaimStatus();
}
