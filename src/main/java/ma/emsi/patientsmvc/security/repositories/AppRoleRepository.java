package ma.emsi.patientsmvc.security.repositories;

import ma.emsi.patientsmvc.security.entities.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppRoleRepository extends JpaRepository<AppRole,Long> {

    AppRole findByRoleName(String rolename);

}
