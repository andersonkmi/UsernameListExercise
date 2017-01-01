package org.techstuff.auth.service;

import org.springframework.stereotype.Service;
import org.techstuff.auth.data.User;
import org.techstuff.auth.data.UserDAO;

import javax.annotation.Resource;

@Service
public class LoginValidationService {
    private static final int USER_NAME_MINIMUM_LENGTH = 6;

    @Resource
    private UserDAO userDAO;

    public void validate(final User user) throws InvalidUserNameException {
        if(user.getUserName() == null || user.getUserName().length() <= USER_NAME_MINIMUM_LENGTH) {
            throw new InvalidUserNameException("User name must have at least 6 characters");
        }
    }

    private boolean userNameAlreadyInUse(final User user) {
        int result = userDAO.count(user.getUserName());
        return result > 0;
    }
}
