# Problema do Carteiro Chinês (PCC) - Circuito Euleriano

## 📋 Sobre o Projeto
Este projeto foi desenvolvido para a disciplina de **Resolução de Problemas com Grafos**. O objetivo é encontrar o caminho de custo mínimo que percorra todas as arestas de um dígrafo ponderado pelo menos uma vez, retornando ao ponto de origem.

## 🧠 Metodologia Aplicada
A resolução foi dividida em duas etapas fundamentais:

1.  **Eulerização Manual:** Análise detalhada do grafo original para identificar vértices desbalanceados ($d_{in} \neq d_{out}$). Foram adicionadas arestas baseadas em caminhos reais para tornar o grafo euleriano.
2.  **Implementação Computacional:** Utilização do **Método de Hierholzer** para extrair o circuito euleriano a partir da instância balanceada.

### Diferencial Técnico: Fidelidade Topológica
Diferente de abordagens que utilizam "atalhos" ou arestas fictícias, esta implementação utiliza **16 arestas** para garantir que:
* Apenas ruas existentes no mapa original fossem duplicadas.
* O balanceamento dos vértices fosse absoluto, permitindo a execução correta do algoritmo.
* O custo total de **284.00** representasse um trajeto real e executável.

---

## 📊 Visualização dos Grafos

### 1. Grafo Original
Instância oficial utilizada como base para a identificação dos vértices desbalanceados.
<br>
<img src="src/imagens/grafo_original.png" width="400">

### 2. Grafo Eulerizado (Solução)
Visualização gerada via **Graphviz** onde as arestas repetidas para eulerização são destacadas.
<br>
<img src="src/imagens/grafo_eulerizado.png" width="450">

---

## ⚖️ Análise de Balanceamento e Eulerização

Para que um circuito euleriano exista, o grafo deve ser **conexo** e todos os seus vértices devem estar **balanceados** ($d_{in} = d_{out}$). Abaixo, apresentamos a evolução do balanceamento entre a instância original e a solução proposta.

### 1. Grafo Original (Desbalanceado)
Identificamos que os vértices **A, B, E e F** impediam a existência do circuito no grafo original:

| Vértice | Entrada ($d_{in}$) | Saída ($d_{out}$) | Status |
| :--- | :---: | :---: | :--- |
| **0 (A)** | 1 | 2 | **Sobra Saída (+1)** |
| **1 (B)** | 1 | 2 | **Sobra Saída (+1)** |
| **2 (C)** | 2 | 2 | OK |
| **3 (D)** | 2 | 2 | OK |
| **4 (E)** | 3 | 2 | **Falta Saída (-1)** |
| **5 (F)** | 2 | 1 | **Falta Saída (-1)** |

### 2. Grafo Eulerizado (Solução Proposta)
Após a eulerização manual, onde foram duplicadas apenas arestas **existentes** (respeitando a topologia real do mapa), alcançamos o equilíbrio perfeito:

| Vértice | Entrada ($d_{in}$) | Saída ($d_{out}$) | Status |
| :--- | :---: | :---: | :--- |
| **0 (A)** | 3 | 3 | **OK** |
| **1 (B)** | 2 | 2 | **OK** |
| **2 (C)** | 3 | 3 | **OK** |
| **3 (D)** | 2 | 2 | **OK** |
| **4 (E)** | 4 | 4 | **OK** |
| **5 (F)** | 2 | 2 | **OK** |

> **Nota Técnica:** Diferente de abordagens que utilizam "atalhos" (arestas que não existem no grafo original), esta solução utiliza 16 arestas para garantir que o trajeto do carteiro seja fisicamente possível, percorrendo ruas reais para levar o fluxo dos vértices com excesso de saída para os vértices com falta de saída.




LINK DO VÍDEO: https://drive.google.com/drive/folders/1UR4UJZJOPaPyaDDZUapU8xDViAc74XnF?usp=sharing