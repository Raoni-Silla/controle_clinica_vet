package controle.clinica;


import entidades.*;
import infra.ConsultaDao;
import infra.DAO;

import javax.swing.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void menuCidade(Scanner sc) {

        DAO<Cidade> daoCidade = new DAO<>();
        DAO<Uf> daoUf = new DAO<>();
        int opcao;

        do {
            System.out.println("\n--- Gerenciar Cidades ---");
            System.out.println("1. Cadastrar nova Cidade");
            System.out.println("2. Listar todas as Cidades");
            System.out.println("3. Excluir Cidade");
            System.out.println("0. Voltar ao menu principal");

            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1:
                    try {
                        System.out.println("Digite o nome da Cidade:");
                        String nome = sc.nextLine();

                        System.out.println("Digite o ID da UF (Estado) desta cidade:");
                        Long ufId = sc.nextLong();
                        sc.nextLine();

                        Uf ufEncontrada = daoUf.findById(Uf.class, ufId);

                        if (ufEncontrada == null) {
                            System.out.println("ERRO: UF com ID " + ufId + " não encontrada. Cadastro cancelado.");
                            break;
                        }

                        System.out.println("Associando à UF: " + ufEncontrada.getSigla());

                        Cidade novaCidade = new Cidade();
                        novaCidade.setNome(nome);
                        novaCidade.setUf(ufEncontrada);

                        daoCidade.incluirAtomico(novaCidade);
                        System.out.println("Cidade '" + nome + "' cadastrada com sucesso!");

                    } catch (Exception e) {
                        System.out.println("ERRO INESPERADO: " + e.getMessage());
                    }
                    break;

                case 2:
                    System.out.println("--- Lista de Cidades Cadastradas ---");
                    List<Cidade> listaCidades = daoCidade.listarTodosAtomico(Cidade.class);

                    for (Cidade cidade : listaCidades) {
                        System.out.println("ID: " + cidade.getId() + " | Nome: " + cidade.getNome() + " | UF: " + cidade.getUf().getSigla());
                    }
                    break;

                case 3:
                    System.out.println("Digite o ID da Cidade a ser excluída:");
                    Long idExcluir = sc.nextLong();
                    sc.nextLine();

                    daoCidade.removeById(Cidade.class, idExcluir);
                    break;

                case 0:
                    System.out.println("Voltando ao menu principal...");
                    break;

                default:
                    System.out.println("Opção inválida.");
            }

        } while (opcao != 0);
    }

    public static void menuUf(Scanner sc) {

        DAO<Uf> daoUf = new DAO<>();
        int opcao;

        do {
            System.out.println("\n--- Gerenciar Estados (UF) ---");
            System.out.println("1. Cadastrar nova UF");
            System.out.println("2. Listar todas as UFs");
            System.out.println("3. Excluir UF");
            System.out.println("0. Voltar ao menu principal");

            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1:
                    System.out.println("Digite a sigla da UF (ex: SP):");
                    String sigla = sc.nextLine();

                    System.out.println("Digite o nome do estado (ex: Sao Paulo):");
                    String nome = sc.nextLine();

                    Uf novaUf = new Uf();
                    novaUf.setSigla(sigla);
                    novaUf.setNome(nome);

                    daoUf.incluirAtomico(novaUf);
                    System.out.println("UF '" + sigla + "' cadastrada com sucesso!");
                    break;

                case 2:
                    System.out.println("--- Lista de UFs Cadastradas ---");
                    List<Uf> listaUfs = daoUf.listarTodosAtomico(Uf.class);

                    for (Uf uf : listaUfs) {
                        System.out.println("ID: " + uf.getId() + " | Sigla: " + uf.getSigla() + " | Nome: " + uf.getNome());
                    }
                    break;

                case 3:
                    System.out.println("Digite o ID da UF a ser excluída:");
                    Long idExcluir = sc.nextLong();
                    sc.nextLine();

                    daoUf.removeById(Uf.class, idExcluir);
                    break;

                case 0:
                    System.out.println("Voltando ao menu principal...");
                    break;

                default:
                    System.out.println("Opção inválida.");
            }

        } while (opcao != 0);
    }

    public static void menuRaca(Scanner sc) {

        DAO<Raca> daoRaca = new DAO<>();
        int opcao;

        do {

            System.out.println("\n--- Gerenciar Raças ---");
            System.out.println("1. Cadastrar nova Raça");
            System.out.println("2. Listar todas as Raças");
            System.out.println("3. Excluir Raça");
            System.out.println("0. Voltar ao menu principal");


            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1:

                    System.out.println("Digite o nome da raça (ex: Labrador):");
                    String nome = sc.nextLine();

                    System.out.println("Digite uma breve descrição:");
                    String descricao = sc.nextLine();

                    Raca novaRaca = new Raca();
                    novaRaca.setNome(nome);
                    novaRaca.setDescricao(descricao);

                    daoRaca.incluirAtomico(novaRaca);
                    System.out.println("Raça '" + nome + "' cadastrada com sucesso!");
                    break;

                case 2:
                    System.out.println("--- Lista de Raças Cadastradas ---");
                    List<Raca> listaRacas = daoRaca.listarTodosAtomico(Raca.class);

                    for (Raca raca : listaRacas) {
                        System.out.println("ID: " + raca.getId() + " | Nome: " + raca.getNome());
                    }
                    break;

                case 3:

                    System.out.println("Digite o ID da raça a ser excluída:");
                    Long idExcluir = sc.nextLong();
                    sc.nextLine();

                    daoRaca.removeById(Raca.class, idExcluir);
                    break;

                case 0:
                    System.out.println("Voltando ao menu principal...");
                    break;

                default:
                    System.out.println("Opção inválida.");
            }

        } while (opcao != 0);
    }

    public static void menuConsulta (Scanner sc){
        int opcao = 0;
        DAO <Consulta> daoConsulta = new DAO<>();
        DAO <Animal> daoAnimal = new DAO<>();
        DAO <Veterinario> daoVeterinario = new DAO<>();
        ConsultaDao consultaDao = new ConsultaDao();

        do {
            System.out.println("Digite a opção desejada:");
            System.out.println("1.Cadastrar Nova Consulta");
            System.out.println("2.Listar Consultas");
            System.out.println("3.Encontrar uma Consulta");
            System.out.println("4.Excluir Uma Consulta");
            System.out.println("Relatorio de Consultas por Periodo");
            System.out.println("0.Voltar ao menu principal");


            opcao = sc.nextInt();
            sc.nextLine();


            switch (opcao) {
                case 1:
                    try {
                        System.out.println("Iniciando sistema de cadastro de consultas...");
                        System.out.println("Digite o ID do animal: ");
                        Long idAnimal = sc.nextLong();
                        sc.nextLine();
                        System.out.println("Digite o ID do veterinario: ");
                        Long idVeterinario = sc.nextLong();
                        sc.nextLine();
                        System.out.println("Digite a data da consulta: ");
                        String dataConsulta = sc.nextLine();
                        System.out.println("Digite o valor da consulta R$: ");
                        double valorConsulta = sc.nextDouble();
                        sc.nextLine();

                        LocalDate dataFormatada = LocalDate.parse(dataConsulta, DateTimeFormatter.ofPattern("dd/MM/yyyy"));

                        System.out.println("Validando os dados, aguarde um momento.....");

                        Veterinario vetAchado = daoVeterinario.findById(Veterinario.class,idVeterinario);
                        Animal animal = daoAnimal.findById(Animal.class,idAnimal);

                        if(vetAchado == null){
                            System.out.println("Veterinario não encontrado");
                            System.out.println("Encerrando programa");
                            System.out.println("Digite um ID valido");
                            break;
                        }

                        if(animal == null){
                            System.out.println("Animal não encontrado");
                            System.out.println("Encerrando programa");
                            System.out.println("Digite um ID valido");
                           break;
                        }

                        Consulta consulta = new Consulta(animal,vetAchado,dataFormatada, valorConsulta);
                        daoConsulta.incluirAtomico(consulta);

                        System.out.println("Consulta cadastrada com sucesso");
                        System.out.println(consulta);

                    }catch (DateTimeParseException e){

                        System.out.println("ERRO: " + e.getMessage());
                        System.out.println("Data mal formatada");

                    }catch (Exception e){
                        System.out.println("ERRO: " + e.getMessage());
                    }



                    break;
                case 2:
                    System.out.println("Listando todas as consultas...");

                    List<Consulta> consultaList = daoConsulta.listarTodosAtomico(Consulta.class);

                    for(Consulta consulta : consultaList){
                        System.out.println(consulta + "\n");
                    }

                    break;
                case 3:
                    System.out.println("Digite o ID da consulta: ");

                    Long idConsulta = sc.nextLong();
                    sc.nextLine();
                    Consulta consulta =  daoConsulta.findById(Consulta.class,idConsulta);

                    if(consulta == null){
                        System.out.println("Consulta inexistente ou ID digitado de forma errada");
                        break;
                    }

                    System.out.println("Consulta encontrada com sucesso");
                    System.out.println(consulta);

                    break;
                case 4:
                    System.out.println("Digite o ID da consulta a ser excluida");
                    Long idConsulta2 = sc.nextLong();
                    sc.nextLine();

                    daoConsulta.removeById(Consulta.class,idConsulta2);

                    System.out.println("Consulta removida com sucesso");
                    break;
                case 5:
                    try {

                        System.out.println("--- Relatório de Consultas por Data ---");

                        System.out.println("Digite a Data Inicial (dd/MM/yyyy):");
                        String dataInicioStr = sc.nextLine();
                        LocalDate dataInicio = LocalDate.parse(dataInicioStr, DateTimeFormatter.ofPattern("dd/MM/yyyy"));

                        System.out.println("Digite a Data Final (dd/MM/yyyy):");
                        String dataFimStr = sc.nextLine();
                        LocalDate dataFim = LocalDate.parse(dataFimStr, DateTimeFormatter.ofPattern("dd/MM/yyyy"));


                        List<Consulta> listaDeConsultas = consultaDao.RelatorioConsultasPorData(dataInicio, dataFim);

                        if (listaDeConsultas.isEmpty()) {
                            System.out.println("Nenhuma consulta encontrada nesse período.");
                            break;
                        }

                        System.out.println("--- Consultas Encontradas ---");


                        for (Consulta consulta1 : listaDeConsultas) {
                            System.out.println("--------------------");

                            System.out.println("Animal: " + consulta1.getAnimal().getNome());
                            System.out.println("Veterinário: " + consulta1.getVeterinario().getNome());
                            System.out.println("Data: " + consulta1.getDataConsulta());
                            System.out.println("Valor: R$ " + consulta1.getValorConsulta());
                        }

                        double valorTotal = listaDeConsultas.stream()
                                .mapToDouble(Consulta::getValorConsulta)
                                .sum();

                        System.out.println("====================");
                        System.out.printf("VALOR TOTAL DAS CONSULTAS: R$ %.2f\n", valorTotal);
                        System.out.println("====================");

                    } catch (DateTimeParseException e) {
                        System.out.println("ERRO: Formato de data inválido. Use dd/MM/yyyy.");
                    } catch (Exception e) {
                        System.out.println("ERRO INESPERADO: " + e.getMessage());
                        e.printStackTrace();
                    }
                    break;
                default:
                    System.out.println("opção não encontrada");
            }



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
                break;

            case 5:
                menuCidade(sc);
                break;
            case 6:
                menuUf(sc);
                break;
            case 7:
                menuRaca (sc);

        }




        sc.close();
    }
}