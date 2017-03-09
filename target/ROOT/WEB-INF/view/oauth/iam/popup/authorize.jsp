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

    <!-- oauth -->
    <script type="text/javascript" src="/js/common/oauth/oauthComponent.js"></script>

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

    <!-- OAuth Renderer -->
    <script type="text/javascript" src="/js/renderer/oauth/render.js"></script>

    <script type="text/javascript">
        $(document).ready(function () {
            let uri = '${uri}';
            let authorizationToken = '${authorizationToken}';
            let implicitToken = '${implicitToken}';

            if(uri != null && uri.length > 0) {
                window.location.href = uri;
            };

            if(uri.length == 0 && (authorizationToken != null && authorizationToken.length > 0)) {
                let renderer = new OAuthRenderer();
                renderer.renderAuthorization(authorizationToken);
            }

            if(uri.length == 0 && (implicitToken != null && implicitToken.length > 0)) {
                let renderer = new OAuthRenderer();
                renderer.renderImplicit(implicitToken);
            }
        });
    </script>

</head>
<body>
</body>
</html>
