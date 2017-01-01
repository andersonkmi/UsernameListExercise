package org.techstuff.auth.service;


import org.springframework.stereotype.Service;
import org.techstuff.auth.data.DataRepository;

import javax.annotation.Resource;

@Service
public class UserNameValidationService {
    private static final int USER_NAME_MINIMUM_LENGTH = 6;

    @Resource
    private DataRepository dataRepository;

    @Resource
    private RestrictedWordVerificationService restrictedWordVerificationService;

    public void validate(final String userName) throws InvalidUserNameException, UserNameAlreadyExistsException, UserNameRestrictedWordException {
        verifyUserNameMinimumLength(userName);

        if(userNameAlreadyInUse(userName)) {
            throw new UserNameAlreadyExistsException(String.format("User name '%s' already exists", userName));
        }

        if(restrictedWordVerificationService.containsRestrictedWords(userName)) {
            throw new UserNameRestrictedWordException(String.format("User name '%s' contains restricted words", userName));
        }
    }

    private void verifyUserNameMinimumLength(final String userName) throws InvalidUserNameException {
        if(userName == null || userName.length() <= USER_NAME_MINIMUM_LENGTH) {
            throw new InvalidUserNameException("User name must have at least 6 characters");
        }
    }

    private boolean userNameAlreadyInUse(final String userName) {
        int result = dataRepository.count(userName);
        return result > 0;
    }
}
