<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<html>
<head>
    <title>Update Consumer</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>

</head>
<body>

<h2>Update values of existing Seller</h2>

<form:form modelAttribute="seller">

  <div class="container">

    <div class="row">
      <div class="col">
        User Name:
      </div>
      <div class="col">
        ${seller.getName()}
      </div>
    </div>

    <br>

    <div class="row">
      <div class="col">
        Password:
      </div>
      <div class="col">
        <input type="password" name="password" value="">

      </div>
    </div>

    <br>

    <div class="row">
      <div class="col">
        First Name:
      </div>
      <div class="col">
        <input type="text" name="fname" value="">
      </div>
    </div>

    <br>

    <div class="row">
      <div class="col">
        Last Name:
      </div>
      <div class="col">
        <input type="text" name="lname" value="">
      </div>
    </div>

    <br>

    <div class="row">
      <div class="col">
        Phone Number:
      </div>
      <div class="col">
        <input type="text" name="phoneNumber" value="">
      </div>
    </div>

    <br>

    <div class="row">
      <div class="col">
        Email Id:
      </div>
      <div class="col">
        <input type="text" name="emailId" value="">
      </div>
    </div>

    <br>

    <div class="row">
      <div class="col">
        Address:
      </div>
      <div class="col">
        <input type="text" name="address" value="" size="60">
      </div>
    </div>

    <br>

      <input type="submit" value="Update Profile Seller" />

      <br>

  </div>

	<br>

</form:form>

</body>
</html>
