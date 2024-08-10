package com.example.demo.controllers;

import com.example.demo.entities.Employe;
import com.example.demo.entities.Taches;
import com.example.demo.services.IEntrepriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employes")
public class EntrepriseController {
    @Autowired
    private IEntrepriseService entrepriseService;

    @GetMapping("/saveEmploye")
    //@PreAuthorize("hasAuthority('Admin')")
    public Employe saveEmploye(@RequestBody Employe employe) {
        return entrepriseService.saveEmploye(employe);
    }

    @PutMapping("/updateEmploye/{id}")
    //@PreAuthorize("hasAuthority('Admin')")
    public Employe updateEmploye(@PathVariable Long id,@RequestBody Employe employe) {
        return entrepriseService.updateEmploye(id,employe);
    }

    @PostMapping("/{id}/taches")
    public void assignTache( @PathVariable Long employeId ,@RequestBody Taches taches) {
       entrepriseService.assignerTache(employeId,taches);
    }

    @GetMapping("/etat")
    public double etatAvancement(@PathVariable Long employeId) {
        return entrepriseService.etatTache(employeId);
    }

    @GetMapping("/allEmpl")
    public List<Employe> allEmpl() {
        return entrepriseService.getAllEmployes();
    }

    @DeleteMapping("/deleteEmpl")
    public void deleteEmpl(@PathVariable Long employeId) {
        entrepriseService.deleteEmploye(employeId);
    }
}
