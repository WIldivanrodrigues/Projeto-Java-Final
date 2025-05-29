package service;

import model.Agendamento;
import java.io.*; // metodo para tratamento de exceções
import java.util.ArrayList; // metodo para arrayList
import java.util.List;

public class AgendamentoService {

    private static final String ARQUIVO = "agendamentos.txt";
    private List<Agendamento> agendamentos = new ArrayList<>();

    public void salvar(Agendamento ag) {
        agendamentos.add(ag);
        gravarEmArquivo(ag);
    }

    public void gravarEmArquivo(Agendamento ag) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARQUIVO, true))) {
            bw.write(
                    ag.getDataHora()      + ";" +
                            ag.getUserId()        + ";" +
                            ag.getNomeUsuario()   + ";" +
                            ag.getTipoServico()
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
                ag.setUserId(Integer.parseInt(partes[1]));
                ag.setNomeUsuario(partes[2]);
                ag.setTipoServico(partes[3]);
                lista.add(ag);
            }
        } catch (FileNotFoundException fnf) {

        } catch (IOException e) {
            System.out.println("Erro ao ler agendamentos: " + e.getMessage());
        }
        return lista;
    }
}