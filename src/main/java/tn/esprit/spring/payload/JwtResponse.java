package tn.esprit.spring.payload;

import java.util.List;

public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private long idUser;

    private String nom;


    private String email;

    private List<String> role;

    public JwtResponse (String token, long idUser, String nom, String email,List<String> role) {
        this.token = token;
        this.idUser = idUser;
        this.email = email;
        this.nom = nom;

        this.role = role ;

    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }






    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}