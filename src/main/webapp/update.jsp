<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
</head>

<body>
 <a href="showEmp.s">查询</a> 
<form id="form1" name="form1" method="post" action="updateemp.s">
  <p>修改员工:
   </p>
  <p>编号:
    <input type="text" name="empno" id="textfield8" readonly="readonly" value="${emp.empno }" />
  </p>
  <p>姓名:
    <input type="text" name="ename" id="textfield7" value="${emp.ename }" />
  </p>
  <p>工种:
    <input type="text" name="job" id="textfield6" value="${emp.job }"  />
  </p>
  <p>员工级别:
    <input type="text" name="mgr" id="textfield5" value="${emp.mgr }"  />
  </p>
  <p>入职日期:
    <input type="text" name="hiredate" id="textfield4" value="${emp.hiredate }"  />
  </p>
  <p>工资:
    <input type="text" name="sal" id="textfield3" value="${emp.sal }" />
  </p>
  <p>奖金：
    <input type="text" name="comm" id="textfield2" value="${emp.comm }"  />
  </p>
  <p>部门号:
    <input type="text" name="deptno" id="textfield" value="${emp.deptno }"  />
    <label>
    <input type="submit" name="button" id="button" value="修改" />
    </label>
  </p>
</form>

</body>
</html>
