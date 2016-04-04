		var  highlightcolor='#c1ebff';//突出
      //此处clickcolor只能用win系统颜色代码才能成功,如果用#xxxxxx的代码就不行,还没搞清楚为什么:(
      var  clickcolor='#51b2f6';
      function  changeto(){
      source=event.srcElement;//设置或获取触发事件的对象。
      if  (source.tagName=="TR"||source.tagName=="TABLE")//源
      return;
      while(source.tagName!="TD")
      source=source.parentElement;//parentElement 属性，，就是在 DOM 层次结构定义的上下级关系，如果元素A包含元素B，那么元素B就可以通过 parentElement 属性来获取元素A
      source=source.parentElement;//获取对象层次中的父对象
      cs  =  source.children;
      //alert(cs.length);
      if  (cs[1].style.backgroundColor!=highlightcolor&&cs[1].style.backgroundColor!=clickcolor)
      for(var i=0;i<cs.length;i++){
      	cs[i].style.backgroundColor=highlightcolor;
      }
      }

      function  changeback(){
      if  (event.fromElement.contains(event.toElement)||source.contains(event.toElement))
      return
      if  (event.toElement!=source&&cs[1].style.backgroundColor!=clickcolor)
      //source.style.backgroundColor=originalcolor
      for(var i=0;i<cs.length;i++){
      	cs[i].style.backgroundColor="";
      }
      }