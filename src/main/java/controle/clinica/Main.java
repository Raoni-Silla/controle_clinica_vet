package controle.clinica;


import entidades.*;
import infra.DAO;

import javax.swing.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void menuConsulta (Scanner sc){
        int opcao = 0;

        do {
            System.out.println("Digite a opção desejada:");
            System.out.println("1.Cadastrar Nova Consulta");
            System.out.println("2.Listar Consultas");
            System.out.println("3.Encontrar uma Consulta");
            System.out.println("4.Excluir Uma Consulta");
            System.out.println("0.Voltar ao menu principal");


            opcao = sc.nextInt();
            sc.nextLine();

        }while (opcao != 0);
    }

    public static void menuVet (Scanner sc){

        int opcao;


        DAO <Animal> daoAnimal = new DAO <>();
        DAO <Veterinario> daoVet = new DAO <>();
        DAO <Cidade> daoCidade = new DAO <>();


        do {

            System.out.println("Digite a opção desejada:");
            System.out.println("1.Cadastrar Veterinario");
            System.out.println("2.Listar Veterinarios");
            System.out.println("3.Encontrar um veterinario especifico");
            System.out.println("4.Excluir veterinario especifico");
            System.out.println("0.Voltar ao menu principal");


            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1:

                    System.out.println("Digite o nome do veterinario:");
                    String nomevet = sc.nextLine();

                    System.out.println("Digite o cpf do veterinario:");
                    String cpf = sc.nextLine();

                    System.out.println("Digite o numero da casa do veterinario:");
                    int numCasa = sc.nextInt();
                    sc.nextLine();

                    System.out.println("Digite o nome do rua do veterinario:");
                    String nomeRua = sc.nextLine();

                    System.out.println("Digite o ID da cidade do veterinario:");
                    long cidadeId = sc.nextLong();
                    sc.nextLine();

                    try {
                        System.out.println("Digite a data de nascimento: dd/MM/yyyy ");
                        String dataAniversario = sc.nextLine();

                        Cidade cidade = daoCidade.findById(Cidade.class, cidadeId);

                        if (cidade == null) {
                            System.out.println("ERRO: Cidade com ID " + cidadeId + " não encontrada. Cadastro cancelado.");
                            break;
                        }

                        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                        LocalDate data_formatada = LocalDate.parse(dataAniversario, formato);

                        System.out.println("Cidade encontrada " + cidade.getNome());

                        Veterinario vet = new Veterinario(nomevet, cpf, nomeRua, numCasa, cidade, data_formatada);

                        daoVet.incluirAtomico(vet);

                    }catch (DateTimeParseException e){

                        System.out.println("ERRO: " + e.getMessage());
                        System.out.println("Data mal formatada");

                    }catch (Exception e){
                        System.out.println("ERRO: " + e.getMessage());
                    }

                    break;

                case 2:
                    List<Veterinario> vetList = daoVet.listarTodosAtomico(Veterinario.class);
                    for(Veterinario vetloop : vetList){
                        System.out.println("veterinario:" );
                        System.out.println(vetloop.getNome());
                    }

                    break;

                case 3:
                    Veterinario vetEncontrado;

                    System.out.println("Digite o ID do veterinario: ");
                    Long idvet = sc.nextLong();
                    sc.nextLine();

                    vetEncontrado = daoVet.findById(Veterinario.class, idvet);

                    if(vetEncontrado == null){
                        System.out.println("não encontramos nenhum veterinario");
                        break;
                    }

                    System.out.println("veterinario encontrado");
                    System.out.println(vetEncontrado);

                    break;
                case 4:

                    Veterinario vetExcluir;
                    System.out.println("Digite o ID do veterinario: ");
                    long vetID = sc.nextLong();
                    sc.nextLine();
                    daoVet.removeById(Veterinario.class, vetID);
                    break;
                default:
                    System.out.println("opção invalida");


            }
        }while (opcao!=0);

    }

    public static void menuAnimal (Scanner sc){

        int opcao;
        DAO <Raca> daoRaca = new DAO <>();
        DAO <Animal> daoAnimal = new DAO <>();
        DAO <Tutores> daoTutor = new DAO <>();

        do {
            System.out.println("Digite a opção desejada:");
            System.out.println("1.Cadastrar Animal");
            System.out.println("2.Listar animais");
            System.out.println("3.Encontrar um animal especifico");
            System.out.println("4.Excluir animal especifico");
            System.out.println("0.Voltar ao menu principal");

            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1:
                    System.out.println("Digite o nome do animal:");
                    String nomeAnimal = sc.nextLine();

                    System.out.println("Digite o ID da raça do animal:");
                    Long idRaca = sc.nextLong();
                    sc.nextLine();
                    Raca racaEncontrada = daoRaca.findById(Raca.class, idRaca);

                    if(racaEncontrada == null){
                        System.out.println("Nenhuma raca encontrada");
                        System.out.println("Parando a execução");
                        break;
                    }

                    System.out.println("Digite o ID do tutor: ");
                    Long idTutor = sc.nextLong();
                    sc.nextLine();
                    Tutores tutorEncontrada = daoTutor.findById(Tutores.class, idTutor);

                    if(tutorEncontrada == null){
                        System.out.println("Nenhum tutor encontrado");
                        System.out.println("Parando a execução");
                        break;
                    }

                    System.out.println("Digite a idade do animal");
                    int idadeAnimal = sc.nextInt();
                    sc.nextLine();

                    System.out.println("Digite o peso: ");
                    String peso = sc.nextLine();

                    Animal novoAnimal = new Animal(nomeAnimal,racaEncontrada,tutorEncontrada,idadeAnimal,peso);

                    daoAnimal.incluirAtomico(novoAnimal);
                    break;
                case 2:
                    List<Animal> animalsList = daoAnimal.listarTodosAtomico(Animal.class);
                    for(Animal animal : animalsList){
                        System.out.println("animal:" );
                        System.out.println(animal);
                    }
                    break;
                case 3:
                    Animal animalEncontrado;

                    System.out.println("Digite o ID do animal: ");
                    Long idAnimal = sc.nextLong();
                    sc.nextLine();

                    animalEncontrado = daoAnimal.findById(Animal.class, idAnimal);

                    if(animalEncontrado == null){
                        System.out.println("não encontramos nenhum animal");
                        break;
                    }

                    System.out.println("Animal encontrado");
                    System.out.println(animalEncontrado);

                    break;
                case 4:

                    Animal animalEncontrado2;
                    System.out.println("Digite o ID do animal: ");
                    long animalID = sc.nextLong();
                    sc.nextLine();
                    daoAnimal.removeById(Animal.class, animalID);
                    break;
                default:
                    System.out.println("opção invalida");


            }
        }while (opcao!=0);

    }

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

                System.out.println("Digite sua data de nascimento: ");
                String dataNascimento = sc.nextLine();
                DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy") ;
                LocalDate data_formatada = LocalDate.parse(dataNascimento, formato);

                System.out.println("Cidade encontrada " + cidade.getNome());

                Tutores tutor = new Tutores(nome,cpf,numCasa,nomeRua,cidade,data_formatada);

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
                menuAnimal(sc);
                break;
            case 3:
                menuVet(sc);
                break;
            case 4:
                menuConsulta(sc);



        }




        sc.close();
    }
}