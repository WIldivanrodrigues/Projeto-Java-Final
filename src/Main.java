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

        teclado.close();








    }
}