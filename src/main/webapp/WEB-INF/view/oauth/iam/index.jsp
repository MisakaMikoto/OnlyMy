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

        #user {
            height: 50px;
            display: none;
        }

        #verify {
            height: 50px;
            margin-top: 20px;
        }

        #tokenInformation {
            height: 150px;
            margin-top: 20px;
        }

        #refreshTokenAgain {
            height: 150px;
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

    <!-- util -->
    <script type="text/javascript" src="/js/util/util.js"></script>

    <title>IAM OAuth 2.0</title>

    <script type="text/javascript">
        function initCenterLayoutConfiguration(thisElement, isResource) {
            emptyData();

            var targetMethod = '';
            // select view
            if(thisElement.getAttribute('class').indexOf('active') == -1) {
                var aNodes = document.getElementById('methodList').getElementsByTagName('a');

                for(var i = 0; i < aNodes.length; i++) {
                    if(aNodes[i].getAttribute('class').indexOf('active') > -1) {
                        aNodes[i].setAttribute('class', 'list-group-item');
                        break;
                    }
                };
                thisElement.setAttribute('class', 'list-group-item active');
                targetMethod = thisElement.getAttribute('id');
            }

            // resource view
            if(isResource) {
                document.getElementById('user').style.display = '';

            } else {
                document.getElementById('user').style.display = 'none';
            }

            // button event handler setting
            document.getElementById('verifyButton').setAttribute('onClick', 'call' + targetMethod + '()');
        };

        function emptyData() {
            var inputNodes = document.getElementsByTagName('input');
            for (var i = 0; i < inputNodes.length; i++) {
                inputNodes[i].value = '';
                inputNodes[i].innerHTML = '';
            }

            var textAreaNodes = document.getElementsByTagName('textarea');
            for (var i = 0; i < textAreaNodes.length; i++) {
                textAreaNodes[i].value = '';
                textAreaNodes[i].innerHTML = '';
            }

        };

        function callIAMAuthorization() {
            // authorization object extends OAuthComponent
            var commonExtends = new CommonExtends();
            var authorization = commonExtends.doExtends(new IAMAuthorization(), OAuthComponent.prototype);

            authorization.setClientId('${client_id}');
            authorization.setResponseType('code');
            // controllers request mapping uri
            authorization.setRedirectUri('http://localhost:8080/oauth/receive/authorization/code');
            authorization.setScope('read');
            authorization.setState('');
            authorization.createUri();
            authorization.prototype.verify();
        };

        function callIAMImplicit() {
            // authorization object extends OAuthComponent
            var commonExtends = new CommonExtends();
            var implicit = commonExtends.doExtends(new IAMImplicit(), OAuthComponent.prototype);

            implicit.setClientId('${client_id}');
            implicit.setResponseType('code');
            // controllers request mapping uri
            implicit.setRedirectUri('http://localhost:8080/oauth/receive/implicit/code');
            implicit.setScope('read');
            implicit.setState('');
            implicit.createUri();
            implicit.prototype.verify();
        };

        function callIAMResource() {
            // validate
            if (document.getElementById('userName').value.length == 0 || document.getElementById('userPassword').value.length == 0) {
                alert('need userName and userPassword');

            } else {
                // extends
                var commonExtends = new CommonExtends();
                var resource = commonExtends.doExtends(new IAMResource(), OAuthComponent.prototype);
                // base64 encode userName and userPassword
                var base64 = new Base64();
                var userName = base64.encode(document.getElementById('userName').value);
                var userPassword = base64.encode(document.getElementById('userPassword').value);

                resource.setClientId('${client_id}');
                resource.setScope('read');
                resource.setUserName(userName);
                resource.setUserPassword(userPassword);
                var parameter = resource.createParameter();

                resource.prototype.setType('POST');
                resource.prototype.setUri('/oauth/receive/resource/token');
                resource.prototype.setParameter(parameter);
                resource.prototype.callRest(resource.view);
            }
        };

        function callIAMClient() {
            // extends
            var commonExtends = new CommonExtends();
            var client = commonExtends.doExtends(new IAMClient(), OAuthComponent.prototype);

            client.setClientId('${client_id}');
            client.setScope('read');
            var parameter = client.createParameter();

            client.prototype.setType('POST');
            client.prototype.setUri('/oauth/receive/client/token');
            client.prototype.setParameter(parameter);
            client.prototype.callRest(client.view);
        };

        function callTokenInfo() {
            var access_token = document.getElementById('accessToken').value;

            // extends
            var commonExtends = new CommonExtends();
            var information = commonExtends.doExtends(new IAMInformation(), OAuthComponent.prototype);

            information.setAccessToken(access_token);
            var parameter = information.createParameter();

            information.prototype.setType('POST');
            information.prototype.setUri('/oauth/receive/tokenInfo');
            information.prototype.setParameter(parameter);
            information.prototype.callRest(information.view);

        };

        function callRefreshToken() {
            var client_id = '${client_id}';
            var refresh_token = document.getElementById('refreshToken').value;

            // extends
            var commonExtends = new CommonExtends();
            var refresh = commonExtends.doExtends(new IAMRefresh(), OAuthComponent.prototype);

            refresh.setClientId(client_id);
            refresh.setRefreshToken(refresh_token);
            var parameter = refresh.createParameter();

            refresh.prototype.setType('POST');
            refresh.prototype.setUri('/oauth/receive/refreshToken');
            refresh.prototype.setParameter(parameter);
            refresh.prototype.callRest(refresh.view);

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
<body>
<div id="container">
    <div id="top">
        <nav class="navbar navbar-default navbar-fixed-top" style="position: relative; width: 100%;">
            <H2 style="padding-left: 30px;"> IAM Sample </H2>
        </nav>
    </div>
    <div id="left">
        <div class="list-group" id="methodList" style="margin-top: 20px;">
            <a href="#" onClick="javascript: initCenterLayoutConfiguration(this, false)" class="list-group-item active" id="IAMAuthorization">Authorization Code</a>
            <a href="#" onClick="javascript: initCenterLayoutConfiguration(this, false)" class="list-group-item" id="IAMImplicit">Implicit Grant Flow</a>
            <a href="#" onClick="javascript: initCenterLayoutConfiguration(this, true)" class="list-group-item" id="IAMResource">Resource Owner Password Credentials Flow</a>
            <a href="#" onClick="javascript: initCenterLayoutConfiguration(this, false)" class="list-group-item" id="IAMClient">Client Credentials Grant Flow</a>
        </div>
    </div>
    <div id="center">
        <div id="user">
            <div class="form-group has-success has-feedback">
                <label class="col-sm-1 control-label" for="userName">User Name</label>
                <div class="col-sm-4">
                    <input type="text" class="form-control" id="userName">
                </div>
            </div>

            <div class="form-group has-success has-feedback">
                <label class="col-sm-1 control-label" for="userPassword">User Password</label>
                <div class="col-sm-4">
                    <input type="password" class="form-control" id="userPassword">
                </div>
            </div>
        </div>

        <div id="verify" style="">
            <div class="form-group has-success has-feedback">
                <label class="col-sm-1 control-label" for="accessToken">Access Token</label>
                <div class="col-sm-4">
                    <input type="text" class="form-control" id="accessToken" disabled>
                </div>
            </div>

            <div class="form-group has-success has-feedback">
                <label class="col-sm-1 control-label" for="refreshToken">Refresh Token</label>
                <div class="col-sm-4">
                    <input type="text" class="form-control" id="refreshToken" disabled>
                </div>
            </div>
            <button class="btn btn-default" id="verifyButton" onClick="javascript: callIAMAuthorization()"> GET </button>
        </div>

        <div id="tokenInformation">
            <div class="form-group has-success has-feedback">
                <label class="col-sm-1 control-label" for="tokenInfo">Token Information</label>
                <div class="col-sm-4">
                    <textarea class="form-control" row="15" id="tokenInfo" style="height: 150px;" disabled></textarea>
                </div>
            </div>
            <button class="btn btn-default" onClick="javascript: callTokenInfo()"> GET </button>
        </div>

        <div id="refreshTokenAgain">
            <div class="form-group has-success has-feedback">
                <label class="col-sm-1 control-label" for="againRefreshToken">Refresh Token</label>
                <div class="col-sm-4">
                    <textarea class="form-control" row="15" id="againRefreshToken" style="height: 150px" disabled></textarea>
                </div>
            </div>
            <button class="btn btn-default" onClick="javascript: callRefreshToken()"> GET </button>
        </div>
    </div>
</div>

<div>
    <form name="form">
        <input type=text id="uri" name="uri"><br>
    </form>
</div>

</body>
</html>
