package persistencia;

import entidades.*;
import java.io.*;
import java.util.List;

public class Persistencia {
    private static final String BASE_PATH = "persistencia/";
    private static final String ARQUIVO_USUARIOS = "usuarios.dat";
    private static final String ARQUIVO_ESPACOS = "espacos.dat";
    private static final String ARQUIVO_RESERVAS = "reservas.dat";

    private static <T> void salvarArquivo(String nomeArquivo, T objeto) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(BASE_PATH + nomeArquivo))) {
            oos.writeObject(objeto);
        } catch (IOException e) {
            System.out.println("Erro ao salvar " + nomeArquivo + ": " + e.getMessage());
        }
    }

    static <T> List<T> carregarArquivo(String nomeArquivo) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(BASE_PATH + nomeArquivo))) {
            Object obj = ois.readObject();
            if (obj instanceof List<?>) {
                List<?> tempList = (List<?>) obj;
                List<T> result = new java.util.ArrayList<>();
                for (Object item : tempList) {
                    result.add((T) item); // Cast elemento a elemento
                }
                return result;
            } else {
                System.out.println("O arquivo não contém uma lista.");
                return new java.util.ArrayList<>();
            }
        } catch (FileNotFoundException e) {
            return new java.util.ArrayList<>();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erro ao carregar " + nomeArquivo + ": " + e.getMessage());
            return new java.util.ArrayList<>();
        }
    }

    public static void salvarUsuarios(List<Usuario> usuarios) {
        salvarArquivo(ARQUIVO_USUARIOS, usuarios);
    }

    public static List<Usuario> carregarUsuarios() {
        return carregarArquivo(ARQUIVO_USUARIOS);
    }

    public static void salvarEspacos(List<EspacoFisico> espacos) {
        salvarArquivo(ARQUIVO_ESPACOS, espacos);
    }

    public static List<EspacoFisico> carregarEspacos() {
        return carregarArquivo(ARQUIVO_ESPACOS);
    }

    public static void salvarReservas(List<Reserva> reservas) {
        salvarArquivo(ARQUIVO_RESERVAS, reservas);
    }

    public static List<Reserva> carregarReservas() {
        return carregarArquivo(ARQUIVO_RESERVAS);
    }
}
