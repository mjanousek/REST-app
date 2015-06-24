<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<html>
<body>
	<h1>Title : ${title}</h1>
	<h1>Message : ${message}</h1>

	<c:url value="/logout" var="logoutUrl" />
	<form action="${logoutUrl}" method="post" id="logoutForm">
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
	</form>
	<script>
		function formSubmit() {
			document.getElementById("logoutForm").submit();
		}
	</script>

	<c:if test="${pageContext.request.userPrincipal.name != null}">
		<h2>Welcome : ${pageContext.request.userPrincipal.name} | <a href="javascript:formSubmit()"> Logout</a>
		</h2>
	</c:if>
	
	
	<h1>UserList!</h1>
        
        <table>
            <tr>
                <th>id</th><th>name</th><th>pass</th><th>enabled</th><th>admin</th>
            </tr>
            
            <c:forEach var="user" items="${users}">
                
                <tr>
                    <td>
                        <c:out value="${user.getIdUser()}"></c:out>
                    </td>
                    <td>
                        <c:out value="${user.getName()}"></c:out>
                    </td>
                    <td>
                        <c:out value="${user.getPassword()}"></c:out>
                    </td>
                    <td>
                        <c:out value="${user.isEnabled()}"></c:out>
                    </td>
                    <td>
                        <c:out value="${user.isIsAdmin()}"></c:out>
                    </td>
                </tr>
                
            </c:forEach>
            
        </table>

</body>
</html>