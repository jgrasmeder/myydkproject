/***********
* javascript file for left menu
*/

//var menuManager = new Object();
var menuManager = new Object();


menuManager.callPage = function  (url){
		//alert(url);
		document.getElementById("content-frame").src = url;
}


menuManager.closefolder = "images/sub_menu.gif"; //set image path to "closed" folder image
menuManager.openfolder = "images/sub_menu_coll.gif"; //set image path to "open" folder image
menuManager.curMenu = null;
menuManager.curSubMenu = new Array();
menuManager.createMenuById = function (pId, mId, hidden){
	var p = document.getElementById(pId);
	this.createMenu(p, mId, hidden);
};

menuManager.curMenuItem = null;
menuManager.activeMenuItem = function  (menuItem){
	if (this.curMenuItem ){
		this.curMenuItem.className = "menuitem";
	}
	this.curMenuItem = menuItem;
	this.curMenuItem.className = "menuitem-active";
};
menuManager.createMenu = function(parentNode, menuId, hidden){

	var menu = document.getElementById(menuId);
	var menuBox = document.createElement("div");
	menuBox.className = "menubox";
	menuBox.setAttribute("id", menu.getAttribute("menuid"));
	var active = menu.getAttribute("active");
	if (active == null || active == "" || active =="false"){
		menuBox.style.display = "none";
	}else{
		menuBox.style.display = "block";
		this.curMenu = menuBox;
	}
	var subMenus = menu.getElementsByTagName("ul");
	for (var i = 0; i < subMenus.length; i++) {
		menuManager.createSubMenu(menuBox, subMenus[i]);
	}
	parentNode.appendChild(menuBox);
};
	
menuManager.createSubMenu = function(menuBox, subMenu){
	var subMenuBox = document.createElement("div");
	subMenuBox.className="submenubox";
	subMenuBox.setAttribute("id", subMenu.getAttribute("menuid"));
	
	var subMenuBar = document.createElement("div");
	subMenuBar.className="submenubar";
	//subMenuBox.setAttribute("id", subMenu.getAttribute("menuid"));
	
	var menuTitle = document.createTextNode(subMenu.getAttribute("name"));
	var textDiv = document.createElement("div");
	textDiv.className = "submenutext";
	textDiv.appendChild(menuTitle);
	subMenuBar.appendChild(textDiv);
	subMenuBox.appendChild(subMenuBar);
	var menuItems = subMenu.getElementsByTagName("li");
	for (var i = 0; i < menuItems.length; i++) {
		var mi = document.createElement("div");
		mi.className = "menuitem";
		
		mi.setAttribute("id", menuItems[i].getAttribute("menuid"));
		mi.setAttribute("action", menuItems[i].getAttribute("action"));
		this.hookEvent(mi, function (e){

									 var Action = "not_exist.html";
									 var miNode = null;
									 if (e.srcElement){
										 miNode = e.srcElement.parentNode;
									 }else if (e.target){
										 miNode = e.target.parentNode;
									 }
									 if (miNode ){
										 Action = miNode.getAttribute("action");
										 menuManager.activeMenuItem(miNode);
										 menuManager.callPage(Action);
									 }else{
										 alert('browser not supported!');
									 }
							}, "click");
		menuTitle = document.createTextNode(menuItems[i].getAttribute("name"));
		textDiv = document.createElement("div");
		textDiv.className = "menuitemtext";
		textDiv.appendChild(menuTitle);
		mi.appendChild(textDiv);
		subMenuBox.appendChild(mi);
		
	}
	
	subMenuBar.onclick = function(e){
		var rel = this.getAttribute("rel");
		menuManager.toggleSubMenuBox(subMenuBar, rel);
	}
	
	menuBox.appendChild(subMenuBox);
};
	
menuManager.toggleSubMenuBox = function (subMenuBar, rel){
	var menuId = this.curMenu.getAttribute("id");
	var curSub = this.curSubMenu[menuId];
	if (curSub){
		if (curSub == subMenuBar){
			
		}else{
			this.foldSubMenu(curSub);
		}
	}
	
	var display = "none";
	var outline = "";
	if (rel == null || rel == "" || rel == "closed"){
/*			subMenuBar.setAttribute("rel", "open"); //let's open it
		subMenuBar.style.backgroundImage="url(" + menuManager.closefolder + ")";
		display = "block";
		outline = "solid 2px #085fa2";*/
		this.unfoldSubMenu(subMenuBar);
		this.curSubMenu[menuId] = subMenuBar;
	}else{
		/*
		subMenuBar.setAttribute("rel", "closed"); //let's close it
		subMenuBar.style.backgroundImage="url(" + menuManager.openfolder + ")";
		display = "none";
		outline = "";*/
		this.foldSubMenu(subMenuBar);
		this.curSubMenu[menuId] = null;
	}
/*		subMenuBar.parentNode.style.border = outline;
	var menuItemDivs = subMenuBar.parentNode.getElementsByTagName("div");
	for (var i = 1; i < menuItemDivs.length; i++) {
		if (menuItemDivs[i].parentNode != subMenuBar){
			menuItemDivs[i].style.display = display;
		}
	}*/
};
	
