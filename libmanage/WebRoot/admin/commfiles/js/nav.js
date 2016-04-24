// 导航栏配置文件
var outlookbar=new outlook();
var t;

t=outlookbar.addtitle('业务信息','业务信息',1)
outlookbar.additem('图书还借',t,'/libmanage/bookhj?operate=list')
outlookbar.additem('还书记录',t,'bookhjl.jsp')


t=outlookbar.addtitle('系统管理','系统管理',1)
outlookbar.additem('添加管理员',t,'addsysusers.jsp')
outlookbar.additem('管理员维护',t,'sysusers.jsp')


t=outlookbar.addtitle('属性配置','属性配置',1)
outlookbar.additem('出版社管理',t,'cbsgl.jsp')
outlookbar.additem('图书类别管理',t,'tslbgl.jsp')
outlookbar.additem('学历管理',t,'xlgl.jsp')
outlookbar.additem('职业管理',t,'zygl.jsp')


t=outlookbar.addtitle('个人信息','个人信息',1)
outlookbar.additem('基本资料管理',t,'pupdatesysusers.jsp')
outlookbar.additem('修改登录密码',t,'updatepwd.jsp')

t=outlookbar.addtitle('统计分析','统计分析',1)
outlookbar.additem('图书查询',t,'abooksck.jsp')
outlookbar.additem('读者查询',t,'sreaderck.jsp')
outlookbar.additem('借出查询',t,'bookhjlck.jsp')
outlookbar.additem('收藏查询',t,'abooksc.jsp')
outlookbar.additem('图书借阅排行',t,'jyph.jsp')
outlookbar.additem('记者借阅排行',t,'jyph-reader.jsp')
outlookbar.additem('图书总数量',t,'bookcount.jsp')

t=outlookbar.addtitle('信息管理','信息管理',1)
outlookbar.additem('图书信息管理',t,'books.jsp')
outlookbar.additem('读者信息管理',t,'sreader.jsp')




