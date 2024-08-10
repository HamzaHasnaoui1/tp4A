package com.example.demo.services;

import com.example.demo.entities.Employe;
import com.example.demo.entities.Status;
import com.example.demo.entities.Taches;
import com.example.demo.repositories.EmployeRepository;
import com.example.demo.repositories.EntrepriseRepository;
import com.example.demo.repositories.TachesRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


@Service
@Slf4j
@RequiredArgsConstructor
public class IEntrepriseServiceImpl implements IEntrepriseService {
private final EmployeRepository employeRepository;
private final EntrepriseRepository entrepriseRepository;
private final TachesRepository tachesRepository;

    @Override
    public Employe saveEmploye(Employe employee) {
        log.info("Saving employee " + employee.getNom());
        return employeRepository.save(employee);
    }

    @Transactional
    @Override
    public Employe updateEmploye( Long id, Employe updatedEmployee) {
        Employe employe=employeRepository.findById(id).orElseThrow(()->new EntityNotFoundException("Employee not found with id " + id));
        employe.setNom(updatedEmployee.getNom());
        employe.setRole(updatedEmployee.getRole());
        employe.setTachesList(updatedEmployee.getTachesList());
         employeRepository.save(employe);
        log.info("Updating employee " + updatedEmployee.getNom());
        return updatedEmployee;
    }

    @Override
    @Transactional
    public void assignerTache(Long employeId, Taches taches) {
    Employe employe=employeRepository.findById(employeId)
            .orElseThrow(() -> new IllegalArgumentException("Employé non trouvé avec l'ID: " + employeId));
    employe.getTachesList().add(taches);
    taches.setEmploye(employe);
    employeRepository.save(employe);
    }

    @Override
    public double etatTache(Long employeId) {
        Employe employe=employeRepository.findById(employeId).orElseThrow();
        long totalTaches=employe.getTachesList().size();
        long tachesTerminees=employe.getTachesList()
                .stream().filter(t -> t.getStatus().equals(Status.terminee)).count();
        if (tachesTerminees==0){
            return 0.0;
        }
        return (double) totalTaches/tachesTerminees * 100;
    }

    @Override
    public void deleteEmploye(Long id) {
    employeRepository.deleteById(id);
    }

    @Override
    public List<Employe> getAllEmployes() {
        return employeRepository.findAll();
    }
}
