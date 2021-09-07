package multipleauthprovider.example.multipleauthprovider.entities;

import javax.persistence.*;

@Entity
@Table(name = "tblotp",schema = "public")
public class Otp {
@Id
@GeneratedValue(strategy = GenerationType.SEQUENCE)
 private  int id;
private String username;
@Column(name = "otp_code")
private  String optCode;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getOptCode() {
        return optCode;
    }

    public void setOptCode(String optCode) {
        this.optCode = optCode;
    }
}
