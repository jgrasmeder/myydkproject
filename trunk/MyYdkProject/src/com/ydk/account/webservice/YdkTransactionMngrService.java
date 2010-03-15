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
public interface YdkTransactionMngrService {
    
	//Transaction  Managements
	WebTransactionListResult getAllTransactions(
			@WebParam(name = "listParams")WebServiceListParams listParams
			);
//    WebServiceTransaction getTransactionByName(@WebParam(name = "name")String name) ;
    WebServiceTransaction getTransactionById(@WebParam(name = "id")Long id);
    OperationStatus storeTransaction(@WebParam(name = "partner")WebServiceTransaction instance);
    OperationStatus updateTransaction(@WebParam(name = "partner")WebServiceTransaction instance);
    OperationStatus deleteTransaction(@WebParam(name = "id")Long id);

    
    
    //TransactionType
    WebTransactionTypeListResult getAllTransactionTypes(
			@WebParam(name = "listParams")WebServiceListParams listParams
			);
    WebServiceTransactionType getTransactionTypeByName(@WebParam(name = "name")String name) ;
    WebServiceTransactionType getTransactionTypeById(@WebParam(name = "id")Long id);
    OperationStatus storeTransactionType(@WebParam(name = "partner")WebServiceTransactionType instance);
    OperationStatus updateTransactionType(@WebParam(name = "partner")WebServiceTransactionType instance);
    OperationStatus deleteTransactionType(@WebParam(name = "id")Long id);
    
    

    

}
