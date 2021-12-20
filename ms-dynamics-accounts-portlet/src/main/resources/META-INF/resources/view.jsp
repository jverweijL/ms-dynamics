<%@ include file="/init.jsp" %>

<%

SearchContainer msAccountsSearchContainer = (SearchContainer) request.getAttribute("accountsSearchContainer");

%>

<liferay-portlet:renderURL var="addMSAccountRenderURL" windowState="<%= LiferayWindowState.NORMAL.toString() %>">
	<liferay-portlet:param name="mvcRenderCommandName" value="addAccount" />
	<liferay-portlet:param name="redirect" value="${themeDisplay.getURLCurrent()}" />
	<liferay-portlet:param name="backURL" value="${themeDisplay.getURLCurrent()}" />
</liferay-portlet:renderURL>


<div class="container-fluid-1280">
	
	<nav class="management-bar management-bar-light navbar navbar-expand-md">
		<div class="container">
			<ul class="navbar-nav">
				<li class="nav-item">
					<div class="custom-control custom-checkbox">
						<label>
							<input class="custom-control-input" type="checkbox"/>
							<span class="custom-control-label"></span>
						</label>
					</div>
				</li>
				<li class="dropdown nav-item">
					<a aria-expanded="false" aria-haspopup="true" class="dropdown-toggle nav-link navbar-breakpoint-down-d-none" data-toggle="dropdown" href="#1" role="button">
						<span class="navbar-text-truncate">Filter and Order</span>
						<clay:icon symbol="caret-bottom" />
					</a>
					<a aria-expanded="false" aria-haspopup="true" class="nav-link nav-link-monospaced dropdown-toggle navbar-breakpoint-d-none" data-toggle="dropdown" href="#1" role="button">
						<clay:icon symbol="filter" />
					</a>
					<div class="dropdown-menu" role="menu">
						<ul class="list-unstyled">
							<li><a class="dropdown-item" href="#1" role="button">Filter Action 1</a></li>
							<li><a class="dropdown-item" href="#1" role="button">Filter Action 2</a></li>
							<li><a class="dropdown-item" href="#1" role="button">Filter Action 3</a></li>
						</ul>
					</div>
				</li>
				<li class="nav-item">
					<a class="btn -link nav-link-monospaced order-arrow-down-active btn-unstyled" href="#1" role="button">
						<clay:icon symbol="order-arrow" />
					</a>
				</li>
			</ul>
			<div class="navbar-form navbar-form-autofit navbar-overlay navbar-overlay-sm-down">
				<div class="container">
					<form role="search">
						<div class="input-group">
							<div class="input-group-item">
								<input class="form-control input-group-inset input-group-inset-after" placeholder="Search for..." type="text"/>
								<span class="input-group-inset-item input-group-inset-item-after">
									<button class="btn btn-unstyled navbar-breakpoint-d-none" type="button">
										<clay:icon symbol="times" />
									</button>
									<button class="btn btn-unstyled navbar-breakpoint-d-block" type="button">
										<clay:icon symbol="search" />
									</button>
								</span>
							</div>
						</div>
					</form>
				</div>
			</div>
			<ul class="navbar-nav">
				<li class="nav-item navbar-breakpoint-d-none">
					<a class="nav-link nav-link-monospaced" href="#1" role="button">
						<clay:icon symbol="search" />
					</a>
				</li>
				<li class="dropdown nav-item">
					<a aria-expanded="false" aria-haspopup="true" class="dropdown-toggle nav-link nav-link-monospaced" data-toggle="dropdown" href="#1" role="button">
						<clay:icon symbol="list" />
					</a>
					<div class="dropdown-menu dropdown-menu-right dropdown-menu-indicator-start" role="menu">
						<ul class="list-unstyled">
							<li><a class="active dropdown-item" href="#1">
								<span class="dropdown-item-indicator">
									<clay:icon symbol="list" />
								</span>
								List View
							</a></li>
							<li><a class="dropdown-item" href="#1">
								<span class="dropdown-item-indicator">
									<clay:icon symbol="table" />
								</span>
								Table View
							</a></li>
							<li><a class="dropdown-item" href="#1">
								<span class="dropdown-item-indicator">
									<clay:icon symbol="cards2" />
								</span>
								Card View
							</a></li>
						</ul>
					</div>
				</li>
				<li class="nav-item">
					<button class="btn btn-primary nav-btn nav-btn-monospaced navbar-breakpoint-down-d-none" type="button" onclick="window.location.href='<%=addMSAccountRenderURL.toString() %>'">
						<clay:icon symbol="plus" />
					</button>
				</li>
			</ul>
		</div>
	</nav>
	
	

	<liferay-ui:search-container searchContainer="<%= msAccountsSearchContainer %>" id="msAccountsSearchContainer">

		<liferay-ui:search-container-row
			className="com.liferay.msdynamics.integration.rest.client.data.MSAccount"
			modelVar="msAccount"
		>
			<liferay-ui:search-container-column-text
				cssClass="table-cell-content"
				name="name"
				property="name"
			/>

			<liferay-ui:search-container-column-text
				cssClass="table-cell-content"
				name="phone"
				property="telephone1"
			/>

			<liferay-ui:search-container-column-text
				cssClass="table-cell-content"
				name="city"
				property="address1_city"
			/>

			<liferay-ui:search-container-column-text
				cssClass="table-cell-content"
				name="mail"
				property="emailaddress1"
			/>

			
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator markupView="lexicon" />

	</liferay-ui:search-container>
</div>