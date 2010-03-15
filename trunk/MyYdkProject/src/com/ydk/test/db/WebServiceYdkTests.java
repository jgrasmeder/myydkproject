package com.ydk.test.db;

import java.util.List;

import com.ydk.account.aspects.UsageLogAspect;


/**
 * <p>
 * Tests for the DAO variant based on the shared EntityManager approach. Uses
 * TopLink Essentials (the reference implementation) for testing.
 * </p>
 * <p>
 * Specifically tests usage of an <code>orm.xml</code> file, loaded by the
 * persistence provider through the Spring-provided persistence unit root URL.
 * </p>
 *
 * @author Rod Johnson
 * @author Juergen Hoeller
 */
public class WebServiceYdkTests extends AbstractJpaYdkWebServicesTests {

	private UsageLogAspect usageLogAspect;

	public void setUsageLogAspect(UsageLogAspect usageLogAspect) {
		this.usageLogAspect = usageLogAspect;
	}

	@Override
	protected String[] getConfigPaths() {
		return new String[] {
//			"applicationContext-jpaCommon.xml",
//			"applicationContext-hibernateAdapter.xml",
//			"applicationContext-entityManager.xml"
			"applicationContext-jpa.xml",
			"applicationContext-webservice.xml"
		};
	}

	public void testUsageLogAspectIsInvoked() {
		String name1 = "Schuurman";
		String name2 = "Greenwood";
		String name3 = "Leau";

		assertTrue(this.accountDbMngr.findAccounts(name1).isEmpty());
		assertTrue(this.accountDbMngr.findAccounts(name2).isEmpty());

		List<String> namesRequested = this.usageLogAspect.getNamesRequested();
		assertTrue(namesRequested.contains(name1));
		assertTrue(namesRequested.contains(name2));
		assertFalse(namesRequested.contains(name3));
	}

}
