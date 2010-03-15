/**
 * 
 */
package com.ydk.account.webservice;

/**
 * @author y140zhan
 *
 */
import java.util.Date;
import java.util.List;
import javax.jws.WebParam;
import javax.jws.WebService;


import org.springframework.dao.DataAccessException;

import com.ydk.account.persistence.entity.Partner;
import com.ydk.account.persistence.entity.Reader;
import com.ydk.account.persistence.entity.Transaction;
import com.ydk.account.persistence.entity.YdkAccount;
import com.ydk.account.persistence.entity.YdkRole;
import com.ydk.book.webservice.WebServiceListParams;


@WebService
public interface YdkBusiQueryService {
    
	
	//Transaction Query Service
	WebTransactionListResult listTransactionByPartner(
    		@WebParam(name = "startDate")Date startDate, 
    		@WebParam(name = "endDate")Date endDate,
    		@WebParam(name = "partnerId")Long partnerId,
    		@WebParam(name = "listParams")WebServiceListParams listParams
    		);
    
	WebTransactionListResult listTransactionByReader(
    		@WebParam(name = "startDate")Date startDate, 
    		@WebParam(name = "endDate")Date endDate,
    		@WebParam(name = "readId")Long readId,
    		@WebParam(name = "listParams")WebServiceListParams listParams
    		);
    
	WebTransactionListResult listTransactionByBook(
    		@WebParam(name = "startDate")Date startDate, 
    		@WebParam(name = "endDate")Date endDate,
    		@WebParam(name = "bookId")Long bookId,
    		@WebParam(name = "listParams")WebServiceListParams listParams
    		);
}
