<%--
  Created by IntelliJ IDEA.
  User: MisakaMikoto
  Date: 2017-08-16
  Time: 오후 2:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <body>
    <div ng-controller="loadCategoryController">
      <ul class="'list-group" ng-repeat="category in categories">
        <li class="list-group-item">
          <span class="label label-default label-pill pull-xs-right"> {{ category.count }} </span>
          <span>
            <a href="#"> {{ category.name }} </a>
          </span>
        </li>
      </ul>
    </div>
  </body>
</html>
