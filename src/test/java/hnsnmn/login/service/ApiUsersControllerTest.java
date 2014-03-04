package hnsnmn.login.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

/**
 * Created with IntelliJ IDEA.
 * User: hongseongmin
 * Date: 2014. 3. 4.
 * Time: 오후 4:13
 * To change this template use File | Settings | File Templates.
 */
@RunWith(value = MockitoJUnitRunner.class)
public class ApiUsersControllerTest {

	@Mock
	private SocialUserService userService;
	private ApiUsersController dut = new ApiUsersController();

	@Test
	public void duplicateUserId_login_isSameUser() {
		String userId = "userId";
		SocialUser loginUser = new SocialUser(1L);
		when(userService.findByUserId(userId)).thenReturn(loginUser);

		String actual = dut.duplicateUserId(loginUser, userId);
		assertThat(actual, is("false"));
	}


	@Test
	public void duplicateUserId_login_isNotSameUser() {
		String userId = "userId";
		SocialUser loginUser = new SocialUser(1L);
		when(userService.findByUserId(userId)).thenReturn(loginUser);

		String actual = dut.duplicateUserId(new SocialUser(2L), userId);
		assertThat(actual, is("true"));
	}






	private class ApiUsersController {
		public String duplicateUserId(SocialUser loginUser, String userId) {
			SocialUser socialUser = userService.findByUserId(userId);
			if (socialUser == null) {
				return "false";
			}

			if (socialUser.isSameUser(loginUser)) {
				return "false";
			}
			return "true";
		}
	}
}
