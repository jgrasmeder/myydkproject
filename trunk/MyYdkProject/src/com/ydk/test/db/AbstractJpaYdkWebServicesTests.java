
package com.ydk.test.db;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.test.annotation.ExpectedException;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.jpa.AbstractJpaTests;

import com.ydk.account.core.AccountDefines;
import com.ydk.account.persistence.entity.*;
import com.ydk.account.persistence.interfaces.AccountDbMngr;
import com.ydk.account.persistence.operator.TransactionQuery;
import com.ydk.account.webservice.WebServiceAccount;
import com.ydk.account.webservice.WebServiceYdkAccount;
import com.ydk.account.webservice.WebServiceYdkRole;
import com.ydk.account.webservice.WebYdkRoleListResult;
import com.ydk.account.webservice.YdkAccountMngrService;
import com.ydk.account.webservice.YdkAccountService;
import com.ydk.account.webservice.YdkBookMngrService;
import com.ydk.account.webservice.YdkBusiQueryService;
import com.ydk.account.webservice.YdkPartnerMngrService;
import com.ydk.account.webservice.YdkSubjectMngrService;
import com.ydk.book.persistence.entity.Book;
import com.ydk.book.persistence.entity.Event;
import com.ydk.book.persistence.entity.EventCategory;
import com.ydk.book.persistence.entity.Interview;
import com.ydk.book.persistence.entity.InterviewCategory;
import com.ydk.book.persistence.interfaces.BookDbMngr;
import com.ydk.book.persistence.operator.AuthorEntity;
import com.ydk.book.persistence.operator.BookEntity;
import com.ydk.book.persistence.operator.EventEntity;
import com.ydk.book.persistence.operator.InterviewEntity;
import com.ydk.book.webservice.WebServiceListParams;
import com.ydk.book.webservice.YdkBookFreeService;
import com.ydk.book.webservice.YdkBookService;
import com.ydk.book.webservice.YdkFrontEndWebService;
import com.ydk.book.webservice.YdkSubjectService;

/**
 * <p>
 * This class extends {@link AbstractJpaTests}, one of the valuable test
 * superclasses provided in the <code>org.springframework.test</code> package.
 * This represents best practice for integration tests with Spring for JPA based
 * tests which require <em>shadow class loading</em>. For all other types of
 * integration testing, the <em>Spring TestContext Framework</em> is
 * preferred.
 * </p>
 * <p>
 * AbstractJpaTests and its superclasses provide the following services:
 * <ul>
 * <li>Injects test dependencies, meaning that we don't need to perform
 * application context lookups. See the setClinic() method. Injection uses
 * autowiring by type.</li>
 * <li>Executes each test method in its own transaction, which is automatically
 * rolled back by default. This means that even if tests insert or otherwise
 * change database state, there is no need for a teardown or cleanup script.</li>
 * <li>Provides useful inherited protected fields, such as a
 * {@link SimpleJdbcTemplate} that can be used to verify database state after
 * test operations, or verify the results of queries performed by application
 * code. Alternatively, you can use protected convenience methods such as
 * {@link #countRowsInTable(String)}, {@link #deleteFromTables(String[])},
 * etc. An ApplicationContext is also inherited, and can be used for explicit
 * lookup if necessary.</li>
 * </ul>
 * <p>
 * {@link AbstractJpaTests} and related classes are shipped in
 * <code>spring-test.jar</code>.
 * </p>
 *
 * @author Rod Johnson
 * @author Sam Brannen
 * @see AbstractJpaTests
 */
public abstract class AbstractJpaYdkWebServicesTests extends AbstractJpaTests {

//	@Autowired
	protected AccountDbMngr accountDbMngr;
	protected BookDbMngr bookDbMngr;
//	@Autowired
	@Qualifier("ydkBookServiceClient")
    protected YdkBookService ydkBookServiceClient;
	
	
	public void setYdkBookServiceClient(@Qualifier("ydkBookServiceClient")YdkBookService ydkBookServiceClient) {
        this.ydkBookServiceClient = ydkBookServiceClient;
    }
	

	@Qualifier("ydkAccountMngrServiceClient")
    protected YdkAccountMngrService ydkAccountMngrService;
	
	public void setYdkAccountMngrService(@Qualifier("ydkAccountMngrServiceClient")YdkAccountMngrService ydkAccountMngrService) {
        this.ydkAccountMngrService = ydkAccountMngrService;
    }
	
	
	
