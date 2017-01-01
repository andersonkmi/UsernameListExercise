package org.techstuff.auth.service;

import org.springframework.stereotype.Service;
import org.techstuff.auth.data.DataRepository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class UserNameSuggestionService {
    private static final int SUGGESTIONS_QUANTITY = 14;
    private static final String REPLACEMENT_RESTRICTED_WORD = "usr";

    @Resource
    private DataRepository dataRepository;

    public List<String> generateSuggestions(final String userName) {
        List<String> suggestions = new ArrayList<>();
        processStrategies(suggestions, userName);
        return suggestions;
    }

    public List<String> generateSuggestions(final String userName, final String restrictedWord) {
        String replacementUserName = userName.replace(restrictedWord, REPLACEMENT_RESTRICTED_WORD);
        List<String> suggestions = new ArrayList<>();
        processStrategies(suggestions, replacementUserName);
        return suggestions;
    }

    private void processStrategies(List<String> suggestions, String userName) {
        int attempts = 0;
        while(suggestions.size() < SUGGESTIONS_QUANTITY && attempts < 3) {
            if(attempts == 0) {
                firstStrategy(suggestions, userName);
            } else if(attempts == 1) {
                secondStrategy(suggestions, userName);
            } else {
                thirdStrategy(suggestions, userName);
            }

            attempts++;
        }
    }

    private void firstStrategy(List<String> suggestions, String userName) {
        for(int counter = 0; counter < SUGGESTIONS_QUANTITY; counter++) {
            String suggestion = String.format("%s%d", userName, counter + 1);
            if(dataRepository.count(suggestion) == 0) {
                suggestions.add(suggestion);
            }
        }
    }

    private void secondStrategy(List<String> suggestions, String userName) {
        for(int counter = 0; counter < SUGGESTIONS_QUANTITY - suggestions.size(); counter++) {
            String suggestion = String.format("%s%s%d", userName, userName, counter + 1);
            if(dataRepository.count(suggestion) == 0) {
                suggestions.add(suggestion);
            }
        }
    }

    private void thirdStrategy(List<String> suggestions, String userName) {
        Random random = new Random();
        random.setSeed(System.currentTimeMillis());

        for(int counter = 0; counter < SUGGESTIONS_QUANTITY - suggestions.size(); counter++) {
            String suggestion = String.format("%s%d", userName, random.nextInt());
            if(dataRepository.count(suggestion) == 0) {
                suggestions.add(suggestion);
            }
        }
    }
}
