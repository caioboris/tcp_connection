package com.br.fiap.socketTcpCalculator;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Cliente {

    static Conexao c;
    static Socket socket;

    public Cliente(){
        try {
            socket = new Socket("localhost", 9600);
            c = new Conexao();
        } catch (IOException e) {
            System.out.println("Não consegui resolver o host...");
        }
    }

    public static void main(String[] args) throws IOException {
        try{
            new Cliente();
            float op1, op2;
            char oper;
            Scanner in = new Scanner(System.in);

            System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxx");
            System.out.println("xxxxxx CALCULADORA xxxxxx");
            System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxx");

            System.out.println("Digite o primeiro valor: ");
            op1 = in.nextFloat();
            System.out.println("Digite o segundo valor: ");
            op2 = in.nextFloat();
            System.out.println("Escolha a operação:");
            System.out.println("Soma(+) Subtração(-) Multiplicação(*) Divisão(/)");
            oper = in.next().charAt(0);

            Requisicao msgReq = new Requisicao(op1, op2, oper);
            c.send(socket,msgReq);

            Resposta msgResp = (Resposta) c.receive(socket);

            switch(msgResp.getStatus()){
                case 0:

                    break;
                case 1:

                    break;
                case 2:

                    break;
                default:

                    break;
            }

        } catch(Exception e){

        }
        finally {
            socket.close();
        }
    }
}
