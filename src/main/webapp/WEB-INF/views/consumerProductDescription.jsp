<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html lang="en" dir="ltr">
  <head>
    <meta charset="utf-8">
    <title>Product Description</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
  </head>
  <body>
    <div class="container">
      <br>
      <br>
      <h1>${product.getName()}</h1>
      <div class="row">
        <div class="col">
          Price:
        </div>
        <div class="col">
          ${product.getPrice()}
        </div>
      </div>

      <div class="row">
        <div class="col">
          Author:
        </div>
        <div class="col">
          ${product.getAuthor()}
        </div>
      </div>

      <div class="row">
        <div class="col">
          Genre:
        </div>
        <div class="col">
          ${product.getGenre()}
        </div>
      </div>

      <div class="row">
        <div class="col">
          Publisher:
        </div>
        <div class="col">
          ${product.getPublisher()}
        </div>
      </div>

      <div class="row">
        <div class="col">
          Seller:
        </div>
        <div class="col">
          ${product.getSeller().getName()}
        </div>
      </div>

      <div class="row">
        <div class="col">
          Description:
        </div>
        <div class="col">
          ${product.getDescription()}
        </div>
      </div>

      <form class="" action="placeOrder.htm" method="get">
        <input type="hidden" id="custId" name="productid" value="${product.getId()}">
        <input type="submit" name="" value="Place order for product">
      </form>
      <br/>
      <hr/>

      <form class="" action="writeReview.htm" method="get">
        <input type="hidden" id="custId" name="productid" value="${product.getId()}">
        <input type="submit" name="" value="WriteReview">
      </form>

      <div class="row">
        <div class="col">
          <h2>Reviews:</h2>
        </div>
        <div class="col">

        </div>
      </div>

      <c:forEach var="review" items="${product.getReviews()}">
        <br>
        <div class="row">
          <div class="col">
              Customer ${review.getConsumer().getName()}:
              <br>
              Posted: ${review.getCreateDate()}
              <br>
              Updated: ${review.getModifyDate()}
          </div>
          <div class="col">
            <h4>${review.getHeading()}</h4>
            <br>
            Rating: ${review.getRating()}
            <br>
            ${review.getDescription()}
            <br>
          </div>


            <div class="col">
              <form class="" action="updateReview.htm" method="get">
                <input type="hidden" name="reviewid" value="${review.getId()}">
                <input type="hidden" name="productid" value="${product.getId()}">
                  <input type="submit" name="" value="Edit">
              </form>
            </div>
            <div class="col">
              <form class="" action="deleteReview.htm" method="get">
                <input type="hidden" name="reviewid" value="${review.getId()}">
                  <input type="hidden" name="productid" value="${product.getId()}">
                  <input type="submit" name="" value="Delete">
              </form>
            </div>

        </div>

        <hr/>
      </c:forEach>
      <br>
      <a href="/advert/consumerhome"> Return Home </a>
    </div>
  </body>
</html>
