<%@ include file="/init.jsp" %>

<%

String redirect = ParamUtil.getString(request, "redirect");
String backURL = ParamUtil.getString(request, "backURL");

String msAccountName = ParamUtil.getString(request, "msAccountName");
String msAccountPhone = ParamUtil.getString(request, "msAccountPhone");
String msAccountCity = ParamUtil.getString(request, "msAccountCity");
String msAccountMail = ParamUtil.getString(request, "msAccountMail");


%>

<portlet:actionURL name="addMSAccount" var="addMSAccountActionURL" />

<aui:form action="<%= addMSAccountActionURL %>" cssClass="container-fluid-1280" method="post" name="fm">
	<aui:input name="redirect" type="hidden" value="${themeDisplay.getURLCurrent()}" />
	<aui:input name="backURL" type="hidden" value="backURL" />
	
	<liferay-ui:error exception="<%= RestException.class %>" message="problem-connecting-to-ms" />


	<div class="lfr-form-content">
		<aui:fieldset-group markupView="lexicon">
			<aui:fieldset>
				<div class="row">
					<div class="col-md-6">
						<aui:input autoFocus="<%= true %>" label="name" name="msAccountName" id="msAccountName" type="text" value="<%= msAccountName %>" />

						<aui:input label="phone" name="msAccountPhone" id="msAccountPhone" type="text" value="<%= msAccountPhone %>"/>

						<aui:input label="city" name="msAccountCity" id="msAccountCity" type="text" value="<%= msAccountCity %>"/>

					</div>

					
				</div>
			</aui:fieldset>


			<aui:fieldset>
				<aui:button-row>
					<aui:button cssClass="btn-lg" type="submit" value="save" />

					<aui:button cssClass="btn-lg" href="<%= backURL %>" type="cancel" />
				</aui:button-row>
			</aui:fieldset>
		</aui:fieldset-group>
	</div>
</aui:form>