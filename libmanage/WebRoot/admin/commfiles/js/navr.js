// 导航栏配置文件
var outlookbar=new outlook();
var t;

t=outlookbar.addtitle('还借信息','还借信息',1)
outlookbar.additem('已借图书',t,'rbookhj.jsp')
outlookbar.additem('还书记录',t,'rbookhjl.jsp')


t=outlookbar.addtitle('个人信息','个人信息',1)
outlookbar.additem('基本资料管理',t,'rupdatesreader.jsp')
outlookbar.additem('修改登录密码',t,'pupdatepwd.jsp')

t=outlookbar.addtitle('信息查询','信息查询',1)
outlookbar.additem('图书查询',t,'booksck.jsp')
outlookbar.additem('图书收藏',t,'booksc.jsp')



