package testes;

import entity.Cidade;
import entity.Pessoa;
import exception.Excessao;
import model.dao.Dao;
import util.Conexao;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {

            operacao(sc);
        }catch(Exception e){
            throw new Excessao( e.getMessage());
        }

        Conexao.closeConnection();

    }
    public static int menu(Scanner sc){
        System.out.println("0 - SAIR");
        System.out.println("1 - Inserir");
        System.out.println("2 - Consultar");
        System.out.println("3 - LISTAR Pessoa e Cidade");
        System.out.println("4 - ALTERAR");
        System.out.println("5 - DELETAR");
        System.out.println("6 - ENCERRAR CONEXÃO");
        System.out.print("Operação desejada: ");
        int op = sc.nextInt();
        sc.nextLine();
        return op;
    }
    public static void operacao(Scanner sc){
        Dao dao = new Dao();
        int op = -1;
        while(op != 0){
            op = menu(sc);
            try{

                switch(op){
                    case 1:
                        System.out.println("========= ESCOLHA OPÇÃO PARA INSERIR =================");
                        System.out.println("1 Inserir Pessoa");
                        System.out.println("2 - Inserir Cidade");
                        System.out.print("Opção: ");
                        int o = sc.nextInt();
                        sc.nextLine();
                        switch(o){
                            case 1:
                                System.out.print("Informe o Nome da Pessoa: ");
                                String nome = sc.nextLine();
                                System.out.print("Informe o ID Cidade: ");
                                Integer idCidade = sc.nextInt();
                                try{

                                    Pessoa p = new Pessoa(nome, idCidade);
                                    dao.inserirPessoa(p);
                                }catch (Excessao e){
                                    throw new Excessao(e.getMessage());
                                }
                                break;
                            case 2:
                                System.out.print("Nome da Cidade: ");
                                nome = sc.nextLine();
                                System.out.print("UF: ");
                                String uf = sc.nextLine();
                                System.out.println("Codigo Cidade: ");
                                Integer codigo = sc.nextInt();
                                sc.nextLine();
                                Cidade c = new Cidade(nome, codigo,uf);
                                dao.inserirCidade(c);
                                break;
                            default:
                                System.out.println("Invalida!!!");
                                break;

                        }//FIM DO SWITCH DENTRO DE CASE 1
                        break;
                    case 2:
                            System.out.print("Informe o ID da pessoa para Consulta: ");
                            int id = sc.nextInt();
                            Pessoa p = dao.consultarPessoa(id);
                            Cidade c = dao.consultarCidade(p.getIdCidade());
                            System.out.println("------DADOS DA CONSULTA PESSOA -------");
                            System.out.println("Nome: " + p.getNome());
                            System.out.println("Codigo: " + p.getCodigo());
                            System.out.println("Id Cidade: "+ p.getIdCidade());
                            System.out.println("Cidade:" + c.getNome());
                            System.out.println("UF: " + c.getUf());
                        break;
                    case 3:
                        List<Pessoa>pessoas = new ArrayList<>();
                        List<Cidade> cidades = new ArrayList<>();
                        pessoas = dao.listaPessoa();
                        cidades = dao.listaCidade();
                        System.out.println("=======LISTA DE Cidades NO BANCO DE DADOS =========");
                        for(Cidade ci: cidades){
                            System.out.println("Cidade: "+ci.getNome() + ", UF: " + ci.getUf() + ", ID: " + ci.getCodigo());
                        }
                        System.out.println("------------------------------");
                        System.out.println("=======LISTA DE PESSOAS NO BANCO DE DADOS =========");
                        for(Pessoa pe: pessoas){
                            System.out.println("Id: " + pe.getCodigo()+ ", Nome: " +  pe.getNome() + ", idCidade: " + pe.getIdCidade());
                        }
                        System.out.println();
                        break;
                    case 4:
                        System.out.println("INFORME 1 PARA ALTERAR CIDADE");
                        System.out.println("INFORME 2 PARA ALTERAR PESSOA");
                        System.out.print("Operacao: ");
                        op =sc.nextInt();

                        switch (op){
                            case 1:
                                System.out.print("INFORME O ID DA CIDADE A SER ALTERADA: ");
                                id = sc.nextInt();
                                sc.nextLine();
                                System.out.print("INFORME O NOME: ");
                                String nome = sc.nextLine();
                                System.out.print("INFORME O NOVO ID CIDADE: ");
                                Integer novo = sc.nextInt();
                                sc.nextLine();
                                System.out.print("UF: ");
                                String uf = sc.nextLine();
                                c = new Cidade(nome,novo, uf);
                                dao.alterarCidade(c, id);
                                break;
                            case 2:
                                System.out.print("INFORME O CODIGO DA PESSOA A SER ALTERADA: ");
                                id = sc.nextInt();
                                sc.nextLine();
                                System.out.print("INFORME O NOME DA PESSOA: ");
                                nome = sc.nextLine();
                                System.out.print("INFORME O NOVO ID DA PESSOA: ");
                                novo = sc.nextInt();
                                System.out.print("INFORME O NOVO ID CIDADE DA PESSOA: ");
                                Integer idCid = sc.nextInt();
                                p = new Pessoa(nome, idCid);
                                p.setCodigo(novo);
                                dao.alterarPessoa(p,id);
                                break;
                        }//FIM SWITCH DENTRO DE CASE 4
                        break;
                    case 5:
                        System.out.println("1 - DELETAR PESSOA");
                        System.out.println("2 - DELETAR CIDADE");
                        System.out.print("Opção: ");
                        op = sc.nextInt();
                        switch (op){
                            case 1:
                                System.out.print("Código da Pessoa para Deletar: ");
                                Integer cod = sc.nextInt();
                                dao.deletarPessoa(cod);
                                break;
                            case 2:
                                System.out.print("Código da Cidade para Deletar: ");
                                cod = sc.nextInt();
                                dao.DeletarCidade(cod);
                                break;
                        }
                        break;
                    case 6:
                        Conexao.closeConnection();
                        break;
                    default:
                        System.out.println("opção inválida!!!!!!");
                        break;
                }//FIM 1º SWITCH
            }catch(Exception e){
                throw new Excessao(e.getMessage() + "\n***VOCÊ DEVE REINICIAR O MENU\n");
            }
        }
    }
}
