<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html lang="en" dir="ltr">
  <head>
    <meta charset="utf-8">
    <title>View Your Products</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
  </head>
  <body>
    <h1>Please find below your products:</h1>
    <br/>
    <form class="" action="editproduct.htm" method="get">
      <table class="table table-bordered">
        <thead>
        <tr>
          <th>Select</th>
          <th>Book Title</th>
          <th>Description</th>
          <th>Author Name</th>
          <th>Price</th>
          <th>Genre</th>
          <th>Number of times ordered</th>
          <th>Last Modified</th>
          <th>Average Rating</th>
        </tr>
      </thead>
      <tbody>
        <c:forEach var="prod" items="${products}">
        <tr>
          <td><input type="radio" name="product" value="${prod.getId()}"></td>
          <td>${prod.getName()}</td>
          <td>${prod.getDescription()}</td>
          <td>${prod.getAuthor()}</td>
          <td>${prod.getPrice()}</td>
          <td>${prod.getGenre()}</td>
          <td>${prod.getOrderedCount()}</td>
          <td>${prod.getModifyDate()}</td>
          <td>${prod.averageRating()}</td>

        </tr>
        </c:forEach>
      </tbody>
      </table>

      <input type="submit" name="" value="Edit Product">

    </form>
    <a href="/advert/Sellerhome"> Return Home </a>

  </body>
</html>
