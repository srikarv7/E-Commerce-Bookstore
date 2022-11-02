<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<html>
<head>
    <title>Register Seller</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>

</head>
<body>

<h2>Register as a new Seller!</h2>

<form:form modelAttribute="seller">

  <div class="container">
    <div class="row">
      <div class="col">
        Seller User Name:
      </div>
      <div class="col">
        <form:input path="name" size="30" /> <font color="red"><form:errors path="name"/></font>
      </div>
    </div>
    <br>

    <div class="row">
      <div class="col">
        Seller Account Password:
      </div>
      <div class="col">
        <form:password path="password" size="30" /> <font color="red"><form:errors path="password"/></font>
      </div>
    </div>
    <br>

    <div class="row">
      <div class="col">
        Seller First Name:
      </div>
      <div class="col">
        <form:input path="firstName" size="30" /> <font color="red"><form:errors path="firstName"/></font>
      </div>
    </div>
    <br>

    <div class="row">
      <div class="col">
        Seller Last Name:
      </div>
      <div class="col">
        <form:input path="lastName" size="30" /> <font color="red"><form:errors path="lastName"/></font>
      </div>
    </div>
    <br>

    <div class="row">
      <div class="col">
        Seller Phone Number:
      </div>
      <div class="col">
        <form:input path="phoneNumber" size="30" /> <font color="red"><form:errors path="phoneNumber"/></font>
      </div>
    </div>
    <br>

    <div class="row">
      <div class="col">
        Seller EmailId:
      </div>
      <div class="col">
        <form:input path="emailId" size="30" /> <font color="red"><form:errors path="emailId"/></font>
      </div>
    </div>
    <br>

    <div class="row">
      <div class="col">
        Seller Address:
      </div>
      <div class="col">
        <form:input path="Address" size="30" /> <font color="red"><form:errors path="Address"/></font>
      </div>
    </div>
    <br>
    <input type="submit" value="Register" />

    <br>

    <a href="/advert/seller/login.htm"> Login Instead </a>

  </div>

</form:form>

</body>
</html>
