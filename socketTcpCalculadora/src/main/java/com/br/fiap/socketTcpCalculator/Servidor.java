package com.br.fiap.socketTcpCalculator;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

    static ServerSocket serverSocket;
    static Socket clientSocket;
    static Conexao  c;

    public Servidor(){
        try{
            serverSocket = new ServerSocket(9600);
            System.out.println("Calculadora distribuida no servidor");
            System.out.println("Aguardando cliente fazer requisição");
        } catch (IOException e) {
            System.out.println("Nao criei o server socket");
        }
    }

    public static void main(String[] args) {
        Requisicao msgReq;
        Resposta msgResposta;
        int visits = 0;
        new Servidor();

        while(true){
            if(connect()){
                msgReq = (Requisicao) c.receive(clientSocket);

                char op = msgReq.getOperacao();
                System.out.println("Operacao feita pelo Cliente = " + op);
                msgResposta = new Resposta();

                switch(op){
                    case '+':
                        msgResposta.setStatus(0);
                        msgResposta.setResult(msgReq.getOp1() + msgReq.getOp2());
                        break;
                    case '-':
                        msgResposta.setStatus(0);
                        msgResposta.setResult(msgReq.getOp1() - msgReq.getOp2());
                        break;
                    case '*':
                        msgResposta.setStatus(0);
                        msgResposta.setResult(msgReq.getOp1() * msgReq.getOp2());
                        break;
                    case '/':
                        if(msgReq.getOp2() == 0.0F){
                            msgResposta.setStatus(2);
                        }else {
                            msgResposta.setStatus(0);
                            msgResposta.setResult(msgReq.getOp1() / msgReq.getOp2());
                            break;
                        }
                    default:
                        msgResposta.setStatus(1);
                        break;
                }
                c.send(clientSocket, msgResposta);
            }
            else{
                try{
                    serverSocket.close();
                    break;
                } catch (IOException e) {
                    System.out.println("Nao desconectei.. " + e.getMessage());;
                }
            }
        }
    }

    static boolean connect(){
        boolean ret;
        try{
            clientSocket = serverSocket.accept();
            return true;
        } catch (IOException e) {
            System.out.println("Erro de connect..." + e.getMessage());
            return false;
        }
    }
}
