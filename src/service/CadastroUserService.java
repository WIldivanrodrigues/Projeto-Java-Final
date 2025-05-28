package service;

import model.CadastroUser;
import java.io.*; // metodo para tratamento de exceções
import java.util.ArrayList; //metodo para arrayList
import java.util.List;

public class CadastroUserService  {

    //Criação do arrayList para coleta de inputs do usuário.
    List<CadastroUser> cadastros = new ArrayList<>();
    private static final String ARQUIVO = "usuario.txt";

    public void salvar(CadastroUser usuario){
        cadastros.add(usuario);
    }
    //Salva no txt coletando com os gets, os dados dos usuários..
    public void gravarEmArquivo(CadastroUser usuario) {
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(ARQUIVO, true))){
            bw.write(
                    usuario.getNome()
                            + ";" + usuario.getDataNascimento()
                            + ";" + usuario.getEmail()
                            + ";" + usuario.getSenha());
            bw.newLine();

            //tratamento de erro.
        }catch (IOException erro1){
            System.out.println("Erro ao gravar usuário: " + erro1.getMessage());
        }
    }

    public boolean validarLogin(String email, String senha){
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
    public List<CadastroUser> listarUsuarios() {
        List<CadastroUser> usuarios = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(ARQUIVO))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(";");
                CadastroUser user = new CadastroUser();
                user.setNome(partes[0]);
                user.setDataNascimento(partes[1]);
                user.setEmail(partes[2]);
                user.setSenha(partes[3]);
                usuarios.add(user);
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler usuários: " + e.getMessage());
        }
        return usuarios;
    }
}