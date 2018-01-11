<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
<script type="text/javascript">
$(function(){
	//取出页码和总页数
	var pageno=$("#pn").html();
	var pz=$("#pz").html();
	//搜索
	$("#butfq").click(function(){
		var fq=$("#fq").val();
		location.href="fuzzyquery.s?pageno=1&fq="+fq;
	});
	//重置
	$("#cz").click(function(){
		location.href="fuzzyquery.s";
	});
	//首页
	$("#sy").click(function(){
		var fq=$("#fq").val();
		location.href="fuzzyquery.s?pageno=1&fq="+fq;
	});
	//上一页
	$("#syy").click(function(){
		var fq=$("#fq").val();
		location.href="fuzzyquery.s?pageno="+(pageno-1)+"&fq="+fq;
	});
	//下一页
	$("#xyy").click(function(){
		var fq=$("#fq").val();
		location.href="fuzzyquery.s?pageno="+(parseInt(pageno)+1)+"&fq="+fq;
	});
	//尾页
	$("#wy").click(function(){
		var fq=$("#fq").val();
		location.href="fuzzyquery.s?pageno="+pz+"&fq="+fq;
	});
	//刷新
	$("#sx").click(function(){
		var fq=$("#fq").val();
		location.href="fuzzyquery.s?pageno="+pageno+"&fq="+fq;
	});
	
})



</script>
</head>
<body>
模糊查询---》
  
  <input type="text"  id="fq" value="${fq }">
  <input type="button" id="butfq" value="搜索">
<br>
    >>模糊查询结果如下: 
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
 <div>	<p style="margin-left: 0px;">
   	<span id="sy"><input type="button" value="首页"/></span>
   	<span id="syy"><input type="button" value="上一页"/></span>
   	<span id="xyy"><input type="button" value="下一页"/></span>
   	<span id="wy"><input type="button" value="尾页"/></span>
   	<span id="sx"><input type="button" value="刷新"/></span>
   	   </p>	
    <p style="margin-top: 0px;" >第<span id="pn">${page.pageno}</span>页/总共<span id="pz">${page.totalpage}</span>页</p>  	
   </div>
</body>
</html>