/**
 * 
 */
package com.ydk.account.webservice;

/**
 * @author y140zhan
 *
 */
import java.util.List;
import javax.jws.WebParam;
import javax.jws.WebService;


import org.springframework.dao.DataAccessException;

import com.ydk.account.persistence.entity.Partner;
import com.ydk.account.persistence.entity.Reader;
import com.ydk.account.persistence.entity.YdkAccount;
import com.ydk.account.persistence.entity.YdkRole;
import com.ydk.book.webservice.OperationStatus;
import com.ydk.book.webservice.WebServiceListParams;


@WebService
public interface YdkAccountMngrService {
    
	//Ydk Accounts Managements
    WebYdkAccountListResult getAllYdkAccounts();
    WebYdkAccountListResult getYdkAccounts(@WebParam(name = "listParams")
    		WebServiceListParams listRarams);
	
    WebYdkAccountListResult findYdkAccounts(@WebParam(name = "name")String name, @WebParam(name = "listParams")
    		WebServiceListParams listRarams);
//    YdkAccount getYdkAccountByName(@WebParam(name = "name")String name) ;
    WebServiceYdkAccount getYdkAccountById(@WebParam(name = "id")Long id);
    WebServiceYdkAccount getYdkAccountByName(@WebParam(name = "name")String name);
    OperationStatus storeYdkAccount(@WebParam(name = "ydkAccount")WebServiceYdkAccount instance);
    OperationStatus updateYdkAccount(@WebParam(name = "ydkAccount")WebServiceYdkAccount instance) ;
    OperationStatus deleteYdkAccount(@WebParam(name = "id")Long id);
    OperationStatus renewYdkAccountKeyPair(@WebParam(name = "id")Long id);
    OperationStatus deactiveYdkAccount(@WebParam(name = "id")Long id);
    OperationStatus assignYdkRole(@WebParam(name = "ydkRoleId") Long ydkRoleId, 
			@WebParam(name = "accountId")Long id);
	WebYdkRoleListResult getAllYdkRoles();
	WebYdkRoleListResult getAllYdkRolesEager();
	WebYdkRoleListResult getYdkRoles(@WebParam(name = "listParams")
    		WebServiceListParams listRarams);
	
	OperationStatus storeYdkRole(@WebParam(name = "ydkRole")WebServiceYdkRole instance);
    OperationStatus updateYdkRole(@WebParam(name = "ydkRole")WebServiceYdkRole instance) ;
    OperationStatus deleteYdkRole(@WebParam(name = "id")Long id);
    
    
	OperationStatus systemKeyPairBackup();
	OperationStatus systemKeyPairRenew();
	OperationStatus backupDb();
	OperationStatus backupResources();
	
    
}
