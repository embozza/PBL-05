import java.util.Scanner;
import java.util.ArrayList;

class Usuario {
    private String nome;
    private int idade;
    private String email;
    private String planoAssinatura;

    public Usuario(String nome, int idade, String email, String planoAssinatura) {
        this.nome = nome;
        this.idade = idade;
        this.email = email;
        this.planoAssinatura = planoAssinatura;
    }

    public String getNome() {
        return nome;
    }

    public int getIdade() {
        return idade;
    }

    public String getEmail() {
        return email;
    }

    public String getPlanoAssinatura() {
        return planoAssinatura;
    }
}

class Playlist {
    private String nome;
    private ArrayList<String> musicas;

    public Playlist(String nome) {
        this.nome = nome;
        this.musicas = new ArrayList<>();
    }

    public void adicionarMusica(String musica) {
        this.musicas.add(musica);
    }

    public String getNome() {
        return nome;
    }

    public ArrayList<String> getMusicas() {
        return musicas;
    }
}

public class SpotifyApp {
    private static Scanner scanner = new Scanner(System.in);
    private static ArrayList<Usuario> usuarios = new ArrayList<>();
    private static ArrayList<Playlist> playlists = new ArrayList<>();

    public static void main(String[] args) {
        exibirMenu();
    }

    public static void exibirMenu() {
        int opcao;
        do {
            System.out.println("---Bem vindo---");
            System.out.println("O que você deseja fazer?");
            System.out.println("1. Cadastrar Usuário");
            System.out.println("2. Criar Playlist");
            System.out.println("3. Adicionar Música a Playlist");
            System.out.println("4. Visualizar Informações do Perfil");
            System.out.println("5. Visualizar Playlists");
            System.out.println("0. Sair");

            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer do scanner

            switch (opcao) {
                case 1:
                    cadastrarUsuario();
                    break;
                case 2:
                    criarPlaylist();
                    break;
                case 3:
                    adicionarMusicaAPlaylist();
                    break;
                case 4:
                    visualizarInformacoesPerfil();
                    break;
                case 5:
                    visualizarPlaylists();
                    break;
                case 0:
                    System.out.println("Saindo do programa...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);
    }

    public static void cadastrarUsuario() {
        System.out.println("Digite o nome do usuário:");
        String nome = scanner.nextLine();
        System.out.println("Digite a idade do usuário:");
        int idade = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer do scanner
        System.out.println("Digite o email do usuário:");
        String email = scanner.nextLine();

        System.out.println("Escolha o plano de assinatura:");
        System.out.println("1. Free");
        System.out.println("2. Premium");
        int escolhaPlano = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer do scanner

        String planoAssinatura;
        switch (escolhaPlano) {
            case 1:
                planoAssinatura = "Free";
                break;
            case 2:
                planoAssinatura = "Premium";
                break;
            default:
                System.out.println("Opção inválida. O plano de assinatura foi definido como Free.");
                planoAssinatura = "Free";
        }

        Usuario usuario = new Usuario(nome, idade, email, planoAssinatura);
        usuarios.add(usuario);
        System.out.println("Usuário cadastrado com sucesso!");
    }

    public static void criarPlaylist() {
        System.out.println("Digite o nome da playlist:");
        String nomePlaylist = scanner.nextLine();
        Playlist playlist = new Playlist(nomePlaylist);
        playlists.add(playlist);
        System.out.println("Playlist criada com sucesso!");
    }

    public static void adicionarMusicaAPlaylist() {
        if (playlists.isEmpty()) {
            System.out.println("Não há playlists criadas. Por favor, crie uma playlist primeiro.");
            return;
        }

        System.out.println("Escolha a playlist:");
        for (int i = 0; i < playlists.size(); i++) {
            System.out.println((i + 1) + ". " + playlists.get(i).getNome());
        }
        int escolhaPlaylist = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer do scanner

        if (escolhaPlaylist < 1 || escolhaPlaylist > playlists.size()) {
            System.out.println("Playlist inválida.");
            return;
        }

        Playlist playlist = playlists.get(escolhaPlaylist - 1);

        System.out.println("Digite o nome da música a ser adicionada:");
        String nomeMusica = scanner.nextLine();
        playlist.adicionarMusica(nomeMusica);
        System.out.println("Música adicionada à playlist com sucesso!");
    }

    public static void visualizarInformacoesPerfil() {
        if (usuarios.isEmpty()) {
            System.out.println("Nenhum usuário cadastrado.");
            return;
        }

        System.out.println("Digite o nome do usuário:");
        String nomeUsuario = scanner.nextLine();

        Usuario usuario = null;
        for (Usuario u : usuarios) {
            if (u.getNome().equalsIgnoreCase(nomeUsuario)) {
                usuario = u;
                break;
            }
        }

        if (usuario != null) {
            System.out.println("Nome: " + usuario.getNome());
            System.out.println("Idade: " + usuario.getIdade());
            System.out.println("Email: " + usuario.getEmail());
            System.out.println("Plano de Assinatura: " + usuario.getPlanoAssinatura());
        } else {
            System.out.println("Usuário não encontrado.");
        }
    }

    public static void visualizarPlaylists() {
        if (playlists.isEmpty()) {
            System.out.println("Não há playlists criadas.");
            return;
        }

        System.out.println("Playlists disponíveis:");
        for (Playlist playlist : playlists) {
            System.out.println("Nome: " + playlist.getNome());
            System.out.println("Músicas:");
            ArrayList<String> musicas = playlist.getMusicas();
            if (musicas.isEmpty()) {
                System.out.println("Nenhuma música nesta playlist.");
            } else {
                for (String musica : musicas) {
                    System.out.println("- " + musica);
                }
            }
            System.out.println();
        }
    }
}

