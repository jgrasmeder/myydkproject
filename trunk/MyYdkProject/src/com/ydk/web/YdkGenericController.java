
package com.ydk.web;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.ServletWebRequest;

import com.ydk.account.persistence.interfaces.*;

/**
 * Annotation-driven <em>MultiActionController</em> that handles all non-form
 * URL's.
 *
 * @author Zhang Yu
 */
@Controller
public class YdkGenericController {

	/**
	 * Maybe we need to access the database. But Deprecated
	 */
	@Deprecated
	private final AccountDbMngr accountDbMngr;
	@Autowired
	public YdkGenericController(AccountDbMngr accountDbMngr) {
		this.accountDbMngr = accountDbMngr;
	}

	/**
	 * Custom handler for the welcome view.
	 * <p>
	 * Note that this handler relies on the RequestToViewNameTranslator to
	 * determine the logical view name based on the request URL: "/welcome.do"
	 * -&gt; "welcome".
	 */
	@RequestMapping("/welcome.do")
	public void welcomeHandler() {
	}

	/**
	 * Custom handler for displaying vets.
	 * <p>
	 * Note that this handler returns a plain {@link ModelMap} object instead of
	 * a ModelAndView, thus leveraging convention-based model attribute names.
	 * It relies on the RequestToViewNameTranslator to determine the logical
	 * view name based on the request URL: "/vets.do" -&gt; "vets".
	 *
	 * @return a ModelMap with the model attributes for the view
	 */
//	@RequestMapping("/vets.do")
//	public ModelMap vetsHandler() {
//		return new ModelMap(this.clinic.getVets());
//	}

	/**
	 * Custom handler for displaying an owner.
	 * <p>
	 * Note that this handler returns a plain {@link ModelMap} object instead of
	 * a ModelAndView, thus leveraging convention-based model attribute names.
	 * It relies on the RequestToViewNameTranslator to determine the logical
	 * view name based on the request URL: "/owner.do" -&gt; "owner".
	 *
	 * @param ownerId the ID of the owner to display
	 * @return a ModelMap with the model attributes for the view
	 */
//	@RequestMapping("/account.do")
//	public ModelMap accountHandler(@RequestParam("accountId") Integer accountId) {
//		return new ModelMap(this.accountDbMngr.getAccount(accountId));
//	}

	

}
