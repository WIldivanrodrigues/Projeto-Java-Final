package controller;

import model.Agendamento;
import model.CadastroUser;
import service.AgendamentoService;
import service.CadastroUserService;
import java.util.List;

public class GestorController {

    private final CadastroUserService userService;
    private final AgendamentoService agendamentoService;

    public GestorController(CadastroUserService userService, AgendamentoService agendamentoService) {
        this.userService = userService;
        this.agendamentoService = agendamentoService;
    }

    public List<CadastroUser> listarUsuarios() {
        return userService.listarUsuarios();
    }

    public List<Agendamento> listarAgendamentos() {
        return agendamentoService.listarAgendamentos();
    }
}
