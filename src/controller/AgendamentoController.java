package controller;

import model.Agendamento;
import service.AgendamentoService;
import java.util.List;

public class AgendamentoController {

    private final AgendamentoService agendamentoService;

    public AgendamentoController(AgendamentoService agendamentoService) {
        this.agendamentoService = agendamentoService;
    }

    public void agendar(Agendamento ag) {
        agendamentoService.salvar(ag);
        agendamentoService.gravarEmArquivo(ag);
    }

    public List<Agendamento> listar() {
        return agendamentoService.listarAgendamentos();
    }
}
