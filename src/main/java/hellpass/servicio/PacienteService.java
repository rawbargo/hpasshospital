package hellpass.servicio;

import hellpass.repo.PacienteRepository;
import hellpass.model.Paciente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    public List<Paciente> obtenerTodosLosPacientes() {
        return pacienteRepository.findAll();
    }

    public List<Paciente> buscarPacientes(String keyword) {
        if (keyword != null) {
            return pacienteRepository.buscarPorNombreODiagnostico(keyword);
        }
        return obtenerTodosLosPacientes();
    }

    public void guardarPaciente(Paciente paciente) {
        pacienteRepository.save(paciente);
    }

    public Paciente obtenerPacientePorId(Long id) {
        return pacienteRepository.findById(id).orElse(null);
    }

    public void eliminarPaciente(Long id) {
        pacienteRepository.deleteById(id);
    }
}

