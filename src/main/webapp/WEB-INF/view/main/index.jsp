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

	<!-- combine js
	<script type="text/javascript" src="/js/combined.min.js"></script> -->

	<!-- combine css
	<link rel="stylesheet" type="text/css" href="/css/combined.min.css"> -->

	<!-- common css -->
	<link rel="stylesheet" type="text/css" href="/css/component/layout/title.css">
	<link rel="stylesheet" type="text/css" href="/css/component/layout/category.css">
	<link rel="stylesheet" type="text/css" href="/css/component/layout/contents.css">

	<!-- common request -->
	<script type="text/javascript" src="/js/common/request/commonRequest.js"></script>

	<!-- layout component -->
	<script type="text/javascript" src="/js/common/layout/layoutComponent.js"></script>

	<!-- component -->
	<script type="text/javascript" src="/js/component/layout/title.js"></script>
	<script type="text/javascript" src="/js/component/layout/category.js"></script>
	<script type="text/javascript" src="/js/component/layout/contents.js"></script>

	<!-- render -->
	<script type="text/javascript" src="/js/renderer/blog/render.js"></script>

	<!-- util -->
	<script type="text/javascript" src="/js/util/util.js"></script>

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
	</style>

	<script type="text/javascript">
		$(document).ready(function() {
			// create renderer
			var renderer = new BlogRenderer();

			// create title
			var title = new Title(renderer, renderer.renderTitle);
			title.type = 'GET';
			title.uri = '/title';
			title.callRest(title);

			// create category
			var category = new Category(renderer, renderer.renderCategory);
			category.type = 'GET';
			category.uri = '/category/list';
			category.callRest(category);

			// create contents
			var contents = new Contents(renderer, renderer.renderContentViewer);
			contents.type = 'GET';
			contents.uri = '/contents/newest';
			contents.callRest(contents);
		});
	</script>
</head>
<body>
	<div id="container">
		<div id="top"></div>
		<div id="left"></div>
		<div id="center"></div>
	</div>
</body>
</html>
