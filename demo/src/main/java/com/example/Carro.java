package com.example;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
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

    public static void cadastrarCar(Scanner sc, List<Carro> carros) {
        System.out.println(" Cadastro de Carro ");
        System.out.print("Placa: ");
        String placa = sc.next();
        System.out.print("Cor: ");
        String cor = sc.next();
        System.out.print("Número de Portas: ");
        int numPortas = sc.nextInt();
        System.out.print("Quilometragem: ");
        int quilometragem = sc.nextInt();
        System.out.print("Chassi: ");
        String chassi = sc.next();
        System.out.print("Valor de Locação: ");
        double valorLoc = sc.nextDouble();
        System.out.print("Modelo: ");
        String modelo = sc.next();

        Carro novoCarro = new Carro(placa, cor, numPortas, quilometragem, chassi, valorLoc, modelo);
        carros.add(novoCarro);
        System.out.println("Carro cadastrado com sucesso!");
    }

    public static void editarCar(Scanner sc, List<Carro> carros) {
        System.out.println(" Edição de Carro ");
        System.out.print("Digite a placa do carro que deseja editar: ");
        String placa = sc.next();

        for (Carro carro : carros) {
            if (carro.getPlaca().equals(placa)) {
                System.out.println("Digite os novos dados do carro:");
                System.out.print("Cor: ");
                carro.setCor(sc.next());
                System.out.print("Número de Portas: ");
                carro.setNumPortas(sc.nextInt());
                System.out.print("Quilometragem: ");
                carro.setQuilometragem(sc.nextInt());
                System.out.print("Chassi: ");
                carro.setChassi(sc.next());
                System.out.print("Valor de Locação: ");
                carro.setValorLoc(sc.nextDouble());
                System.out.print("Modelo: ");
                carro.setModelo(sc.next());
                System.out.println("Carro editado com sucesso!");
                return;
            }
        }
        System.out.println("Carro não encontrado.");
    }

    public static void removerCar(Scanner sc, List<Carro> carros) {
        System.out.println(" Remoção de Carro ");
        System.out.print("Digite a placa do carro que deseja remover: ");
        String placa = sc.next();

        Iterator<Carro> iterator = carros.iterator();
        while (iterator.hasNext()) {
            Carro carro = iterator.next();
            if (carro.getPlaca().equals(placa)) {
                iterator.remove();
                System.out.println("Carro removido com sucesso!");
                return;
            }
        }
        System.out.println("Carro não encontrado.");
    }

    public static void listarCar(List<Carro> carros) {
        System.out.println(" Lista de Carros ");
        if (carros.isEmpty()) {
            System.out.println("Não há carros cadastrados.");
        } else {
            for (Carro carro : carros) {
                System.out.println("Placa: " + carro.getPlaca());
                System.out.println("Cor: " + carro.getCor());
                System.out.println("Número de Portas: " + carro.getNumPortas());
                System.out.println("Quilometragem: " + carro.getQuilometragem());
                System.out.println("Chassi: " + carro.getChassi());
                System.out.println("Valor de Locação: " + carro.getValorLoc());
                System.out.println("Modelo: " + carro.getModelo());
                System.out.println("Disponível: " + (carro.isDisponivel() ? "Sim" : "Não"));
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
