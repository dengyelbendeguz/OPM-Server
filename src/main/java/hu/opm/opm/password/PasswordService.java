package hu.opm.opm.password;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class PasswordService {
    private final PasswordRepository passwordRepository;

    @Autowired
    public PasswordService(PasswordRepository passwordRepository) {
        this.passwordRepository = passwordRepository;
    }

    public List<Password> getPasswords() {
        return passwordRepository.findAll();
    }

    public void addNewPassword(Password password) {
        Optional<Password> passwordOptional = passwordRepository.findPasswordByTitle(password.getTitle());
        if (passwordOptional.isPresent()) {
            throw new IllegalStateException("[-] title is taken");
        }
        passwordRepository.save(password);
    }

    public void deletePassword(Long passwordId) {
        if (!passwordRepository.existsById(passwordId)) {
            throw new IllegalStateException("[-] password id" + passwordId + " does not exists");
        }
        passwordRepository.deleteById(passwordId);
    }

    public void updatePassword(Long passwordId, String owner, String title, String username, String password, String webPage, String comment) {
        Password passwordRecord = passwordRepository.findById(passwordId).orElseThrow(() -> new IllegalStateException("[-] password id" + passwordId + " does not exists") );
        if(title != null && title.length() > 0 && !Objects.equals(passwordRecord.getTitle(), title)){
            passwordRecord.setTitle(title);
        }


        /*
        Student student = studentRepository.findById(studentId).orElseThrow(() -> new IllegalStateException("student with id " + studentId + " does not exists"));
        if (name != null && name.length() > 0 && !Objects.equals(student.getName(), name)) {
            student.setName(name);
        }
        if (email != null && email.length() > 0 && !Objects.equals(student.getEmail(), email)) {
            Optional<Student> studentOptional = studentRepository.findStudentByEmail(email);
            if (studentOptional.isPresent()) {
                throw new IllegalStateException("email is taken");
            }
            student.setEmail(email);
        }*/
    }
}
