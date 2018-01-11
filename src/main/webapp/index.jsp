<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    
    <title>My JSP 'index.jsp' starting page</title>
  </head>
  
  <body>
     >>员工如下: <a href="add.jsp">新增员工</a><a href="fuzzyquery.jsp">模糊查询加分页</a>
   <table border=1>
    <tr>
     <td>工号</td> <td>姓名</td> <td>工资</td><td colspan="2" align="center">操作</td>
    </tr>
    <c:forEach items="${emps}" var="e">
     <tr>
     <td>${e.empno}</td> <td>${e.ename }</td> <td>${e.sal }</td>
     <td><a href="update.s?id=${e.empno}">修改</a></td>
     <td><a href="delEmp.s?id=${e.empno }">删除</a></td>
    </tr>
    </c:forEach>
   
   </table>
 <form action="upload.s" method="post" enctype="multipart/form-data" name="form1" id="form1">>
 文件上传
  <input type="file" name="file" id="fileField" />
 <br />
  <input type="submit" name="button" id="button" value="上传" />
 
 </form>
   
   
  </body>
</html>
