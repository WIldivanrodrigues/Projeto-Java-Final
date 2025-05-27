package service;

import model.Agendamento;
import java.util.ArrayList;
import java.util.List;
import java.io.*; // metodo para tratamento de exceções

public class AgendamentoService {

    List<Agendamento> agendamentos = new ArrayList<>();
    private static final String ARQUIVO = "agendamentos.txt";

    public void salvar(Agendamento agendamento) {
        agendamentos.add(agendamento);
    }

    public void gravarEmArquivo(Agendamento agendamento) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARQUIVO, true))) {
            bw.write(
                    agendamento.getDataHora() + ";" +
                        agendamento.getNomeUsuario() + ";" +
                        agendamento.getTipoServico()
            );
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Erro ao gravar agendamento: " + e.getMessage());
        }
    }

    public List<Agendamento> listarAgendamentos() {
        List<Agendamento> lista = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(ARQUIVO))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(";");
                Agendamento ag = new Agendamento();
                ag.setDataHora(partes[0]);
                ag.setNomeUsuario(partes[1]);
                ag.setTipoServico(partes[2]);
                lista.add(ag);
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler agendamentos: " + e.getMessage());
        }
        return lista;
    }
}