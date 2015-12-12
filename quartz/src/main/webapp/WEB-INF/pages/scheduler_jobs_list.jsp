<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
   String ctx = request.getContextPath();
    request.setAttribute("ctx",ctx);
%>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>定时任务管理页面</title>
    <link  href="${ctx}/js/bootstrap-3.3.5/css/bootstrap.min.css" rel="stylesheet" type="text/css"  />
    <script type="text/javascript" src="${ctx}/js/jquery-1.11.3.min.js"></script>
    <script type="text/javascript" src="${ctx}/js/bootstrap-3.3.5/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="${ctx}/js/quartz_manager.js"></script>
    <style type="text/css">
        .circle_red {
            background: none repeat scroll 0 0 #FF3300;
            border-radius: 10px 10px 10px 10px;
            color: #FFFFFF;
            padding:2px 10px;
        }
        .timezone-box {
            margin: 0 auto;
            width: 240px;
        }
        .circle_green {
            background: none repeat scroll 0 0 green;
            border-radius: 10px 10px 10px 10px;
            color: #FFFFFF;
            padding:2px 10px;
        }
        td , th{
            text-align: center;
        }
        .timezone{
             float: left;
        }
        .table input{
            width: 30px;
        }
    </style>
    <script type="text/javascript">
        var ctx = "${ctx}";
    </script>
</head>
<body>

<div class="container-fluid">
    <!-- 查询面板开始 -->
    <div class="panel panel-default" style="margin-top: 10px">
        <%--<div class="panel-heading">Panel heading without title</div>--%>
        <div class="panel-body">
            <form class="form-inline">
                <div class="form-group">
                    <label for="code">任务编码：</label>
                    <input type="text" class="form-control" name="code" id="code" placeholder="">
                </div>
                <div class="form-group">
                    <label for="jobName">任务名称：</label>
                    <input type="email" class="form-control" name="jobName" id="jobName" placeholder="">
                </div>
                <button type="submit" class="btn btn-primary" style="width: 100px"><i class="glyphicon glyphicon-search"></i> 查&nbsp;询</button>
            </form>
        </div>
    </div>
    <!-- 查询面板结束 -->

    <!-- 表格开始   -->
    <div class="panel panel-default">
        <table class="table table-striped table-bordered">
            <th>序号</th>
            <th>任务ID</th>
            <th>任务名称</th>
            <th>表达式</th>
            <th>当前状态</th>
            <th>创建时间</th>
            <th>修改时间</th>
            <th>操作</th>
            <c:forEach items="${jobs}" var="j" varStatus="index">
                 <tr>
                    <td style="vertical-align: inherit">${index.count}</td>
                     <td style="vertical-align: inherit">
                         <%--${j.code}--%>
                     </td>
                     <td style="vertical-align: inherit">${j.jobName}</td>
                     <td style="vertical-align: inherit">
                         <div class="timezone-box" style="width: 300px">
                                     <div class="timezone">
                                         <div class="tip">秒</div>
                                         <div><input name="second" value="${j.second}" /></div>
                                     </div>
                                     <div class="timezone">-</div>
                                     <div class="timezone">
                                         <div class="tip">分</div>
                                         <div><input name="minute" value="${j.minute}" /></div>
                                     </div>
                                     <div class="timezone">-</div>
                                     <div class="timezone">
                                         <div class="tip">时</div>
                                         <div><input name="hour" value="${j.hour}" /></div>
                                     </div>
                                     <div class="timezone">-</div>
                                     <div class="timezone">
                                         <div class="tip">天</div>
                                         <div><input name="day" value="${j.day}" /></div>
                                     </div>
                                     <div class="timezone">-</div>
                                     <div class="timezone">
                                         <div class="tip">月</div>
                                         <div><input name="month" value="${j.month}" /></div>
                                     </div>
                                     <div class="timezone">-</div>
                                     <div class="timezone">
                                         <div class="tip">周</div>
                                         <div><input name="week" value="${j.week}" /></div>
                                     </div>
                                     <div class="timezone">-</div>
                                     <div class="timezone">
                                         <div class="tip">年</div>
                                         <div><input name="year" value="${j.year}" /></div>
                                     </div>
                             <div class="timezone" style="margin-top: 12px;margin-left: 5px">
                                 <button type="button" sid="${j.id}"  id="resetCron" class="btn btn-default">保存</button>
                             </div>
                         </div>



                     </td>
                     <td style="vertical-align: inherit;width: 100px">
                         <c:if test="${j.status==0}">
                             <span class="circle_green" title="启用状态"></span>
                         </c:if>
                         <c:if test="${j.status==1}">
                              <span class="circle_red" title="禁用状态"></span>
                         </c:if>
                     </td>
                     <td style="vertical-align: inherit">
                         <fmt:formatDate value="${j.createTime}" pattern="yyyy-MM-dd HH:mm:ss" />
                     </td>
                     <td style="vertical-align: inherit">
                         <fmt:formatDate value="${j.updateTime}" pattern="yyyy-MM-dd HH:mm:ss" />
                     </td>
                     <td style="vertical-align: inherit">
                         <c:if test="${j.status==0}">
                              <%--<i class="icon-off"></i> <span>停止</span>--%>
                             <a class="btn btn-default" style="display: inline" href="javascript:void(0)" onclick="stop(this)" sid="${j.id}" status="${j.status}"><i class="glyphicon glyphicon-off"></i> 停止</a>
                         </c:if>
                         <c:if test="${j.status==1}">
                             <a class="btn btn-default" style="display: inline" href="javascript:void(0)" onclick="stop(this)" sid="${j.id}" status="${j.status}"><i class="glyphicon glyphicon-off"></i> 启用</a>
                         </c:if>

                     </td>
                 </tr>
            </c:forEach>
        </table>
    </div>



</div>






</body>
</html>
