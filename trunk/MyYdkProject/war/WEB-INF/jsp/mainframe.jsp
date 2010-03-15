<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/includes.jsp" %>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>悦读客综合管理系统</title>
<link rel="stylesheet" type="text/css" href="left_menu.css" charset="utf-8" />
<link rel="stylesheet" type="text/css" href="ydkbasic.css" charset="utf-8" />
<style type="text/css">
</style>
<script language="javascript" type="text/javascript" src="menu.js" charset="utf-8">
/*** 
	for left side tree menu
	
**/

</script>
<script language="JavaScript" type="text/javascript" src="basicframe.js" charset="utf-8" >

</script>
<noscript><span class="noscript">当前浏览器不支持 JavaScript, 或是禁止了脚本的运行，本系统将无法正常工作！<br />Your browser doesn't support JavaScript, or you disabled it. The tool cannot run properly! </span></noscript>
</head>
<body>
<table
	width="100%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0" id="main-layout-table" >
	<tr>
		<td  class="layout-td"  >
		<table id="top-title-layout-table" align="left" border="0"
			cellpadding="0" cellspacing="0" >
			<tr>
				<td class="top_title" ><img name="logo" src="images/ydk_logo.gif" 
					border="0" id="ydk_logo" alt="悦读客" /></td>
				<td  class="top_title" width="90%">
				综 合 管 理 平 台
				</td>
			</tr>
		</table>
		</td>
	</tr>


	<tr>
		<td class="layout-td"  >
		<table id="top-menu-bar-layout-table" align="left" border="0"
			cellpadding="0" cellspacing="0" width="100%" >
			<tr class="top_menu_bar">
				<td width="18"></td>
				<td class="top_left_menu_cell top-left-menu-active" id="menu_resource_manage"
					onclick="topLeftMenuClick(this);" menu="resource_manage_menu">
				资源管理</td>
				<td class="top_left_menu_cell" id="menu_business_manage"
					onclick="topLeftMenuClick(this);" menu="business_manage_menu">
				业务管理</td>
				<td class="top_left_menu_cell" id="menu_system_manage"
					onclick="topLeftMenuClick(this);" menu="system_manage_menu">系统管理</td>
				<td class="top_menu_padding_cell">&nbsp;</td>
				<td class="top_right_menu_cell" id="menu_resource_manage"
					onclick="topRightMenuClick(this);menuManager.callPage('first_page.html');">首页</td>
				<td class="top_right_menu_cell" id="menu_business_manage"
					onclick="topRightMenuClick(this);menuManager.callPage('changePassword.do');">修改口令</td>
				<td class="top_right_menu_cell" id="menu_system_manage"
					onclick="topRightMenuClick(this); addBookPage();"><a href="logout.do?logOut=true">退出</a></td>
			</tr>
		</table>
		</td>
	</tr>
