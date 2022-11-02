<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<html>
<head>
    <title>Advert Lab</title>
</head>
<body>

<h2>Login User</h2>

<form:form modelAttribute="user">

	<table>
		<tr>
		    <td>User Name:</td>
		    <td><form:input path="name" size="30" /> <font color="red"><form:errors path="name"/></font></td>
		</tr>
		
		<tr>
		    <td>Password:</td>
		    <td><form:password path="password" size="30" /> <font color="red"><form:errors path="password"/></font></td>
		</tr>
		
		<tr>
		    <td colspan="2"><input type="submit" value="Login" /></td>
		</tr>
	</table>
	<br>
	<a href="/advert/user/register.htm"> Register </a>  

</form:form>

</body>
</html>