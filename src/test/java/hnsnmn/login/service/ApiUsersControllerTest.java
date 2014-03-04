package hnsnmn.login.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
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

	public static final String FALSE = "false";
	public static final String TRUE = "true";
	public static final String USER_ID = "userId";
	public static final String EMAIL = "email@slipp.net";
	public static final SocialUser USER1 = new SocialUser(1L);
	public static final SocialUser USER2 = new SocialUser(2L);

	@Mock
	private SocialUserService userService;

	@InjectMocks
	private ApiUsersController dut = new ApiUsersController();

	@Test
	public void duplicateUserId_login_isSameUser() {
		when(userService.findByUserId(USER_ID)).thenReturn(USER1);

		String actual = dut.duplicateUserId(USER1, USER_ID);
		assertThat(actual, is(FALSE));
	}


	@Test
	public void duplicateUserId_login_isNotSameUser() {
		when(userService.findByUserId(USER_ID)).thenReturn(USER1);

		String actual = dut.duplicateUserId(USER2, USER_ID);
		assertThat(actual, is(TRUE));
	}

	@Test
	public void duplicateEmail_doesnot_existed() {
		String actual = dut.duplicateEmail(SocialUser.GUEST_USER, USER_ID, ProviderType.slipp);
		assertThat(actual, is(FALSE));
	}

	@Test
	public void duplicateEmail_login_isSameUser() {
		when(userService.findByEmailAndProviderId(EMAIL, ProviderType.slipp)).thenReturn(USER1);

		String actual = dut.duplicateEmail(USER1, EMAIL, ProviderType.slipp);
		assertThat(actual, is(FALSE));
	}

	@Test
	public void duplicateEmail_login_isNotSameUser() {
		when(userService.findByEmailAndProviderId(EMAIL, ProviderType.slipp)).thenReturn(USER1);

		String actual = dut.duplicateEmail(USER2, EMAIL, ProviderType.slipp);
		assertThat(actual, is(TRUE));
	}



	private class ApiUsersController {
		public String duplicateUserId(SocialUser USER1, String userId) {
			SocialUser socialUser = userService.findByUserId(userId);
			if (socialUser == null) {
				return "false";
			}

			if (socialUser.isSameUser(USER1)) {
				return "false";
			}
			return "true";
		}

		public String duplicateEmail(SocialUser USER1, String email, ProviderType provierType) {
			SocialUser socialUser = userService.findByEmailAndProviderId(email, provierType);
			if (socialUser == null) {
				return "false";
			}
			if (socialUser.isSameUser(USER1)) {
				return "false";
			}
			return "true";
		}
	}
}
