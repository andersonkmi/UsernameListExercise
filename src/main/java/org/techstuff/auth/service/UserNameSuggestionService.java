package org.techstuff.auth.service;

import org.springframework.stereotype.Service;
import org.techstuff.auth.data.DataRepository;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserNameSuggestionService {

    @Resource
    private DataRepository dataRepository;

    public List<String> generateSuggestions(final String userName) {
        return null;
    }
}
