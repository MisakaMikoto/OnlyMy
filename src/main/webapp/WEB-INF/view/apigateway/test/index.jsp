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
        function clean() {
            $('#result').find('input').each(function() {
                $(this).val('');
            });

            $('#result').find('textarea').each(function() {
                $(this).val('');
            });
        };

        function callURI(type) {
            var uri = document.getElementById('uri').value;

            if(uri != null && uri.length > 0) {
                // extends
                var commonExtends = new CommonExtends();
                var apigatewayComponent = commonExtends.doExtends(new ApigatewayComponent(), CommonRequest.prototype);

                apigatewayComponent.prototype.setType(type);
                apigatewayComponent.prototype.setUri('/apiGateway/call/' + type.toLowerCase());
                apigatewayComponent.prototype.setParameter('uri=' + uri);

                $('#header').find('[id^="list_"]').each(function () {
                    var key = $(this).find('[name="key"]').val();
                    var value = $(this).find('[name="value"]').val();

                    if (key.length > 0 && value.length > 0) {
                        apigatewayComponent.prototype.addHeader(key, value);
                    }

                });
                apigatewayComponent.callRest(view)

            } else {
                alert('write uri');
            }
        };

        function addHeader() {
            if($('[id^="list_"]').length < 8) {
                var list = $('[id^="list_"]').last();
                var clone = list.clone();

                var id = clone.attr('id');
                var name = id.split('_')[0];
                var count = Number(id.split('_')[1]);

                clone.attr('id', name + '_' + (count + 1));
                clone.find('[name="key"]').val('');
                clone.find('[name="value"]').val('');

                list.find('button').remove();
                list.after(clone);

            } else {
                alert('dont add more');
            }
        };

        function view(xmlHttpRequest) {
            var responseText = xmlHttpRequest.responseText;
            var responseTextJSON = JSON.parse(responseText);

            document.getElementById('status').value = responseTextJSON.responseCode;
            document.getElementById('responseText').value = responseTextJSON.responseText;
            document.getElementById('responseHeader').value = responseTextJSON.responseHeaders;
        };
    </script>
</head>
<body>
<div id="container">
    <div id="top">
        <nav class="navbar navbar-default navbar-fixed-top" style="position: relative; width: 100%;">
            <H2 style="padding-left: 30px;"> APIGateway Sample </H2>
        </nav>
    </div>
    <div id="left">
        <div class="list-group" id="methodList" style="margin-top: 20px;">
            <a href="#" class="list-group-item active"> Call API </a>
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

        <div id="header">
            <div id="list_0" style="height: 25px;">
                <div class="form-group has-success has-feedback">
                    <label class="col-sm-1 control-label"> KEY </label>
                    <div class="col-sm-2">
                        <input type="text" class="form-control" name="key">
                    </div>
                </div>

                <div class="form-group has-success has-feedback">
                    <label class="col-sm-1 control-label"> VALUE </label>
                    <div class="col-sm-2">
                        <input type="text" class="form-control" name="value">
                    </div>
                </div>

                <button class="btn btn-default" id="addButton" onClick="javascript: addHeader()"> ADD </button>
            </div>
        </div>

        <div id="result">
            <div id="responseStatus" style="height: 50px;">
                <div class="form-group has-success has-feedback" style="height: 25px;">
                    <label class="col-sm-1 control-label" for="status"> RESPONSE STATUS </label>
                    <div class="col-sm-2">
                        <input type="text" class="form-control" id="status" disabled>
                    </div>
                    <button class="btn btn-default" onClick="javascript: clean()"> RESPONSE CLEAN </button>
                </div>
            </div>

            <div id="responseDetail">
                <div class="form-group has-success has-feedback">
                    <label class="col-sm-1 control-label" for="responseText"> RESPONSE TEXT </label>
                    <div class="col-sm-4">
                        <textarea class="form-control" row="15" id="responseText" style="height: 150px;" disabled></textarea>
                    </div>
                </div>

                <div class="form-group has-success has-feedback">
                    <label class="col-sm-1 control-label" for="responseHeader"> RESPONSE HEADERS </label>
                    <div class="col-sm-4">
                        <textarea class="form-control" row="15" id="responseHeader" style="height: 150px;" disabled></textarea>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
