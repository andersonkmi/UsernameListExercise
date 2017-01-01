package org.techstuff.auth.service;


import org.springframework.stereotype.Service;
import org.techstuff.auth.data.DataRepository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserNameValidationService {
    private static final int USER_NAME_MINIMUM_LENGTH = 6;

    @Resource
    private DataRepository dataRepository;

    @Resource
    private RestrictedWordVerificationService restrictedWordVerificationService;

    @Resource
    private UserNameSuggestionService userNameSuggestionService;

    public UserNameValidationResult validate(final String userName) throws InvalidUserNameException {
        UserNameValidationResult result = new UserNameValidationResult();

        verifyUserNameMinimumLength(userName);

        List<String> items = new ArrayList<>();
        verifyUserNameAlreadyInUse(userName, items);
        if(!items.isEmpty()) {
            result.setMessage("User name is already in use!");
            result.setSuggestions(items);
            return result;
        }

        verifyRestrictedWords(userName, items);
        if(!items.isEmpty()) {
            result.setMessage("User name contains restricted words!");
            result.setSuggestions(items);
            return result;
        }

        result.setIsValid(true);
        result.setMessage("User name is valid!");
        result.setSuggestions(items);
        return result;
    }

    private void verifyUserNameMinimumLength(final String userName) throws InvalidUserNameException {
        if(userName == null || userName.length() <= USER_NAME_MINIMUM_LENGTH) {
            throw new InvalidUserNameException("User name must have at least 6 characters");
        }
    }

    private void verifyUserNameAlreadyInUse(final String userName, List<String> suggestions) {
        int result = dataRepository.count(userName);
        if(result > 0) {
            suggestions.addAll(userNameSuggestionService.generateSuggestions(userName));
        }
    }

    private void verifyRestrictedWords(final String userName, List<String> suggestions) {
        String restrictedWord = restrictedWordVerificationService.findRestrictedWord(userName);
        if(!restrictedWord.isEmpty()) {
            suggestions.addAll(userNameSuggestionService.generateSuggestions(userName, restrictedWord));
        }
    }
}
