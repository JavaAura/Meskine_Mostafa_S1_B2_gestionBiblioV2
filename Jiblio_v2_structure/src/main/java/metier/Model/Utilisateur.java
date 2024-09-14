package metier.Model;

import java.util.UUID;

abstract class Utilisateur {
    protected UUID id;
    protected String username;
    protected String email;
    protected String password;

    abstract void afficherDetails();

    protected Utilisateur() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Utilisateur{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
