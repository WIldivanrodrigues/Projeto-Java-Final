import controller.CadastroUserController;
import model.CadastroUser;
import service.CadastroUserService;
import controller.AgendamentoController;
import service.AgendamentoService;
import model.Agendamento;
import java.util.List;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner teclado = new Scanner(System.in);
        CadastroUserService userService = new CadastroUserService();
        CadastroUserController controller = new CadastroUserController(userService);
        AgendamentoService agService = new AgendamentoService();
        AgendamentoController agController = new AgendamentoController(agService);

        while (true) {
            System.out.println("\n=-=-= MENU =-=-=");
            System.out.println("1 - Cadastrar usuário");
            System.out.println("2 - Fazer login");
            System.out.println("3 - Agendar horário");
            System.out.println("4 - Listar agendamentos");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");

            String opcao = teclado.nextLine();

            if (opcao.equals("1")) {
                CadastroUser novoUsuario = new CadastroUser();

                System.out.print("Nome: ");
                novoUsuario.setNome(teclado.nextLine());

                System.out.print("Data de nascimento (DD-MM-AAAA): ");
                novoUsuario.setDataNascimento(teclado.nextLine());

                System.out.print("E-mail: ");
                novoUsuario.setEmail(teclado.nextLine());

                System.out.print("Senha: ");
                novoUsuario.setSenha(teclado.nextLine());

                controller.cadastrarUsuario(novoUsuario);
                
            } else if (opcao.equals("2")) {

                System.out.print("E-mail: ");
                String email = teclado.nextLine();

                System.out.print("Senha: ");
                String senha = teclado.nextLine();

                if (controller.fazerLogin(email, senha)) {
                    System.out.println(">>> Login efetuado com sucesso!");
                } else {
                    System.out.println("!!! E-mail ou senha inválidos.");
                }
            } else if (opcao.equals("3")) {
                Agendamento ag = new Agendamento();
                System.out.print("Data e hora (dd-mm-aaaa hh:mm): ");
                ag.setDataHora(teclado.nextLine());
                System.out.print("Nome do usuário: ");
                ag.setNomeUsuario(teclado.nextLine());

                System.out.println("Tipo de serviço:");
                System.out.println("1 - Instalação de Unhas em Gel");
                System.out.println("2 - Manutenção de Unhas");
                System.out.println("3 - Banho de Gel");
                System.out.println("4 - Cutilação Simples");
                System.out.print("Opção: ");
                String servOpcao = teclado.nextLine();
                switch (servOpcao) {
                    case "1": ag.setTipoServico("Instalação de Unhas em Gel"); break;
                    case "2": ag.setTipoServico("Manutenção de Unhas"); break;
                    case "3": ag.setTipoServico("Banho de Gel"); break;
                    case "4": ag.setTipoServico("Cutilação Simples"); break;
                    default:
                        System.out.println("Opção inválida para serviço.");
                        continue;
                }

                agController.agendar(ag);
                System.out.println(">>> Agendamento realizado com sucesso!");
                
            } else if (opcao.equals("4")) {
                List<Agendamento> lista = agController.listar();
                System.out.println("\n-- Agendamentos --");
                if (lista.isEmpty()) {
                    System.out.println("Nenhum agendamento encontrado.");
                } else {
                    for (Agendamento a : lista) {
                        System.out.println(
                                a.getDataHora() + " | " +
                                a.getNomeUsuario() + " | " +
                                a.getTipoServico()
                        );
                    }
                }

            } else if (opcao.equals("0")) {
                System.out.println("Saindo...");
                break;
            } else {
                System.out.println("Opção inválida.");
            }
        }

        teclado.close();

    }
}