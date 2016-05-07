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

    <script type="text/javascript">
        function callParentFunction() {
            // check methods
            var parent = opener;
            var parentDocument = parent.document;
            var targetMethod = parentDocument.getElementsByClassName('active')[0].getAttribute('id');

            if(targetMethod == IAMAuthorization.name) {
                parent.callAuthorization();

            } else if(targetMethod == IAMImplicit.name) {
                parent.callImplicit();

            } else if(targetMethod == IAMResource.name) {
                parent.callResource();

            } else if(targetMethod == IAMClient.name) {
                parent.callResource();
            }
        };

        $(document).ready(function () {
            callParentFunction();

            //window.opener = self;
            //window.close();
        });
    </script>

</head>
<body>
</body>
</html>
