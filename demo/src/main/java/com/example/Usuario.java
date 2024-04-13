package com.example;
import java.util.List;


public class Usuario {
    private String Nome;
    private int CPF;
    private String Endereco;
    private int Numero;
    private String Bairro;
    private String Telefone;
    
    public Usuario(String nome, int cPF, String endereco, int numero, String bairro, String telefone) {
        Nome = nome;
        CPF = cPF;
        Endereco = endereco;
        Numero = numero;
        Bairro = bairro;
        Telefone = telefone;
    }


    public void cadastrarUsu(List<Usuario> listausuarios, Usuario novoUsuario){
        listausuarios.add(novoUsuario);
        System.out.println("Cadastrado com sucesso!");
    }

    public void removerUsu(List<Usuario> listausuarios, Usuario removerUsuario){
        listausuarios.remove(removerUsuario);
        System.out.println("Removido com sucesso!");
    }

    public void editarUsu(String novonome, int novocpf, String novoendereco, int novonumero, String novobairro, String novotelefone){
        this.Nome = novonome;
        this.CPF = novocpf;
        this.Endereco = novoendereco;
        this.Numero = novonumero;
        this.Bairro = novobairro;
        this.Telefone = novotelefone;
        System.out.println("Informacoes atualizadas");
    }

    public void visuUsu(){
        System.out.println("Nome: " + Nome);
        System.out.println("CPF: " + CPF);
        System.out.println("Endereco: " + Endereco);
        System.out.println("Numero: " + Numero);
        System.out.println("Bairro: " + Bairro);
        System.out.println("Telefone: " + Telefone);
    }

    public void listarUsu(List<Usuario> listausuarios){
        for(Usuario Usuario: listausuarios){
            Usuario.visuUsu();
            System.out.println("Listados");
        }       
    }
}




