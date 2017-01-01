package org.techstuff.auth.service;

import org.springframework.stereotype.Service;
import org.techstuff.auth.data.DataRepository;
import org.techstuff.auth.data.RestrictedWord;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RestrictedWordVerificationService {
    @Resource
    private DataRepository dataRepository;

    public String findRestrictedWord(final String userName) {
        List<RestrictedWord> items = dataRepository.findAllRestrictedWords();
        return items.stream().filter(item -> userName.toLowerCase().contains(item.getWord().toLowerCase())).findFirst().map(RestrictedWord::getWord).orElse("");
    }
}
