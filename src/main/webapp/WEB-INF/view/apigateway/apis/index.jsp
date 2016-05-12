<%--
  Created by IntelliJ IDEA.
  User: Misaka
  Date: 2016-05-12
  Time: 오전 11:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>APIGateway Test</title>

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
      height: 500px;
      margin: 0px 0px 10px 0px;
    }

    #verify {
      height: 10%;
      margin-top: 20px;
    }

    #header {
      height: 70%;
      margin-top: 20px;
    }

    #result{
      height: 20%;
      margin-top: 20px;
    }
  </style>

  <!-- jQuery -->
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>

  <!-- bootstrap -->
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">

  <!-- common request -->
  <script type="text/javascript" src="/js/common/request/commonRequest.js"></script>

  <!-- apigatewayComponent -->
  <script type="text/javascript" src="/js/common/apigateway/apigatewayComponent.js"></script>

  <!-- extends -->
  <script type="text/javascript" src="/js/common/inherit/commonExtends.js"></script>

  <!-- common request -->
  <script type="text/javascript" src="/js/common/request/commonRequest.js"></script>

  <!-- util -->
  <script type="text/javascript" src="/js/util/util.js"></script>

  <script type="text/javascript">
  </script>
</head>
<body>
<div id="container">
  <div id="top">
    <nav class="navbar navbar-default navbar-fixed-top" style="position: relative; width: 100%;">
      <H2 style="padding-left: 30px;"> APIGateway </H2>
    </nav>
  </div>
  <div id="left">
    <div class="list-group" id="methodList" style="margin-top: 20px;">
      <a href="#" class="list-group-item active"> APIs </a>
    </div>
  </div>
  <div id="center">
    <div id="verify">
      <div class="form-group has-success has-feedback">
        <label class="col-sm-1 control-label" for="uri"> URI </label>
        <div class="col-sm-4">
          <input type="text" class="form-control" id="uri">
        </div>
      </div>

      <button class="btn btn-default" onClick="javascript: callURI('GET')"> CALL(GET) </button>
      <button class="btn btn-default" onClick="javascript: callURI('POST')"> CALL(POST) </button>
    </div>
  </div>
</div>
</body>
</html>
