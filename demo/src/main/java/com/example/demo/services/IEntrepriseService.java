package com.example.demo.services;

import com.example.demo.entities.Employe;
import com.example.demo.entities.Taches;

import java.util.List;

public interface IEntrepriseService {
    Employe saveEmploye(Employe employee);
    Employe updateEmploye(Long id, Employe updatedEmployee);
    void assignerTache(Long employeId, Taches taches);
    double etatTache(Long employeId);
    void deleteEmploye(Long id);
    List<Employe> getAllEmployes();
}
