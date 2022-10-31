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

import {ClayButtonWithIcon} from '@clayui/button';
import ClayPopover from '@clayui/popover';
import i18n from '../../../../../../common/I18n';

const PopoverIcon = ({alignPosition = 'right'}) => {
	return (
		<ClayPopover
			alignPosition={alignPosition}
			className="cp-team-members-popover"
			closeOnClickOutside
			trigger={
				<ClayButtonWithIcon
					className="text-brand-primary-darken-2"
					displayType={null}
					size="sm"
					symbol="info-circle"
				/>
			}
		>
			<p className="m-0 text-neutral-10">
				{i18n.translate(
					'lxc-sm-is-the-abbreviation-of-liferay-experience-cloud-self-managed'
				)}
			</p>
		</ClayPopover>
	);
};

export default PopoverIcon;
