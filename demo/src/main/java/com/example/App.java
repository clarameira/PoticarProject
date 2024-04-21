package com.example;

import java.io.IOException;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {

        int cpfAux;
        String placaAux;
        int idLocacaoAux;

        Scanner sc = new Scanner(System.in);
        System.out.println("-----------------------------------------");
        System.out.println("===== BEM-VINDO A PLATAFORMA POTICAR =====");
        System.out.println("-----------------------------------------");
        System.out.println("Deseja acessar como administrador?");
        System.out.println("1. Sim");
        System.out.println("2. Não");
        System.out.println("-----------------------------------------");
        int res = sc.nextInt();

        Controlador controlador = new Controlador();

        if (res == 1) {
            System.out.println("Acesso permitido como administrador!");
            limparTela();
            int op = 1;

            do {
                System.out.println("\n-----------------------------------------");
                System.out.println("\n==== SISTEMA DE LOCAÇÃO DE VEÍCULOS =====\n");
                System.out.println("-----------------------------------------");
                System.out.println("Selecione uma opção: ");
                System.out.println("-----------------------------------------");
                System.out.println("1. Gerenciar clientes");
                System.out.println("2. Gerenciar carros");
                System.out.println("3. Gerenciar locações");
                System.out.println("4. Sair");
                int op2 = 1;
                op = sc.nextInt();
                limparTela();

                switch (op) {
                    case 1:
                        while (op2 != 4) {
                            System.out.println("-----------------------------------------");
                            System.out.println("   ===== GERENCIAMENTO DE CLIENTES ===== ");
                            System.out.println("-----------------------------------------");
                            System.out.println("Selecione uma opção:");
                            System.out.println("-----------------------------------------");
                            System.out.println("1. Cadastrar cliente");
                            System.out.println("2. Editar cliente");
                            System.out.println("3. Ver dados do cliente");
                            System.out.println("4. Excluir cliente");
                            System.out.println("5. Listar clientes");
                            System.out.println("0. Voltar\n");
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
                                    System.out.println("\nRetornando...");
                                    break;
                                default:
                                    System.out.println("\nOpção inválida, tente novamente!");
                                    break;
                            }
                        }
                        break;
            
                    case 2:
                        while (op2 != 0) {
                            System.out.println("\n-----------------------");
                            System.out.println("\n==== MENU CARROS =====");
                            System.out.println("-------------------------");
                            System.out.println("Selecione uma opção");
                            System.out.println("--------------------------");
                            System.out.println("1. Cadastrar carro");
                            System.out.println("2. Editar dados do carro");
                            System.out.println("3. Ver dados do carro");
                            System.out.println("4. Excluir carro do sistema");
                            System.out.println("5. Listar carros");
                            System.out.println("0. Voltar\n");
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
                                    System.out.println("\nRetornando...");
                                    break;
                                default:
                                    System.out.println("\nOpção inválida.");
                                    break;
                            }
                        }
                        break;
                    case 3:
                        while (op2 != 0) {
                            System.out.println("\n--------------------------");
                            System.out.println("\n==== MENU LOCAÇÕES =====");
                            System.out.println("----------------------------");
                            System.out.println("Selecione uma opção");
                            System.out.println("----------------------------");
                            System.out.println("1. Cadastrar locação");
                            System.out.println("2. Ver dados de uma locação ");
                            System.out.println("3. Excluir locação");
                            System.out.println("4. Listar locações");
                            System.out.println("0. Voltar\n");
                            op2 = sc.nextInt();
                            switch (op2) {
                                case 1:
                                    try {
                                        controlador.cadastrarLocacao(Locacao.registrarLoc(sc));
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                    break;
                                case 2:
                                    System.out.print("Digite o ID da locação: ");
                                    idLocacaoAux = sc.nextInt();
                                    System.out.print("Digite o CPF do locador: ");
                                    cpfAux = sc.nextInt();
                                    try {
                                        controlador.verLocacao(cpfAux, idLocacaoAux);
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                    break;
                                case 3:
                                    System.out.print("Digite o ID da locação: ");
                                    idLocacaoAux = sc.nextInt();
                                    System.out.print("Digite o CPF do locador: ");
                                    cpfAux = sc.nextInt();
                                    try {
                                        controlador.removerLocacao(cpfAux, idLocacaoAux);
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                    break;
                                case 4:
                                    try {
                                        controlador.listarTodasAsLocacoes();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                    break;
                                case 0:
                                    System.out.println("\nRetornando...");
                                    break;
                                default:
                                    System.out.println("\nOpção inválida");
                                    break;
                            }
                        }
                        break;
                    case 0:
                        System.out.println("\nEncerrando sistema\n");
                        break;
                    default:
                        System.out.println("Opção inválida.");
                        break;
                }
            } while (op != 0);

        } else {
            System.out.println("Acesso como cliente.");
            int op = 1;

            limparTela();

            do {
                System.out.println("\n-----------------------------------------");
                System.out.println("\n==== SISTEMA DE LOCAÇÃO DE VEÍCULOS =====");
                System.out.println("-------------------------------------------");
                System.out.println("Selecione uma opção");
                System.out.println("-------------------------------------------");
                System.out.println("1. Menu cliente");
                System.out.println("2. Menu locação");
                System.out.println("0. Sair\n");
                int op2 = 1;
                op = sc.nextInt();
                limparTela();
                switch (op) {
                    case 1:
                        while (op2 != 0) {
                            System.out.println("--------------------------");
                            System.out.println("\n==== MENU CLIENTE =====");
                            System.out.println("--------------------------");
                            System.out.println("Selecione uma opção");
                            System.out.println("---------------------------");
                            System.out.println("1. Cadastrar cliente");
                            System.out.println("2. Ver dados do cliente");
                            System.out.println("0. Voltar\n");
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
                                    System.out.print("Digite o CPF do usuário que deseja visualizar: ");
                                    cpfAux = sc.nextInt();
                                    try {
                                        controlador.verClientePorCpf(cpfAux);
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                    break;
                                case 0:
                                    System.out.println("\nRetornando...");
                                    break;
                                default:
                                    System.out.println("\nOpção inválida.");
                                    break;
                            }
                        }
                        break;
                    case 2:
                        while (op2 != 0) {
                            System.out.println("\n-----------------------");
                            System.out.println("\n==== MENU LOCAÇÕES ====");
                            System.out.println("-------------------------");
                            System.out.println("Selecione uma opção");
                            System.out.println("-------------------------");
                            System.out.println("1. Cadastrar locação");
                            System.out.println("2. Ver dados de uma locação");
                            System.out.println("0. Voltar\n");
                            op2 = sc.nextInt();
                            switch (op2) {
                                case 1:
                                    try {
                                        controlador.cadastrarLocacao(Locacao.registrarLoc(sc));
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                    break;
                                case 2:
                                    System.out.print("Digite o ID da locação: ");
                                    idLocacaoAux = sc.nextInt();
                                    System.out.print("Digite o CPF do locador: ");
                                    cpfAux = sc.nextInt();
                                    try {
                                        controlador.verLocacao(cpfAux, idLocacaoAux);
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                    break;
                                case 0:
                                    System.out.println("\nRetornando...");
                                    break;
                                default:
                                    System.out.println("\nOpção inválida.");
                                    break;
                            }
                        }
                        break;
                    case 0:
                        System.out.println("\nEncerrando sistema...\n");
                        break;
                    default:
                        System.out.println("Opção inválida.");
                        break;
                }
            } while (op != 0);
        }

    }

    public static void limparTela() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            System.out.println("Erro ao limpar a tela: " + e.getMessage());
        }
    }
}
