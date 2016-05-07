<%--
  Created by IntelliJ IDEA.
  User: Misaka
  Date: 2016-05-04
  Time: 오후 2:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Authorize</title>

    <!-- jQuery -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>

    <script type="text/javascript">
        $(document).ready(function () {
            var uri = '${uri}';
            if(uri != null && uri.length > 0) {
                window.location.href = uri;
            };
        });
    </script>

</head>
<body>
</body>
</html>
