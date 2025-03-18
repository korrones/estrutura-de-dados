package estruturas;

public class Lista {

    private No inicio;

    public void Lista() {
        this.inicio = null;
    }

    public void adicionarAoInicio (int valor){
        No no = new No valor;

        No antigoInicio = this.inicio;

        antigoInicio.anterior = no;

        no.proximo = antigoInicio;

        this.inicio = no;
    }

    public void mostrarNos(){
        No noProxAux = this.inicio;
        while (noProxAux != null){
            System.out.println("No " + noProxAux.dado)
            if(noProxAux.proximo != null){
                System.out.println(" | Proximo -> " + noProxAux.proximo.dado);

            }
            noProxAux = noProxAux.proximo;
        }
    }
    public void removerNo (int valor){
        No noProxAux = this.inicio;
        No noAntAux = null;

        while (noProxAux != null && noProxAux.dado != valor){
            noProxAux = noProxAux.proximo;
        }

        if (noProxAux =  null){
            System.out.println("Valor não encontrado");
            return;
        }

        noAntAux.proximo = noProxAux.proximo;
    }
}
