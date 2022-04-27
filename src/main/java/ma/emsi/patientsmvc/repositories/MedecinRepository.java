package ma.emsi.patientsmvc.repositories;

import ma.emsi.patientsmvc.entities.Medecin;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedecinRepository  extends JpaRepository<Medecin,Long> {
    //Page<Medecin> findByNomContains(String nom,Pageable pageable);
    Page<Medecin> findByNomContainsOrEmailContainsOrSpecialiteContains(String nom, String email,String specialite, Pageable pageable);

}