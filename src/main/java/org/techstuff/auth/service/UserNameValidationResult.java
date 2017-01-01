package org.techstuff.auth.service;

import java.util.List;

public class UserNameValidationResult {
    private boolean isValid;
    private List<String> suggestions;

    public void setIsValid(boolean flag) {
        isValid = flag;
    }

    public boolean isValid() {
        return isValid;
    }

    public void setSuggestions(List<String> suggestions) {
        this.suggestions = suggestions;
    }

    public List<String> getSuggestions() {
        return suggestions;
    }
}