menuManager.foldSubMenu = function (subMenuBar){
	var display = "none";
	var outline = "";
	subMenuBar.setAttribute("rel", "closed"); //let's close it
	subMenuBar.style.backgroundImage="url(" + menuManager.openfolder + ")";
	subMenuBar.parentNode.style.border = outline;
	var menuItemDivs = subMenuBar.parentNode.getElementsByTagName("div");
	for (var i = 1; i < menuItemDivs.length; i++) {
		if (menuItemDivs[i].parentNode != subMenuBar){
			menuItemDivs[i].style.display = display;
		}
	}
};

menuManager.unfoldSubMenu = function (subMenuBar){
	var display = "block";
	var outline = "solid 2px #085fa2";
	subMenuBar.setAttribute("rel", "open"); //let's close it
	subMenuBar.style.backgroundImage="url(" + menuManager.closefolder + ")";
	subMenuBar.parentNode.style.border = outline;
	var menuItemDivs = subMenuBar.parentNode.getElementsByTagName("div");
	for (var i = 1; i < menuItemDivs.length; i++) {
		if (menuItemDivs[i].parentNode != subMenuBar){
			menuItemDivs[i].style.display = display;
		}
	}
};
	
menuManager.switchMenu = function (menuId){
	if (this.curMenu != null){
		this.curMenu.style.display = "none";
	}
	//alert(menuId);
	this.curMenu = document.getElementById(menuId);
	this.curMenu.style.display = "block";
};

menuManager.onChangeMenu = function (obj){
	var id = obj.getAttribute("menu");
	this.switchMenu(id);
};
	
menuManager.rememberstate = function(menuGroupId, durationdays){ //store index of opened ULs relative to other ULs in Tree into cookie
	var ultags = document.getElementById(menuGroupId).getElementsByTagName("ul");
	var openuls = new Array();
	for (var i = 0; i < ultags.length; i++){
		if (ultags[i].getAttribute("rel")=="open"){
			openuls[openuls.length]=i //save the index of the opened UL (relative to the entire list of ULs) as an array element
		}
	}
	if (openuls.length==0) //if there are no opened ULs to save/persist
	openuls[0]="none open" //set array value to string to simply indicate all ULs should persist with state being closed
	menuManager.setCookie(menuGroupId, openuls.join(","), durationdays) //populate cookie with value treeid=1,2,3 etc (where 1,2... are the indexes of the opened ULs)
};
	
menuManager.getCookie = function(Name){ //get cookie value
	var re=new RegExp(Name+"=[^;]+", "i"); //construct RE to search for target name/value pair
	if (document.cookie.match(re)) {//if cookie found
		return document.cookie.match(re)[0].split("=")[1] //return its value
	}
	return "";
};

menuManager.setCookie = function(name, value, days){ //set cookei value
	var expireDate = new Date();
	//set "expstring" to either future or past date, to set or delete cookie, respectively
	var expstring = expireDate.setDate(expireDate.getDate()+parseInt(days));
	document.cookie = name + "=" + value + "; expires=" + expireDate.toGMTString() + "; path=/";
};
	
menuManager.searcharray = function(thearray, value){ //searches an array for the entered value. If found, delete value from array
	var isfound=false;
	for (var i=0; i<thearray.length; i++){
		if (thearray[i]==value){
			isfound=true;
			thearray.shift(); //delete this element from array for efficiency sake
			break;
		}
	}
	return isfound;
};

menuManager.preventpropagate = function(e){ //prevent action from bubbling upwards
	if (typeof e!="undefined"){
		e.stopPropagation()
	}
	else{
		event.cancelBubble=true;
	}
};

menuManager.hookEvent = function(target, functionref, tasktype){ //assign a function to execute to an event handler (ie: onunload)
	
	var tasktype=(window.addEventListener)? tasktype : "on"+tasktype;
	if (target.addEventListener){
		target.addEventListener(tasktype, functionref, false);
		//alert('FF hookEvent'+tasktype);
	} else if (target.attachEvent) {
		target.attachEvent(tasktype, functionref);
		//alert('IE hookEvent'+tasktype);
	}else{
		alert('hook failed '+ tasktype);
	}
};
	

