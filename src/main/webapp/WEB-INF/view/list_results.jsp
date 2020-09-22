<%@ include file="inc/header.jsp"%>
<%@ include file="inc/nav.jsp"%>
<%@ page import="places.util.Mappings" %>

 <div class="container" align="center">

<div class="panel-body">
 		<div class="panel-heading">
 		     <h2>${nameOfResults}</h2>
 		</div>

 		<form method="POST" action="${Mappings.RESULTS}">
 	        <button type="submit" class="btn btn-primary" name = "saveAsJson" value = "true">Save table to Json file </button>
 	        <button type="submit" class="btn btn-primary" name = "saveAsJson" value = "false">Save table to CSV file </button>
 	    </form>
 	    <br></br>

        <table border="1" cellpadding="4" class="table table-striped">

            <tr>
                <th width="5%">id</th>
                <th width="80%">Title</th>
                <th width="5%">Lang</th>
                <th width="10%">Geodata</th>
            </tr>

            <c:forEach var="item" varStatus="status" items="${listArticles}">
                <tr>
                    <td><c:out value="${status.count}"/></td>
                    <td><a href="${item.getReference()}">"${item.getName()}"</a></td>
                    <td><c:out value="${item.getLang()}"/></td>
                    <td><c:out value="${item.getGeoData()}"/></td>
                </tr>
            </c:forEach>
       </table>

 </div>
 </div>

<%@ include file="inc/footer.jsp"%>
