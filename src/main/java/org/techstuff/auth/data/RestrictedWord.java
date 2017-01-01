package org.techstuff.auth.data;

public class RestrictedWord {
    private Integer id;
    private String word;

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getWord() {
        return word;
    }
}
