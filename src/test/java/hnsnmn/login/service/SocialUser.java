package hnsnmn.login.service;

/**
* Created with IntelliJ IDEA.
* User: hongseongmin
* Date: 2014. 3. 4.
* Time: 오후 4:19
* To change this template use File | Settings | File Templates.
*/
public class SocialUser {
	private Long id;

	public SocialUser(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public boolean isSameUser(SocialUser loginUser) {
		return this.id == loginUser.getId();
	}
}
