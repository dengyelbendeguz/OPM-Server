package hu.opm.opm.password;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table
public class Password {
    @Id
    @SequenceGenerator(
            name = "password_sequence",
            sequenceName = "password_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "password_sequence"
    )
    private Long id;
    @NotBlank
    private String owner;
    @NotBlank
    private String title;
    private String username;
    private String password;
    private String webPage;
    private String comment;

    public Password(Long id, String owner, String title, String username, String password, String webPage, String comment) {
        this.id = id;
        this.owner = owner;
        this.title = title;
        this.username = username;
        this.password = password;
        this.webPage = webPage;
        this.comment = comment;
    }

    public Password(String owner, String title, String username, String password, String webPage, String comment) {
        this.owner = owner;
        this.title = title;
        this.username = username;
        this.password = password;
        this.webPage = webPage;
        this.comment = comment;
    }

    public Password() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

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

    public String getWebPage() {
        return webPage;
    }

    public void setWebPage(String webPage) {
        this.webPage = webPage;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "Password{" +
                "id=" + id +
                ", owner='" + owner + '\'' +
                ", title='" + title + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", webPage='" + webPage + '\'' +
                ", comment='" + comment + '\'' +
                '}';
    }
}