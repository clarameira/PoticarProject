package com.example;

import java.util.List;

public class Usuario {
    private String nome;
    private int CPF;
    private String endereco;
    private int numero;
    private String bairro;
    private String telefone;
    private int Numero;
    
    public Usuario(String nome, int CPF, String endereco, int numero, String bairro, String telefone) {
        this.nome = nome;
        this.CPF = CPF;
        this.endereco = endereco;
        this.numero = numero;
        this.bairro = bairro;
        this.telefone = telefone;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getCPF() {
        return CPF;
    }

    public void setCPF(int CPF) {
        this.CPF = CPF;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
       this.endereco = endereco;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
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
        this.nome = novonome;
        this.CPF = novocpf;
        this.endereco = novoendereco;
        this.Numero = novonumero;
        this.bairro = novobairro;
        this.telefone = novotelefone;
        System.out.println("Informacoes atualizadas");
    }

    public void visuUsu(){
        System.out.println("Nome: " + nome);
        System.out.println("CPF: " + CPF);
        System.out.println("Endereco: " + endereco);
        System.out.println("Numero: " + Numero);
        System.out.println("Bairro: " + bairro);
        System.out.println("Telefone: " + telefone);
    }

    public void listarUsu(List<Usuario> listausuarios){
        for(Usuario Usuario: listausuarios){
            Usuario.visuUsu();
            System.out.println("Listados");
        }       
    }
}




