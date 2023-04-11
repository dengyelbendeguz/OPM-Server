package hu.opm.opm.password;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping(path = "api/v1/password")
public class PasswordController {
    private final PasswordService passwordService;

    @Autowired
    public PasswordController(PasswordService passwordService) {
        this.passwordService = passwordService;
    }
    @GetMapping
    public List<Password> getPassword() {
        return passwordService.getPasswords();
    }

    @PostMapping
    public void registerNewPassword(@RequestBody Password password) {
        passwordService.addNewPassword(password);
    }

    @DeleteMapping(path = "{passwordId}")
    public void deletePassword(@PathVariable("passwordId") Long passwordId) {
        passwordService.deletePassword(passwordId);
    }

    @PutMapping(path = "{passwordId}")
    public void updatePassword(
            @PathVariable("passwordId") Long passwordId,
            @RequestParam(required = false) String owner,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String username,
            @RequestParam(required = false) String password,
            @RequestParam(required = false) String webPage,
            @RequestParam(required = false) String comment){
        passwordService.updatePassword(passwordId, owner, title, username, password, webPage, comment);
    }
}