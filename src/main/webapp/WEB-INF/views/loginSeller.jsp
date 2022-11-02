<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<html>
<head>
    <title>Seller Login</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>

</head>
<body>
<br>
  <div class="container">
    <h2>Login Seller</h2>
<br>
    <form:form modelAttribute="seller">


    	<table>
    		<tr>
    		    <td>Seller User Name:</td>
    		    <td><form:input path="name" size="30" /> <font color="red"><form:errors path="name"/></font></td>
    		</tr>
 <br>
    		<tr>
    		    <td>Seller Password:</td>
    		    <td><form:password path="password" size="30" /> <font color="red"><form:errors path="password"/></font></td>
    		</tr>
<br>
    		<tr>
    		    <td colspan="2"><input type="submit" value="Login" /></td>
    		</tr>
    	</table>
    	<br>
    	<a href="/advert/seller/register.htm"> Register Seller </a>

    </form:form>
  </div>



</body>
</html>
