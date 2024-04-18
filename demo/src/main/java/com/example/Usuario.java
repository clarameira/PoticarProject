package com.example;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

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

    public static void cadastrarUsu(Scanner sc, List<Usuario> usuarios) {
        System.out.println(" Cadastro de Usuário ");
        System.out.print("Nome: ");
        String nome = sc.next();
        System.out.print("CPF: ");
        int cpf = sc.nextInt();
        System.out.print("Endereço: ");
        String endereco = sc.next();
        System.out.print("Número: ");
        int numero = sc.nextInt();
        System.out.print("Bairro: ");
        String bairro = sc.next();
        System.out.print("Telefone: ");
        String telefone = sc.next();

        Usuario novoUsuario = new Usuario(nome, cpf, endereco, numero, bairro, telefone);
        usuarios.add(novoUsuario);
        System.out.println("Usuário cadastrado com sucesso!");
    }

    public static void removerUsu(Scanner sc, List<Usuario> usuarios) {
        System.out.println(" Remoção de Usuário ");
        System.out.print("Digite o CPF do usuário que deseja remover: ");
        int cpf = sc.nextInt();

        Iterator<Usuario> iterator = usuarios.iterator();
        while (iterator.hasNext()) {
            Usuario usuario = iterator.next();
            if (usuario.getCPF() == cpf) {
                iterator.remove();
                System.out.println("Usuário removido com sucesso!");
                return;
            }
        }
        System.out.println("Usuário não encontrado.");
    }

    public static void editarUsu(Scanner sc, List<Usuario> usuarios) {
        System.out.println(" Edição de Usuário ");
        System.out.print("Digite o CPF do usuário que deseja editar: ");
        int cpf = sc.nextInt();

        for (Usuario usuario : usuarios) {
            if (usuario.getCPF() == cpf) {
                System.out.println("Digite os novos dados do usuário:");
                System.out.print("Nome: ");
                usuario.setNome(sc.next());
                System.out.print("Endereço: ");
                usuario.setEndereco(sc.next());
                System.out.print("Número: ");
                usuario.setNumero(sc.nextInt());
                System.out.print("Bairro: ");
                usuario.setBairro(sc.next());
                System.out.print("Telefone: ");
                usuario.setTelefone(sc.next());
                System.out.println("Usuário editado com sucesso!");
                return;
            }
        }
        System.out.println("Usuário não encontrado.");
    }

    public void visuUsu(){
        System.out.println("Nome: " + nome);
        System.out.println("CPF: " + CPF);
        System.out.println("Endereco: " + endereco);
        System.out.println("Numero: " + Numero);
        System.out.println("Bairro: " + bairro);
        System.out.println("Telefone: " + telefone);
    }

    public static void listarUsu(List<Usuario> usuarios) {
        System.out.println(" Lista de Usuários ");
        if (usuarios.isEmpty()) {
            System.out.println("Não há usuários cadastrados.");
        } else {
            for (Usuario usuario : usuarios) {
                System.out.println("Nome: " + usuario.getNome());
                System.out.println("CPF: " + usuario.getCPF());
                System.out.println("Endereço: " + usuario.getEndereco());
                System.out.println("Número: " + usuario.getNumero());
                System.out.println("Bairro: " + usuario.getBairro());
                System.out.println("Telefone: " + usuario.getTelefone());
                System.out.println("------------------------");
            }
        }
    }
}




