<%@ include file="/inc/taglib.jsp" %>
Twitter<br /><br />
${cacheMessage} <br />
 
<c:forEach var="s" items="${status}">
	${s.text} <br />
</c:forEach>
