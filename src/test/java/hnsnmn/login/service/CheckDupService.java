package hnsnmn.login.service;

/**
 * Created with IntelliJ IDEA.
 * User: hongseongmin
 * Date: 2014. 3. 4.
 * Time: 오후 6:12
 * To change this template use File | Settings | File Templates.
 */
public interface CheckDupService {
	public boolean checkDuplicateUserId(SocialUser loginUser, String userId);
	public boolean checkDuplicateEmail(SocialUser loginUser, String email, ProviderType provierType);
}
