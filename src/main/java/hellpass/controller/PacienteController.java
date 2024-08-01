package hellpass.controller;

import hellpass.servicio.PacienteService;
import hellpass.model.Paciente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    @GetMapping("/")
    public String listarPacientes(Model model, @RequestParam(value = "keyword", required = false) String keyword) {
        if (keyword != null) {
            model.addAttribute("pacientes", pacienteService.buscarPacientes(keyword));
        } else {
            model.addAttribute("pacientes", pacienteService.obtenerTodosLosPacientes());
        }
        return "lista-pacientes";
    }

    @GetMapping("/pacientes/nuevo")
    public String mostrarFormularioDeNuevoPaciente(Model model) {
        model.addAttribute("paciente", new Paciente());
        return "formulario-paciente";
    }

    @PostMapping("/pacientes")
    public String guardarPaciente(@ModelAttribute("paciente") Paciente paciente) {
        pacienteService.guardarPaciente(paciente);
        return "redirect:/pacientes";
    }

    @GetMapping("/pacientes/editar/{id}")
    public String mostrarFormularioDeEditarPaciente(@PathVariable("id") Long id, Model model) {
        Paciente paciente = pacienteService.obtenerPacientePorId(id);
        model.addAttribute("paciente", paciente);
        return "formulario-paciente";
    }

    @PostMapping("/pacientes/{id}")
    public String actualizarPaciente(@PathVariable("id") Long id, @ModelAttribute("paciente") Paciente paciente) {
        Paciente pacienteExistente = pacienteService.obtenerPacientePorId(id);
        pacienteExistente.setNombre(paciente.getNombre());
        pacienteExistente.setApellido(paciente.getApellido());
        pacienteExistente.setDiagnostico(paciente.getDiagnostico());
        pacienteService.guardarPaciente(pacienteExistente);
        return "redirect:/pacientes";
    }

    @GetMapping("/pacientes/eliminar/{id}")
    public String eliminarPaciente(@PathVariable("id") Long id) {
        pacienteService.eliminarPaciente(id);
        return "redirect:/pacientes";
    }
}

