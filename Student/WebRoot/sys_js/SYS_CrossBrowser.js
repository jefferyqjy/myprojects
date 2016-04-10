// @author Jason.yao @date 2012-08-20 @ref EE-6348 EDIT _S
String.prototype.colorHex = function(){
		var that = this;
		var reg = /^#([0-9a-fA-f]{3}|[0-9a-fA-f]{6})$/;
		if(/^(rgb|RGB)/.test(that)){
			var aColor = that.replace(/(?:\(|\)|rgb|RGB)*/g,"").split(",");
			var strHex = "#";
			for(var i=0; i<aColor.length; i++){
				var hex = Number(aColor[i]).toString(16);
				if(hex === "0"){
					hex += hex;	
				}
				strHex += hex;
			}
			if(strHex.length !== 7){
				strHex = that;	
			}
			return strHex;
		}else if(reg.test(that)){
			var aNum = that.replace(/#/,"").split("");
			if(aNum.length === 6){
				return that;	
			}else if(aNum.length === 3){
				var numHex = "#";
				for(var i=0; i<aNum.length; i+=1){
					numHex += (aNum[i]+aNum[i]);
				}
				return numHex;
			}
		}else{
			return that;	
		}
};
String.prototype.colorRgb = function(){
		var reg = /^#([0-9a-fA-f]{3}|[0-9a-fA-f]{6})$/;
		var sColor = this.toLowerCase();
		if(sColor && reg.test(sColor)){
			if(sColor.length === 4){
				var sColorNew = "#";
				for(var i=1; i<4; i+=1){
					sColorNew += sColor.slice(i,i+1).concat(sColor.slice(i,i+1));	
				}
				sColor = sColorNew;
			}
			var sColorChange = [];
			for(var i=1; i<7; i+=2){
				sColorChange.push(parseInt("0x"+sColor.slice(i,i+2)));	
			}
			return "RGB(" + sColorChange.join(",") + ")";
		}else{
			return sColor;	
		}
};
// @author Jason.yao @date 2012-08-20 @ref EE-6348 EDIT _E
//EE is the javascript function namespace of Eximbills Enterprise
//it must be a top level namespace which can be used everywhere in Eximbills Enterprise
var EEBrowser;
var EEHtml,$E;
/*@author yangchao @date 2012-04-01 @ref:EE-5997 _S*/
var storeCookie = false;
/*@author yangchao @date 2012-04-01 @ref:EE-5997 _E*/
var EE = {};
String.prototype.trim = function()
{
	return this.replace(/(^\s*)|(\s*$)/g, "");
};

if (typeof (EE.Browser) == "undefined")
{
	var browserInfo = window.navigator.userAgent.toUpperCase();
	EE.Browser = {
	    safari :/WEBKIT/.test(browserInfo),
	    opera :/OPERA/.test(browserInfo),
	    msie :/MSIE/.test(browserInfo) && !/opera/.test(browserInfo),
	    mozilla :/MOZILLA/.test(browserInfo)
	            && !/(compatible|webkit)/.test(browserInfo),
	    // here firefox means if the current browser is mozilla firefox
	    //@author Jason.yao @date 2012-07-31 @ref EE-6348 EDIT _S
	    /*firefox :(typeof (document.implementation) != 'undefined')
	            && (typeof (document.implementation.createDocument) != 'undefined'),*/
	    firefox :/FIREFOX\/([\d.]+)/.test(browserInfo),
	    //@author Jason.yao @date 2012-07-31 @ref EE-6348 EDIT _E
	    // here ie means if the current browser is Internet Explorer
	    ie :(typeof (window.ActiveXObject) != 'undefined'),
	    // here chrome means if the current browser is Google Chrome browser
	  //@author Jason.yao @date 2012-07-31 @ref EE-6348 EDIT _S
	    //chrome :false,
	    chrome :/CHROME\/([\d.]+)/.test(browserInfo),
	  //@author Jason.yao @date 2012-07-31 @ref EE-6348 EDIT _E
	    supported :true,
	    isFirefox : function()
	    {
		    return this.firefox;
	    },
	    isMSIE : function()
	    {
		    return this.ie;
	    },
	    isChrome : function()
	    {
		    return this.chrome;
	    },
	    checkBrowserSupportVersion : function()
	    {
		    if (this.firefox)
		    {
		    	//@author Jason.yao @date 2012-07-31 @ref EE-6348 EDIT _S
			    /*var indexFf = browserInfo.indexOf("FIREFOX");
			    var indexChr = browserInfo.indexOf("CHROME");
			    if (indexFf > 1)
			    {
				    var majorVersion = browserInfo.substr(indexFf + 8, 1);
				    if (parseInt(majorVersion) < 2) this.firefox = false;
			    }
			    else this.firefox = false;
			    if (indexChr > 1)
			    {
				    this.chrome = true;
			    }
			    else this.chrome = false;*/
		    	var indexFf = browserInfo.match(/FIREFOX\/([\d.]+)/)[1];
			    indexFf = indexFf.substring(0,indexFf.indexOf("."));
			    if (indexFf > 1) {
				    if (parseInt(indexFf) < 13) this.firefox = false;
			    } else {
			    	this.firefox = false;
			    }
			  //@author Jason.yao @date 2012-07-31 @ref EE-6348 EDIT _E
		    }
		    else if (this.ie)
		    {
			    var indexIe = browserInfo.indexOf("MSIE");
			    if (indexIe > 1)
			    {
				    var majorVersion = browserInfo.substr(indexIe + 5, 1);
				    if (parseInt(majorVersion) < 5) this.ie = false;
			    }
		    }
		  //@author Jason.yao @date 2012-07-31 @ref EE-6348 ADD _S
		    else if (this.chrome) {
		    	 var indexChr = browserInfo.match(/CHROME\/([\d.]+)/)[1];
		    	 indexChr = indexChr.substring(0,indexChr.indexOf("."));
		    	 if (indexChr > 1)
				    {
		    		 	if(parseInt(indexChr) < 18) 
		    		 		this.chrome = false;
		    		 	else 
		    		 		this.chrome = true;
				    }
				    else this.chrome = false;
		    }
		  //@author Jason.yao @date 2012-07-31 @ref EE-6348 ADD _E
		    else this.supported = false;
		    browserInfo = null;
		  //@author Jason.yao @date 2012-07-31 @ref EE-6348 EDIT _S
		    //if (!this.firefox && !this.ie)
		    if (!this.firefox && !this.ie && !this.chrome)
		  //@author Jason.yao @date 2012-07-31 @ref EE-6348 EDIT _E
		    {
			    this.supported = false;
			    this.popNotSupportedWarning();
			    return;
		    }
	    },
	    disableKey : function(e)
	    {
		    if (EE.Browser.firefox || EEBrowser.isChrome())
		    {
			    var key = e.which;
			    if (key == 114 || key == 115 || key == 116 || key == 117
			            || key == 122 || key == 8)
			    {
				    if (key == 8)
				    {
					    var objType = e.target.type;
					    if (objType == "text" || objType == "textarea") return true;
				    }
				    e.preventDefault = function()
				    {
					    return false;
				    };
				    return false;
			    }
			    key = null;
		    }
		    else if (EE.Browser.ie)
		    {
			    var key = window.event.keyCode;
			    if (key == 114 || key == 115 || key == 116 || key == 117
			            || key == 122 || key == 8)
			    {
				    if (key == 8)
				    {
					    var objType = event.srcElement.type;
					    if (objType == "text" || objType == "textarea") return true;
				    }
				    window.event.keyCode = 0;
				    event.returnValue = false;
				    return false;
			    }
			    key = null;
		    }
		    else this.popNotSupportedWarning();
	    },
	    preventRightClick : function()
	    {
		    document.oncontextmenu = function()
		    {
			    return false;
		    };
	    },
	    addPreventKeyEvent : function()
	    {
		    if (this.firefox)
		    {
			    document.captureEvents(Event.KEYDOWN);
		    }
	    },
	    addPreventKeyEventListener : function()
	    {
		    document.onkeydown = this.disableKey;
	    },
	    preventRightClickAndKeyEvent : function()
	    {
		    this.preventRightClick();
		    this.addPreventKeyEvent();
		    this.addPreventKeyEventListener();
	    },
	    popNotSupportedWarning : function()
	    {
		    if (!this.supported)
		    {
		    	//@author Jason.yao @date 2012-07-31 @ref EE-6348 EDIT _S
			    //alert("Your Browser is not supported by Eximbills Enterprise.\r\nPlease use IE5.x/higher or Mozilla Firefox2.x/higher.");
		    	alert("Your Browser is not supported by Eximbills Enterprise.\r\nPlease use IE5.x/higher or Mozilla Firefox13.x/higher or Google Chrome18.x/higher.");
		    	//@author Jason.yao @date 2012-07-31 @ref EE-6348 EDIT _E
		    	return false;
		    }
		    return true;
	    }
	};
	
	EEBrowser = EE.Browser;
	/***************************************************************************
	 * EE.System={ } EESystem = EE.System;
	 **************************************************************************/
	/**
	 * folloing function is used to just simulate the nodeslist object of IE
	 * when using selectNodes(xpath) method don't use this function directly by
	 * any way
	 */
	if (EEBrowser.firefox || EEBrowser.isChrome())
	{
		function __XMLNodes(result)
		{
			this.length = 0;
			this.pointer = 0;
			this.array = [];
			var i = 0;
			// @author Jason.yao @date 2012-08-20 @ref EE-6348 EDIT _S
			//while ((this.array[i] = result.iterateNext()) != null)
			//	i++;
			// @author Jason.yao @date 2012-08-20 @ref EE-6348 EDIT _E
			var tempNode = null;
			while ((tempNode = result.iterateNext()) != null) {
				this.array[i] = tempNode;
				i++;
			}
			this.length = this.array.length;
		}
		__XMLNodes.prototype.nextNode = function()
		{
			this.pointer++;
			return this.array[this.pointer - 1];
		};
		__XMLNodes.prototype.reset = function()
		{
			this.pointer = 0;
		};
		__XMLNodes.prototype.item = function(index)
		{
			return this.array[index];
		};
		XMLDocument.prototype.selectNodes = Element.prototype.selectNodes = function(xpath)
		{
			var result = this.evaluate(xpath, this, null, 0, null);
			var xns = new __XMLNodes(result);
			result = null;
			return xns;
		};
		XMLDocument.prototype.selectSingleNode = function(xpath)
		{
			var result = this.evaluate(xpath, this, null, 0, null);
			return result.iterateNext();
		};
		/***********************************************************************
		 * for to be compatible to the IE XMLNode.prototype.children=function() {
		 * var c=[]; var node=this.firstChild; while(node) {
		 * if(node.nodeType==1) c.push(node); else node=node.nextSibling; }
		 * return c; }
		 **********************************************************************/
	}
	// html facilitaty function with compatibility support
	EE.HTML = {
	    // the eventName must be like 'onclick','onblur' etc..
	    attachEventListener : function(targetObj, eventName, eventHandler)
	    {
			if(targetObj.addEventListener)
		    {
			    // remove "on" from eventName
			    targetObj.addEventListener(eventName.substr(2), eventHandler,
			            false);
				return true;
		    }
		    else if (targetObj.attachEvent) //for IE
			{
				targetObj['e'+eventName+eventHandler]=eventHandler;
				targetObj[eventName+eventHandler]=function(){targetObj['e'+eventName+eventHandler]();};
				targetObj.attachEvent(eventName, targetObj[eventName+eventHandler]);
				return true;
			}
		    else return false;
	    },
	    detachEventListener : function(targetObj, eventName, eventHandler)
	    {
	    	if (targetObj.removeEventListener)
		    {
				targetObj.removeEventListener(eventName.substr(2),
			            eventHandler, false);
				return true;
		    }
		    else if (targetObj.detachEvent)//for IE
		    {
		    	if(targetObj[eventName+eventHandler])
		    	{
				    targetObj.detachEvent(eventName, targetObj[eventName+eventHandler]);
					targetObj[eventName+eventHandler]=null;
					targetObj['e'+eventName+eventHandler]=null;
		    	}
		    	else
		    		targetObj.detachEvent(eventName, eventHandler);
				return true;
		    }
		   return false;
	    },
	    /*
	     * to be compatible when using document.onreadystatechange to determin whether the DOM has been loaded completely
	     * usage sample:
	     * DOMLoadedHandler(methodname);using current document without arguments
	     * DOMLoadedHandler(methodname,win);using doc as document object without arguments
	     * DOMLoadedHandler(methodname,win,[arg1,arg2]);using doc as document object with two arguments arg1 and arg2
	     * DOMLoadedHandler(methodname,null,[arg1,arg2]);using current document with two arguments arg1 and arg2
	     * if aVars is not null or empty, targetDocument must be there, even null
	     */
	    addDOMLoadedEventListener:function(handler,targetWin,aVars)
	    {
	    	var d=targetWin?targetWin.document:document;
	    	var init=function(){
	    		if(arguments.callee.done) return;
	    		arguments.callee.done=true;
	    		if(aVars)
	    			handler.apply(d,aVars);
	    		else
	    			handler.apply(d);
	    	};
	    	if(d.addEventListener){
	    		d.addEventListener("DOMContentLoaded", init, false);
	    	}
	    	//for safari
	    	else if(/WebKit/i.test(navigator.userAgent)){
	    		var _timer=setInterval(
	    				function(){
	    					if(/loaded|complete/.test(d.readyState)){
	    						clearInterval(_timer);
	    						init();
	    					}
	    				}
	    			,10);
	    	}
	    	//for IE
	    	else
	    	{
    			/**Modify by yaoyao date 2011-08-10 EE-5680-s**/
	    		//d.write("<script id=__ie_onload defer src=javascript:void(0)><\/script>");
	    		var dummy = (location.protocol=='https:')? '//:':'javascript:void(0)';
				d.write("<script id=__ie_onload defer src='" + dummy + "'><\/script>");   		
	    		/**Modify by yaoyao date 2011-08-10 EE-5680-e**/
	    		var script=d.getElementById("__ie_onload");
	    		script.onreadystatechange=function(){
	    			if(this.readyState=="complete"){
	    				init();
	    			}
	    		};
	    	}
	    	return true;
	    },
	    //to load script file dynamically at runtime
	    loadjs:function(url)
	    {
	       var script = document.createElement("script");
	       script.language = 'JavaScript';
	       script.type = 'text/javascript';
	       script.src = url;
	       script.defer="defer";
	       var head = document.getElementsByTagName("head")[0];
	       head.appendChild(script);
	    },
	    //to bind a function to a specified context,e.g. bindFunction(document, funcname), the funcname's runtime context will be changed to document;
	    bindFunction:function(context, funcName)
	    {
	    	return function(){funcName.apply(context, arguments);}
	    },
	    // eval a JSON string as an object
	    // returned,{name1:"value1",function1:function(){},...}
	    evalJSONToObject : function(JSONStr)
	    {
		    return eval("(" + JSONStr + ")");
	    },
	    // the eventName must be like 'onchange','onclick' etc.
	    fireEvent : function(targetObject, eventName)
	    {
	    	if (document.createEvent)
		    {
			    var eventObj = document.createEvent("HTMLEvents");
			    eventObj.initEvent(eventName.substr(2), true, false);
			    targetObject.dispatchEvent(eventObj);
			    eventObj = null;
		    }
	    	else if (targetObject.fireEvent)//for IE
		    {
			    targetObject.fireEvent(eventName);
		    }
		    else
		    {
			    EEBrowser.popNotSupportedWarning();
		    }
	    },
	    // to show the execution exception to the front user
	    dispExcep : function(funcname, e)
	    {
		    alert("error when executing function <" + funcname + ">\r\n"
		            + e.message);
	    },
	    /**
		 * to remove the specific option of html select tag specified by index
		 * <select name="fieldName"> <option value="12">12</option> <option
		 * value="13" selected>13</option> </select>
		 */
	    removeOption : function(selectObj, indexOfOpt)
	    {
	    	if(seleObj.remove)
	    	{
	    		//@author Jason.yao @date 2012-07-30 @ref EE-6348 EDIT _S
	    		//seleObj.remove(0);
	    		seleObj.remove(indexOfOpt);
	    		//@author Jason.yao @date 2012-07-30 @ref EE-6348 EDIT _E
	    	}
	    	else if(seleObj.options.remove)//for some IE version
	    	{
	    		//@author Jason.yao @date 2012-07-30 @ref EE-6348 EDIT _S
	    		//seleObj.options.remove(0);
	    		seleObj.options.remove(indexOfOpt);
	    		//@author Jason.yao @date 2012-07-30 @ref EE-6348 EDIT _E
	    	}
		    else
		    {
			    EEBrowser.popNotSupportedWarning();
		    }
	    },
	    addOption : function(selectObj, optionObj)
	    {
		    selectObj.options.add(optionObj);
	    },
	    // create normal html tag element:
	    //to use innerHTML and if there are several subsequent elements not including these child elements, just return an element array of these elements.
	    //e.g.
	    //var ele=createHtmlElement("<SELECT NAME=\"TESTSELECT1\" id=\"TESTSELECT1\" disabled><option value='1'>1</option></SELECT>");
	    //this will only return element SELECT, since the element option is the child of SELECT.
	    //but e.g. var ele=createHtmlElement("<SELECT NAME=\"TESTSELECT1\" id=\"TESTSELECT1\" disabled><option value='1'>1</option></SELECT><input type='text' name='testText'>");
	    //will return an array which will hold 2 elements SELECT and INPUT.
		//document.MAINFORM.appendChild(ele);
	    createHtmlElement : function(tagStr)
	    {
	    	var divE=document.createElement("div");
	    	var eles=null;
	    	try
	    	{
		    	divE.innerHTML=tagStr;
		    	eles=this.getChildElements(divE);
		    	if(eles.length==1)
		    		return eles[0];
		    	else return eles;
	    	}
	    	finally{eles=null;divE=null;}
	    	
	    	if(true)
	    		return;
		    if (tagStr == null || tagStr.length < 1) return false;
		    var tagEle;
		    try
		    {
		    	// this method should never be called by client directly.
		    	if(!this.createHtmlElement.getAttr){
		    		this.createHtmlElement.getAttr=function(str) {
					    if (str == null || str.length < 1) return "";
					    str = str.trim();
					    var length = str.length;
					    var lastIndexOfSingleSign = str.lastIndexOf("'");
					    var lastIndexOfDoubleSign = str.lastIndexOf("\"");
					    if (lastIndexOfSingleSign < 0 && lastIndexOfDoubleSign < 0) { return str; }
					    var lastSeparateIndex = lastIndexOfSingleSign <= lastIndexOfDoubleSign ? lastIndexOfDoubleSign
					            : lastIndexOfSingleSign;
					    var sign = str.substr(lastSeparateIndex, 1);
					    var firstSeparateIndex = str.indexOf(sign);
					    if (firstSeparateIndex >= lastSeparateIndex) return str.trim();
					    else
					    {
						    if (firstSeparateIndex > 0)
						    {
							    return str;
						    }
						    else
						    {
							    return str.substring(firstSeparateIndex + 1,
							            lastSeparateIndex).trim();
						    }
					    }
				    };
		    	}
			    tagStr = tagStr.trim();
			    if (EEBrowser.ie)
			    {
				    tagEle = document.createElement(tagStr);
			    }
			    else if (EEBrowser.firefox)
			    {
				    tagStr = tagStr.substring(1, tagStr.length - 1).trim();
				    var tagArray = tagStr.split("=");
				    var tagTemp = tagArray[0];
				    var tagCode = "";
				    if (tagArray.length == 1)
				    {
					    tagCode = tagTemp;
				    }
				    else
				    {
					    tagCode = tagTemp.substring(0, tagTemp.indexOf(" "));
				    }
				    tagEle = document.createElement(tagCode);
				    for ( var i = 0; i < tagArray.length - 1; i++)
				    {
					    var temp1 = tagArray[i].trim();
					    var temp2 = tagArray[i + 1];
					    var lastSpaceIndex = temp1.lastIndexOf(" ");
					    var propName = temp1.substring(lastSpaceIndex + 1);
					    lastSpaceIndex = temp2.lastIndexOf(" ");
					    var propValue = "";
					    if (i + 1 == tagArray.length - 1)
					    {
						    propValue = temp2;
					    }
					    else
					    {
						    propValue = temp2.substring(0, lastSpaceIndex);
					    }
					    var propValue = this.createHtmlElement.getAttr(propValue);
					    tagEle.setAttribute(propName, propValue);
				    }
			    }
			    else
			    {
				    EEBrowser.popNotSupportedWarning();
			    }
			    return tagEle;
		    }
		    catch (e)
		    {
			    this.dispExcep("EE.HTML.createHtmlElement", e);
		    }
		    finally
		    {
			    tagEle = null;
		    }
	    },
	    // get any element's attribute value,especially for those which is not
	    // standard attributes
	    // for the compatibility, should always use this method instead of
	    // obj.attrName since this may not be working
	    getAttribute : function(obj, attrName)
	    {
		    return obj.getAttribute(attrName);
	    },
	    getNodeValue:function(ele)
	    {
	    	if (EEBrowser.ie)
		    {
			    return ele?ele.text:"";
		    }
		    else
		    {
			    return ele&&ele.firstChild?ele.firstChild.nodeValue:"";
		    }
	    },
	    // get the text value of a html tag, i.e <td>test</td>, then tag td's
	    // text is test. for the compatibility,
	    // should use this method instead of innerText which is only for IE
	    getInnerText : function(ele)
	    {
		    try
		    {
		    	if(ele.textContent)
		    	{ return ele.textContent; }
		    	else if(ele.innerText)
		    	{ return ele.innerText; }
		    	return "";
		    }
		    catch (e)
		    {
			    this.dispExcep("EE.HTML.getInnerText", e);
			    return "";
		    }
	    },
	    getElementText : function(ele)
	    {
		    try
		    {
		    	if(ele.textContent)//for firefox
		    	{ return ele.textContent; }
		    	else if(ele.text)//for ie
		    	{ return ele.text; }
		    	return "";
		    }
		    catch (e)
		    {
			    this.dispExcep("EE.HTML.getElementText", e);
			    return "";
		    }
	    },
	    setInnerText : function(obj, text)
	    {
		    try
		    {
		    	if (EEBrowser.ie || EEBrowser.isChrome())
			    {
				    obj.innerText = text;
			    }
			    else if (EEBrowser.firefox)
			    {
				    obj.textContent = text;
			    }
			    else
			    {
				    if (obj.textContent) obj.textContent = text;
				    else obj.innerText = text;
			    }
		    }
		    catch (e)
		    {
			    this.dispExcep("EE.HTML.setInnerTest", e);
		    }
	    },
	    // get all of the child element of first level of one tag element , not
	    // including text node
	    getChildElements : function(parent)
	    {
		    var childEles = [];
		    if (parent.nodeType == 1)
		    {
			    // if parent is a table object ,
			    // then both IE and Mozilla would have a first child called
			    // TBODY, all of TR //elements is inside TBODY NOT TABLE, even
			    // you didn¡¯t specify TBODY //explicitly in your screen.
			    // this is to say, for table, we will only return TR objects as
			    // its children
			    // one more thing, like here, if we got a TR object of a table,
			    // we want to get the parent TABLE object, we have to call like:
			    // _trObject.parentNode.parentNode;
			    if (parent.tagName == "TABLE")
			    {
				    var tBodyNodes = parent.tBodies;
				    parent = tBodyNodes[0];
				    tBodyNodes = null;
			    }
			    var children = parent.childNodes;
			    var length = children.length;
			    for ( var i = 0; i < length; i++)
			    {
				    var c1 = children[i];
				    if (c1.nodeType == 1)
				    {
					    childEles.push(c1);
				    }
				    c1 = null;
			    }
			    children = null;
		    }
		    try
		    {
			    return childEles;
		    }
		    finally
		    {
			    childEles = null;
		    }
	    },
	    getFirstChildElement : function(parent)
	    {
		    var cNode;
		    if (parent.nodeType == 1)
		    {
			    // if parent is a table object ,
			    // then both IE and Mozilla would have a first child called
			    // TBODY, all of TR //elements is inside TBODY NOT TABLE, even
			    // you didn¡¯t specify TBODY //explicitly in your screen.
			    // this is to say, for table, we will only return TR objects as
			    // its children
			    // one more thing, like here, if we got a TR object of a table,
			    // we want to get the parent TABLE object, we have to call like:
			    // _trObject.parentNode.parentNode;
			    if (parent.tagName == "TABLE")
			    {
				    var tBodyNodes = parent.tBodies;
				    parent = tBodyNodes[0];
				    tBodyNodes = null;
			    }
			    var child = parent.firstChild;
			    while (child)
			    {
				    if (child.nodeType == 1)
				    {
					    cNode = child;
					    break;
				    }
				    child = child.nextSibling;
			    }
			    child = null;
		    }
		    try
		    {
			    return cNode;
		    }
		    finally
		    {
			    cNode = null;
		    }
	    },
	    getLastChildElement : function(parent)
	    {
		    var cNode;
		    if (parent.nodeType == 1)
		    {
			    var child = parent.lastChild;
			    while (child)
			    {
				    if (child.nodeType == 1)
				    {
					    cNode = child;
					    break;
				    }
				    child = child.previousSibling;
			    }
			    child = null;
		    }
		    try
		    {
			    return cNode;
		    }
		    finally
		    {
			    cNode = null;
		    }
	    },
	    /**
		 * insert the newinput node before referenceNode in firefox,
		 * referenceNode is required even though pass null if no reference
		 */
	    insertNodeBefore : function(parent, newinput, referenceNode)
	    {
		    parent.insertBefore(newinput, referenceNode);
		    return newinput;
	    },
	    insertNodeAfter : function(parent, newinput, referenceNode)
	    {
		    if (referenceNode) referenceNode = EEBrowser.ie ? referenceNode.nextSibling
		            : this.getNextSiblingElement(referenceNode);
		    parent.insertBefore(newinput, referenceNode);
		    return newinput;
	    },
	    /**
		 * firefox doesnot have replace node method,using newNode to replace
		 * oldNode e.g. <root> <oldNode></oldNode> </root> after replace:
		 * <root> <newNode></newNode> </root>
		 */
	    replaceNode : function(newNode, oldNode)
	    {
		    if (EEBrowser.ie)
		    {
			    return oldNode.replaceNode(newNode);
		    }
		    else if (EEBrowser.firefox)
		    {
			    oldNode.parentNode.replaceChild(newNode, oldNode);
			    return oldNode
		    }
		    else
		    {
			    EEBrowser.popNotSupportedWarning();
			    return null;
		    }

	    },
	    /**
		 * to swap the nodes in the DOM structure e.g. <root> <firstNode></firstNode>
		 * <secondNode></secondNode> </root> after swap: <root> <secondNode></secondNode>
		 * <firstNode></firstNode> </root>
		 */
	    swapNode : function(firstNode, secondNode)
	    {
		    if (EEBrowser.ie)
		    {
			    return firstNode.swapNode(secondNode);
		    }
		    else if (EEBrowser.firefox)
		    {
			    this.replaceNode(this.replaceNode(secondNode, firstNode),
			            secondNode);
			    return firstNode;
		    }
		    else
		    {
			    EEBrowser.popNotSupportedWarning();
			    return null;
		    }
	    },
	    // trNode must be a tr tag of html
	    getOwnerTableOfTR : function(trNode)
	    {
		    return trNode.parentNode.parentNode;
	    },
	    // tdNode must be a td tag of html
	    getOwnerTableOfTD : function(tdNode)
	    {
		    return tdNode.parentNode.parentNode.parentNode;
	    },
	    /**
		 * this method only return direct children with the specified tag name
		 * of a specific parent object, not contain indirect children i.e,
		 * <body> <table>
		 * <tr>
		 * <td></td>
		 * </tr>
		 * </table> <tabel>
		 * <tr>
		 * <td> <table>
		 * <tr>
		 * <td></td>
		 * </tr>
		 * </table> </td>
		 * </tr>
		 * </table> </body> asume we've got the BODY object, named _body, then
		 * getChildElementsByTagName(_body, "table") and this will only return
		 * two objects, if we just use _body.getElementsByTagName("table") and
		 * it will return three objects
		 */
	    getChildElementsByTagName : function(parentTag, tagName)
	    {
		    var elements = [];
		    if (tagName == null || tagName.length < 1) return elements;
		    tagName = tagName.toUpperCase();
		    var children = this.getChildElements(parentTag);
		    var length = children.length;
		    for ( var i = 0; i < length; i++)
		    {
			    var o = children[i];
			    if (o.tagName == tagName) elements.push(o);
		    }
		    children = null;
		    try
		    {
			    return elements;
		    }
		    finally
		    {
			    elements = null;
		    }
	    },
	    /**
		 * just return the first suitable child element. this method is
		 * deprecated, please use getFirstChildElementByTagName instead
		 */
	    getChildElementByTagName : function(parentTag, tagName)
	    {
		    return this.getFirstChildElementByTagName(parentTag, tagName);
	    },
	    getFirstChildElementByTagName : function(parentTag, tagName)
	    {
		    var element;
		    if (tagName == null || tagName.length < 1) return element;
		    tagName = tagName.toUpperCase();
		    element = parentTag.firstChild;
		    while (element)
		    {
			    if (element.nodeType == 1 && element.tagName == tagName) break;
			    else
			    {
				    element = element.nextSibling;
			    }
		    }
		    try
		    {
			    return element;
		    }
		    finally
		    {
			    element = null;
		    }
	    },
	    /**
		 * for nexsibling, IE and firefox have different process this
		 * getNextSiblingElement will only return the node which is an element
		 */
	    getNextSiblingElement : function(currentEle)
	    {
		    var next;
		    if (currentEle)
		    {
			    if (EEBrowser.ie)
			    {
				    return currentEle.nextSibling;
			    }
			    else if (EEBrowser.firefox || EEBrowser.isChrome())
			    {
				    next = currentEle.nextSibling;
				    while (next)
				    {
					    if (next.nodeType == 1) break;
					    next = next.nextSibling;
				    }
			    }
			    else
			    {
				    EEBrowser.popNotSupportedWarning();
			    }
		    }
		    try
		    {
			    return next;
		    }
		    finally
		    {
			    next = null;
		    }
	    },
	    /**
		 * for previousSibling, IE and firefox have different process this
		 * getPreviousSiblingElement will only return the node which is an
		 * element
		 */
	    getPreviousSiblingElement : function(currentEle)
	    {
		    var previous;
		    if (currentEle)
		    {
			    if (EEBrowser.ie)
			    {
				    return currentEle.previousSibling;
			    }
			    else if (EEBrowser.firefox || EEBrowser.isChrome())
			    {
				    previous = currentEle.previousSibling;
				    while (previous)
				    {
					    if (previous.nodeType == 1) break;
					    previous = previous.previousSibling;
				    }
			    }
			    else
			    {
				    EEBrowser.popNotSupportedWarning();
			    }
		    }
		    try
		    {
			    return previous;
		    }
		    finally
		    {
			    previous = null;
		    }
	    },
	    // just return the first suitable parent element from the first parent
	    // level.
	    getParentElementFromFirstParentLevel : function(currentTag, tagName)
	    {
		    var element;
		    if (tagName == null || tagName.length < 1) return element;
		    tagName = tagName.toUpperCase();
		    element = currentTag.parentNode;
		    while (element)
		    {
			    if (element.nodeType == 1 && element.tagName == tagName) break;
			    else
			    {
				    element = element.nextSibling;
			    }
		    }
		    return element;
	    },
	    /**
		 * get the first suitable parent element search from the whole html tree
		 * <root> <node1> <node2> <node3></node3> </node2> </node1> <node1>
		 * <node4> <node5></node5> </node4> </node1> </root> if call
		 * getParentElementByTagName(node3,node1) it will return the first
		 * presented node1
		 */
	    getParentElementByTagName : function(currentTag, tagName)
	    {
		    var element;
		    if (tagName == null || tagName.length < 1) return element;
		    tagName = tagName.toUpperCase();
		    element = currentTag.parentNode;
		    while (element)
		    {
			    if (element.nodeType == 1 && element.tagName == tagName) break;
			    element = element.parentNode;
		    }
		    try
		    {
			    return element;
		    }
		    finally
		    {
			    element = null;
		    }
	    },
	    // get the first parent element
	    getParentElement : function(childNode)
	    {
		    if (EEBrowser.ie) return childNode.parentElement;
		    else if (EEBrowser.firefox || EEBrowser.isChrome())
		    {
			    var ele = childNode.parentNode;
			    while (ele)
			    {
				    if (ele.nodeType == 1) break;
				    ele = ele.previousSibling;
			    }
			    try
			    {
				    return ele;
			    }
			    finally
			    {
				    ele = null;
			    }
		    }
		    else
		    {
			    EEBrowser.popNotSupportedWarning();
			    return null;
		    }
	    },
	    /**
		 * <div name='name'></div> or just use document.getElementsByName to
		 * retrieve all the elements whose name is specified by the argument
		 * name
		 */
	    getChildElementsByName : function(parent, name)
	    {
		    var eles = [];
		    var all = parent.getElementsByTagName("*");
		    for ( var i = 0; i < all.length; i++)
		    {
			    if (all[i].getAttribute("name") == name) eles.push(all[i]);
		    }
		    try
		    {
			    return eles;
		    }
		    finally
		    {
			    all = null;
			    eles = null;
		    }
	    },
	    /**
		 * to retrieve all the tag elements which has the specified class and
		 * tag name in case of tagName is null,it will return all tags which
		 * just have the specified class
		 */
	    getElementsByClassName : function(className, tagName)
	    {
		    var r = [];
		    if (EEBrowser.ie || EEBrowser.isChrome())
		    {
			    tagName = tagName || "*";
			    var a = document.getElementsByTagName(tagName);
			    var i = 0;
			    var ele;
			    while (ele = a[i++])
			    {
				    if (ele.className == className) r.push(ele);
			    }
			    ele = null;
			    a = null;
		    }
		    else if (EEBrowser.firefox)
		    {
			    var a = document.getElementsByClassName(className);
			    if (tagName)
			    {
				    var i = 0;
				    var ele;
				    while (ele = a[i++])
				    {
					    if (ele.tagName == tagName) r.push(ele);
				    }
				    ele = null;
			    }
			    else r = a;
			    a = null;
		    }
		    else
		    {
			    EEBrowser.popNotSupportedWarning();
		    }
		    return r;
	    },
	    /**
		 * get the source element of an event which is happened on
		 */
	    getEventSrcElement : function(e)
	    {
		    if (EEBrowser.ie || EEBrowser.isChrome())
		    {
			    return window.event.srcElement;
		    }
		    else if (EEBrowser.firefox)
		    {
			    e = e || EE.HTML.getEvent();
			    return e.target;
		    }
		    else
		    {
			    return EEBrowser.popNotSupportedWarning();
		    }
	    },
	    // get the event object when an event is happening
	    getEvent : function()
	    {
		    var event = null;
		    if (EEBrowser.ie || EEBrowser.isChrome())
		    {
			    event = window.event;
		    }
		    else if (EEBrowser.firefox)
		    {
			    if (window.Event)
			    {
				    var objCaller = arguments.callee.caller;
				    var ObjEvt;
				    while (objCaller != null)
				    {
					    ObjEvt = objCaller.arguments[0];
					    if (ObjEvt
					            && ObjEvt.constructor.toString().indexOf(
					                    "Event") > -1)
					    {
						    event = ObjEvt;
						    break;
					    }
					    objCaller = objCaller.caller;
				    }
				    ObjEvt = null;
				    objCaller = null;
			    }
		    }
		    else EEBrowser.popNotSupportedWarning();
		    return event;
	    },
	    // to simulate the IE's window.event on Firefox and this method should
	    // never be called directly by client
	    fixPrototypeForGecko : function()
	    {
		    if (EEBrowser.firefox)
		    {
			    HTMLElement.prototype.__defineGetter__("runtimeStyle",
			            function()
			            {
				            return this.style;
			            });
			    window.constructor.prototype.__defineGetter__("event", this
			            .getEvent());
			    Event.prototype.__defineGetter__("srcElement", function()
			    {
				    return this.target;
			    });
		    }
	    },
	    setIFrameBorder:function(iframeObj,borderSize)
	    {
	    	iframeObj.setAttribute("frameborder",borderSize,0);
	    },
	    /**
		 * get a specific iframe object according to the iframe id within the
		 * current document range. for the frame and frameset: in order to get
		 * the frame and frameset html object, must use getElementById in order
		 * to get the frame window object, must use top.window.frameName
		 */
	    getIFrame : function(iframeId, winDocument)
	    {
	    	return winDocument?winDocument.getElementById(iframeId):document.getElementById(iframeId);
	    },
	    getIFrameWindow : function(iframeName, baseWindow)
	    {
	    	return baseWindow?baseWindow.frames[iframeName]:window.frames[iframeName];
	    },
	    getIFrameDocument : function(iframeName, baseWindow)
	    {
	    	return baseWindow?baseWindow.frames[iframeName].document:window.frames[iframeName].document;
	    },
	    // for frame and frameset
	    getFrameSet : function(frameSetId, winDocument)
	    {
	    	return winDocument?winDocument.getElementById(frameSetId):top.document.getElementById(frameSetId);
	    },
	    getFrame : function(frameId, winDocument)
	    {
	    	return winDocument?winDocument.getElementById(frameId):top.document.getElementById(frameId);
	    },
	    getFrameWindow : function(frameName, baseWindow)
	    {
	    	//@author Jason.yao @date 2012-07-31 @ref EE-6348 EDIT _S
	    	//return baseWindow?baseWindow.frames[frameName]:top.frames[frameName];
	    	if(frameName == "toolbar") {
	    		frameName = "eeToolbar";
	    	}
	    	var rtnWin = baseWindow? baseWindow.frames[frameName] : top.frames[frameName];
	    	if(EEBrowser.isChrome() && typeof rtnWin != "undefined" && rtnWin != null) {
	    		rtnWin =  rtnWin.contentWindow? rtnWin.contentWindow : rtnWin;
	    	}
	    	return rtnWin;
	    	//@author Jason.yao @date 2012-07-31 @ref EE-6348 EDIT _E
	    },
	    getFrameDocument : function(frameName, baseWindow)
	    {
	    	//@author Jason.yao @date 2012-07-31 @ref EE-6348 EDIT _S
	    	//return baseWindow?baseWindow.document:top.frames[frameName].document;
	    	return this.getFrameWindow(frameName, baseWindow).document;
	    	//@author Jason.yao @date 2012-07-31 @ref EE-6348 EDIT _E
	    },
	    // to get the key code when key is clicked
	    getKeyCode : function(event)
	    {
		    if (EEBrowser.ie || EEBrowser.isChrome())
		    {
			    var e = event || window.event;
			    return e.keyCode;
		    }
		    else if (EEBrowser.firefox)
		    {
			    var e = event || EE.HTML.getEvent();
			    return e.which;
		    }
		    else
		    {
			    EEBrowser.popNotSupportedWarning();
			    return null;
		    }
	    },
	    /**
		 * to simulate the event.keyCode=9 of IE in firefox
		 * if(event.keyCode==13)event.keyCode=9 when click Enter key, then move
		 * the focus to the next visible form element. only form element.
		 */
	    moveFocus : function(ev)
	    {
		    ev = ev || this.getEvent();
		    var keyCode = this.getKeyCode(ev);
		    if (EEBrowser.ie)
		    {
			    if (keyCode == 13)// enter key
			    {
				    ev.keyCode = 9;// tab key
			    }
		    }
		    else if (EEBrowser.firefox || EEBrowser.isChrome())
		    {
			    if (keyCode == 13)// enter key
			    {
				    var evSrc = this.getEventSrcElement(ev);
				    var fields = document.getElementsByTagName("*");
				    var field;
				    var len = fields ? fields.length : 0;

				    var find = false;
				    for ( var i = 0; i < len; i++)
				    {
					    field = fields[i];
					    if (find)
					    {
						    var tag = field.tagName;
						    if (tag == "INPUT" || tag == "SELECT"
						            || tag == "TEXTAREA")
						    {
							    if (field.type != "hidden")
							    {
								    if (field.style.visibility != "hidden"
								            && !field.disabled
								            && field.style.display != "none")
								    {
									    field.focus();
									    break;
								    }
							    }
						    }
					    }
					    else if (evSrc == field)
					    {
						    find = true;
					    }
				    }
				    evSrc = null;
				    fields = null;
				    field = null;
			    }
		    }
		    else
		    {
			    EEBrowser.popNotSupportedWarning();
		    }
	    },
	    /**
		 * get mouse absolute position on the browser window return an object
		 * which contains two properties x and y
		 */
	    getMouseAbsPos : function(ev)
	    { // ev is the event object
		    if (EEBrowser.firefox)
		    {
			    ev = ev || this.getEvent();
			    return {
			        x :ev.pageX,
			        y :ev.pageY
			    };
		    }
		    else if (EEBrowser.ie || EEBrowser.isChrome())
		    {
			    ev = ev || window.event;
			    if (document.documentElement.scrollLeft
			            || document.documentElement.scrollTop) return {
			        x :ev.clientX + document.documentElement.scrollLeft,
			        y :ev.clientY + document.documentElement.scrollTop
			    };
			    else return {
			        x :ev.clientX + document.body.scrollLeft
			                - document.body.clientLeft,
			        y :ev.clientY + document.body.scrollTop
			                - document.body.clientTop
			    };
		    }
		    else
		    {
			    EEBrowser.popNotSupportedWarning();
			    return {
			        x :0,
			        y :0
			    };
		    }
	    },
	    //to retrieve the scroll's top,left position(coordination, left-top point)
	    getPageScrollXY:function()
	    {
	    	var x,y;
	    	if(document.body.scrollTop||document.body.scrollLeft){
	    	  x=document.body.scrollLeft;
	    	  y=document.body.scrollTop;
	    	}
	    	else{
	    	  x=document.documentElement.scrollLeft;
	    	  y=document.documentElement.scrollTop;
	    	}
	    	return {left:x,top:y};
	    },
	    getHtmlBodySize:function()
		{
			var width=document.body.scrollWidth;
			var height=document.body.scrollHeight;
			return {"width":width,"height":height};
		},
		//mostly, this will return the current document window size
		getVisibleHtmlBodySize:function()
		{
			var vWidth=document.body.clientWidth;
			var vHeight=document.body.clientHeight;
			return {"width":vWidth,"height":vHeight};
		},
		//to get the size of width and height for a cover which is going to cover the entire html content, such as a DIV 
		getSizeOfBodyCover:function()
		{
			var vSize= $E.getVisibleHtmlBodySize();
			var bodySize=$E.getHtmlBodySize();
			var width=Math.max(vSize.width,bodySize.width);
			var height=Math.max(vSize.height,bodySize.height);
			return {"width":width,"height":height};
		},
		/**
		 * to set a DIV's size (width and heigth) which is a mask of a html document, so it can cover the entire html page
		 * the recommendation of css for DIV
		 * {background:gray none repeat scroll 0% 0%;
			z-index: 19998;
			position: absolute;
			opacity: 0; 
			filter: alpha(opacity=0);
			-ms-filter: "alpha(opacity=0)";
			zoom: 1;
			top: 0px; 
			left: 0px; }
			opacity for FF,filter and zoom for IE7 or lower,-ms-filter for IE8
		 */
		setMaskSizeOfHtmlBody:function(divObj)
		{
			var vSize=this.getVisibleHtmlBodySize();
			divObj.style.width=vSize.width+"px";
			divObj.style.height=vSize.height+"px";
			window.setTimeout(function(){
				var size= $E.getSizeOfBodyCover();
				divObj.style.width=size.width+"px";
				divObj.style.height=size.height+"px";
				divObj=null;
				$E.setInnerWindowSize(size);
			},5);
		},
		createHtmlBodyMask:function(doc,divId)
		{
			var divIdName=divId?divId:"htmlBodyContentMask";
			var docObj=doc?doc:document;
			var maskDiv=docObj.getElementById(divIdName);
			if(!maskDiv)
			{
				maskDiv=$E.insertHTML(docObj.body, "BeforeEnd", "<div id='"+divIdName+"'></div>");
			}
			maskDiv.style.display="block";
			if (EEBrowser.ie){
				//maskDiv.innerHTML="<iframe name=\"_innerWindow\" id=\"_innerWindow\">";
				var formsArray=docObj.forms;
				for(var i=0,length=formsArray.length;i<length;i++)
				{
					var fields=formsArray[i].elements;
					for(var j=0,len=fields.length;j<len;j++)
					{
						var field=fields[j];
						if(field.tagName=="SELECT"&&!field.disabled)
						{
							field.disabled=true;
							if(!window.disabledElementsUnderMask)
								window.disabledElementsUnderMask=new Array();
							window.disabledElementsUnderMask.push(field);
						}
					}
				}
			}
			return {"divId":divIdName,"innerWindow":"_innerWindow"};
		},
		closeHtmlBodyMask:function(doc,divId)
		{
			var divIdName=divId?divId:"htmlBodyContentMask";
			var docObj=doc?doc:document;
			var maskDiv=docObj.getElementById(divIdName);
			if(maskDiv)
			{
				maskDiv.style.display="none";
			}
			if(window.disabledElementsUnderMask)
			{
				for(var field in window.disabledElementsUnderMask)
				{
					window.disabledElementsUnderMask[field].disabled=false;
				}
				window.disabledElementsUnderMask=null;
			}
		},
		//this method must not be called by user, called by system instead.
		setInnerWindowSize:function(sizeObj)
		{
			//alert("window.frames[innerWindow]: sizeObj.width="+sizeObj.width+",sizeObj.height="+sizeObj.height);
			var iframeObj=$E.getIFrame("_innerWindow");
			if(iframeObj){
				iframeObj.style.width=sizeObj.width+"px";
				iframeObj.style.height=sizeObj.height+"px";
				iframeObj.style.padding="0px";
				iframeObj.style.margin="0px";
				$E.setIFrameBorder(iframeObj,0);
				$E.getIFrameWindow("_innerWindow").document.body.style.backgroundColor="#D4D4D4";
			}
		},
	    // to stop the event propagation and return a false boolean value
	    stopEventPropagation:function(event)
	    {
	    	if(event.stopPropagation)
	    	{
	    		event.stopPropagation();
	    	}
	    	else if(event.cancelBubble)
	    	{
	    		event.cancelBubble=true;
	    	}
	    },
	    // firefox doesnot support to change the key code, as in
	    // IE,event.keyCode=9
	    stopEvent : function(event)
	    {
		    if (EEBrowser.ie || EEBrowser.isChrome())
		    {
			    event = event || window.event;
			    event.returnValue = false;
		    }
		    else if (EEBrowser.firefox)
		    {
			    event = event || this.getEvent();
			    event.preventDefault();
		    }
		    else
		    {
			    EEBrowser.popNotSupportedWarning();
			    return false;
		    }
		    return false;
	    },
	    /**
		 * to get the offset(x,y) of any html element to the body element
		 */
	    getOffsetToBody : function(target)
	    {
		    var nLt = 0;
		    var nTp = 0;
		    var offsetParent = target;
		    while (offsetParent != null && offsetParent != document.body)
		    {
			    nLt += offsetParent.offsetLeft;
			    nTp += offsetParent.offsetTop;
			    if (EEBrowser.ie)
			    {
				    parseInt(offsetParent.currentStyle.borderLeftWidth) > 0 ? nLt += parseInt(offsetParent.currentStyle.borderLeftWidth): "";
				    parseInt(offsetParent.currentStyle.borderTopWidth) > 0 ? nTp += parseInt(offsetParent.currentStyle.borderTopWidth): "";
			    }
			    offsetParent = offsetParent.offsetParent;
		    }
		    return {x:nLt,y:nTp};
	    },
	    /**
		 * to let the script inside the htmlcode take effect if no script, then
		 * just use standard method element.innerHTML
		 */
	    innerHTML : function(el, htmlCode)
	    {
		    if (EEBrowser.ie || EEBrowser.isChrome())
		    {
			    htmlCode = htmlCode.replace(/<script([^>]*)>/gi,
			            '<script$1 defer="true">');
			    el.innerHTML = htmlCode;
		    }
		    else if (EEBrowser.firefox)
		    {
			    var el_next = el.nextSibling;
			    var el_parent = el.parentNode;
			    el_parent.removeChild(el);
			    el.innerHTML = htmlCode;
			    if (el_next)
			    {
				    el_parent.insertBefore(el, el_next);
			    }
			    else
			    {
				    el_parent.appendChild(el);
			    }
		    }
	    },
	    /**
		 * to be compatible with IE and firefox on method insertAdjacentHTML
		 * which is IE only where can be one of
		 * "beforebegin","afterbegin","beforeend","afterend" el the reference
		 * element that html will be inserted related to html the html code
		 * which will be inserted this moment return the new html element which
		 * is closest to the el
		 */
	    insertHTML : function(el, where, html)
	    {
		    var nd;
		    where = where.toLowerCase();
		    if (EEBrowser.ie || EEBrowser.isChrome())
		    {
			    switch (where)
			    {
				    case "beforebegin":
					    el.insertAdjacentHTML('BeforeBegin', html);
					    nd = el.previousSibling;
					    break;
				    case "afterbegin":
					    el.insertAdjacentHTML('AfterBegin', html);
					    nd = el.firstChild;
					    break;
				    case "beforeend":
					    el.insertAdjacentHTML('BeforeEnd', html);
					    nd = el.lastChild;
					    break;
				    case "afterend":
					    el.insertAdjacentHTML('AfterEnd', html);
					    nd = el.nextSibling;
			    }
		    }
		    else if (EEBrowser.firefox)
		    {
			    var range = el.ownerDocument.createRange();
			    var frag;
			    switch (where)
			    {
				    case "beforebegin":
					    range.setStartBefore(el);
					    frag = range.createContextualFragment(html);
					    el.parentNode.insertBefore(frag, el);
					    nd = el.previousSibling;
					    break;
				    case "afterbegin":
					    if (el.firstChild)
					    {
						    range.setStartBefore(el.firstChild);
						    frag = range.createContextualFragment(html);
						    el.insertBefore(frag, el.firstChild);
						    nd = el.firstChild;
					    }
					    else
					    {
						    el.innerHTML = html;
						    nd = el.firstChild;
					    }
					    break;
				    case "beforeend":
					    if (el.lastChild)
					    {
						    range.setStartAfter(el.lastChild);
						    frag = range.createContextualFragment(html);
						    el.appendChild(frag);
						    nd = el.lastChild;
					    }
					    else
					    {
						    el.innerHTML = html;
						    nd = el.lastChild;
					    }
					    break;
				    case "afterend":
					    range.setStartAfter(el);
					    frag = range.createContextualFragment(html);
					    el.parentNode.insertBefore(frag, el.nextSibling);
					    nd = el.nextSibling;
			    }
			    frag = null;
			    range = null;
		    }
		    else
		    {
			    EEBrowser.popNotSupportedWarning();
		    }
		    try
		    {
			    return nd;
		    }
		    finally
		    {
			    nd = null;
		    }
	    },
	    /**
		 * get the position of selection of mouse in textarea return the
		 * argument textBox must be a textarea or text element {start:sp,end:ep}
		 */
	    getInputSelectionPos : function(textBox,doc)
	    {
		    var start, end;
		    if (EEBrowser.firefox)
		    {
			    if (typeof (textBox.selectionStart) == "number")
			    {
				    start = textBox.selectionStart;
				    end = textBox.selectionEnd;
			    }
		    }
		    else if (EEBrowser.ie)
		    {
		    	doc=doc||document;
			    var range = doc.selection.createRange();
			    /** create a selection of the whole textarea* */
			    var range_all = doc.body.createTextRange();
			    // text input object
			    var range_all;
			    if ("INPUT" == textBox.tagName && textBox.type == "text")
			    {
				    range_all = textBox.createTextRange();
				    range.setEndPoint("StartToStart", range_all);
				    start = range.text.length;
				    range_all = null;
				    range = null;
				    return {
				        "start" :start,
				        "end" :0
				    };
			    }
			    // text input object end

			    // for textarea input object
			    range_all.moveToElementText(textBox);
			    /***************************************************************
				 * calculate selection start point by moving beginning of
				 * range_all to beginning of range
				 **************************************************************/
			    for (start = 0; range_all.compareEndPoints("StartToStart", range) < 0; start++)
			    {
				    range_all.moveStart('character', 1);
			    }
			    /***************************************************************
				 * get number of line breaks from textarea start to selection
				 * start and add them to start
				 **************************************************************/
			    for ( var i = 0; i <= start; i++)
			    {
				    if (textBox.value.charAt(i) == '\n')
				    {
					    start++;
				    }
			    }
			    /** create a selection of the whole textarea* */
			    var range_all = doc.body.createTextRange();
			    range_all.moveToElementText(textBox);
			    /***************************************************************
				 * calculate selection end point by moving beginning of
				 * range_all to end of range
				 **************************************************************/
			    for (end = 0; range_all.compareEndPoints('StartToEnd', range) < 0; end++)
			    {
				    range_all.moveStart('character', 1);
			    }
			    /***************************************************************
				 * get number of line breaks from textarea start to selection
				 * end and add them to end
				 **************************************************************/
			    for ( var i = 0; i <= end; i++)
			    {
				    if (textBox.value.charAt(i) == '\n') end++;
			    }
		    }
		    else
		    {
			    EEBrowser.popNotSupportedWarning();
		    }
		    return {
		        "start" :start,
		        "end" :end
		    };
	    },
	    /**
		 * to select/hightlight the text between the start and end range in a
		 * text/textarea input element start: where to start to select the text
		 * end: where to end the selection, end can be positive or negative
		 * number, like -1,1 if end is negative, it means the end point is the
		 * place where it is begining from the end of the text value if end is
		 * positive, then the end value is the length that text will be selected
		 * from the start position if end is 0, then it will select all the text
		 * from the start position
		 */
	    selectInputText : function(textBox, start, end)
	    {
		    if (isNaN(end))
		    {
			    end = 0;
		    }
		    else
		    {
			    end = parseInt(end);
		    }
		    if (EEBrowser.ie)
		    {
			    var range = textBox.createTextRange();
			    if (end > 0)
			    {
				    range.collapse(true);
			    }
			    range.moveStart("character", start);
			    range.moveEnd("character", end);
			    range.select();
			    range = null;
		    }
		    else if (EEBrowser.firefox)
		    {
			    if (end <= 0)
			    {
				    end = textBox.value.length + end;
			    }
			    else
			    {
				    end = parseInt(start) + end;
			    }
			    textBox.setSelectionRange(start, end);
		    }
		    else
		    {
			    EEBrowser.popNotSupportedWarning();
		    }
	    },
	    /**
		 * to insert the text into a text/textarea input element at the
		 * specified position value: to be inserted start: must be greater than
		 * or equal to 0 end: same as the definition of method selectInputText
		 * replace: boolean value, indicate whether to repalce the selected text
		 * by the range of start to end if replace is false, then will insert
		 * the text value to the end position. recommendation: if no replace,
		 * donot use end and replace arguments, just set the start argument as
		 * the position where the text will be inserted
		 * if replace is true, doc might be specifid to indicate which document will be handled
		 */
	    insertInputText : function(textBox, value, start, end, replace)
	    {
		    var oValue = textBox.value;
		    var newV;
		    if (isNaN(end)||start==end)
		    {
			    newV = oValue.substring(0, start) + value
			            + oValue.substring(start);
		    }
		    else
		    {
			    if (replace)
			    {
			    	newV = oValue.substring(0, start) + value
					            + oValue.substring(end);
			    }
			    else
			    {
				    newV = oValue.substring(0, end) + value
				            + oValue.substring(end);
			    }
		    }
		    textBox.value = newV;
	    },
	    /**
		 * get the selected text in a text or textarea element
		 */
	    getSelectedInputText : function(textBox,doc)
	    {
		    var text;
		    if (EEBrowser.ie)
		    {
		    	doc=doc||document;
			    text = doc.selection.createRange().text;
		    }
		    else if (EEBrowser.firefox || EEBrowser.chrome)
		    {
			    text = textBox.value.substring(textBox.selectionStart,
			            textBox.selectionEnd);
		    }
		    else
		    {
			    EEBrowser.popNotSupportedWarning();
		    }
		    return text;
	    },
	    /**
		 * select the specified text in a text
		 */
	    selectSpecifiedTailText : function(textBox, text)
	    {
		    if (EEBrowser.ie)
		    {
			    var remainLen = text.length;
			    var range = textBox.createTextRange();
			    range.findText(text, remainLen * -2);
			    range.select();
		    }
		    else if (EEBrowser.firefox || EEBrowser.isChrome())
		    {
			    var curVal = textBox.value;
			    var totalLen = curVal.length;
			    textBox.setSelectionRange((totalLen - text.length), totalLen);
		    }
		    else
		    {
			    EEBrowser.popNotSupportedWarning();
		    }
	    },
	    /**
		 * to select/hightlight the specified element/node
		 */
	    selectNode : function(ele)
	    {
		    var selection, range, doc, win;
		    if (EEBrowser.firefox || EEBrowser.isChrome())
		    {
			    doc = node.ownerDocument;
			    range = doc.createRange();
			    range.selectNode(node);
			    selection = window.getSelection();
			    selection.removeAllRanges();
			    selection.addRange(range);
			    selection = null;
			    range = null;
			    doc = null;
		    }
		    else if (EEBrowser.ie)
		    {
			    /*
				 * node.select() only textarea and text element support the
				 * select method in IE*
				 */
			    range = document.body.createTextRange();
			    range.moveToElementText(node);
			    range.select();
			    range = null;
		    }
		    else
		    {
			    EEBrowser.popNotSupportedWarning();
		    }
	    },
	    diselectNode : function(ele)
	    {
	    	if (EEBrowser.ie || EEBrowser.isChrome())
		    {
			    document.selection.empty();
		    }
		    else if (EEBrowser.firefox)
		    {
			    window.getSelection().removeAllRanges();
		    }
		    else
		    {
			    EEBrowser.popNotSupportedWarning();
		    }
	    },

	    /**
		 * To load the xml string and return a XML DOM object
		 */
	    loadXML : function(xmlstr)
	    {
		    var xmlDom;
		    if (EEBrowser.ie)
		    {
			    xmlDom = new ActiveXObject("Microsoft.XMLDOM")
			    xmlDom.async = "false";
			    xmlDom.loadXML(xmlstr);
		    }
		    else if (EEBrowser.firefox || EEBrowser.isChrome())
		    {
			    var oParser = new DOMParser();
			    xmlDom = oParser.parseFromString(xmlstr, "text/xml");
			    oParser = null;
		    }
		    else
		    {
			    EEBrowser.popNotSupportedWarning();
		    }
		    try
		    {
			    return xmlDom;
		    }
		    finally
		    {
			    xmlDom = null;
		    }
	    },
	    /**
		 * to be compatible with IE specific method of DOM
		 */
	    selectSingleNode : function(xmlDoc, xpath)
	    {
		    if (xmlDoc.nodeType != 9) xmlDoc = xmlDoc.ownerDocument;
		    var aResult;
		    if (EEBrowser.ie || EEBrowser.firefox || EEBrowser.isChrome())
		    {
			    aResult = xmlDoc.selectSingleNode(xpath);
		    }
		    else
		    {
			    EEBrowser.popNotSupportedWarning();
		    }
		    try
		    {
			    return aResult;
		    }
		    finally
		    {
			    aResult = null;
		    }
	    },
	    selectNodes : function(xmlDoc, xpath)
	    {
		    var aResult;
		    if (xmlDoc.nodeType != 9) xmlDoc = xmlDoc.ownerDocument;
		    if (EEBrowser.ie || EEBrowser.firefox || EEBrowser.isChrome())
		    {
			    aResult = xmlDoc.selectNodes(xpath);
		    }
		    /*******************************************************************
			 * else if (EEBrowser.firefox) { aResult = []; var oNSResolver =
			 * xmlDoc .createNSResolver(xmlDoc.documentElement) var aItems =
			 * xmlDoc.evaluate(xpath, xmlDoc, oNSResolver,
			 * XPathResult.ORDERED_NODE_SNAPSHOT_TYPE, null) for ( var i = 0; i <
			 * aItems.snapshotLength; i++) { aResult[i] =
			 * aItems.snapshotItem(i); } }
			 ******************************************************************/
		    else
		    {
			    EEBrowser.popNotSupportedWarning();
		    }
		    try
		    {
			    return aResult;
		    }
		    finally
		    {
			    aResult = null;
		    }
	    },
	    toString:function(){
	    	//alert("Eximbills Enterprise JS Library");  
	    },
	  //@author Jason.yao @date 2012-07-31 @ref EE-6348 ADD _S
	    getElementObj : function(id,winDoc) {
	    	if(id.indexOf("#") == 1) {
	    		return winDoc? winDoc.getElementById(id.substring(1,id.length)) : document.getElementById(id.substring(1,id.length));
	    	}
	    	var rtnValue;
	    	if(EEBrowser.ie || EEBrowser.firefox || EEBrowser.chrome) {
	    		rtnValue = winDoc? winDoc.all(id) : document.all(id);
	    	}else {
	    		if(winDoc){
	    			rtnValue = winDoc.getElementsByName(id);
	    			if(typeof rtnValue == "undefind" || rtnValue == null || rtnValue.length == 0) {
	    				rtnValue = winDoc.getElementById(id);
	    			}
	    			if(typeof rtnValue == "undefined" || rtnValue == null) {
	    				rtnValue = winDoc.getElementsByTagName(id);
	    			}
	    		}else {
	    			rtnValue = document.getElementsByName(id);
	    			if(typeof rtnValue == "undefined" || rtnValue == null || rtnValue.length == 0) {
	    				rtnValue = document.getElementById(id);
	    			}
	    			if(typeof rtnValue == "undefined" || rtnValue == null) {
	    				rtnValue = document.getElementsByTagName(id);
	    			}
	    		} 
	    	}
	    	return rtnValue;
	    },
	    getSelectElement : function(selectName,formName) {
	    	var rtnValue;
	    	if(formName) {
	    		rtnValue = eval("document." + formName + "." + selectName);
	    	} else {
	    		rtnValue = document.getElementById(selectName);
	    		if(!EEBrowser.ie) {
		    		if(rtnValue == null || typeof rtnValue == "undefined") {
		    			rtnValue = document.getElementsByName(selectName);
		    			if(rtnValue) {
		    				return rtnValue[0];
		    			}
		    		}
	    		}
	    	}
	    	return rtnValue;
	    },
	    getElementById : function(elementId, winDoc) {
	    	var rtnValue = winDoc ? winDoc.getElementById(elementId) : document.getElementById(elementId);
    		if(rtnValue == null || typeof rtnValue == "undefined") {
    			rtnValue = winDoc ? winDoc.getElementsByName(elementId) : document.getElementsByName(elementId);
    			if(rtnValue) {
    				return rtnValue[0];
    			}
    		}
	    	return rtnValue;
	    },
	    insertCell : function (rowObj, baseWin) {
	    	var eeTd;
	    	if(EEBrowser.ie) {
	    		eeTd = rowObj.insertCell();
	    	} else {
		    	eeTd = baseWin? baseWin.document.createElement("td") : document.createElement("td");
		    	rowObj.appendChild(eeTd);
	    	}
	    	return eeTd;
	    },
	    getEventX : function (event) {
	    	var e = event ? event : this.getEvent();
	    	if(EEBrowser.isMSIE()) {
	    		return e.x;
	    	}else {
	    		return e.pageX;
	    	}
	    },
	    getEventY : function (event) {
	    	var e = event ? event : this.getEvent();
	    	if(EEBrowser.isMSIE()) {
	    		return e.y;
	    	}else {
	    		return e.pageY;
	    	}
	    },
	    getEventFromElement : function(event) {
		    if (EEBrowser.ie || EEBrowser.isChrome()) {
			    var e = event || window.event;
			    return e.fromElement;
		    } else if (EEBrowser.firefox) {
		    	var node = null;
			    var e = event || EE.HTML.getEvent();
			    if(e.type == "mouseover") {
			    	node = e.relatedTarget;  
			    } else if (e.type == "mouseout") {
			    	node = e.target;  
			    }
			    if(!node) return;  
			    while(node.nodeType != 1) {
			    	node = node.parentNode;  
			    }
			    return node;  
		    } else {
			    EEBrowser.popNotSupportedWarning();
			    return null;
		    }
	    },
	    getEventToElement : function(event) {
	    	if(EEBrowser.ie || EEBrowser.isChrome()) {
	    		var e = event || window.event;
	    		return e.toElement;
	    	} else if (EEBrowser.firefox) {
	    		var node = null;  
	    		var e = event || EE.HTML.getEvent();
	            if(e.type == "mouseout") {
	            	node = e.relatedTarget;  
	            } else if (e.type == "mouseover") {
	            	node = e.target; 
	            }
	            if(!node) return;  
	            while (node.nodeType != 1)  
	               node = node.parentNode;  
	            return node;  
	    	} else {
			    EEBrowser.popNotSupportedWarning();
			    return null;
		    }
	    }
	  //@author Jason.yao @date 2012-07-31 @ref EE-6348 ADD _E
	};
	EEHtml = EE.HTML;
	$E=EE.HTML;
}
EEBrowser.checkBrowserSupportVersion();
// @author Jason.yao @date 2012-08-20 @ref EE-6348 ADD _S
if(EEBrowser.isFirefox()) {
	window.constructor.prototype.__defineGetter__("event",EEHtml.getEvent); 
	Event.prototype.__defineGetter__("srcElement", EEHtml.getEventSrcElement);
	Event.prototype.__defineGetter__("keyCode", EEHtml.getKeyCode);
	Event.prototype.__defineGetter__("fromElement", EEHtml.getEventFromElement);
	Event.prototype.__defineGetter__("toElement", EEHtml.getEventToElement);
	Element.prototype.__defineGetter__("text",function(){ return this.textContent; });
	Window.prototype.attachEvent = HTMLDocument.prototype.attachEvent = 
	HTMLElement.prototype.attachEvent = function(en, func, cancelBubble) { 
		var cb = cancelBubble ? true : false; 
		this.addEventListener(en.toLowerCase().substr(2), func, cb); 
	}; 
}
if(EEBrowser.isChrome()) {
	Element.prototype.__defineGetter__("text",function(){ return this.textContent; });
	Window.prototype.attachEvent = HTMLDocument.prototype.attachEvent = 
	HTMLElement.prototype.attachEvent = function(en, func, cancelBubble) { 
		var cb = cancelBubble ? true : false; 
		this.addEventListener(en.toLowerCase().substr(2), func, cb); 
	};
}
// @author Jason.yao @date 2012-08-20 @ref EE-6348 ADD _E
// EEBrowser.preventRightClickAndKeyEvent();
