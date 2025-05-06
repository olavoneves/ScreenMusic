package br.com.screenmusic.application;

import br.com.screenmusic.model.Music;
import br.com.screenmusic.model.Singer;
import br.com.screenmusic.repository.IMusicRepository;

import java.util.List;
import java.util.Scanner;

public class ConsoleApplication {
    private Scanner scanner;
    private Singer singer;
    private Music music;
    private List<Singer> listSinger;
    private List<Music> listMusic;
    private IMusicRepository iMusicRepository;

    public ConsoleApplication(IMusicRepository iMusicRepository) {
        this.iMusicRepository = iMusicRepository;
    }

    public void exibirMenu() {
        scanner = new Scanner(System.in);
        int option = -1;

        while (option != 0) {
            var menu = """
                    
                    *************** OPÇÕES ***************
                    
                        > 1. Cadastrar Cantor
                        > 2. Cadastrar Música
                        > 3. Listar Músicas
                        > 4. Buscar Músicas por cantor
                        > 5. Buscar dados de um cantor
                    
                        > 0. Sair
                    """;

            try {
                System.out.print(menu + "\n\n    > ");
                option = scanner.nextInt();
                scanner.nextLine();

                switch (option) {
                    case 1:
                        cadastrarCantor();
                        break;
                    case 2:
                        cadastrarMusica();
                        break;
                    case 3:
                        listarMusicas();
                        break;
                    case 4:
                        buscarMusicasPorCantor();
                        break;
                    case 5:
                        buscarDadosCantor();
                        break;
                    case 0:
                        System.out.println("Saindo...");
                        break;
                    default:
                        System.err.println("Digite um valor válido nas opções!");
                        break;
                }

            } catch (Exception e) {
                throw new RuntimeException("\nErro no método exibirMenu!" + e.getMessage());
            }
        }
    }

    private void cadastrarCantor() {
        scanner = new Scanner(System.in);
        singer = new Singer();

        System.out.print("Digite um Cantor(a): ");
        var cantor = scanner.nextLine();

        List<Singer> listaCantores = iMusicRepository.buscarCantor(cantor);

        if (listaCantores.isEmpty()) {
            singer.setName(cantor);
            listSinger.add(singer);

            System.out.println(singer.getName() + " cadastrado(a) com sucesso!");
        } else {
            System.out.println(cantor + " já cadastrado no banco de dados!");
        }
    }

    private void cadastrarMusica() {
        scanner = new Scanner(System.in);
        music = new Music();

        listSinger.forEach(System.out::println);

        System.out.print("Escolha um Cantor(a): ");
        var cantor = scanner.nextLine();

        List<Singer> listaCantores = iMusicRepository.buscarCantor(cantor);

        if (listaCantores.isEmpty()) {
            System.out.println("Para cadastrar música é necessário cadastrar o cantor primeiro!");
        } else {
            System.out.print("Digite uma Música: ");
            var musica = scanner.nextLine();

            music.setName(musica);
            listMusic.add(music);

            System.out.println(music.getName() + " cadastrado(a) com sucesso!");
        }
    }

    private void listarMusicas() {

    }

    private void buscarMusicasPorCantor() {

    }

    private void buscarDadosCantor() {

    }
}
