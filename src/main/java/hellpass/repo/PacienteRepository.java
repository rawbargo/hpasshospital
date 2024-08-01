package hellpass.repo;

import hellpass.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {

    @Query("SELECT p FROM Paciente p WHERE LOWER(p.nombre) LIKE LOWER(CONCAT('%', :keyword, '%')) OR LOWER(p.diagnostico) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Paciente> buscarPorNombreODiagnostico(@Param("keyword") String keyword);
}
