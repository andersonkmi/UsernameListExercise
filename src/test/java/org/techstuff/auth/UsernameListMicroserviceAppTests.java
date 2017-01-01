package org.techstuff.auth;

import org.junit.Assert;
import static org.hamcrest.CoreMatchers.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.techstuff.auth.service.InvalidUserNameException;
import org.techstuff.auth.service.UserNameValidationResult;
import org.techstuff.auth.service.UserNameValidationService;

import javax.annotation.Resource;


@RunWith(SpringJUnit4ClassRunner.class)
@ComponentScan()
@EnableTransactionManagement

public class UsernameListMicroserviceAppTests {
	@Resource
	private UserNameValidationService userNameValidationService;

	@Test
	public void validateUserNameOK() throws InvalidUserNameException{
		UserNameValidationResult result = userNameValidationService.validate("thompson");
		Assert.assertThat(result, is(notNullValue()));
		Assert.assertThat(result.isValid(), is(true));
		Assert.assertThat(result.getMessage(), is("User name is valid!"));
	}

	@Test(expected = InvalidUserNameException.class)
	public void validateUserNameInvalidLengthOk() throws InvalidUserNameException {
		UserNameValidationResult result = userNameValidationService.validate("bob");
	}

	@Test
	public void validateUserNameAlreadyInUseOK() throws InvalidUserNameException {
		UserNameValidationResult result = userNameValidationService.validate("superuser");
		Assert.assertThat(result, is(notNullValue()));
		Assert.assertThat(result.isValid(), is(false));
		Assert.assertThat(result.getMessage(), is("User name is already in use!"));
		Assert.assertThat(result.getSuggestions().size(), is(14));
		Assert.assertThat(result.getSuggestions(), hasItems("superuser1", "superuser2", "superuser3", "superuser4", "superuser5", "superuser6", "superuser7", "superuser8", "superuser9", "superuser10", "superuser11", "superuser12", "superuser13", "superuser14"));
	}

	@Test
	public void validateUserNameRestrictedWordsOK() throws InvalidUserNameException {
		UserNameValidationResult result = userNameValidationService.validate("danielcrack");
		Assert.assertThat(result, is(notNullValue()));
		Assert.assertThat(result.isValid(), is(false));
		Assert.assertThat(result.getMessage(), is("User name contains restricted words!"));
		Assert.assertThat(result.getSuggestions().size(), is(14));
		Assert.assertThat(result.getSuggestions(), hasItems("danielusr1", "danielusr2", "danielusr3", "danielusr4", "danielusr5", "danielusr6", "danielusr7", "danielusr8", "danielusr9", "danielusr10", "danielusr11", "danielusr12", "danielusr13", "danielusr14"));
	}

}