<tr>
		<td class="padding-row-under-top-menu-bar" height="12"  >&nbsp;
		</td>
  </tr>
	<tr>
		<td  class="layout-td" height="200" >
		<table id="center-layout-table" align="left" border="0"
			cellpadding="0" cellspacing="0" width="100%"  >
			<tr>

				<td class="left_menu_layout_bg" id="left_menu_area">
				<menu id="businessmenu" menuid="business_manage_menu"
					style="display: none" active="false">
					<ul menuid="partner_management_menu" name="合作伙伴管理">
						<li menuid="partner_type_management_create_account" name="新建伙伴类型"
							action="createPartnerType.do" />
						</li>
						<li menuid="partner_type_management_modify_account" name="修改伙伴类型"
							action="listPartnerType.do" />
						</li>
						<li menuid="partner_type_management_delete_account" name="删除伙伴类型"
							action="listPartnerType.do" />
						</li>
						<li menuid="partner_management_create_account" name="新建账号"
							action="createPartner.do" />
						</li>
						<li menuid="partner_management_modify_account" name="修改账号"
							action="listPartner.do" />
						</li>
						<li menuid="partner_management_delete_account" name="删除账号"
							action="listPartner.do" />
						</li>
						<li menuid="partner_management_create_account" name="新建读者"
							action="createReader.do" />
						</li>
						<li menuid="partner_management_modify_account" name="修改读者"
							action="listReader.do" />
						</li>
						<li menuid="partner_management_delete_account" name="删除读者"
							action="listReader.do" />
						</li>
					</ul>

					<ul menuid="partner_management_menu" name="合作授权">
						<li menuid="transaction_type_management" name="新建交易类型"
							action="createTransactionType.do" />
						</li>
						<li menuid="transaction_type_management" name="修改交易类型"
							action="listTransactionType.do" />
						</li>
						<li menuid="partner_management_book_authorize" name="书籍授权"
							action="createTransaction.do" />
						</li>
						<li menuid="partner_management_book_authorize" name="书籍授权管理"
							action="listTransaction.do" />
						</li>
						<li menuid="partner_management_partner_autorize" name="合作伙伴授权"
							action="createTransaction.do" />
						</li>
					</ul>
				</menu>
				<menu id="systemmenu" menuid="system_manage_menu"
					style="display: none" active="false">
					<ul menuid="system_management_menu" name="内部账号管理">
						<li menuid="system_management_create_account" name="新建账号"
							action="createYdkAccount.do"/>
						</li>
						<li menuid="system_management_modify_account" name="修改账号"
							action="listYdkAccount.do"/>
						</li>
						<li menuid="system_management_delete_account" name="删除账号"
							action="listYdkAccount.do"/>
						</li>
					</ul>
					<ul menuid="system_maintenance_menu" name="系统维护">
						<li menuid="system_maintenance_book_bakcup" name="备份书籍"
							action="system_maintenance_book_bakcup.html" />
						</li>
						<li menuid="system_maintenance_db_bakcup" name="备份数据库"
							action="system_maintenance_db_bakcup.html" />
						</li>
						<li menuid="system_maintenance_log_bakcup" name="备份日志"
							action="system_maintenance_log_bakcup.html" />
						</li>
					</ul>
					<ul menuid="system_audit_menu" name="审计">
						<li menuid="system_audit_operation_statistics" name="操作统计"
							action="system_audit_operation_statistics.html" />
						</li>

					</ul>
				</menu>
				<menu id="resourcemenu" menuid="resource_manage_menu"
					style="display: none" active="true">

					<ul menuid="book_management_menu" name="书籍管理">
						<li menuid="book_management_add" name="Flash添加书籍"

							action="flashAddBook.do" />
						<li menuid="book_management_add" name="添加书籍"

							action="createBook.do" /> 
						</li> 
						<li menuid="book_management_edit" name="编辑书籍"

							action="listBook.do" /> 
						</li> 
						<li menuid="book_management_delete" name="删除书籍"

							action="listBook.do" /> 
						</li> 
					</ul> 
					<ul menuid="library_management_menu" name="书馆管理"> 
						<li menuid="library_management_add" name="添加书馆"

							action="createLibrary.do" /> 
						</li> 
						<li menuid="library_management_edit" name="编辑书馆"

							action="listLibrary.do" /> 
						</li> 
						<li menuid="library_management_delete" name="删除书馆"

							action="listLibrary.do" /> 
						</li> 
					</ul> 
					<ul menuid="category_management_menu" name="书籍分类管理"> 
						<li menuid="category_management_add" name="添加书籍分类"

							action="createCategory.do" /> 
						</li> 
						<li menuid="category_management_edit" name="编辑书籍分类"

							action="listCategory.do" /> 
						</li> 
						<li menuid="category_management_delete" name="删除书籍分类"

							action="listCategory.do" /> 
						</li> 
					</ul> 
					<ul menuid="book_news_management_menu" name="书讯管理"> 
						<li menuid="book_news_cate_management_add" name="添加书讯类型"

							action="createBookNewsCategory.do" /> 
						</li> 
						<li menuid="book_news_cate_management_edit" name="编辑书讯类型"

							action="listBookNewsCategory.do" /> 
						</li> 
						<li menuid="book_news_cate_management_delete" name="删除书讯类型"

							action="listBookNewsCategory.do" /> 
						</li> 
						<li menuid="book_news_management_add" name="添加书讯"

							action="createBookNews.do" /> 
						</li> 
						<li menuid="book_news_management_edit" name="编辑书讯"

							action="listBookNews.do" /> 
						</li> 
						<li menuid="book_news_management_delete" name="删除书讯"

							action="listBookNews.do" /> 
						</li> 
					</ul> 
					<ul menuid="book_review_management_menu" name="书评管理"> 
						<li menuid="book_review_cate_management_add" name="添加书评类型"

							action="createBookReviewCategory.do" /> 
						</li> 
						<li menuid="book_review_cate_management_edit" name="编辑书评类型"

							action="listBookReviewCategory.do" /> 
						</li> 
						<li menuid="book_review_cate_management_delete" name="删除书评类型"

							action="listBookReviewCategory.do" /> 
						</li> 
						<li menuid="book_review_management_add" name="添加书评"

							action="createBookReview.do"/> 
						</li> 
						<li menuid="book_review_management_edit" name="编辑书评"

							action="listBookReview.do"/> 
						</li> 
						<li menuid="book_review_management_delete" name="删除书评"

							action="listBookReview.do"/> 
						</li> 
					</ul> 
					<ul menuid="interview_management_menu" name="访谈管理"> 
						<li menuid="interview_cate_management_add" name="添加访谈类型"

							action="createInterviewCategory.do" /> 
						</li> 
						<li menuid="interview_cate_management_edit" name="编辑访谈类型"

							action="listInterviewCategory.do" /> 
						</li> 
						<li menuid="interview_cate_management_delete" name="删除访谈类型"

							action="listInterviewCategory.do" /> 
						</li>
						<li menuid="interview_management_add" name="添加访谈"

							action="createInterview.do" /> 
						</li> 
						<li menuid="interview_management_edit" name="编辑访谈"

							action="listInterview.do" /> 
						</li> 
						<li menuid="interview_management_delete" name="删除访谈"

							action="listInterview.do" /> 
						</li> 
					</ul> 
					<ul menuid="subject_management_menu" name="栏目管理"> 
						<li menuid="subject_management_add" name="添加栏目"

							action="createBookTag.do" /> 
						</li> 
						<li menuid="subject_management_edit" name="编辑栏目"

							action="listBookTag.do" /> 
						</li> 
						<li menuid="subject_management_delete" name="删除栏目"

							action="listBookTag.do" /> 
						</li> 
					</ul> 
					<ul menuid="activity_management_menu" name="俱乐部活动管理"> 
						<li menuid="activity_cate_management_add" name="添加活动类型"

							action="createEventCategory.do" /> 
						</li> 
						<li menuid="activity_cate_management_edit" name="编辑活动类型"

							action="listEventCategory.do" /> 
						</li> 
						<li menuid="activity_cate_management_delete" name="删除活动类型"

							action="listEventCategory.do" /> 
						</li>
						<li menuid="activity_management_add" name="添加活动"

							action="createEvent.do" /> 
						</li> 
						<li menuid="activity_management_edit" name="编辑活动"

							action="listEvent.do" /> 
						</li> 
						<li menuid="activity_management_delete" name="删除活动"

							action="listEvent.do" /> 
						</li> 
					</ul> 
				</menu>
				<script type="text/javascript">
					menuManager.createMenuById("left_menu_area", "resourcemenu");
					menuManager.createMenuById("left_menu_area", "systemmenu");
					menuManager.createMenuById("left_menu_area", "businessmenu");

				</script></td>
				<td id="content-td" width="100%" height="100%" >
					<iframe id="content-frame" title="content frame" src="first_page.html" frameborder="0" width="100%" height="400"  onload="setIframeHeight(this);"></iframe>
			  </td>
			</tr>
		</table>
		</td>
	</tr>
	<tr>
		
		<td  class="bottom_line"  >&nbsp;</td>
		
	</tr>
</table>
</body>
</html>
