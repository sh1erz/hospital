<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <title>Person</title>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
</head>
<body>
<%//get medRecords

%>
<p>Name: ${patient.name}</p>
<p>Birth: ${patient.birth}</p>
<p>Diagnosis: ${patient.diagnosis}</p><br>

<h2>Set diagnosis:</h2>
<form action="/controller" method="post">
    <input type="hidden"/>
    <label>
        <input type="text" name="diagnosis"/>
    </label>
    <input type="submit"/>
</form>

<table>
    <c:forEach var="record" items="${records}">
        <tr>
            <td>${record.name}</td>
            <td>${p.birth}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
