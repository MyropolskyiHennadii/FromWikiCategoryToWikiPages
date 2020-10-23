<%@ include file="inc/header.jsp"%>
<%@ include file="inc/nav.jsp"%>

 <div class="container">
     	<br/>
     	<form method="POST" action="${Mappings.SAVED_FILES}" align="center">
              <button type="submit" class="btn btn-primary" name = "deleteAllFiles" value = "true">Delete all files </button>
        </form>
    	<br/>
     	<form method="POST" action="/files/{filename:.+}">
     	<div class="panel panel-primary" align="center">
     		<div class="panel-heading">
     		    <h2> Saved files </h2>
     			<br> </br>
     			<table border="1" cellpadding="2" class="table table-striped">
                <c:forEach var="file" varStatus="status" items="${files}">
                      <tr>
                          <td><c:out value="${status.count}"/></td>
                          <td><a href="${file}">"${file}"</a></td>
                      </tr>
                </c:forEach>
                </table>
            </div>
        </div>
        </form>
 </div>

 <%@ include file="inc/footer.jsp"%>
