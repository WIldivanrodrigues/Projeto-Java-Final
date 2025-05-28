import controller.CadastroUserController;
import controller.GestorController;
import model.CadastroUser;
import service.CadastroUserService;
import controller.AgendamentoController;
import service.AgendamentoService;
import model.Agendamento;
import model.LogGestor;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner teclado = new Scanner(System.in);
        CadastroUserService userService = new CadastroUserService();
        CadastroUserController controller = new CadastroUserController(userService);
        AgendamentoService agService = new AgendamentoService();
        AgendamentoController agController = new AgendamentoController(agService);
        LogGestor lGestor = new LogGestor();
        GestorController gestorController = new GestorController(userService, agService);

        while (true) {
            System.out.println("\n=-=-= MENU PRINCIPAL =-=-=");
            System.out.println("1 - Cadastrar usuário");
            System.out.println("2 - Fazer login");
            System.out.println("3 - Agendar horário");
            System.out.println("4 - Login Gestor");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");

            String opcao = teclado.nextLine();

            switch (opcao) {

                case "1":
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
                    break;

                case "2":
                    System.out.print("E-mail: ");
                    String email = teclado.nextLine();

                    System.out.print("Senha: ");
                    String senha = teclado.nextLine();

                    if (controller.fazerLogin(email, senha)) {
                        System.out.println(">>> Login efetuado com sucesso!");
                    } else {
                        System.out.println("!!! E-mail ou senha inválidos.");
                    }
                    break;

                case "3":
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
                        case "1":
                            ag.setTipoServico("Instalação de Unhas em Gel");
                            break;
                        case "2":
                            ag.setTipoServico("Manutenção de Unhas");
                            break;
                        case "3":
                            ag.setTipoServico("Banho de Gel");
                            break;
                        case "4":
                            ag.setTipoServico("Cutilação Simples");
                            break;
                        default:
                            System.out.println("Opção inválida para serviço.");
                            break;
                    }

                    agController.agendar(ag);
                    System.out.println(">>> Agendamento realizado com sucesso!");
                    break;

                case "4":
                    boolean autGestor = false;

                    while (!autGestor) {
                        System.out.println("Login gestor: ");
                        String emailGestor = teclado.nextLine();

                        System.out.println("Senha gestor: ");
                        String senhaGestor = teclado.nextLine();

                        if (lGestor.validarGestor(emailGestor, senhaGestor)) {
                            System.out.println("Login de gestor efetuado, bem-vindo!");
                            autGestor = true;

                            while (true) {
                                System.out.println("=-=-MENU GESTOR-=-=");
                                System.out.println("1 - Listar todos os agendamentos");
                                System.out.println("2 - Listar todos os usuários");
                                System.out.println("0 - Sair do menu gestor");
                                System.out.print("Escolha uma opção: ");

                                String opcaoGestor = teclado.nextLine();

                                if (opcaoGestor.equals("1")) {
                                    List<Agendamento> lista = agController.listar();
                                    System.out.println("\n=-Agendamentos realizados-=");
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
                                } else if (opcaoGestor.equals("2")) {
                                    List<CadastroUser> listaUsuarios = gestorController.listarUsuarios();
                                    System.out.println("\n=-Usuários cadastrados-=");
                                    if (listaUsuarios.isEmpty()) {
                                        System.out.println("Nenhum usuário cadastrado.");
                                    } else {
                                        for (CadastroUser u : listaUsuarios) {
                                            System.out.println("Nome: " + u.getNome() + " | Email: " + u.getEmail());
                                        }
                                    }
                                } else if (opcaoGestor.equals("0")) {
                                    System.out.println("Saindo do menu gestor...");
                                    break;
                                } else {
                                    System.out.println("Opção inválida.");
                                }
                            }
                        } else {
                            System.out.println("Login ou senha do gestor incorretos.");
                            System.out.print("Deseja tentar novamente? (s/n): ");
                            String tentar = teclado.nextLine();
                            if (!tentar.equalsIgnoreCase("s")) { //
                                System.out.println("Saindo do login gestor...");
                                break; // Sai do loop (!autGestor)
                            }
                        }
                    }
                    break;

                case "0":
                    System.out.println("Saindo...");
                    teclado.close();        //Encerrando o teclado.
                    System.exit(0);  //Encerrando do programa.
                    break;

                default:
                    System.out.println("Opção inválida.");
                    break;
            }
        }
    }
}
