import model.CadastroUser;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner teclado = new Scanner(System.in);
        CadastroUser cadastro = new CadastroUser();

        System.out.println("=-=-=-Cadastro de usuários-=-=-=");

        System.out.println("Insira o seu nome: ");
        cadastro.setNome(teclado.nextLine());

        System.out.println("Insira sua data de nascimento: ");
        cadastro.setDataNascimento(teclado.nextLine());

        System.out.println("Insira o seu e-mail: ");
        cadastro.setEmail(teclado.nextLine());

        System.out.println("Insira sua senha: ");
        cadastro.setSenha(teclado.nextInt());

        System.out.println("\nUsuário cadastrado com sucesso!");

        System.out.println("=-=- Dados cadastrados -=-=");
        System.out.println("Nome: " + cadastro.getNome());
        System.out.println("Data de nascimento: " + cadastro.getDataNascimento());
        System.out.println("E-mail: " + cadastro.getEmail());

        //login

        System.out.println("\n=-=-= Agora faça o Login =-=-=");

        System.out.println("E-mail: ");
        String loginEmail = teclado.next();

        System.out.println("Senha: ");
        int loginSenha = teclado.nextInt();

        if (loginEmail.equals(cadastro.getEmail()) && loginSenha == cadastro.getSenha()) {
            System.out.print("Login efetuado com sucesso");
        } else {
            System.out.print("Falha no login, E-mail ou senha incorretos.");
        }




        teclado.close();








    }
}