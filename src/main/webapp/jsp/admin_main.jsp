<%@ page import="domain.Patient" %>
<%@ page import="java.util.List" %>
<%@ page import="static controller.util.constants.Attribute.PATIENTS" %>
<%@ page import="controller.command.CommandList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin Main</title>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
</head>
<body>
<jsp:useBean id="DoctorSpecialisaton" class="domain.DoctorSpecialisation" scope="application"/>
<%--<% Patient[] patients = (Patient[]) request.getSession().getAttribute(PATIENTS.getAttribute());
    out.print(request.getSession().getAttribute("patients"));
    out.print(patients[0]);%>--%>
<table border="1">
    <thead>
    <tr>
        <th>Name</th>
        <th>Birth</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="p" items="${patients}">
        <tr>
            <td>${p.name}</td>
            <td>${p.birth}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<h2>Add patient</h2>
<form method="post" action="controller">
    <input type="hidden" name="command" value="<%=CommandList.ADD_PATIENT%>">

    <p>Name:</p>
    <label>
        <input type="text" name="name">
    </label><br>

    <label>Birth:
        <input type="date" name="birth">
    </label><br>
    <label>
        <select name="doctor">
            <c:forEach items="${doctors}" var="doctor">
                <option value="${doctor.id}">${doctor.name} ${doctor.doctorSpecialisationName.name}</option>
            </c:forEach>
        </select>
    </label><br>
    <input type="submit" name="button">
</form>

<%--<jsp:useBean id="PATIENTS" scope="request" type="controller.util.constants.Attribute"/>--%>
</body>
</html>
