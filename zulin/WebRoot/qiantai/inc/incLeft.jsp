<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page isELIgnored="false" %> 

<%
   String path = request.getContextPath();
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
      <meta http-equiv="pragma" content="no-cache">
	  <meta http-equiv="cache-control" content="no-cache">
	  <meta http-equiv="expires" content="0">    
	  <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	  <meta http-equiv="description" content="This is my page">
      
      
      <script type='text/javascript' src='<%=path %>/dwr/interface/loginService.js'></script>
      <script type='text/javascript' src='<%=path %>/dwr/engine.js'></script>
      <script type='text/javascript' src='<%=path %>/dwr/util.js'></script>
     
      <script type="text/javascript">
            function check()
			{                                                                                         
			     if(document.ThisForm.loginname.value=="")
				 {
				 	alert("请输入账号");
					return false;
				 }
				 if(document.ThisForm.loginpw.value=="")
				 {
				 	alert("请输入密码");
					return false;
				 }
				 document.getElementById("indicator").style.display="block";
				 loginService.login(document.ThisForm.loginname.value,document.ThisForm.loginpw.value,3,callback);
			}
		
			function callback(data)
			{
			    document.getElementById("indicator").style.display="none";
			    if(data=="no")
			    {
			        alert("账号或密码错误");
			    }
			    if(data=="yes")
			    {   
			        alert("登陆成功");
			        var url="<%=path %>/qiantai/default.jsp";
			        window.location.href=url;
			    }
			}
	        
	        
	        
	        function userLogout()
	        {
	            document.getElementById("indicator1").style.display="block";
	            loginService.userLogout(callback1);
	        }
	        function callback1(data)
			{
			    document.getElementById("indicator1").style.display="none";
			    if(data=="no")
			    {
			        alert("系统错误，请联系管理员");
			    }
			    if(data=="yes")
			    {   
			        alert("安全退出系统");
			        var url="<%=path %>/qiantai/default.jsp";
			        window.location.href=url;
			    }
			    
			}
			
			
			function c()
	        {
	           	document.form1.submit();
	        }
	        
	        function cheZhuChe(){
	        	document.form2.submit();	        
	        }
	        
	        // view contract list
	        function contractView() {
	        	document.form3.submit();
	        }
	        
	        // view fund list
	        function fundView() {	
	        	document.form4.submit();
	        }
      </script>
  </head>
  
  <body>
        <TABLE border=0 cellSpacing=0 cellPadding=0>
		        <!--  -->
		        <TR>
		          <TD height="27" style="background-image: url('<%=path %>/img/gonggao.gif'); background-repeat : repeat-xy;">
		             &nbsp;&nbsp;&nbsp;&nbsp;<font color="white" style="font-weight: 700">>>系统公告</font>
		          </TD>
		        </TR>
		        <TR>
		          <TD class=b>
		            <TABLE border=0 cellSpacing=0 cellPadding=0 width=178 align=center height=100%>
		              <TR>
		                <TD width=178 align=center>
		                    <MARQUEE onmouseover=this.stop() onmouseout=this.start() direction=up height=90 width=160 scrollAmount=3 scrollDelay=166 border="0">本站于2016年04月01日正式开通,欢迎大家访问本网站,各种户型出租房应有尽有，欢迎租赁。</MARQUEE>
		                </TD>
		              </TR>
		            </TABLE>
		         </TD>
		        </TR>
		        <tr><td height="20"></td></tr>
		        <!-- -->
		        
		        
		        <!--  -->
		        <TR>
		          <TD height="27" style="background-image: url('<%=path %>/img/gonggao.gif'); background-repeat : repeat-xy;">
		             &nbsp;&nbsp;&nbsp;&nbsp;<font color="white" style="font-weight: 700">>>租房查询</font>
		          </TD>
		        </TR>
		        <TR>
		          <TD class=b>
		            <TABLE border=0 cellSpacing=0 cellPadding=0 width=178 align=center height=100%>
		              <TR>
		                <TD width=178 align=center>
		                     <form action="<%=path %>/cheSeaQian.action" name="form1" method="post">
						        <table>
						        <tr>
						                <td>
						              	区域
						                </td>
						                <td>
						              	<select name="quyu" >
						    <option value="">--选择区域--</option>
							<option value="鼓楼区">鼓楼区</option>
							<option value="建邺区">建邺区</option>
							<option value="栖霞区">栖霞区</option>
							<option value="下关区">下关区</option>
							<option value="六合区">六合区</option>
							<option value="秦淮区">秦淮区</option>
							<option value="江宁区">江宁区</option>
							<option value="浦口区">浦口区</option>
							</select>
						                </td>
						            </tr>
						             <tr>
						                <td>
						              	户型
						                </td>
						                <td>
						                <select name="pinpai" >
						                <option value="">--选择户型--</option>
							<option value="一室一厅">一室一厅</option>
							<option value="两室一厅">两室一厅</option>
							<option value="三室一厅">三室一厅</option>
							<option value="三室两厅">三室两厅</option>
							<option value="四室一厅">四室一厅</option>
							<option value="四室两厅">四室两厅</option>
							<option value="五室两厅">五室两厅</option>
							<option value="其他">其他</option>
							</select>
						                </td>
						            </tr>
						             <tr>
						                <td>
						              	 类型
						                </td>
						                <td>
						                      <select name="ttype" >
						                       <option value="">--选择类型--</option>
							<option value="公寓">公寓</option>
							<option value="牌楼">牌楼</option>
							<option value="别墅">别墅</option>
							<option value="农居房">农居房</option>
							<option value="其他">其他</option>
							</select>
						                </td>
						            </tr>
						               <tr>
						                <td>
						              	 装修
						                </td>
						                <td>
						          <select name="zhxiu" >
						          <option value="">--选择装修--</option>
							<option value="普通">普通</option>
							<option value="中等">中等</option>
							<option value="精装">精装</option>
							<option value="豪华装">豪华装</option>
							<option value="其他">其他</option>
							</select>
						                </td>
						            </tr>
						            <tr>
						                <td>
						                    标题
						                </td>
						                <td><input type="text" name="chexing" size="14"/>
						                </td>
						            </tr>
						               <tr>
						                <td colspan="2">
						                    <input type="button" value="查询" onclick="c()" style="border:#ccc 1px solid; background-color:#FFFFFF; font-size:12px; padding-top:3px;"/>
						                </td>
						            </tr>
						        </table>
						     </form>
		                </TD>
		              </TR>
		            </TABLE>
		         </TD>
		        </TR>
		        <tr><td height="5"></td></tr>
		        <!-- -->
		        
		        <TR>
		          <TD class=b vAlign=top>
		            <TABLE border=0 cellSpacing=0 cellPadding=0 width=178>
		              <TR>
				          <TD height="27" style="background-image: url('<%=path %>/img/gonggao.gif'); background-repeat : repeat-xy;">
				             &nbsp;&nbsp;&nbsp;&nbsp;<font color="white" style="font-weight: 700">>>友情连接</font>
				          </TD>
				      </TR>
		            </TABLE>
		            <TABLE border=0 cellSpacing=3 cellPadding=3 width=178>
		              <TR>
		                <TD height=6 vAlign=middle width="100%">
		                    
		                </TD>
		              </TR>
		              <c:forEach items="${sessionScope.lianjieList}" var="lianjie">
		              <TR>
		                <TD height=22 vAlign=middle width="100%">
		                    <FONT color=#ff0000><IMG src="<%=path %>/img/dot.gif" width=9 height=9>
		                       &nbsp;&nbsp;&nbsp;  
		                       <a href="${lianjie.url}" target="_blank"><FONT color=#ff0000>${lianjie.name}</FONT></A>
		                    </FONT>
		                </TD>
		              </TR>
		              </c:forEach>
		            </TABLE>
		          </TD>
		        </TR>
		        <tr><td height="10"></td></tr>		
		        
		        <!-- -->
		        <TR>
		          <TD class=b vAlign=top>
		            <TABLE border=0 cellSpacing=0 cellPadding=0 width=178>
		              <TR>
				          <TD height="27" style="background-image: url('<%=path %>/img/gonggao.gif'); background-repeat : repeat-xy;">
				             &nbsp;&nbsp;&nbsp;&nbsp;<font color="white" style="font-weight: 700">>>登录窗口</font>
				          </TD>
				      </TR>
		            </TABLE>
		            <TABLE border=0 cellSpacing=3 cellPadding=3 width=178 height=22>
		              <TR>
		                <TD height=22 vAlign=middle width="100%">
		                    <c:if test="${sessionScope.user==null}">
		                    <form action="<%=path %>/user?type=userLogin" name="ThisForm" method="post">
							      <table cellspacing="0" cellpadding="0" width="98%" align="center" border="0" height="60">
							          <tr>
							            <td align="center" colspan="2" height="10"></td>
							          </tr>
							          <tr>
							            <td align="right" width="31%" height="30" style="font-size: 11px;">账号：</td>
							            <td align="left" width="69%"><input name="loginname" type="text" style="width: 100px;"/></td>
							          </tr>
							          <tr>
							            <td align="right" height="30" style="font-size: 11px;">密码：</td>
							            <td align="left"><input type="password" style="width: 100px;" name="loginpw"/></td>
							          </tr>
							          <tr>
							            <td align="center" colspan="2" height="10"></td>
							          </tr>
							          <tr>
							            <td align="center" colspan="2" height="30">
							               &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							               <input type="button" value="登  录" onclick="check()" style="border:#ccc 1px solid; background-color:#FFFFFF; font-size:12px; padding-top:3px;" />
										   &nbsp;
										   <input type="button" value="注  册" onclick="userReg()" style="border:#ccc 1px solid; background-color:#FFFFFF; font-size:12px; padding-top:3px;" />
							               &nbsp;<br><a href="#" onclick="admin()" style="color: red">房东|管理员登录</a>
							               <img id="indicator" src="<%=path %>/img/loading.gif" style="display:none"/>
							            </td>
							          </tr>
							      </table>
						    </form>
						    </c:if>
						    <c:if test="${sessionScope.user!=null}">
						        <br/>
									欢迎您：${sessionScope.user.userName } 
							    	<a href="#" onclick="userLogout()">安全退出</a> &nbsp;&nbsp;&nbsp;&nbsp;
							    	<img id="indicator1" src="<%=path %>/img/loading.gif" style="display:none"/>
							    <br/>
							    <form action="<%=path %>/cheZhuChe.action" name="form2" method="post">
							    	<a href="#" onclick="cheZhuChe()" style="color: red">管理我的租房信息</a>
							    </form>
							    <form action="<%=path %>/contractView.action" name="form3" method="post">
							    	<a href="#" onclick="contractView()" style="color: red">查看我的合同信息</a>
							    </form>
							    <form action="<%=path %>/fundView.action" name="form4" method="post">
							    	<a href="#" onclick="fundView()" style="color: red">查看我的资金信息</a>
							    </form>
							    <br/><br/>
							</c:if>
		                </TD>
		              </TR>
		            </TABLE>
		          </TD>
		        </TR>
		</TABLE> 
  </body>
</html>
