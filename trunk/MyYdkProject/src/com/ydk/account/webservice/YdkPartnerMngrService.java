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
public interface YdkPartnerMngrService {
    
	//Partner Accounts Managements
	WebPartnerListResult getAllPartners(
			@WebParam(name = "listParams")WebServiceListParams listParams
			);
    WebServicePartner getPartnerByName(@WebParam(name = "name")String name) ;
    WebServicePartner getPartnerById(@WebParam(name = "id")Long id);
    OperationStatus storePartner(@WebParam(name = "partner")WebServicePartner instance);
    OperationStatus updatePartner(@WebParam(name = "partner")WebServicePartner instance);
    OperationStatus deletePartner(@WebParam(name = "id")Long id);
    OperationStatus renewPartnerKeyPair(@WebParam(name = "id")Long id);
    OperationStatus deactivePartner(@WebParam(name = "id")Long id);
    
    
    //PartnerType
    WebPartnerTypeListResult getAllPartnerTypes(
			@WebParam(name = "listParams")WebServiceListParams listParams
			);
    WebServicePartnerType getPartnerTypeByName(@WebParam(name = "name")String name) ;
    WebServicePartnerType getPartnerTypeById(@WebParam(name = "id")Long id);
    OperationStatus storePartnerType(@WebParam(name = "partner")WebServicePartnerType instance);
    OperationStatus updatePartnerType(@WebParam(name = "partner")WebServicePartnerType instance);
    OperationStatus deletePartnerType(@WebParam(name = "id")Long id);
    
    
	//Reader Accounts Managements
	WebReaderListResult getReaders(
			@WebParam(name = "listParams")WebServiceListParams listParams
			);
    WebServiceReader getReaderByName(@WebParam(name = "name")String name) ;
    WebServiceReader getReaderById(@WebParam(name = "id")Long id);
    OperationStatus storeReader(@WebParam(name = "partner")WebServiceReader instance);
    OperationStatus updateReader(@WebParam(name = "partner")WebServiceReader instance);
    OperationStatus deleteReader(@WebParam(name = "id")Long id);
    OperationStatus renewReaderKeyPair(@WebParam(name = "id")Long id);
    OperationStatus deactiveReader(@WebParam(name = "id")Long id);
    

}
