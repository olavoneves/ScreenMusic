package br.com.screenmusic.application;

import br.com.screenmusic.model.Banda;
import br.com.screenmusic.model.Music;
import br.com.screenmusic.model.Singer;
import br.com.screenmusic.model.StyleMusic;
import br.com.screenmusic.repository.ISingerRepository;
import br.com.screenmusic.service.HuggingFaceService;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class ConsoleApplication {
    private Scanner scanner;
    private final ISingerRepository iSingerRepository;

    public ConsoleApplication(ISingerRepository iSingerRepository) {
        this.iSingerRepository = iSingerRepository;
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
                    
                    **************************************
                    """;

            try {
                System.out.print(menu + "    > ");
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
        var novoCantor = "S";

        while (novoCantor.equals("S")) {
            System.out.print("Digite um Cantor(a): ");
            var cantor = scanner.nextLine();

            System.out.print("""
                    
                    *************** TIPO ***************
                    
                        > 1. Solo
                        > 2. Dupla
                        > 3. Trio
                        > 4. Quarteto
                    
                        > 0. Outro
                    
                    *************************************
                    """);
            System.out.print("    > ");
            var tipoDaBanda = scanner.nextInt();
            scanner.nextLine();

            Banda banda = null;
            switch (tipoDaBanda) {
                case 1:
                    banda = Banda.valueOf("SOLO");
                    break;
                case 2:
                    banda = Banda.valueOf("DUPLA");
                    break;
                case 3:
                    banda = Banda.valueOf("TRIO");
                    break;
                case 4:
                    banda = Banda.valueOf("QUARTETO");
                    break;
                default:
                    break;
            }

            Singer singer = new Singer(cantor, banda);
            iSingerRepository.save(singer);

            System.out.println(singer.getName() + " cadastrado(a) com sucesso!");

            System.out.print("\nGostaria de cadastrar outro artista? [S/N] > ");
            novoCantor = scanner.nextLine().toUpperCase();
            if (novoCantor.equals("N")) {
                break;
            }
        }
    }

    private void cadastrarMusica() {
        scanner = new Scanner(System.in);
        var novaMusica = "S";

        while (novaMusica.equals("S")) {
            List<Singer> listSingers = iSingerRepository.findAll();
            listSingers.forEach(c -> System.out.println("Artista: " + c.getName()));

            System.out.print("Escolha um Cantor(a): ");
            var cantor = scanner.nextLine();

            Optional<Singer> cantores = iSingerRepository.findByNameContainingIgnoreCase(cantor);

            if (cantores.isEmpty()) {
                System.out.println("Para cadastrar música é necessário cadastrar o cantor primeiro!");
            } else {
                System.out.print("Digite uma Música: ");
                var musica = scanner.nextLine();

                System.out.print("""
                        
                         *************** ESTILO ***************
                        
                                >  1. Sertanejo
                                >  2. Funk
                                >  3. Pop
                                >  4. Rap
                                >  5. Trap
                                >  6. Forro
                                >  7. Pagode
                                >  8. Samba
                                >  9. MPB
                                > 10. Rock
                        
                                > 0. Outro
                        
                        ****************************************
                        """);
                System.out.print("    > ");
                var tipoDaBanda = scanner.nextInt();
                scanner.nextLine();

                StyleMusic estiloMusica = null;
                switch (tipoDaBanda) {
                    case 1:
                        estiloMusica = StyleMusic.valueOf("SERTANEJO");
                        break;
                    case 2:
                        estiloMusica = StyleMusic.valueOf("FUNK");
                        break;
                    case 3:
                        estiloMusica = StyleMusic.valueOf("POP");
                        break;
                    case 4:
                        estiloMusica = StyleMusic.valueOf("RAP");
                        break;
                    case 5:
                        estiloMusica = StyleMusic.valueOf("TRAP");
                        break;
                    case 6:
                        estiloMusica = StyleMusic.valueOf("FORRO");
                        break;
                    case 7:
                        estiloMusica = StyleMusic.valueOf("PAGODE");
                        break;
                    case 8:
                        estiloMusica = StyleMusic.valueOf("SAMBA");
                        break;
                    case 9:
                        estiloMusica = StyleMusic.valueOf("MPB");
                        break;
                    case 10:
                        estiloMusica = StyleMusic.valueOf("ROCK");
                        break;
                    default:
                        break;
                }

                Music music = new Music(musica, estiloMusica);
                music.setSinger(cantores.get());
                cantores.get().getListMusic().add(music);
                iSingerRepository.save(cantores.get());

                System.out.println(music.getName() + " cadastrado(a) com sucesso!");

                System.out.print("\nGostaria de cadastrar outra música? [S/N] > ");
                novaMusica = scanner.nextLine().toUpperCase();
                if (novaMusica.equals("N")) {
                    break;
                }
            }
        }
    }

    private void listarMusicas() {
        List<Singer> listSingers = iSingerRepository.findAll();
        listSingers.forEach(c -> c.getListMusic().forEach(System.out::println));
    }

    private void buscarMusicasPorCantor() {
        List<Singer> listSingers = iSingerRepository.findAll();
        listSingers.forEach(c -> System.out.println("Artista: " + c.getName()));

        System.out.print("Escolha um Cantor(a): ");
        var cantor = scanner.nextLine();

        List<Music> musicaPorCantor = iSingerRepository.buscarMusica(cantor);
        musicaPorCantor.forEach(System.out::println);
    }

    private void buscarDadosCantor() {
        HuggingFaceService huggingFaceService = new HuggingFaceService();

        System.out.print("Digite o que você gostaria de saber sobre o(a) Cantor(a): ");
        var buscaDados = scanner.nextLine();

        huggingFaceService.getAIResponse(buscaDados);
    }
}
