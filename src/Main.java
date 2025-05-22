import controller.CadastroUserController;
import model.CadastroUser;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner teclado = new Scanner(System.in);
        CadastroUserController controller = new CadastroUserController();
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

        System.out.println("\nUsuário cadastrado com sucesso!");
        controller.cadastrarUsuario(cadastro);

        // Try-with-resources abre o BufferedWriter e fecha no final
        // FileWriter("usuario.txt", true) true = append, não sobrescreve o arquivo


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