package estruturas;
import java.util.Arrays;
import java.util.Scanner;
//exercicios - listas, vetores e iteradores
class Vetor {
    private int[] vetor;
    private int capacidade;
    private int tamanho;

    public Vetor(int[] vetor) {
        this.vetor = Arrays.copyOf(vetor, vetor.length);
        this.capacidade = vetor.length;
        this.tamanho = vetor.length;
    }

    public void buscaBinaria(int valor) {
        int inicio = 0, fim = tamanho - 1;
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
        if (tamanho == capacidade) {
            aumentarCapacidade();
        }
        int[] novoVetor = new int[capacidade];
        novoVetor[0] = valor;
        System.arraycopy(vetor, 0, novoVetor, 1, tamanho);
        vetor = novoVetor;
        tamanho++;
        System.out.println("Vetor após inserção: " + Arrays.toString(Arrays.copyOf(vetor, tamanho)));
    }

    private void aumentarCapacidade() {
        int novaCapacidade = this.capacidade + (this.capacidade / 2);
        vetor = Arrays.copyOf(vetor, novaCapacidade);
        this.capacidade = novaCapacidade;
        System.out.println("Capacidade da lista aumentada: " + Arrays.toString(Arrays.copyOf(vetor, tamanho)));
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
    private No atual;

    public ListaDuplamenteEncadeada() {
        this.primeiro = null;
        this.ultimo = null;
        this.atual = null;
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

    public void inserirApos(int valorExistente, int novoValor) {
        No atual = primeiro;
        while (atual != null && atual.dado != valorExistente) {
            atual = atual.proximo;
        }
        if (atual == null) {
            System.out.println("Valor não encontrado na lista.");
            return;
        }
        No novo = new No(novoValor);
        novo.proximo = atual.proximo;
        novo.anterior = atual;
        if (atual.proximo != null) {
            atual.proximo.anterior = novo;
        }
        atual.proximo = novo;
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

    public void setAtual(int valor) {
        No temp = primeiro;
        while (temp != null) {
            if (temp.dado == valor) {
                this.atual = temp;
                return;
            }
            temp = temp.proximo;
        }
        System.out.println("Valor não encontrado na lista.");
    }

    public void inserirAposAtual(int valor) {
        if (atual == null) {
            System.out.println("Nenhum nó atual definido.");
            return;
        }
        No novo = new No(valor);
        novo.anterior = atual;
        novo.proximo = atual.proximo;
        if (atual.proximo != null) {
            atual.proximo.anterior = novo;
        }
        atual.proximo = novo;
    }

    public void removerAposAtual() {
        if (atual == null || atual.proximo == null) {
            System.out.println("Não há nó após o atual.");
            return;
        }
        No remover = atual.proximo;
        atual.proximo = remover.proximo;
        if (remover.proximo != null) {
            remover.proximo.anterior = atual;
        }
    }

    public void inserirAntesAtual(int valor) {
        if (atual == null) {
            System.out.println("Nenhum nó atual definido.");
            return;
        }

        No novo = new No(valor);
        novo.proximo = atual;
        novo.anterior = atual.anterior;

        if (atual.anterior != null) {
            atual.anterior.proximo = novo;
        } else {
            primeiro = novo;
        }

        atual.anterior = novo;
    }

    public void removerAntesAtual() {
        if (atual == null || atual.anterior == null) {
            System.out.println("Não há nó antes do atual.");
            return;
        }

        No remover = atual.anterior;
        atual.anterior = remover.anterior;

        if (remover.anterior != null) {
            remover.anterior.proximo = atual;
        } else {
            primeiro = atual;
        }

        remover.proximo = null;
        remover.anterior = null;
    }
}

public class VetorApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] valores = {2, 7, 9, 12, 16, 21, 27, 33, 42, 54};
        Vetor vetor = new Vetor(valores);

        System.out.println("\n01 - Vetores");
        System.out.print("Digite um valor para buscar: ");
        int valorBusca = scanner.nextInt();
        vetor.buscaBinaria(valorBusca);

        System.out.print("Digite um valor para inserir no início do vetor: ");
        int valorInsercao = scanner.nextInt();
        vetor.inserirNoInicio(valorInsercao);

        System.out.println("\n02 - Listas Duplamente Encadeadas");
        ListaDuplamenteEncadeada lista = new ListaDuplamenteEncadeada();
        for (int val : valores) {
            lista.inserirNoFinal(val);
        }
        lista.exibirLista();

        System.out.print("Digite um valor para inserir no final da lista: ");
        int valorListaFinal = scanner.nextInt();
        lista.inserirNoFinal(valorListaFinal);
        lista.exibirLista();

        System.out.println("Número de nós na lista: " + lista.contarNos());

        System.out.print("Digite um valor existente e um novo valor para inserir após ele: ");
        int valorExistente = scanner.nextInt();
        int novoValor = scanner.nextInt();
        lista.inserirApos(valorExistente, novoValor);
        lista.exibirLista();

        System.out.println("\n04 - Iteradores");
        System.out.print("Digite um valor da lista para ser o nó atual: ");
        int valorAtual = scanner.nextInt();
        lista.setAtual(valorAtual);

        System.out.print("Digite um valor para inserir após o atual: ");
        int valorAposAtual = scanner.nextInt();
        lista.inserirAposAtual(valorAposAtual);
        lista.exibirLista();

        System.out.println("Removendo após o atual...");
        lista.removerAposAtual();
        lista.exibirLista();

        System.out.print("Digite um valor para inserir antes do atual: ");
        int valorAntesAtual = scanner.nextInt();
        lista.inserirAntesAtual(valorAntesAtual);
        lista.exibirLista();

        System.out.println("Removendo antes do atual...");
        lista.removerAntesAtual();
        lista.exibirLista();

        scanner.close();
    }
}
