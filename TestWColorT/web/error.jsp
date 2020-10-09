<%
   String message = pageContext.getException().getMessage();
   String exception = pageContext.getException().getClass().toString();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Error</title>
</head>
<body>
<h2>Ups! Error page..</h2>
<p>Type: <%= exception%></p>
<p>Message: <%= message %></p>
</body>
</html>