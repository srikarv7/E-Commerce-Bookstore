<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>

<html>
	<head>
		<title>Consumer Home</title>
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>

	</head>
	<body>

		<div class="container">
			<h1>Welcome Consumer, ${consumer.getName()}</h1>

      <h3>Please make your selection from the menu</h3>

       <a href="/advert/consumer/exploreProducts.htm">Order an Book</a><br> <br>

         <a href="/advert/consumer/viewProduct.htm">View all previously ordered Books</a><br><br>
         <a href="/advert/consumer/viewProfile.htm">View Profile/Account information</a><br><br>
          <a href="/advert/user/logout.htm">Logout</a><br><br>
		</div>



	</body>
</html>
