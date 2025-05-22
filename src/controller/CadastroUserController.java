package controller;

import model.CadastroUser;
import service.CadastroUserService;

//Estanciando a service.
public class CadastroUserController {
    private final CadastroUserService cadastroUserService; // declaração de método.

    public CadastroUserController(CadastroUserService cadastroUserService){
        this.cadastroUserService = cadastroUserService;
    }
    //Recebe o cadastro(user-service) salva e grava o arquivo no txt.
    public void cadastrarUsuario (CadastroUser user){
        cadastroUserService.salvar(user);
        cadastroUserService.gravarEmArquivo(user);
    }

    //Validação do login(retorna true or false(recebe da service)).
    public boolean fazerLogin(String email, String senha){
        return cadastroUserService.validarLogin(email, senha);
    }
}