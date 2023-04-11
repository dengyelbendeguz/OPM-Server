package hu.opm.opm.owner;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table
public class Owner {
    //private String Salt;
    @Id
    @SequenceGenerator(
            name = "owner_sequence",
            sequenceName = "owner_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "owner_sequence"
    )
    private Long id;
    @NotBlank
    private String MasterUsername;
    @NotBlank
    private String MasterPassword;


    public Owner(Long id, String masterUsername, String masterPassword) {
        this.id = id;
        MasterUsername = masterUsername;
        MasterPassword = masterPassword;
    }

    public Owner(String masterUsername, String masterPassword) {
        MasterUsername = masterUsername;
        MasterPassword = masterPassword;
    }

    public Owner() {
    }

    public Long getId() {
        return id;
    }

    public String getMasterUsername() {
        return MasterUsername;
    }

    public String getMasterPassword() {
        return MasterPassword;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setMasterUsername(String masterUsername) {
        MasterUsername = masterUsername;
    }

    public void setMasterPassword(String masterPassword) {
        MasterPassword = masterPassword;
    }

    @Override
    public String toString() {
        return "Owner{" + "id=" + id + ", MasterUsername='" + MasterUsername + '\'' + ", MasterPassword='" + MasterPassword + '\'' + '}';
    }
}