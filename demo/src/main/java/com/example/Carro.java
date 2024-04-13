package com.example;

import java.util.ArrayList;
import java.util.List;
public class Carro {
    
    private String placa;
    private String cor;
    private int numPortas;
    private int quilometragem;
    private String chassi;
    private double valorLoc;
    private String modelo;
    private boolean disponivel;

    public Carro(String placa, String cor, int numPortas, int quilometragem, String chassi, double valorLoc, String modelo) {
        this.placa = placa;
        this.cor = cor;
        this.numPortas = numPortas;
        this.quilometragem = quilometragem;
        this.chassi = chassi;
        this.valorLoc = valorLoc;
        this.modelo = modelo;
        this.disponivel = true; 
    }

    public void visuCar() {
        System.out.println("Placa: " + placa);
        System.out.println("Cor: " + cor);
        System.out.println("Número de Portas: " + numPortas);
        System.out.println("Quilometragem: " + quilometragem);
        System.out.println("Chassi: " + chassi);
        System.out.println("Valor de Locação: " + valorLoc);
        System.out.println("Modelo: " + modelo);
        System.out.println("Disponível: " + (disponivel ? "Sim" : "Não"));
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public int getNumPortas() {
        return numPortas;
    }

    public void setNumPortas(int numPortas) {
        this.numPortas = numPortas;
    }

    public int getQuilometragem() {
        return quilometragem;
    }

    public void setQuilometragem(int quilometragem) {
        this.quilometragem = quilometragem;
    }

    public String getChassi() {
        return chassi;
    }

    public void setChassi(String chassi) {
        this.chassi = chassi;
    }

    public double getValorLoc() {
        return valorLoc;
    }

    public void setValorLoc(double valorLoc) {
        this.valorLoc = valorLoc;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public void cadastrarCar(List<Carro> listaCarros, Carro novoCarro) {
        listaCarros.add(novoCarro);
        System.out.println("Carro cadastrado com sucesso!");
    }

    public void editarCar(Carro carro, String novaPlaca, String novaCor, int novoNumPortas, int novaQuilometragem, String novoChassi, double novoValorLoc, String novoModelo) {
        carro.placa = novaPlaca;
        carro.cor = novaCor;
        carro.numPortas = novoNumPortas;
        carro.quilometragem = novaQuilometragem;
        carro.chassi = novoChassi;
        carro.valorLoc = novoValorLoc;
        carro.modelo = novoModelo;
        System.out.println("Informações do carro atualizadas com sucesso!");
    }

    public void removerCar(List<Carro> listaCarros, Carro carro) {
        listaCarros.remove(carro);
        System.out.println("Carro removido com sucesso!");
    }

    public void listarCar(List<Carro> listaCarros) {
        if (listaCarros.isEmpty()) {
            System.out.println("Não há carros cadastrados.");
        } else {
            for (Carro carro : listaCarros) {
                carro.visuCar();
                System.out.println("------------------------");
            }
        }
    }

    public void listarCarDisp(List<Carro> listaCarros) {
        List<Carro> carrosDisponiveis = new ArrayList<>();
        for (Carro carro : listaCarros) {
            if (carro.disponivel) {
                carrosDisponiveis.add(carro);
            }
        }
        if (carrosDisponiveis.isEmpty()) {
            System.out.println("Não há carros disponíveis.");
        } else {
            for (Carro carro : carrosDisponiveis) {
                carro.visuCar();
                System.out.println("------------------------");
            }
        }
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }
}
