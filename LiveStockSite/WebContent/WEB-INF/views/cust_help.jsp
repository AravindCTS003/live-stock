<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>LiveStock Trading | Help</title>
	<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js">
	</script>
	<script type="text/javascript">

	// <!CDATA[
	$(document).ready(function(){
		console.log("help!");	
		$("#help").addClass('active');
	});

	// ]]>
	</script>
	<link rel="stylesheet" href="gen.css" />
</head>
<body>
	<jsp:include page="_header.jsp"></jsp:include>
	<jsp:include page="_cust_menu.jsp"></jsp:include>
	<div class="body" align="Left">
		<h2>Help</h2>
		<p>Hello! You are currently logged in as a customer. If this is incorrect,
		please logout using the button on the top right. Otherwise, here is what
		you can do on this account:</p>
		<dl>
			<dt>Account Info</dt>
			<dd>View your account information.</dd>
			<dt>Portfolio</dt>
			<dd>View information about your accounts and the stocks that you currently own.</dd>
			<dt>Orders</dt>
			<dd>View your pending and completed orders. View the conditional price history for each order.</dd>
			<dt>Stocks</dt>
			<dd>View current stock listings, a list of bestsellers, and a personalized suggestion list.<br />
				Also search for stocks by type or keyword, and view their stock price history.</dd>
			<dt>Help</dt>
			<dd>Read this help page.</dd>
			<dt>Logout</dt>
			<dd>Logout of this account and end the session.</dd>
		</dl>
	</div>
	
	<jsp:include page="_footer.jsp"></jsp:include>
</body>
</html>