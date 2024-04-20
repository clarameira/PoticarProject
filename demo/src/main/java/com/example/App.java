package com.example;

import java.io.IOException;
import java.util.Scanner;


public class App {
    public static void main(String[] args) {
        int cpfAux;
        String placaAux;
        int idLocacaoAux;

        Scanner sc = new Scanner(System.in);
        System.out.println("----------------------------------");
        System.out.println("Deseja acessar como administrador?");
        System.out.println("1. Sim");
        System.out.println("2. Não");
        System.out.println("----------------------------------");
        int res = sc.nextInt();

        Controlador controlador = new Controlador();

        if (res == 1) {
            System.out.println("Acesso permitido como administrador!");
            int op = 1;

            do {
                System.out.println("\nSISTEMA DE LOCADORA\n");
                System.out.println("\n Selecione uma opção: \n");
                System.out.println("1 - Gerenciar clientes");
                System.out.println("2 - Gerenciar carros");
                System.out.println("3 - Gerenciar locações");
                System.out.println("4 - Sair");
                int op2 = 1;
                op = sc.nextInt();

                switch (op){
                    case 1:
                        while (op2 != 4){
                            System.out.println("\nGERENCIAMENTO DE CLIENTES\n");
                            System.out.println("\n Selecione uma opção: \n");
                            System.out.println("1 - Cadastrar cliente");
                            System.out.println("2 - Editar cliente");
                            System.out.println("3 - Ver dados do cliente");
                            System.out.println("4 - Excluir cliente");
                            System.out.println("5 - Listar clientes");
                            System.out.println("0 - Voltar\n");
                            op2 = sc.nextInt();
                            switch (op2) {
                                case 1:

                                    try {
                                        controlador.cadastrarCliente(Usuario.cadastrarUsu(sc));
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                    break;
                                case 2:
                                    System.out.print("Digite o CPF do usuário que deseja editar: ");
                                    cpfAux = sc.nextInt();
                                    try {
                                        controlador.editarClientePorCpf(cpfAux, Usuario.editarUsu(sc));
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                    break;
                                    case 3:
                                    System.out.print("Digite o CPF do usuário que deseja visualizar: ");
                                    cpfAux = sc.nextInt();
                                    try {
                                        controlador.verClientePorCpf(cpfAux);
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                    break;
                                case 4:
                                    System.out.print("Digite o CPF do usuário que deseja excluir: ");
                                    cpfAux = sc.nextInt();
                                    try {
                                        controlador.removerClientePorCpf(cpfAux);
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                    break;
                                case 5:
                                    try {
                                        controlador.listarClientes();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                    break;
                                case 0:
                                    System.out.println("\nRetornando para o menu principal");
                                    break;
                                default:
                                    System.out.println("\nOpção inválida, tente novamente");
                                    break;
                            }
                            
                        }
                        break;
                        case 2:
                        while (op2 != 0) {
                            System.out.println("\nMENU CARROS\n");
                            System.out.println("Selecione uma opção");
                            System.out.println("1 - Cadastrar carro");
                            System.out.println("2 - Editar dados do carro");
                            System.out.println("3 - Ver dados do carro");
                            System.out.println("4 - Excluir carro do sistema");
                            System.out.println("5 - Listar carros");
                            System.out.println("0 - Voltar\n");
                            op2 = sc.nextInt();
                            switch (op2) {
                                case 1:
                                    try {
                                        controlador.cadastrarCarro(Carro.cadastrarCar(sc));
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                    break;
                                case 2:
                                    System.out.print("Digite a placa do carro que deseja editar: ");
                                    placaAux = sc.next();
                                    try {
                                        controlador.editarCarroPorPlaca(placaAux, Carro.editarCar(sc));
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                    break;
                                    case 3:
                                    System.out.print("Digite a placa do carro que deseja visualizar: ");
                                    placaAux = sc.next();
                                    try {
                                        controlador.verCarroPorPlaca(placaAux);
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                    break;
                                case 4:
                                    System.out.print("Digite a placa do carro que deseja deletar: ");
                                    placaAux = sc.next();
                                    try {
                                        controlador.removerCarroPorPlaca(placaAux);
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                    break;
                                case 5:
                                    try {
                                        controlador.listarCarros();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                    break;
                                case 0:
                                    System.out.println("\nRetornando para o menu principal");
                                    break;
                                    default:
                                    System.out.println("\nOpção inválida");
                                    break;
                            }
                        }
                        break;
                }
            }
        }
    }
}

                        
                
