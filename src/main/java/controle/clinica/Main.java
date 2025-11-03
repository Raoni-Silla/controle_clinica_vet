package controle.clinica;


import entidades.Cidade;
import entidades.Tutores;
import infra.DAO;

import javax.swing.*;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void menuTutor (Scanner sc){


        DAO<Cidade> daoCidade = new DAO<>();
        DAO<Tutores> daoTutores = new DAO<>();


        int opcao;

        do {

        System.out.println("Digite a opção desejada:");
        System.out.println("1.Cadastrar Tutor");
        System.out.println("2.Listar Tutores");
        System.out.println("3.Encontrar um tutor especifico");
        System.out.println("4.Excluir tutor especifico");
        System.out.println("0.Voltar ao menu principal");


        opcao = sc.nextInt();
        sc.nextLine();

        switch(opcao){

            case 1:

                System.out.println("Digite o nome do tutor:");
                String nome = sc.nextLine();

                System.out.println("Digite o cpf do tutor:");
                String cpf = sc.nextLine();

                System.out.println("Digite o numero da casa do tutor:");
                long numCasa = sc.nextInt();
                sc.nextLine();

                System.out.println("Digite o nome do rua do tutor:");
                String nomeRua = sc.nextLine();

                System.out.println("Digite o ID da cidade do tutor:");
                long cidadeId = sc.nextLong();
                sc.nextLine();

                Cidade cidade = daoCidade.findById(Cidade.class,cidadeId);

                if (cidade == null) {
                    System.out.println("ERRO: Cidade com ID " + cidadeId + " não encontrada. Cadastro cancelado.");
                    break;
                }

                System.out.println("Cidade encontrada " + cidade.getNome());

                Tutores tutor = new Tutores(nome,cpf,numCasa,nomeRua,cidade);

                daoTutores.incluirAtomico(tutor);

                break;

            case 2:
                System.out.println("sistema listando todos os tutores: ");
                List<Tutores> tutoresList = daoTutores.listarTodosAtomico(Tutores.class);
                for (Tutores tutores : tutoresList) {
                    System.out.println(tutores);
                }
                break;

            case 3:

                System.out.println("Digite o ID do tutor desejado: ");
                long idTutor = sc.nextLong();
                sc.nextLine();

                Tutores encontrado = daoTutores.findById(Tutores.class,idTutor);
                if (encontrado == null) {
                    System.out.println("objeto nulo, não encontrado o ID buscado");
                    break;
                }
                System.out.println("Tutor encontrado");
                System.out.println(encontrado);
                break;

            case 4:
                System.out.println("Digite o ID da tutor desejado para exclusão: ");
                long idTutor2 = sc.nextLong();
                sc.nextLine();

                daoTutores.removeById(Tutores.class,idTutor2);
                break;

            default:
                System.out.println("ERROR: numero digitado não existe em nosso menu");
        }
        }while (opcao != 0);
    }

    public static void main(String[] args) {

        int opcao = 0;

        System.out.println("-=-=-=-=-=-=-Bem vindo ao sistema de controle-=-=-=-=-=-=-");
        System.out.println("              Selecione a opção desejada");
        System.out.println("-=-=-=-=-=-=--=-=-=-=-=-=--=-=-=-=-=-=--=-=-=-=-=-=--=-=-=-=-=-=");
        System.out.println("1.Gerenciar Tutor");
        System.out.println("2.Gerenciar Animal");
        System.out.println("3.Gerenciar Veterinario");
        System.out.println("4.Gerenciar Consultas");
        System.out.println("5.Gerenciar Cidades");
        System.out.println("6.Gerenciar estados");
        System.out.println("7.Gerenciar Raças");

        Scanner sc = new Scanner(System.in);
        opcao = sc.nextInt();


        switch (opcao){
            case 1:
                menuTutor(sc);
                break;
            case 2:

        }




        sc.close();
    }
}