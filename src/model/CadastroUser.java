package model;

public class CadastroUser {

    private String Nome;
    private String DataNascimento;
    private String Email;
    private int Senha;

    public CadastroUser(){

    }


    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public String getDataNascimento() {
        return DataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        DataNascimento = dataNascimento;
    }

    public String getEmail() {

        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public int getSenha() {
        return Senha;
    }

    public void setSenha(int senha) {
        this.Senha = senha;
    }
}

