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
	<!-- jQuery -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>

	<!-- bootstrap -->
	<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
	<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">

	<!-- sockJS -->
	<script src="//cdn.jsdelivr.net/sockjs/1/sockjs.min.js"></script>
	<!-- stomp -->
	<script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>

	<!-- combine js
	<script type="text/javascript" src="/js/combined.min.js"></script> -->

	<!-- combine css
	<link rel="stylesheet" type="text/css" href="/css/combined.min.css"> -->

	<!-- common css -->
	<link rel="stylesheet" type="text/css" href="/css/component/layout/title.css">
	<link rel="stylesheet" type="text/css" href="/css/component/layout/category.css">
	<link rel="stylesheet" type="text/css" href="/css/component/layout/contents.css">
	<link rel="stylesheet" type="text/css" href="/css/component/mode/write.css">

	<!-- common request -->
	<script type="text/javascript" src="/js/common/request/commonRequest.js"></script>

	<!-- common websocket -->
	<script type="text/javascript" src="/js/common/websocket/commonWebSocket.js"></script>

	<!-- layout component -->
	<script type="text/javascript" src="/js/common/layout/layoutComponent.js"></script>

	<!-- component -->
	<script type="text/javascript" src="/js/component/layout/title.js"></script>
	<script type="text/javascript" src="/js/component/layout/category.js"></script>
	<script type="text/javascript" src="/js/component/layout/contents.js"></script>
	<script type="text/javascript" src="/js/component/mode/write.js"></script>

	<!-- render -->
	<script type="text/javascript" src="/js/renderer/blog/layout/render.js"></script>
	<script type="text/javascript" src="/js/renderer/blog/mode/render.js"></script>
	<script type="text/javascript" src="/js/renderer/blog/progress/render.js"></script>

	<!-- util -->
	<script type="text/javascript" src="/js/util/util.js"></script>

	<!-- html import -->
	<script src="http://www.w3schools.com/lib/w3data.js"></script>

	<!-- clear -->
	<script type="text/javascript" src="/js/component/util/cleaner.js"></script>

	<style type="text/css">
		#left {
			float: left;
			width: 19%;
			height: 100%;
			margin: 0px 10px 0px 0px;
			padding-left: 10px;
		}

		#center {
			display: inline-block;
			width: 79%;
			height: 100%;
			margin: 0px 0px 10px 0px;
		}

		#progress {
			width: 97%;
		}

	</style>

	<script type="text/javascript">
		$(document).ready(function() {
			// webSocket
			let webSocket = new CommonWebSocket();
			webSocket.endPoint = 'http://localhost:8080/webSocket/contents/upload/progress';
			webSocket.connect();


			// create renderer
			let renderer = new BlogLayoutRenderer();

			// create title
			let title = new Title(renderer, renderer.renderTitle);
			title.type = 'GET';
			title.uri = '/title';
			title.callRest(title);

			// create category
			let category = new Category(renderer, renderer.renderCategoryList);
			category.type = 'GET';
			category.uri = '/category/list';
			category.callRest(category);

			// create contents
			let contents = new Contents(renderer, renderer.renderContentViewer);
			contents.type = 'GET';
			contents.uri = '/contents/newest';
			contents.callRest(contents);
		});
	</script>
</head>
<body>
	<div id="progress" style="display: none">
		<progress id="progressBar" value="0"></progress>
		<span id="display"></span>
	</div>
	<div id="container">
		<div id="top"></div>
		<div id="left"></div>
		<div id="center"></div>
	</div>
</body>
</html>
