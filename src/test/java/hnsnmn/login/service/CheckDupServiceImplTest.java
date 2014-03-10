package hnsnmn.login.service;

import org.junit.Before;
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
 * Date: 2014. 3. 10.
 * Time: 오전 10:12
 * To change this template use File | Settings | File Templates.
 */
@RunWith(value = MockitoJUnitRunner.class)
public class CheckDupServiceImplTest {

	@Mock
	private SocialUserService userService;
	private CheckDupService checkDupService;

	public static final String USER_ID = "userId";
	public static final String EMAIL = "email@slipp.net";
	public static final SocialUser USER1 = new SocialUser(1L);
	public static final SocialUser USER2 = new SocialUser(2L);

	@Before
	public void init() {
		checkDupService = new CheckDupServiceImpl(userService);
	}

	@Test
	public void shouldReturnFalseWhenLoggedUserInputSelfCurrentUserId() {
		when(userService.findByUserId(USER_ID)).thenReturn(USER1);
		boolean actual = checkDupService.checkDuplicateUserId(USER1, "userId");
		assertThat(actual, is(false));
	}

	@Test
	public void shouldReturnTrueWhenLoggedUserInputUserIdOfAnotherUser() {
		when(userService.findByUserId(USER_ID)).thenReturn(USER1);
		boolean actual = checkDupService.checkDuplicateUserId(USER2, USER_ID);
		assertThat(actual, is(true));
	}

	@Test
	public void shouldReturnFalseWhenguestInputEmailWhichDoesNotExist() {
		when(userService.findByEmailAndProviderId(USER_ID, ProviderType.slipp)).thenReturn(null);
		boolean actual = checkDupService.checkDuplicateEmail(SocialUser.GUEST_USER, USER_ID, ProviderType.slipp);
		assertThat(actual, is(false));
	}

	@Test
	public void shouldReturnFalseWhenLooggedUserInputSelfCurrentEmail() {
		when(userService.findByEmailAndProviderId(EMAIL, ProviderType.slipp)).thenReturn(USER1);
		boolean actual = checkDupService.checkDuplicateEmail(USER1, EMAIL, ProviderType.slipp);
		assertThat(actual, is(false));
	}

	@Test
	public void shouldReturnTrueWhenLoggendUserInputEmailOfOtherUser() {
		when(userService.findByEmailAndProviderId(EMAIL, ProviderType.slipp)).thenReturn(USER1);
		boolean actual = checkDupService.checkDuplicateEmail(USER2, EMAIL, ProviderType.slipp);
		assertThat(actual, is(true));
	}
}
