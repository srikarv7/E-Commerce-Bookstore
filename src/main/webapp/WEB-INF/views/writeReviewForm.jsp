<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html lang="en" dir="ltr">
  <head>
    <meta charset="utf-8">
    <title>Add Product</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
  </head>
  <body>

    <div class="container">
      <h1>Write review for the product ${product.getName()}:</h1>
      <br>
      <br>

      <form:form modelAttribute="review" method="post">


        <div class="row">
          <div class="col">
            <label for="heading" class="col">Heading:</label>
          </div>
          <div class="col">
          	<form:input path="heading" /> <font color="red"><form:errors path="heading" /></font>

          </div>
        </div>

        <br>

        <div class="row">
          <div class="col">
            <label for="descripton">Description:</label>
          </div>
          <div class="col">
            <form:textarea path="description" rows="8" cols="80"  /> <font color="red"><form:errors path="description" /></font>
          </div>
        </div>

        <br>

          <div class="row">
            <div class="col">
              <label for="rating">Rating out of five:</label>
            </div>
            <div class="col">
              <form:input path="rating" size="30" /> <font color="red"><form:errors path="rating" /></font>
            </div>
          </div>

          <br>

          <div class="row">
            <div class="col">
            </div>
            <div class="col">
              <input type="submit" name="Submit" value="Add Review">
            </div>
          </div>

      </form:form>
    </div>
    </body>
  </html>
