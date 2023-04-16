package hu.opm.opm.owner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class OwnerService {
    private final OwnerRepository ownerRepository;

    @Autowired
    public OwnerService(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    public List<Owner> getOwners() {
        return ownerRepository.findAll();
    }

    public void addNewOwner(Owner owner) {
        Optional<Owner> ownerOptional = ownerRepository.findOwnerByMasterUsername(owner.getMasterUsername());
        if (ownerOptional.isPresent()) {
            throw new IllegalStateException("[-] master username is taken");
        }
        ownerRepository.save(owner);
    }

    public void deleteOwner(Long ownerId) {
        if (!ownerRepository.existsById(ownerId)) {
            throw new IllegalStateException("[-] owner id" + ownerId + " does not exists");
        }
        ownerRepository.deleteById(ownerId);
    }

    @Transactional
    public void updateOwner(Long ownerId, String masterUsername, String masterPassword) {
        Owner owner = ownerRepository.findById(ownerId).orElseThrow(() -> new IllegalStateException("[-] owner id" + ownerId + " does not exists"));
        if (masterUsername != null && masterUsername.length() > 0 && !Objects.equals(owner.getMasterUsername(), masterUsername)) {
            owner.setMasterUsername(masterUsername);
        }
        if (masterPassword != null && masterPassword.length() > 0 && !Objects.equals(owner.getMasterPassword(), masterPassword)) {
            Optional<Owner> ownerOptional = ownerRepository.findOwnerByMasterUsername(masterUsername);
            if (ownerOptional.isPresent()) {
                throw new IllegalStateException("[-] master username is taken");
            }
            owner.setMasterPassword(masterPassword);
        }
    }
    public OwnerRepository getOwnerRepository(){
        return ownerRepository;
    }
}
