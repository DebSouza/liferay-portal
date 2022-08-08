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

import ClayButton from '@clayui/button';
import {useFormikContext} from 'formik';

import PRMForm from '../../../../common/components/PRMForm';
import PRMFormik from '../../../../common/components/PRMFormik';
import PRMFormikPageProps from '../../../../common/components/PRMFormik/interfaces/prmFormikPageProps';
import {LiferayPicklistName} from '../../../../common/enums/liferayPicklistName';
import MDFRequest from '../../../../common/interfaces/mdfRequest';
import useDynamicFieldEntries from './hooks/useDynamicFieldEntries';

const Goals = ({
	onCancel,
	onContinue,
	onSaveAsDraft,
}: PRMFormikPageProps<MDFRequest>) => {
	const {
		additionalOptionsEntries,
		companiesEntries,
		fieldEntries,
	} = useDynamicFieldEntries();

	const {isSubmitting, isValid, values, ...formikHelpers} = useFormikContext<
		MDFRequest
	>();

	const countryOptions = fieldEntries[LiferayPicklistName.COUNTRIES];

	const onCountrySelected = (event: React.ChangeEvent<HTMLInputElement>) => {
		const countrySelected = countryOptions.find(
			(countryOption) => countryOption.value === event.target.value
		);

		formikHelpers.setFieldValue('country', {
			key: countrySelected?.value,
			name: countrySelected?.label,
		});
	};

	return (
		<PRMForm name="Goals" title="Campaign Information">
			<PRMForm.Section title="Partner">
				<PRMForm.Group>
					<PRMFormik.Field
						component={PRMForm.Select}
						label="Company Name"
						name="r_company_accountEntryId"
						options={companiesEntries}
						required
					/>

					<PRMFormik.Field
						component={PRMForm.Select}
						label="Country"
						name="country"
						onChange={onCountrySelected}
						options={fieldEntries[LiferayPicklistName.COUNTRIES]}
						required
					/>
				</PRMForm.Group>
			</PRMForm.Section>

			<PRMForm.Section title="Campaign">
				<PRMFormik.Field
					component={PRMForm.InputText}
					label="Provide a name and short description of the overall campaign"
					name="overallCampaign"
					required
				/>

				<div className="border border-neutral-3 mb-4 p-3 rounded-lg">
					<PRMFormik.Field
						component={PRMForm.CheckboxGroup}
						items={
							fieldEntries[
								LiferayPicklistName.LIFERAY_BUSINESS_SALES_GOALS
							]
						}
						label="Select Liferay business/sales goals this Campaign serves (choose up to three)"
						name="liferayBusinessSalesGoals"
						required
					/>
				</div>
			</PRMForm.Section>

			<PRMForm.Section title="Target Market">
				<div className="border border-neutral-3 mb-4 p-3 rounded-lg">
					<PRMFormik.Field
						component={PRMForm.CheckboxGroup}
						items={fieldEntries[LiferayPicklistName.TARGETS_MARKET]}
						label="Please select the target market(s) for this campaign (choose up to three)"
						name="targetsMarket"
						required
					/>
				</div>

				<PRMFormik.Field
					component={PRMForm.RadioGroup}
					items={additionalOptionsEntries}
					label="Additional options? Choose one if applicable"
					name="r_additionalOption_mdfRequest"
				/>

				<div className="border border-neutral-3 mb-4 p-3 rounded-lg">
					<PRMFormik.Field
						component={PRMForm.CheckboxGroup}
						items={
							fieldEntries[
								LiferayPicklistName.TARGETS_AUDIENCE_ROLE
							]
						}
						label="Choose your target audience/role (Select all that apply)"
						name="targetsAudienceRole"
						required
					/>
				</div>
			</PRMForm.Section>

			<PRMForm.Footer>
				<div className="mr-auto pl-0 py-3">
					<ClayButton
						className="pl-0"
						disabled={isSubmitting}
						displayType={null}
						onClick={() => onSaveAsDraft?.(values, formikHelpers)}
					>
						Save as Draft
					</ClayButton>
				</div>

				<div className="p-2">
					<ClayButton
						className="mr-4"
						displayType="secondary"
						onClick={onCancel}
					>
						Cancel
					</ClayButton>

					<ClayButton
						disabled={!isValid}
						onClick={() => onContinue?.(formikHelpers)}
					>
						Continue
					</ClayButton>
				</div>
			</PRMForm.Footer>
		</PRMForm>
	);
};

export default Goals;
