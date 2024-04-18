package com.example;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("----------------------------------");
        System.out.println("Deseja acessar como administrador?");
        System.out.println("1. Sim");
        System.out.println("2. Não");
        System.out.println("----------------------------------");
        int res = sc.nextInt();

        boolean ehAdmin = (res == 1);
        System.out.println("Acesso Permitido!");

        sc.close();
    }

}
