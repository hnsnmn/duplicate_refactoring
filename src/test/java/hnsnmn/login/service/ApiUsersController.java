package hnsnmn.login.service;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

/**
* Created with IntelliJ IDEA.
* User: hongseongmin
* Date: 2014. 3. 4.
* Time: 오후 6:07
* To change this template use File | Settings | File Templates.
*/
@Controller
public class ApiUsersController {

	private SocialUserService userService;
	private CheckDupService checkDupService;

	public ApiUsersController(CheckDupService checkDupService) {
		this.checkDupService = checkDupService;
	}

	@ResponseBody
	public String duplicateUserId(SocialUser loginUser, String userId) {
		return Boolean.toString(checkDupService.duplicateUserId(loginUser, userId));
	}

	public String duplicateEmail(SocialUser loginUser, String email, ProviderType provierType) {
		return Boolean.toString(checkDupService.duplicateEmail(loginUser, email, provierType));
	}
}
