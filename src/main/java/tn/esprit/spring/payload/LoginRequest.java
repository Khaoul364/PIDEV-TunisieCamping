package tn.esprit.spring.payload;
import tn.esprit.spring.entity.User;
import javax.validation.constraints.NotBlank;


public class LoginRequest {

    @NotBlank
    private String username;
    User utilisateur;

    @NotBlank
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    }

