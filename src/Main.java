import controller.CadastroUserController;
import model.CadastroUser;
import service.CadastroUserService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner teclado = new Scanner(System.in);
        CadastroUserService userService = new CadastroUserService();
        CadastroUserController controller = new CadastroUserController(userService);
        CadastroUser cadastro = new CadastroUser();

        System.out.println("=-=-=-Cadastro de usuários-=-=-=");

        //coletando dados por meio do metodo set.
        System.out.println("Insira o seu nome: ");
        cadastro.setNome(teclado.nextLine());

        System.out.println("Insira sua data de nascimento: ");
        cadastro.setDataNascimento(teclado.nextLine());

        System.out.println("Insira o seu e-mail: ");
        cadastro.setEmail(teclado.nextLine());

        System.out.println("Insira sua senha: ");
        cadastro.setSenha(teclado.nextLine());

        controller.cadastrarUsuario(cadastro);

        System.out.println("\nUsuário cadastrado com sucesso!");

        System.out.println("\n=-=-= Agora faça o Login =-=-=");

        System.out.println("E-mail: ");
        String loginEmail = teclado.nextLine();

        System.out.println("Senha: ");
        String loginSenha = teclado.nextLine();

        boolean sucesso = controller.fazerLogin(loginEmail, loginSenha);

        if (sucesso) {
            System.out.println(">>> Login efetuado com sucesso!");
        } else {
            System.out.println("!!! E-mail ou senha incorretos.");
        }

        teclado.close();

    }
}