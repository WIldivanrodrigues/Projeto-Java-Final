package service;

import model.CadastroUser;
import java.io.*; // metodo para tratamento de exceções
import java.util.ArrayList; //metodo para arrayList
import java.util.List;

public class CadastroUserService  {

    //Criação do arrayList para coleta de inputs do usuário.
    List<CadastroUser> cadastros = new ArrayList<>();
    private static final String ARQUIVO = "usuario.txt";

    public void salvar(CadastroUser user){
        cadastros.add(user);
    }
    //Salva no txt coletando com os gets, os dados dos usuários..
    public void GravarEmArquivo(CadastroUser user) {
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(ARQUIVO, true))){
            bw.write(user.getNome() + ";" + user.getDataNascimento() + ";" + user.getEmail() + ";" + user.getSenha());
            bw.newLine();

            //tratamento de erro.
        }catch (IOException erro1){
            System.out.println("Erro ao gravar usuário: " + erro1.getMessage());
        }
    }

    public boolean ValidarLogin(String email, String senha){
        try(BufferedReader br = new BufferedReader(new FileReader(ARQUIVO))){
            String linha;
            while((linha = br.readLine()) !=null){
                String[] partes = linha.split(";");
                if (partes[2].equals(email) && partes[3].equals(senha)){
                    return true;
                }
            }
            //Tratamento de erro.
        }catch (IOException erro2){
            System.out.println("Erro ao ler usuários: " + erro2.getMessage());
        }
        return false;

    }
}