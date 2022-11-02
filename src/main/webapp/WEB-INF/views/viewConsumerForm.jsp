<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<html>
<head>
    <title>View Profile</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>

</head>
<body>

<h2>View profile information</h2>

  <div class="container">

    <div class="row">
      <div class="col">
        User Name:
      </div>
      <div class="col">
        ${consumer.getName()}
      </div>
    </div>

    <br>

    <div class="row">
      <div class="col">
        Password:
      </div>
      <div class="col">
        ${consumer.getPassword()}

      </div>
    </div>

    <br>

    <div class="row">
      <div class="col">
        First Name:
      </div>
      <div class="col">
        ${consumer.getFirstName()}
      </div>
    </div>

    <br>

    <div class="row">
      <div class="col">
        Last Name:
      </div>
      <div class="col">
        ${consumer.getLastName()}
      </div>
    </div>

    <br>

    <div class="row">
      <div class="col">
        Phone Number:
      </div>
      <div class="col">
        ${consumer.getPhoneNumber()}
      </div>
    </div>

    <br>

    <div class="row">
      <div class="col">
        Email Id:
      </div>
      <div class="col">
        ${consumer.getEmailId()}
      </div>
    </div>

    <br>

    <div class="row">
      <div class="col">
        Address:
      </div>
      <div class="col">
        ${consumer.getAddress()}
      </div>
    </div>

    <br>

      <form class="" action="updateProfile.htm" method="get">
        <input type="submit" value="Update this account" />
      </form>

      <br>

        <a href="/advert/consumerhome">Go back to Consumer Home</a>

  </div>

	<br>



</body>
</html>
