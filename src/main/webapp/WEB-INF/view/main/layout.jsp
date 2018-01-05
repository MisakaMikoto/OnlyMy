<%--
  Created by IntelliJ IDEA.
  User: Misaka
  Date: 2016-03-08
  Time: 오후 4:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

	<!-- create angular app -->
	<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.4/angular.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/angular-ui-router/0.3.1/angular-ui-router.js"></script>

	<!-- jQuery -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>

	<!-- bootstrap -->
	<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
	<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">

	<%--<!-- util -->--%>
	<%--<script type="text/javascript" src="/js/util/util.js"></script>--%>

	<%--<!-- html import -->--%>
	<%--<script src="http://www.w3schools.com/lib/w3data.js"></script>--%>

	<%--<!-- clear -->--%>
	<%--<script type="text/javascript" src="/js/component/util/cleaner.js"></script>--%>

	<!-- app -->
	<script type="text/javascript" src="/js/module/app.js"></script>

	<!-- sockJS -->
	<script src="https://cdn.jsdelivr.net/npm/sockjs-client@1/dist/sockjs.min.js"></script>
	<!-- stomp -->
	<script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>

	<!-- common websocket -->
	<script type="text/javascript" src="/js/service/common/progressWebSocketService.js"></script>

	<!-- service -->
	<script type="text/javascript" src="/js/service/common/ajaxService.js"></script>

	<script type="text/javascript" src="/js/controller/main/layoutController.js"></script>
	<script type="text/javascript" src="/js/service/main/layoutService.js"></script>

	<script type="text/javascript" src="/js/controller/category/gameController.js"></script>
	<script type="text/javascript" src="/js/service/category/gameService.js"></script>
</head>

<body>
	<div ng-app="onlyMy">
		<div ng-include src="'/template/main/layout.html'"></div>
	</div>
</body>
</html>
