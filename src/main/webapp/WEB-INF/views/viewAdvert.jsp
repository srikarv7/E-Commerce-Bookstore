<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.*"%>
<%@ page import="com.me.pojo.Category"%>
<%@ page import="com.me.pojo.Advert"%>


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <table border="1" cellpadding="5" cellspacing="5">
            <tr>
                <td><b>ADVERT TITLE</b></td>
                <td><b>MESSAGE</b></td>
                <td><b>CATEGORIES</b></td>
            </tr>
            <c:forEach var="adv" items="${adverts}">
                <tr>
                    <td>${adv.title}</td>
                    <td>${adv.message}</td>
                    <%
                      Advert advert = (Advert) pageContext.getAttribute("adv");
					  List<Category> categories = advert.getCategories();
                      String categoryList="";
                      
                      for(Category cat: categories){
                    	  categoryList = cat.getTitle() + " ";
                      }
                      pageContext.setAttribute("categoryList", categoryList);
					%>
                    <td>${categoryList}</td>
                </tr>
            </c:forEach>
        </table>
        <br>
        <br>
		<a href="/advert/home"> Return Home </a>  
    </body>
</html>