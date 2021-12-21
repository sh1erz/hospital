<%@ page import="controller.command.CommandList" %>
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <title>Person</title>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
</head>
<body>
<p>Name: ${patient.name}</p>
<p>Birth: ${patient.birth}</p>
<p>Diagnosis: ${patient.diagnosis}</p><br>

<h2>Set diagnosis:</h2>
<form action="/controller" method="post">
    <input type="hidden" name="command" value="<%=CommandList.SET_DIAGNOSIS%>"/>
    <label>
        <input type="text" name="diagnosis"/>
    </label>
    <input type="submit"/>
</form><br>

<table border="1">
    <thead>
    <tr>
        <th>Type</th>
        <th>Performer</th>
        <th>Description</th>
        <th>Set Performer</th>
    </tr>
    </thead>
    <tbody>
    <form action="/controller" method="post">
        <c:forEach var="record" items="${records}" varStatus="loop">
            <tr>
                <td>${record.procedureType.name()}</td>
                <td>${record.performer}</td>
                <td>${record.description}</td>
                <td>
                    <select name="performer${loop.index}">
                        <option value=""></option>
                        <c:forEach items="${doctors}" var="doctor">
                            <option value="${doctor.name}">${doctor.name} ${doctor.doctorSpecialisationName}</option>
                        </c:forEach>
                        <c:if test="${record.procedureType.name().equals('MEDICINE') || record.procedureType.name().equals('PROCEDURE')}">
                            <c:forEach items="${nurses}" var="nurse">
                                <option value="${nurse.name}">${nurse.name}</option>
                            </c:forEach>
                        </c:if>
                    </select>
                </td>
            </tr>
        </c:forEach>
        <input type="hidden" name="command" value="<%=CommandList.SET_PERFORMER%>"/>
        <input type="submit"/>
    </form>
    </tbody>
</table>
</body>
</html>
