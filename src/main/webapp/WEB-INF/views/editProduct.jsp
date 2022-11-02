<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html lang="en" dir="ltr">
  <head>
    <meta charset="utf-8">
    <title>Edit Product</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
  </head>
  <body>

    <div class="container">
      <h1>Edit Product Information:</h1>
      <br>
      <br>

      <form:form modelAttribute="product" method="post">


        <div class="row">
          <div class="col">
            <label for="productName" class="col">Product Name::</label>
          </div>
          <div class="col">
          	<input type="text" name="productName" id="productName" value="">

          </div>
        </div>

        <br>

        <div class="row">
          <div class="col">
            <label for="descripton">Description:</label>
          </div>
          <div class="col">
            <input type="textarea" name="descripton" rows="8" cols="80" id="descripton" value="">
          </div>
        </div>

        <br>

          <div class="row">
            <div class="col">
              <label for="price">Price:</label>
            </div>
            <div class="col">
              <input type="text" name="price" id="price" value="">
            </div>
          </div>

          <br>

          <div class="row">
            <div class="col">
              <label for="genre">Genre:</label>
            </div>
            <div class="col">
              <input type="text" name="genre" id="genre" value="">
            </div>
          </div>

          <br>

          <div class="row">
            <div class="col">
              <label for="author">Author:</label>
            </div>
            <div class="col">
              <input type="text" name="author" id="author" value="">
            </div>
          </div>

          <br>

          <div class="row">
            <div class="col">
              <label for="publisher">Publisher:</label>
            </div>
            <div class="col">
              <input type="text" name="publisher" id="publisher" value="">
            </div>
          </div>

          <br>

          <div class="row">
            <div class="col">
            </div>
            <div class="col"><input type="hidden" name="product" value="${product.getId()}">
              <input type="submit" name="Submit" value="Edit Book">
            </div>
          </div>

      </form:form>
    </div>

  </body>
</html>
