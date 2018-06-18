<!DOCTYPE HTML>
<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:th="http://www.thymeleaf.org"
      xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity3"
      xmlns:layout="http://www.ultraq.net.nz/thymeleaf/layout">
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html xmlns:th="http://www.thymeleaf.org">

<head>
    <title>Getting Started: Serving Web Content</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
    <script>
       /* function addItem(){
            var ul = document.getElementById("dynamic-list");
            var candidate = document.getElementById("candidate");
            var li = document.createElement("li");
            li.setAttribute('id',candidate.value);
            li.appendChild(document.createTextNode(candidate.value));

            // Controller:
            var jqxhr = $.post( "example.php", function() {
                alert( "success" );
            })

            ul.appendChild(li);


        }

        function removeItem(){
            var ul = document.getElementById("dynamic-list");
            var candidate = document.getElementById("candidate");
            var item = document.getElementById(candidate.value);
            ul.removeChild(item);
        }*/


    </script>
</head>
<body>

    <body>


        <forEach items="${userList}" var="entry">
            Key : <out value="${entry.key}"/>  Value: <out value="${entry.value}"/> <br />
        </forEach>

    </body>

</body>
</html>


