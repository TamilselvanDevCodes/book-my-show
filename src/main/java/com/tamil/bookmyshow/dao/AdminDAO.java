package com.tamil.bookmyshow.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tamil.bookmyshow.entity.AdminEntity;
import com.tamil.bookmyshow.repositry.AdminRepo;

@Repository
public class AdminDAO {
    @Autowired
    private AdminRepo repo;

    public AdminEntity save(AdminEntity admin) {
        return repo.save(admin);
    }

    public AdminEntity find(String email) {
        Optional<AdminEntity> opt = repo.findById(email);

        if (opt.isPresent()) return opt.get();
        return null;
    }

    public AdminEntity find(long contactNumber) {
        Optional<AdminEntity> opt = repo.findByContactNumber(contactNumber);
        if (opt.isPresent()) return opt.get();
        return null;
    }

    public AdminEntity update(AdminEntity admin, String email) {
        AdminEntity e = find(email);
        if (e == null || admin == null) return null;
        admin.setEmail(e.getEmail());
        return repo.save(admin);
    }

    public AdminEntity delete(String email) {
        AdminEntity a = find(email);
        if (a == null) return null;
        repo.deleteById(email);
        return a;
    }
}
