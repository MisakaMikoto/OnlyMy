<%--
  Created by IntelliJ IDEA.
  User: Misaka
  Date: 2016-04-18
  Time: 오후 2:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <!-- jQuery -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>

    <!-- common oauth request -->
    <script type="text/javascript" src="/js/common/request/commonRequest.js"></script>

    <!-- oauth -->
    <script type="text/javascript" src="/js/common/oauth/oauthComponent.js"></script>

    <!-- extends -->
    <script type="text/javascript" src="/js/common/inherit/commonExtends.js"></script>

    <!-- authorization -->
    <script type="text/javascript" src="/js/component/oauth/iam/methods/authorization.js"></script>

    <!-- implicit -->
    <script type="text/javascript" src="/js/component/oauth/iam/methods/implicit.js"></script>

    <!-- resource -->
    <script type="text/javascript" src="/js/component/oauth/iam/methods/resource.js"></script>

    <!-- client -->
    <script type="text/javascript" src="/js/component/oauth/iam/methods/client.js"></script>

    <!-- refresh -->
    <script type="text/javascript" src="/js/component/oauth/iam/methods/refresh.js"></script>

    <!-- information -->
    <script type="text/javascript" src="/js/component/oauth/iam/methods/information.js"></script>

    <!-- base64 -->
    <script type="text/javascript" src="/js/common/encryption/base64.js"></script>

    <title>IAM OAuth 2.0</title>

    <script type="text/javascript">
        function emptyInput() {
            var inputNodes = document.getElementsByTagName('input');
            for (var i = 0; i < inputNodes.length; i++) {
                inputNodes[i].value = '';
                inputNodes[i].innerHTML = '';
            }
        };

        function useAuthorization() {
            emptyInput();
            // authorization object extends OAuthComponent
            var commonExtends = new CommonExtends();
            var authorization = commonExtends.doExtends(new IAMAuthorization(), OAuthComponent.prototype);

            authorization.setClientId('${client_id}');
            authorization.setResponseType('code');
            // controllers request mapping uri
            authorization.setRedirectUri('http://localhost:8080/iamOAuth/receive/authorization/code');
            authorization.setScope('read');
            authorization.setState('');
            authorization.createUrl();
            authorization.prototype.verify();
        };

        function useImplicit() {
            emptyInput();
            // authorization object extends OAuthComponent
            var commonExtends = new CommonExtends();
            var implicit = commonExtends.doExtends(new IAMImplicit(), OAuthComponent.prototype);

            implicit.setClientId('${client_id}');
            implicit.setResponseType('code');
            // controllers request mapping uri
            implicit.setRedirectUri('http://localhost:8080/iamOAuth/receive/implicit/code');
            implicit.setScope('read');
            implicit.setState('');
            implicit.createUrl();
            implicit.prototype.verify();
        };

        function useResource() {
            // validate
            if (document.getElementById('userName').value.length == 0 || document.getElementById('userPassword').value.length == 0) {
                alert('need userName and userPassword');

            } else {
                var resource = new IAMResource();
                // base64 encode userName and userPassword
                var base64 = new Base64();
                var userName = base64.encode(document.getElementById('userName').value);
                var userPassword = base64.encode(document.getElementById('userPassword').value);

                emptyInput();

                resource.setClientId('${client_id}');
                resource.setScope('read');
                resource.setUserName(userName);
                resource.setUserPassword(userPassword)
                resource.callRest();
            }
        };

        function useClient() {
            emptyInput();
            var client = new IAMClient();

            client.setClientId('${client_id}');
            client.setScope('read');
            client.callRest();
        }
        ;

        function receiveTokenInfo() {
            var access_token = document.getElementById('accessToken').value;

            var information = new IAMInformation();
            information.setAccessToken(access_token);
            information.callRest();
        };

        function receiveRefreshToken() {
            var client_id = '${client_id}';
            var refresh_token = document.getElementById('refreshToken').value;

            var refresh = new IAMRefresh();
            refresh.setClientId(client_id);
            refresh.setRefreshToken(refresh_token);
            refresh.callRest();
        };

        function tyk() {
            window.loca
        };

        $(document).ready(function () {
            // authorization and implicit are returned index.jsp page.
            // but resource and client's view funcion processed commonOAuthRequest's load function.
            var authorizationToken = '${authorizationToken}';
            var implicitToken = '${implicitToken}';

            if (authorizationToken.length > 0) {
                var authorization = new IAMAuthorization();
                authorization.view(authorizationToken);

            } else if (implicitToken.length > 0) {
                var implicit = new IAMImplicit();
                implicit.view(implicitToken);

            } else {
                // otherwise...
                ;
            }
        });

    </script>
</head>
<br>

<div>
    <button id="authorization" onClick="javascript: useAuthorization()">Authorization Code</button>
    <button id="implicit" onClick="javascript: useImplicit()">Implicit Grant Flow</button>
    <button id="resource" onClick="javascript: useResource()">Resource Owner Password Credentials Flow</button>
    <button id="client" onClick="javascript: useClient()">Client Credentials Grant Flow</button>
</div>

</br>

<div>
    userName : <input type="text" id="userName">
    userPassword: <input type="text" id="userPassword">
</div>

</br>

<div>
    accessToken : <input type="text" id="accessToken">
    refreshToken : <input type="text" id="refreshToken">

    <button id="info" onClick="javascript: receiveTokenInfo()">Token Info</button>
    <button id="refresh" onClick="javascript: receiveRefreshToken()">Token Refresh</button>
</div>

</br>

<div>
    tokenInfo : <input type="text" id="tokenInfo">
    againRefreshToken : <input type="text" id="againRefreshToken">
</div>

</br>

<div>
    <button id="tyk" onClick="javascript: tyk()">tyk</button>
</div>
</body>
</html>
