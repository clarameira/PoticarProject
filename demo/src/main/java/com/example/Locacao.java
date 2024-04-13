package com.example;

import java.util.Date;
import java.util.List;

public class Locacao {
    
    private Date dataLoc;
    private Date dataDevolucao;
    private int quilometragem;
    private double valorLoc;
    private boolean devolvido;
    private boolean entregue;
    private boolean aprovado;

    public Locacao(Date dataLoc, Date dataDevolucao, int quilometragem, double valorLoc) {
        this.dataLoc = dataLoc;
        this.dataDevolucao = dataDevolucao;
        this.quilometragem = quilometragem;
        this.valorLoc = valorLoc;
        this.devolvido = false;
        this.entregue = false;
        this.aprovado = false;
    }

    public void registrarLoc(List<Locacao> listaLocacoes, Locacao novaLocacao) {
        listaLocacoes.add(novaLocacao);
        System.out.println("Locação registrada com sucesso!");
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
