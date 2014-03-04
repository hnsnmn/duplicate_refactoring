package hnsnmn.login.service;

/**
 * Created with IntelliJ IDEA.
 * User: hongseongmin
 * Date: 2014. 3. 4.
 * Time: 오후 6:25
 * To change this template use File | Settings | File Templates.
 */
public class CheckDupServiceImpl implements CheckDupService{
	private SocialUserService userService;

	public CheckDupServiceImpl(SocialUserService userService) {
		this.userService = userService;
	}

	@Override
	public boolean duplicateUserId(SocialUser loginUser, String userId) {
		SocialUser socialUser = userService.findByUserId(userId);
		if (socialUser == null)
			return false;
		return !socialUser.isSameUser(loginUser);
	}


	@Override
	public boolean duplicateEmail(SocialUser loginUser, String email, ProviderType provierType) {
		SocialUser socialUser = userService.findByEmailAndProviderId(email, provierType);
		if (socialUser == null)
			return false;
		return !socialUser.isSameUser(loginUser);
	}
}
