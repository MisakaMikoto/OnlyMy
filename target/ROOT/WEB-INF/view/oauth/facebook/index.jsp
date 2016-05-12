<%--
  Created by IntelliJ IDEA.
  User: Misaka
  Date: 2016-04-18
  Time: 오후 2:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <!-- jQuery -->
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>

  <!-- common request -->
  <script type="text/javascript" src="/js/common/layout/layoutRequest.js"></script>

  <!-- facebookoauth -->
  <script type="text/javascript" src="/js/common/oauth/oauthComponent.js"></script>

  <title>Facebook API OAuth 2.0</title>

  <script type="text/javascript">
    function authorize() {
        // authorization object extends OAuthComponent
        var commonExtends = new CommonExtends();
        var authorization = commonExtends.doExtends(new FacebookAuthorization(), OAuthComponent.prototype);

        authorization.setClientId('${client_id}');
        authorization.setResponseType('code');
        // controllers request mapping uri
        authorization.setRedirectUri('http://localhost:8080/facebookOAuth/receive/authorization/code');
        authorization.setScope('read');
        authorization.setState('');
        authorization.createUrl();
        authorization.prototype.verify();
    };
  </script>
</head>
<body>
  <div>
    <input type="text" id="accessToken"/>
    <button id="submit" onClick="javascript:authorize()">코드 발행 받기</button>
  </div>

  <div id="code"></div>
  <div id="acesstoken"></div>
</body>
</html>
