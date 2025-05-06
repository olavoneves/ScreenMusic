package br.com.screenmusic.application;

import br.com.screenmusic.model.Banda;
import br.com.screenmusic.model.Music;
import br.com.screenmusic.model.Singer;
import br.com.screenmusic.model.StyleMusic;
import br.com.screenmusic.repository.IMusicRepository;
import br.com.screenmusic.repository.ISingerRepository;

import java.util.List;
import java.util.Scanner;

public class ConsoleApplication {
    private Scanner scanner;
    private Singer singer;
    private Music music;
    private List<Music> listMusic;
    private IMusicRepository iMusicRepository;
    private ISingerRepository iSingerRepository;

    public ConsoleApplication(IMusicRepository iMusicRepository, ISingerRepository iSingerRepository) {
        this.iMusicRepository = iMusicRepository;
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

        System.out.print("Digite um Cantor(a): ");
        var cantor = scanner.nextLine();

        System.out.print("""
                
                 *************** TIPO ***************
                
                    > 1. Solo
                    > 2. Dupla
                    > 3. Trio
                    > 4. Banda
            
                    > 0. Outro
                """);
        System.out.print("\n    > ");
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
                banda = Banda.valueOf("BANDA");
                break;
            case 0:
                banda = Banda.valueOf("OUTRO");
                break;
            default:
                break;
        }
        singer = new Singer(cantor, banda);
        iSingerRepository.save(singer);

        System.out.println(singer.getName() + " cadastrado(a) com sucesso!");
    }

    private void cadastrarMusica() {
        scanner = new Scanner(System.in);

        System.out.print("Escolha um Cantor(a): ");
        var cantor = scanner.nextLine();

        List<Singer> listaCantores = iMusicRepository.buscarCantor(cantor);

        if (listaCantores.isEmpty()) {
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
                """);
            System.out.print("\n    > ");
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
                case 0:
                    estiloMusica = StyleMusic.valueOf("OUTRO");
                    break;
                default:
                    break;
            }
            music = new Music(musica, estiloMusica);
            iMusicRepository.save(music);

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
