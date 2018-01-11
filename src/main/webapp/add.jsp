<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
</head>

<body>
 <a href="showEmp.s">查询</a> 
<form id="form1" name="form1" method="post" action="addEmp.s">
  <p>添加员工:
   </p>
  <p>编号:
    <input type="text" name="empno" id="textfield8" />
  </p>
  <p>姓名:
    <input type="text" name="ename" id="textfield7" />
  </p>
  <p>工种:
    <input type="text" name="job" id="textfield6" />
  </p>
  <p>员工级别:
    <input type="text" name="mgr" id="textfield5" />
  </p>
  <p>入职日期:
    <input type="text" name="hiredate" id="textfield4" />
  </p>
  <p>工资:
    <input type="text" name="sal" id="textfield3" />
  </p>
  <p>奖金：
    <input type="text" name="comm" id="textfield2" />
  </p>
  <p>部门号:
    <input type="text" name="deptno" id="textfield" />
    <label>
    <input type="submit" name="button" id="button" value="添加" />
    </label>
  </p>
</form>

</body>
</html>
