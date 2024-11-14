package br.com.mateusfilpo.cryptography.dto;

import br.com.mateusfilpo.cryptography.domain.User;

public class UserDTO {

    private Long id;
    private String userDocument;
    private String creditCardToken;
    private Long value;

    public UserDTO() {
    }

    public UserDTO(String userDocument, String creditCardToken, Long value) {
        this.userDocument = userDocument;
        this.creditCardToken = creditCardToken;
        this.value = value;
    }

    public UserDTO(User user) {
        this.id = user.getId();
        this.userDocument = user.getUserDocument();
        this.creditCardToken = user.getCreditCardToken();
        this.value = user.getValue();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserDocument() {
        return userDocument;
    }

    public void setUserDocument(String userDocument) {
        this.userDocument = userDocument;
    }

    public String getCreditCardToken() {
        return creditCardToken;
    }

    public void setCreditCardToken(String creditCardToken) {
        this.creditCardToken = creditCardToken;
    }

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }
}
