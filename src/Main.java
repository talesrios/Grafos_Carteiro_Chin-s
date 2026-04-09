import lib.*;
import java.io.PrintWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        // Caminhos dos arquivos na pasta dados
        String pathOriginal = "dados/entrada_original.txt";
        String pathEulerizado = "dados/entrada_eulerizada.txt";

        // 1. Processar Grafo Original (Apenas para visualização do desbalanceamento)
        System.out.println("Lendo grafo original para geracao de visualizacao...");
        gerarVisualizacaoOriginal(pathOriginal);

        // 2. Processar Grafo Eulerizado (Para calculo do caminho e custo)
        System.out.println("\nLendo grafo eulerizado para execucao do algoritmo...");
        processarCicloEuleriano(pathEulerizado);
    }

    private static void gerarVisualizacaoOriginal(String path) {
        In in = new In(path);
        if (!in.exists()) {
            System.out.println("Aviso: Arquivo " + path + " nao encontrado.");
            return;
        }

        int V = in.readInt();
        int E = in.readInt();
        StringBuilder dotEdges = new StringBuilder();

        for (int i = 0; i < E; i++) {
            int v = in.readInt();
            int w = in.readInt();
            double peso = in.readDouble();
            dotEdges.append(String.format("    %d -> %d [label=\"%.1f\"];\n", v, w, peso));
        }

        salvarArquivoDot("grafo_original.dot", dotEdges.toString());
    }

    private static void processarCicloEuleriano(String path) {
        In in = new In(path);
        if (!in.exists()) {
            System.out.println("Erro: Arquivo " + path + " nao encontrado.");
            return;
        }

        int V = in.readInt();
        int E = in.readInt();
        Digraph G = new Digraph(V);
        double custoTotal = 0;
        StringBuilder dotEdges = new StringBuilder();
        
        // Mapeamento de nomes
        String[] nomes = {"A", "B", "C", "D", "E", "F", "G", "H"};

        for (int i = 0; i < E; i++) {
            int v = in.readInt();
            int w = in.readInt();
            double peso = in.readDouble();
            G.addEdge(v, w);
            custoTotal += peso;
            dotEdges.append(String.format("    %d -> %d [label=\"%.1f\", color=red];\n", v, w, peso));
        }

        System.out.println("================================================");
        System.out.println("      RESULTADOS: GRAFO EULERIZADO             ");
        System.out.println("================================================");
        
        System.out.println("TABELA DE BALANCEAMENTO:");
        System.out.println("------------------------------------------------");
        System.out.printf("%-15s | %-10s | %-10s\n", "Vertice", "d_in", "d_out");
        System.out.println("------------------------------------------------");
        
        for (int v = 0; v < G.V(); v++) {
            String letra = (v < nomes.length) ? nomes[v] : String.valueOf(v);
            System.out.printf("Vertice %d : %s   | %-10d | %-10d\n", 
                              v, letra, G.indegree(v), G.outdegree(v));
        }
        System.out.println("------------------------------------------------");

        DirectedEulerianCycle euler = new DirectedEulerianCycle(G);

        if (euler.hasEulerianCycle()) {
            System.out.println("STATUS: Circuito euleriano encontrado.");
            
            // Gerando caminhos em Letras e Números
            StringBuilder caminhoLetras = new StringBuilder();
            StringBuilder caminhoNumeros = new StringBuilder();
            
            for (int v : euler.cycle()) {
                String letra = (v < nomes.length) ? nomes[v] : String.valueOf(v);
                caminhoLetras.append(letra).append(" -> ");
                caminhoNumeros.append(v).append(" -> ");
            }

            String resLetras = caminhoLetras.toString();
            String resNumeros = caminhoNumeros.toString();

            System.out.println("CAMINHO (LETRAS): " + resLetras.substring(0, resLetras.length() - 4));
            System.out.println("CAMINHO (NUMERO): " + resNumeros.substring(0, resNumeros.length() - 4));
            
            System.out.println("------------------------------------------------");
            System.out.printf("CUSTO TOTAL: %.2f\n", custoTotal);
        } else {
            System.out.println("STATUS: O grafo nao permite um circuito euleriano.");
        }
        System.out.println("================================================");

        salvarArquivoDot("grafo_eulerizado.dot", dotEdges.toString());
    }

    private static void salvarArquivoDot(String nomeArquivo, String edges) {
        String dotContent = "digraph G {\n" +
                            "    node [shape=circle, fontname=\"Arial\", style=filled, fillcolor=white];\n" +
                            "    edge [fontname=\"Arial\", fontsize=10];\n" +
                            "    rankdir=LR;\n" + 
                            edges + 
                            "}";
        try (PrintWriter out = new PrintWriter(nomeArquivo)) {
            out.println(dotContent);
            System.out.println("Arquivo '" + nomeArquivo + "' gerado.");
        } catch (IOException e) {
            System.err.println("Erro ao salvar DOT: " + e.getMessage());
        }
    }
}