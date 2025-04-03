package estruturas;
import java.util.Arrays;
import java.util.Scanner;

class Vetor {
    private int[] vetor;

    public Vetor(int[] vetor) {
        this.vetor = vetor;
    }

    public void buscaBinaria(int valor) {
        int inicio = 0, fim = vetor.length - 1;
        while (inicio <= fim) {
            int meio = inicio + (fim - inicio) / 2;
            if (vetor[meio] == valor) {
                System.out.println("Valor " + valor + " encontrado na posição: " + meio);
                return;
            }
            if (vetor[meio] < valor) {
                inicio = meio + 1;
            } else {
                fim = meio - 1;
            }
        }
        System.out.println("Valor " + valor + " não encontrado no vetor.");
    }

    public void inserirNoInicio(int valor) {
        int[] novoVetor = new int[vetor.length + 1];
        novoVetor[0] = valor;
        System.arraycopy(vetor, 0, novoVetor, 1, vetor.length);
        vetor = novoVetor;
        System.out.println("Vetor após inserção: " + Arrays.toString(vetor));
    }
}

class No {
    int dado;
    No proximo;
    No anterior;

    public No(int dado) {
        this.dado = dado;
        this.proximo = null;
        this.anterior = null;
    }
}

class ListaDuplamenteEncadeada {
    private No primeiro;
    private No ultimo;

    public ListaDuplamenteEncadeada() {
        this.primeiro = null;
        this.ultimo = null;
    }

    public void inserirNoFinal(int dado) {
        No novoNo = new No(dado);
        if (ultimo == null) {
            primeiro = ultimo = novoNo;
        } else {
            ultimo.proximo = novoNo;
            novoNo.anterior = ultimo;
            ultimo = novoNo;
        }
    }

    public int contarNos() {
        int contador = 0;
        No atual = primeiro;
        while (atual != null) {
            contador++;
            atual = atual.proximo;
        }
        return contador;
    }

    public void exibirLista() {
        No atual = primeiro;
        System.out.print("Lista: ");
        while (atual != null) {
            System.out.print(atual.dado + " ");
            atual = atual.proximo;
        }
        System.out.println();
    }
}

public class VetorApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] valores = {2, 7, 9, 12, 16, 21, 27, 33, 42, 54};
        Vetor vetor = new Vetor(valores);

        System.out.println("\n01 - Vetores");
        System.out.print("1.1 - Digite um valor para buscar: ");
        int valorBusca = scanner.nextInt();
        vetor.buscaBinaria(valorBusca);
        
        System.out.print("1.2 - Digite um valor para inserir no início do vetor: ");
        int valorInsercao = scanner.nextInt();
        vetor.inserirNoInicio(valorInsercao);

        System.out.println("\n02 - Listas Duplamente Encadeadas");
        ListaDuplamenteEncadeada lista = new ListaDuplamenteEncadeada();
        for (int val : valores) {
            lista.inserirNoFinal(val);
        }
        lista.exibirLista();

        System.out.print("2.1 - Digite um valor para inserir no final da lista: ");
        int valorListaFinal = scanner.nextInt();
        lista.inserirNoFinal(valorListaFinal);
        lista.exibirLista();

        System.out.println("2.2 - Número de nós na lista: " + lista.contarNos());
        
        scanner.close();
    }
}
