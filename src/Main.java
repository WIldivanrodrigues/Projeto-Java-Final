import controller.CadastroUserController;
import model.CadastroUser;
import service.CadastroUserService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner teclado = new Scanner(System.in);
        CadastroUserService userService = new CadastroUserService();
        CadastroUserController controller = new CadastroUserController(userService);

        while (true) {
            System.out.println("\n=-=-= MENU =-=-=");
            System.out.println("1 - Cadastrar usuário");
            System.out.println("2 - Fazer login");
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