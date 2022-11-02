<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>UpdateorDeleteReview</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>

</head>
<body>
  <h1>Choose the review to Update or delete</h1>

  <div class="container">

    <form class="" action="chooseUpdateDeleteReview.htm" method="post">
      <table>
        <tr>
          <th>Select</th>
          <th>Customer name</th>
          <th>Heading and Description</th>
          <th>Rating</th>

        </tr>
        <c:forEach var="review" items="${reviews}">
        <tr>
          <td><input type="radio" name="selectedReview" value="${review.getId()}"></td>
          <td>${review.getConsumer().getName()}</td>
          <td>
            ${review.getHeading()}
            <br>
            ${review.getDescription()}
          </td>
          <td>${review.getRating()}</td>


        </tr>
        </c:forEach>
      </table>

    </form>

  </div>



</body>
</html>