	@Qualifier("ydkBookFreeServiceClient")
    protected YdkBookFreeService ydkBookFreeService;
	
	public void setYdkBookFreeService(@Qualifier("ydkBookFreeServiceClient")YdkBookFreeService service) {
        this.ydkBookFreeService = service;
    }
	
	@Qualifier("ydkSubjectServiceClient")
    protected YdkSubjectService ydkSubjectService;
	
	public void setYdkSubjectService(@Qualifier("ydkSubjectServiceClient")YdkSubjectService service) {
        this.ydkSubjectService = service;
    }
	
	@Qualifier("ydkFrontEndServiceClient")
    protected YdkFrontEndWebService ydkFrontEndWebService;
	
	public void setYdkFrontEndWebService(@Qualifier("ydkFrontEndServiceClient")YdkFrontEndWebService service) {
        this.ydkFrontEndWebService = service;
    }
	
	
	@Qualifier("ydkSubjectMngrServiceClient")
    protected YdkSubjectMngrService ydkSubjectMngrService;
	
	public void setYdkSubjectMngrService(@Qualifier("ydkSubjectMngrServiceClient")YdkSubjectMngrService service) {
        this.ydkSubjectMngrService = service;
    }
	
	@Qualifier("ydkPartnerMngrServiceClient")
    protected YdkPartnerMngrService ydkPartnerMngrService;
	
	public void setYdkPartnerMngrService(@Qualifier("ydkPartnerMngrServiceClient")YdkPartnerMngrService service) {
        this.ydkPartnerMngrService = service;
    }
	
	@Qualifier("ydkBookMngrServiceClient")
    protected YdkBookMngrService ydkBookMngrService;
	
	public void setYdkBookMngrService(@Qualifier("ydkBookMngrServiceClient")YdkBookMngrService service) {
        this.ydkBookMngrService = service;
    }
	
	@Qualifier("ydkBusiQueryServiceClient")
    protected YdkBusiQueryService ydkBusiQueryService;
	
	public void setYdkBusiQueryService(@Qualifier("ydkBusiQueryServiceClient")YdkBusiQueryService service) {
        this.ydkBusiQueryService = service;
    }
	
	
	


	/**
	 * This method is provided to set the Clinic instance being tested by the
	 * Dependency Injection injection behaviour of the superclass from the
	 * <code>org.springframework.test</code> package.
	 *
	 * @param clinic clinic to test
	 */
	public void setAccountDbMngr(AccountDbMngr accountDbMngr) {
		this.accountDbMngr = accountDbMngr;
	}
	
	public void setBookDbMngr(BookDbMngr bookDbMngr) {
		this.bookDbMngr = bookDbMngr;
	}

	@ExpectedException(IllegalArgumentException.class)
	public void testBogusJpql() {
		this.sharedEntityManager.createQuery("SELECT RUBBISH FROM RUBBISH HEAP").executeUpdate();
	}

