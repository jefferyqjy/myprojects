		var  highlightcolor='#c1ebff';//ͻ��
      //�˴�clickcolorֻ����winϵͳ��ɫ������ܳɹ�,�����#xxxxxx�Ĵ���Ͳ���,��û�����Ϊʲô:(
      var  clickcolor='#51b2f6';
      function  changeto(){
      source=event.srcElement;//���û��ȡ�����¼��Ķ���
      if  (source.tagName=="TR"||source.tagName=="TABLE")//Դ
      return;
      while(source.tagName!="TD")
      source=source.parentElement;//parentElement ���ԣ��������� DOM ��νṹ��������¼���ϵ�����Ԫ��A����Ԫ��B����ôԪ��B�Ϳ���ͨ�� parentElement ��������ȡԪ��A
      source=source.parentElement;//��ȡ�������еĸ�����
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