<%--
  Created by IntelliJ IDEA.
  User: Misaka
  Date: 2016-05-02
  Time: 오후 3:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>TYK Test</title>

    <!-- common request -->
    <script type="text/javascript" src="/js/common/request/commonRequest.js"></script>

    <!-- util -->
    <script type="text/javascript" src="/js/util/util.js"></script>

    <!-- Test -->
    <script type="text/javascript" src="/js/component/apigateway/tyk/test.js"></script>

    <script type="text/javascript">
        function tyk() {
            window.open('http://uengine.cloud.tyk.io/naver/', '_self');
//            var test = new Test();
//            var commonRequest = new CommonRequest();
//
//            commonRequest.setType('GET');
//            commonRequest.setUri('http://uengine.cloud.tyk.io/naver/');
//            commonRequest.load(test.view);
        };
    </script>
</head>
<body>
<div>
    <button id="tyk" onClick="javascript: tyk()">tyk</button>
</div>
</body>
</html>
