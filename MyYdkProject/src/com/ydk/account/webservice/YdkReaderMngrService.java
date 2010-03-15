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


@WebService
public interface YdkReaderMngrService {
    
  //Reader Accounts Managements
    List<Reader> getAllReaders();
    List<Reader> getReaders(@WebParam(name = "maxNumPerPage")Integer maxNumPerPage);
	
    List<Reader> findReaders(@WebParam(name = "name")String name);
    List<Reader> findLimitedReaders(@WebParam(name = "name")String name,
    		@WebParam(name = "maxNumPerPage")Integer maxNumPerPage);
//    Reader getReaderByName(@WebParam(name = "name")String name) ;
    Reader getReaderById(@WebParam(name = "id")Long id);
	void saveReader(@WebParam(name = "reader")Reader reader);
	void storeReader(@WebParam(name = "reader")Reader instance);
	void updateReader(@WebParam(name = "reader")Reader instance);
	void deleteReader(@WebParam(name = "id")Long id);
	void renewReaderKeyPair(@WebParam(name = "id")Long id);
	void deactiveReader(@WebParam(name = "id")Long id);

}
