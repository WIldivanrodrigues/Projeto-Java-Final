package service;

import model.CadastroUser;
import java.io.*; // metodo para tratamento de exceções
import java.util.ArrayList; // metodo para arrayList
import java.util.List;

public class CadastroUserService {

    private static final String ARQUIVO = "usuario.txt";

    private int nextId = 1;

    // Construtor que já faz o scan inicial do arquivo para definir nextId
    public CadastroUserService() {
        try (BufferedReader br = new BufferedReader(new FileReader(ARQUIVO))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(";");
                int idExistente = Integer.parseInt(partes[0]);

                if (idExistente >= nextId) {
                    nextId = idExistente + 1;
                }
            }
        } catch (FileNotFoundException fnf) {

        } catch (IOException erro2) {
            System.out.println("Erro ao inicializar usuários: " + erro2.getMessage());
        }
    }

    public void salvar(CadastroUser usuario){
        // Verifica se e-mail já existe no arquivo
        try (BufferedReader br = new BufferedReader(new FileReader(ARQUIVO))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(";");
                if (partes[3].equalsIgnoreCase(usuario.getEmail())) {
                    throw new IllegalArgumentException("E-mail já cadastrado: " + usuario.getEmail());
                }
            }
        } catch (FileNotFoundException fnf) {

        } catch (IOException e) {
            System.out.println("Erro ao verificar duplicidade: " + e.getMessage());
        }

        usuario.setId(nextId++);
    }

    public void gravarEmArquivo(CadastroUser usuario) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARQUIVO, true))) {
            bw.write(
                    usuario.getId() + ";" +
                            usuario.getNome() + ";" +
                            usuario.getDataNascimento() + ";" +
                            usuario.getEmail() + ";" +
                            usuario.getSenha()
            );
            bw.newLine();
        } catch (IOException erro1) {
            System.out.println("Erro ao gravar usuário: " + erro1.getMessage());
        }
    }

    public boolean validarLogin(String email, String senha){
        try (BufferedReader br = new BufferedReader(new FileReader(ARQUIVO))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(";");
                if (partes[3].equals(email) && partes[4].equals(senha)) {
                    return true;
                }
            }
        } catch (IOException erro2) {
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
                CadastroUser user = new CadastroUser(
                        Integer.parseInt(partes[0]),
                        partes[1],
                        partes[2],
                        partes[3],
                        partes[4]
                );
                usuarios.add(user);
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler usuários: " + e.getMessage());
        }
        return usuarios;
    }
}