	public void testApplicationManaged() {
		EntityManager appManaged = this.entityManagerFactory.createEntityManager();
		appManaged.joinTransaction();
	}
	
	
	@Rollback(false)
	public void testYdkAccountMngrService() {
		//CRUD PartnerType;
		YdkRole ydkRole = new YdkRole();
		ydkRole.setRoleName(AccountDefines.ACCOUT_ROLE_GUEST);
		ydkRole.setMask(AccountDefines.MASK_ACCOUT_ROLE_GUEST);
		WebServiceYdkRole webYdkRole = new WebServiceYdkRole();
		webYdkRole.setYdkRole(ydkRole);
		
		ydkAccountMngrService.storeYdkRole(webYdkRole);
		
		WebYdkRoleListResult webRoleList = ydkAccountMngrService.getAllYdkRoles();
		
		assertNotNull(webRoleList);
		assertNotNull(webRoleList.getOs());
		assertEquals(new Boolean(true),webRoleList.getOs().getIsSuccessful());
		assertNotNull(webRoleList.getYdkRoles());
		assertEquals(1, webRoleList.getYdkRoles().size());
		assertEquals(1, webRoleList.getTotalItems().intValue());
		assertNotNull(webRoleList.getYdkRoles().get(0).getId());
		assertEquals(AccountDefines.ACCOUT_ROLE_GUEST, webRoleList.getYdkRoles().get(0).getRoleName());
		
		YdkRole ydkRoleTemp = webRoleList.getYdkRoles().get(0);
		
		ydkRole = new YdkRole();
		ydkRole.setRoleName(AccountDefines.ACCOUT_ROLE_VIP);
		ydkRole.setMask(AccountDefines.MASK_ACCOUT_ROLE_VIP);
		webYdkRole = new WebServiceYdkRole();
		webYdkRole.setYdkRole(ydkRole);
		
		ydkAccountMngrService.storeYdkRole(webYdkRole);
		
		ydkRole = new YdkRole();
		ydkRole.setRoleName(AccountDefines.ACCOUT_ROLE_ADMIN);
		ydkRole.setMask(AccountDefines.MASK_ACCOUT_ROLE_ADMIN);
		webYdkRole = new WebServiceYdkRole();
		webYdkRole.setYdkRole(ydkRole);
		
		ydkAccountMngrService.storeYdkRole(webYdkRole);
		
		WebServiceListParams listRarams = new WebServiceListParams();
		listRarams.setPageSize(3);
		listRarams.setPageOffset(0);
		
		webRoleList = ydkAccountMngrService.getAllYdkRoles();
		
		assertNotNull(webRoleList);
		assertNotNull(webRoleList.getOs());
		assertEquals(new Boolean(true),webRoleList.getOs().getIsSuccessful());
		assertNotNull(webRoleList.getYdkRoles());
		assertEquals(3, webRoleList.getYdkRoles().size());
		assertEquals(3, webRoleList.getTotalItems().intValue());
		assertNotNull(webRoleList.getYdkRoles().get(0).getId());
		assertEquals(AccountDefines.ACCOUT_ROLE_GUEST, webRoleList.getYdkRoles().get(0).getRoleName());
		
		
		
		
		WebServiceYdkAccount instance = new WebServiceYdkAccount();
		YdkAccount ydkAccount = new YdkAccount();
		ydkAccount.setYdkRole(ydkRole);
		ydkAccount.setIsActived(true);
		ydkAccount.setName("ydkAccount1");
		ydkAccount.setEmail("ydkAccount@gmail.com");
		
		ydkAccountMngrService.storeYdkAccount(instance);
		
		
	}
	
	
//	@Rollback(false)
//	public void testPartner() {
//		//CRUD PartnerType;
//		PartnerType instance = new PartnerType("1");
//		accountDbMngr.storePartnerType(instance);
//		instance = accountDbMngr.getPartnerType(instance.getType());
//		assertNotNull(instance);
//		assertEquals("1", instance.getType());
//		
//		instance = new PartnerType("2");
//		accountDbMngr.storePartnerType(instance);
//		instance = new PartnerType("3");
//		accountDbMngr.storePartnerType(instance);
//		instance = new PartnerType("4");
//		accountDbMngr.storePartnerType(instance);
//		accountDbMngr.deletePartnerType("1");
//		instance = accountDbMngr.getPartnerType("1");
//		assertNull(instance);
//		assertEquals(3, accountDbMngr.getAllPartnerTypes().size());
//		
//		
//		instance = accountDbMngr.getPartnerType("2");
//		Key key = new Key("public_1", "private_1", new Date(), false);
//		Partner partner = new Partner(instance, "_partner_1", "_partner_1@ydk.com");
//		partner.setCurrentKeyPair(key);
//		key = new Key("public_2", "private_2", new Date(), false);
//		partner.setLastKeyPair(key);
//		accountDbMngr.storePartner(partner);
//		
//		assertNotNull(partner.getId());
//		
//		partner = new Partner(instance, "_partner_2", "_partner_2@ydk.com");
//		accountDbMngr.storePartner(partner);
//		assertNotNull(partner.getId());
//		
//		Partner result = accountDbMngr.getPartner(partner.getId());
//		assertNotNull(partner.getId());
//		
//		instance = null;
//		PartnerType newResult = accountDbMngr.getPartnerType("2");
//		//This will fail, because the transaction has not been submitted.
////		assertEquals(2, newResult.getPartners().size());
//	}
//
//	public void testPartner2() {
//		
//		
//		Partner result = accountDbMngr.getPartner((long)1);
//		assertNotNull(result);
//		assertNotNull(result.getCurrentKeyPair());
//		assertNotNull(result.getLastKeyPair());
//		
//		result = accountDbMngr.getPartner((long)2);
//		assertNotNull(result);
//		
//		PartnerType instance = accountDbMngr.getPartnerType("2");
//		assertEquals(2, instance.getPartners().size());
//	}
	

}
