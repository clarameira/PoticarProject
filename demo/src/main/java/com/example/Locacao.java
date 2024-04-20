package com.example;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Locacao {

    private Date dataLoc;
    private Date dataDevolucao;
    private int quilometragem;
    private double valorLoc;
    private boolean devolvido;
    private boolean entregue;
    private boolean aprovado;
    private int cpfLocador;
    private int idLocacao;

    public Locacao() {

    }

    public Locacao(Date dataLoc, Date dataDevolucao, int quilometragem, double valorLoc, int cpfLocador,
            int idLocacao) {
        this.dataLoc = dataLoc;
        this.dataDevolucao = dataDevolucao;
        this.quilometragem = quilometragem;
        this.valorLoc = valorLoc;
        this.devolvido = false;
        this.entregue = false;
        this.aprovado = false;
        this.cpfLocador = cpfLocador;
        this.idLocacao = idLocacao;
    }

    public static Locacao registrarLoc(Scanner sc) {

        Locacao locacao = new Locacao();
        System.out.println("Digite os novos dados do usuário:");
        System.out.print("Quilometragem: ");
        locacao.setQuilometragem(sc.nextInt());
        System.out.print("valor da locação: ");
        locacao.setValorLoc(sc.nextDouble());
        System.out.print("cpf do locador: ");
        locacao.setCpfLocador(sc.nextInt());
        System.out.print("id da locação: ");
        locacao.setIdLocacao(sc.nextInt());
        locacao.setAprovado(false);
        locacao.setDevolvido(false);
        locacao.setDevolvido(false);
        locacao.setDataLoc(new Date());
        locacao.setDataDevolucao(null);

        return locacao;
    }

    public void editarLoc(Date novaDataLoc, Date novaDataDevolucao, int novaQuilometragem, double novoValorLoc) {
        this.dataLoc = novaDataLoc;
        this.dataDevolucao = novaDataDevolucao;
        this.quilometragem = novaQuilometragem;
        this.valorLoc = novoValorLoc;
    }

    public void removerLoc(List<Locacao> listaLocacoes, Locacao locacao) {
        listaLocacoes.remove(locacao);
        System.out.println("Locação removida com sucesso!");
    }

    public void setCpfLocador(int cpfLocador) {
        this.cpfLocador = cpfLocador;
    }

    public int getIdLocacao() {
        return idLocacao;
    }

    public void setIdLocacao(int idLocacao) {
        this.idLocacao = idLocacao;
    }

    public void listarLoc(List<Locacao> listaLocacoes) {
        if (listaLocacoes.isEmpty()) {
            System.out.println("Não há locações cadastradas.");
        } else {
            System.out.println("Listagem de Locações:");
            for (Locacao locacao : listaLocacoes) {
                System.out.println("Data de Locação: " + locacao.getDataLoc());
                System.out.println("Data de Devolução: " + locacao.getDataDevolucao());
                System.out.println("Quilometragem: " + locacao.getQuilometragem());
                System.out.println("Valor de Locação: " + locacao.getValorLoc());
                System.out.println("Devolvido: " + (locacao.isDevolvido() ? "Sim" : "Não"));
                System.out.println("Entregue: " + (locacao.isEntregue() ? "Sim" : "Não"));
                System.out.println("Aprovado: " + (locacao.isAprovado() ? "Sim" : "Não"));
                System.out.println("--------------------------------------");
            }
        }
    }

    public void aprovarLoc() {
        this.aprovado = true;
    }

    public boolean carroAlugado() {
        return !devolvido && !entregue;
    }

    public void entregue() {
        this.entregue = true;
    }

    public void finalizarLoc() {
        this.devolvido = true;
    }

    public Date getDataLoc() {
        return dataLoc;
    }

    public void setDataLoc(Date dataLoc) {
        this.dataLoc = dataLoc;
    }

    public int getCpfLocador() {
        return this.cpfLocador;
    }

    public Date getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(Date dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public int getQuilometragem() {
        return quilometragem;
    }

    public void setQuilometragem(int quilometragem) {
        this.quilometragem = quilometragem;
    }

    public double getValorLoc() {
        return valorLoc;
    }

    public void setValorLoc(double valorLoc) {
        this.valorLoc = valorLoc;
    }

    public boolean isDevolvido() {
        return devolvido;
    }

    public void setDevolvido(boolean devolvido) {
        this.devolvido = devolvido;
    }

    public boolean isEntregue() {
        return entregue;
    }

    public void setEntregue(boolean entregue) {
        this.entregue = entregue;
    }

    public boolean isAprovado() {
        return aprovado;
    }

    public void setAprovado(boolean aprovado) {
        this.aprovado = aprovado;
    }

}