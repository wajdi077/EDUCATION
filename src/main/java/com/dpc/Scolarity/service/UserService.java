package com.dpc.Scolarity.service;



import java.util.List;

import com.dpc.Scolarity.Domain.Utilisateur;


public interface UserService {
    public Utilisateur findById(Long id);
    public Utilisateur findByUsername(String username);
    public List<Utilisateur> findAll ();
}
