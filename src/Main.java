import model.CadastroUser;

import java.util.Scanner;

import java.io.FileWriter;            // fluxo básico de escrita em arquivo
import java.io.BufferedWriter;        // envolve FileWriter para escrita em buffer
import java.io.FileReader;            // fluxo básico de leitura de arquivo
import java.io.BufferedReader;        // envolve FileReader para leitura em buffer
import java.io.IOException;

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

        // Try-with-resources abre o BufferedWriter e fecha no final
        // FileWriter("usuario.txt", true) true = append, não sobrescreve o arquivo
        try (BufferedWriter bw = new BufferedWriter(
                new FileWriter("usuario.txt", true))) {

            // Grava todos os campos separados por ; em uma unica linha
            bw.write(
                    cadastro.getNome() + ";" +
                            cadastro.getDataNascimento() + ";" +
                            cadastro.getEmail() + ";" +
                            cadastro.getSenha()
            );
            bw.newLine(); // pula para a próxima linha

        } catch (IOException erro) {
            // Em caso de falha de I/O, exibe mensagem simples e prossegue
            System.out.println("Aviso: não foi possível salvar o usuário.");
        }

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

        boolean sucesso = false;

        try (BufferedReader br = new BufferedReader(
                new FileReader("usuario.txt"))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(";");
                // partes[2]=email, partes[3]=senha
                if (partes[2].equals(loginEmail)
                        && Integer.parseInt(partes[3]) == loginSenha) {
                    sucesso = true;
                    break;
                }
            }
        } catch (IOException erro) {
            // Em falha de leitura, avisa e considera falha no login
            System.out.println("Aviso: não foi possível ler o arquivo de usuários.");
        }

        if (sucesso) {
            System.out.println(">>> Login efetuado com sucesso!");
        } else {
            System.out.println("!!! E-mail ou senha incorretos.");
        }

        teclado.close();

    }
}