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

    public boolean containsRestrictedWords(final String userName) {
        List<RestrictedWord> items = dataRepository.findAllRestrictedWords();
        return items.parallelStream().anyMatch(item -> userName.contains(item.getWord()));
    }
}
