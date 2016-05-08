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
            height: 800px;
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

    <script type="text/javascript">
        function callURI() {
            var uri = document.getElementById('uri').value;

            var commonRequest = new CommonRequest();
            commonRequest.setType();
            commonRequest.setUri(uri);

            var headers = $('#header').find('[id^="list_"]');
            for(var i = 0; i < headers.length; i++) {
                var key = $(this).find('[name="key"]').val();
                var value = $(this).find('[name="value"]').val();

                var header = '{' + key + ":" + value + '}';
                commonRequest.addHeader(header);

            }
            commonRequest.load(view);
        };

        function addHeader() {
            var list = $('[id^="list_"]').last();
            var clone = list.clone();

            var id = clone.attr('id');
            var name = id.split('_')[0];
            var count = Number(id.split('_')[1]);
            clone.attr('id', name + '_' + (count + 1));

            list.find('button').remove();
            list.after(clone);
        };

        function view(responseText) {
            document.getElementById('callResult').value = responseText;
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

            <button class="btn btn-default" onClick="javascript: callURI()"> CALL </button>
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
            <div class="form-group has-success has-feedback">
                <label class="col-sm-1 control-label" for="callResult"> Call Result </label>
                <div class="col-sm-4">
                    <textarea class="form-control" row="15" id="callResult" style="height: 150px;" disabled></textarea>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
