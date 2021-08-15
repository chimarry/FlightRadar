<%@tag description="Featured flights" pageEncoding="UTF-8"%>
<%@attribute name="title" fragment="true"%>
<%@attribute name="tableBody" fragment="true"%>
<table class="table table-sm --featured-fligths">
	<caption class='--featured-flight-caption'
		style="caption-side: top !important">
		<jsp:invoke fragment="title" /></caption>
	<thead>
		<tr>
			<th scope="col">Start location</th>
			<th scope="col">Destination</th>
			<th scope="col">Time of departure</th>
			<th scope="col">Status</th>
			<th scope="col">Flight type</th>
		</tr>
	</thead>
	<jsp:invoke fragment="tableBody" />
</table>