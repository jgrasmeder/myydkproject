function addTopLeftMenu(name, href)
{
	
}

function setIframeHeight(frame){ 	
	//frame.height = frame.document.body.scrollHeight;
/*
	try{
			var bHeight = frame.contentWindow.document.body.scrollHeight;
			var dHeight = frame.contentWindow.document.documentElement.scrollHeight;
			var height = Math.max(bHeight, dHeight);
			alert(frame.height + "|"+height);
			frame.height =  height;
	}catch (ex){
	}
	*/
}

function addBookPage(){
	
}
var active_top_menu = null;
var active_top_right_menu = null;
function topLeftMenuClick(menu)
{
	if (active_top_menu == null){
		active_top_menu = document.getElementById("menu_resource_manage");
	}
		/*active_top_menu.style.backgroundImage=""; 
		active_top_menu.style.color="#FFFFFF"; */
		active_top_menu.className = "top-left-menu-inactive top_left_menu_cell";
	active_top_menu = menu;
	
	/*
	active_top_menu.style.backgroundImage="url(images/active_top_left_menu_bk.jpg)"
	active_top_menu.style.color="#FF0000"; */
	active_top_menu.className = "top-left-menu-active top_left_menu_cell";
	menuManager.onChangeMenu(menu);
}
function topRightMenuClick(menu)
{
	if (active_top_right_menu != null){
		//active_top_right_menu.style.backgroundImage=""; 
		active_top_right_menu.style.color="#FFFFFF"; 
	}
	active_top_right_menu = menu;
	//active_top_menu.style.backgroundImage="url(images/active_top_left_menu_bk.jpg)"
	active_top_right_menu.style.color="#FF0000"; 
}
function onLeftMenuClicked(menu)
{
}
var activeMenuGroup = null;
function onLeftMenuGroupChanged(group)
{
	if (activeMenuGroup != null)
	{
		activeMenuGroup.style.height="32px";
	}
	activeMenuGroup = group;
	activeMenuGroup.style.height="124px";
}