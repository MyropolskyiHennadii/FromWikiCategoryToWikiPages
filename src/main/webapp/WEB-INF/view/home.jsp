<%@ include file="inc/header.jsp"%>
<%@ include file="inc/nav.jsp"%>
<%@ page import="places.util.Mappings" %>

 <div class="container">
     	<br/>
     	<form method="POST" action="${Mappings.HOME}">
     	<div class="panel panel-primary" align="center">
      		<h4> This small application parses Category Wiki-page with its synonymic pages in different languages</h4>
            <h4> and shows all unique final pages (are not category) having geo-coordinates.</h4>
            <br> </br>
            <h6> You can try, for instance, with https://en.wikipedia.org/wiki/Category:Romanesque_architecture</h6>
      		<br> </br>
     		<div class="panel-heading">
     		    <h2> Insert link to Wiki-category here </h2>
     			<input type="text" size=200 name="startPage"/>
     			<br> </br>
     			<h3>Do not go from this page after click on Submit, please. Wait for results.</h3>
     			<h3>It may take a time.</h3>
     			<br> </br>
     			<button type="submit" class="btn btn-primary">Submit</button>
              <!--  <input type="submit" value="Submit"/> -->
            </div>
        </div>
        </form>
 </div>

 <%@ include file="inc/footer.jsp"%>
