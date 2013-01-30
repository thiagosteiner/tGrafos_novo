/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tgrafos;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.BitSet;
import java.util.LinkedList;
import java.util.Stack;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 * @author Thiago Steiner Alfeu
 */
public class Graph {

    public int nVertices = 0;
    public double mPesos[][];
    public static double custoTSP;
    public ArrayList<Integer> cicloHamiltoniano = new ArrayList<Integer>();
    public ArrayList< Edge> arestas = new ArrayList< Edge>();
    public ArrayList< Vertex> vertices = new ArrayList< Vertex>();
    public ArrayList< String> dist = new ArrayList< String>();
    public String distancia = "";
    public ArrayList< DepositoDoPeso> pesos = new ArrayList< DepositoDoPeso>();
    public ArrayList< Float> distancias = new ArrayList< Float>();
    Map< String, Integer> freqDist = new HashMap< String, Integer>();
    Map< Float, Integer> freqDist2 = new HashMap< Float, Integer>();
    public ArrayList< ArrayList> componentesConexos = new ArrayList< ArrayList>();
    public ArrayList elementos = new ArrayList();
    public ArrayList< ArrayList> arvoreGeradora = new ArrayList< ArrayList>();
    public char modo_de_representacao;
    //    boolean[][] matriz;
    BitSet gigante;
    BitSet gigante1;
    BitSet gigante2;
    ArrayList pais = new ArrayList();
    ArrayList niveis = new ArrayList();
    ArrayList marcados = new ArrayList();
    public String busca = "";

    Graph(int nVertices2, ArrayList arestas2) {
        this.nVertices = nVertices2;
        this.arestas = arestas2;
    }

    Graph(int nVertices2, ArrayList arestas2, ArrayList vertices2) {
        this.nVertices = nVertices2;
        this.arestas = arestas2;
        this.vertices = vertices2;
    }

    Graph(int nVertices2, ArrayList arestas2, ArrayList vertices2, double[][] mPesos) {
        this.nVertices = nVertices2;
        this.arestas = arestas2;
        this.vertices = vertices2;
        this.mPesos = mPesos;
    }

    Graph(ArrayList vertices2) {
        this.nVertices = vertices2.size();

    }

    void setGigante(BitSet g) {
        this.gigante = g;
    }

    void setGigante1(BitSet g) {
        this.gigante1 = g;
    }

    void setGigante2(BitSet g) {
        this.gigante2 = g;
    }

    BitSet getGigante() {
        return this.gigante;
    }

    BitSet getGigante2() {
        return this.gigante2;
    }

    BitSet getGigante1() {
        return this.gigante1;
    }

    boolean getBooleanByEdge(int v1, int v2) {

        long max = (long) Integer.MAX_VALUE;

        BigInteger bigMaxInt = BigInteger.valueOf(max);
        BigInteger bigTamanho = retornaBitSetSize();
        BigInteger bigPosicao = BigInteger.ZERO;
        //BigPosicao2 é a diferanca entre o bigPosicao e o Inteiro Maximo
        BigInteger bigPosicao2 = BigInteger.ZERO;

        if (v1 > v2) {
            int aux = v1;
            v1 = v2;
            v2 = aux;
        }

        if (bigTamanho.compareTo(bigMaxInt) == 1) {

            bigPosicao = retornaIndiceBitSet(v1, v2);


            if (bigPosicao.compareTo(bigMaxInt) == -1) {
                int a = bigPosicao.intValue();

                boolean gig1 = gigante1.get(bigPosicao.intValue());
                return gig1;

            } else {

                bigPosicao2 = bigPosicao.subtract(bigMaxInt);
                int b = bigPosicao2.intValue();

                boolean gig2 = gigante2.get(bigPosicao2.intValue());
                return gig2;

            }

        } else {



            bigPosicao = retornaIndiceBitSet(v1, v2);
            return gigante.get(bigPosicao.intValue());

        }
    }

    public void representacao() {
        if (this.modo_de_representacao == 'm') {
            representaMatriz();
        } else if (this.modo_de_representacao == 'l') {
            representaLista();
        } else {
            System.out.println("Nao existe essa representacao");
        }
    }

    BigInteger retornaBitSetSize() {

        BigInteger bignVertices = BigInteger.valueOf((long) nVertices);
        BigInteger resultado = BigInteger.ZERO;
        BigInteger big2 = BigInteger.valueOf((long) 2);

        resultado = (bignVertices.multiply(
                bignVertices.add(
                BigInteger.ONE)));
        resultado = resultado.divide(big2);

        return resultado;
    }

    BigInteger retornaIndiceBitSet(int v1, int v2) {

        if (v2 < v1) {
            System.out.println("ERRO v2 < v1");
        }

        BigInteger bignVertices = BigInteger.valueOf((long) nVertices);
        BigInteger bigv2 = BigInteger.valueOf((long) v2);
        BigInteger bigv1 = BigInteger.valueOf((long) v1);
        BigInteger big2 = BigInteger.valueOf((long) 2);


        BigInteger resultado = BigInteger.ZERO;

        resultado = bigv2.subtract(
                bigv1).add(
                BigInteger.ONE).add(
                bigv1.subtract(BigInteger.ONE).multiply(
                bignVertices)).subtract((bigv1.multiply(
                bigv1.subtract(BigInteger.ONE))).divide(
                big2));



        return resultado;
    }

    public void representaMatriz() {


        int indice = arestas.size();
        long max = (long) Integer.MAX_VALUE;

        BigInteger bigMaxInt = BigInteger.valueOf(max);
        BigInteger bigTamanho = retornaBitSetSize();


        if (bigTamanho.compareTo(bigMaxInt) == 1) {


            BigInteger bigPosicao = BigInteger.ZERO;
            //BigPosicao2 é a diferanca entre o bigPosicao e o Inteiro Maximo
            BigInteger bigPosicao2 = BigInteger.ZERO;

            BigInteger bigDiferenca = BigInteger.ZERO;
            bigDiferenca = bigTamanho.subtract(bigMaxInt);

            int diferenca = bigDiferenca.intValue();

            gigante1 = new BitSet(bigMaxInt.intValue());
            gigante2 = new BitSet(diferenca);

            for (int i = 0; i < indice; i++) {
                Edge aresta = (Edge) arestas.get(i);
                int linha = aresta.v1;
                int coluna = aresta.v2;
                if (coluna < linha) {
                    int apoio = coluna;
                    coluna = linha;
                    linha = apoio;
                }

                bigPosicao = retornaIndiceBitSet(linha, coluna);

                if (bigPosicao.compareTo(bigMaxInt) == -1) {
                    gigante1.set(bigPosicao.intValue(), true);

                } else {

                    bigPosicao2 = bigPosicao.subtract(bigMaxInt);
                    gigante2.set(bigPosicao2.intValue(), true);

                }
            }
            setGigante1(gigante1);
            setGigante2(gigante2);
        } //caso o bigTamanho seja menor que o bigMaxInt
        else {

            gigante = new BitSet(bigTamanho.intValue());
            gigante.clear();



            for (int i = 0; i < indice; i++) {
                Edge aresta = (Edge) arestas.get(i);
                int linha = aresta.v1;
                int coluna = aresta.v2;

                if (coluna < linha) {
                    int apoio = coluna;
                    coluna = linha;
                    linha = apoio;
                }

                BigInteger bigPosicao = BigInteger.ZERO;

                bigPosicao = retornaIndiceBitSet(linha, coluna);

                gigante.set(bigPosicao.intValue());


            }
            setGigante1(gigante);
        }
    }

    private void defineVertices() {

        if (vertices.isEmpty()) {
            for (int i = 0; i < nVertices; i++) {
                int indice = i + 1;
                Vertex vertice = new Vertex(indice);
                vertice.marcado = false;
                vertices.add(vertice);
            }
        } else {
            for (Vertex v : vertices) {
                v.marcado = false;
            }
        }
    }

    public void defineVizinhos() {
        for (int i = 0; i < vertices.size(); i++) {
            Vertex vert = (Vertex) vertices.get(i);
            int velemento = vert.oid;

            for (int j = 0; j < arestas.size(); j++) {
                Edge aresta = (Edge) arestas.get(j);
                int velemento1 = aresta.v1;
                int velemento2 = aresta.v2;
                float weight = aresta.w;

                ArrayList vizinhos = vert.vizinhos;
                if (velemento == velemento1 && !vizinhos.contains(velemento2)) {

                    ElementoVizinho elemento2 = new ElementoVizinho(velemento2, weight);

                    vert.vizinhos.add(elemento2);
                }

                if (velemento == velemento2 && !vizinhos.contains(velemento1)) {
                    ElementoVizinho elemento1 = new ElementoVizinho(velemento1, weight);

                    vert.vizinhos.add(elemento1);
                }

            }
        }

    }

    public void representaLista() {
        ArrayList< ArrayList> lista = new ArrayList< ArrayList>();

        this.defineVertices();
        this.defineVizinhos();

        for (int i = 0; i < nVertices; i++) {
            Vertex vert = (Vertex) vertices.get(i);
            ArrayList array = new ArrayList();
            ArrayList vizinhos = vert.vizinhos;
            for (int j = 0; j < vizinhos.size(); j++) {

                ElementoVizinho aux = vert.vizinhos.get(j);
                int labelVizinho = aux.getOid();

                array.add(labelVizinho - 1); //veja que labelVizinho >= 1
            }
            lista.add(array);
        }

    }

    private void imprimeMatriz(boolean[][] matriz) {
        for (int i = 0; i < nVertices; i++) {
            String linha = "";
            for (int j = 0; j < nVertices; j++) {
                int valor = 0;
                if (matriz[i][j] == true) {
                    valor = 1;
                }
                linha += valor + " ";
            }
            System.out.println(linha);
        }
    }

    public void imprimeGigante() {
        for (int i = 0; i < 1000000; i++) {

            BitSet aux = new BitSet();

            aux = getGigante1();

            System.out.println(aux.get(i));
        }


    }

    private void imprimeLista(ArrayList< ArrayList> lista) {
        for (int i = 0; i < lista.size(); i++) {
            String linha = (i + 1) + " -> ";
            int tam = lista.get(i).size();
            for (int j = 0; j < tam; j++) {
                String elemento = lista.get(i).get(j).toString();
                if (j != tam - 1) {
                    int valor = Integer.parseInt(elemento) + 1;
                    linha = linha + valor + " , ";
                } else {
                    int valor = Integer.parseInt(elemento) + 1;
                    linha = linha + valor;
                }
            }
            System.out.println(linha);
        }
    }

    @SuppressWarnings("static-access")
    public void BFS(int oid) {
        this.busca = "BFS";
        if (this.modo_de_representacao == 'l') {
            desmarcarVertices();
        }
        if (this.modo_de_representacao == 'm') {
            defineParametrosLista();
        }
        elementos.clear();
        arvoreGeradora.clear();
        realizarBFS(oid);

        if (this.modo_de_representacao == 'l') {
            BFSCompleta();
        }
        if (this.modo_de_representacao == 'm') {
            BFSCompleta();
        }
    }

    public void realizarBFS(int oid) {
        if (this.modo_de_representacao == 'l') {
            ArrayList els = new ArrayList();
            LinkedList< String> fila = new LinkedList< String>();

            int index = oid - 1;
            Vertex vertice = (Vertex) vertices.get(index);

            fila.addFirst(String.valueOf(vertice.oid));
            vertice.marcar();

            vertice.nivel = 0;
            vertice.pai = 0;
            elementos.add(vertice.oid);

            while (fila.size() != 0) {
                int v = Integer.parseInt(fila.removeLast());
                int ind = v - 1;
                if (ind != -1) {
                    Vertex vert = (Vertex) vertices.get(ind);
                    els.add(vert.oid);
                    ArrayList vizinhosV = (ArrayList) vert.vizinhos;
                    for (int i = 0; i < vizinhosV.size(); i++) {


                        // #vizinhos.
                        int vizinho = Integer.parseInt(vizinhosV.get(i).toString());
                        int ind2 = vizinho - 1;
                        if (ind2 != -1) {
                            Vertex viz = (Vertex) vertices.get(ind2);
                            if (!viz.marcado) {
                                viz.marcar();
                                fila.addFirst(String.valueOf(vizinho));
                                viz.nivel = vert.nivel + 1; //nivel
                                viz.pai = v; //pai
                                elementos.add(viz.oid);
                            }
                        }
                    }
                }
            }
            arvoreGeradora.add(els);
        } else if (this.modo_de_representacao == 'm') {
            ArrayList els = new ArrayList();
            LinkedList< String> fila = new LinkedList< String>();
            fila.addFirst(String.valueOf(oid));

            marcados.set(oid - 1, true);
            niveis.set(oid - 1, 0);
            pais.set(oid - 1, 0);

            elementos.add(oid);

            while (fila.size() != 0) {

                int v = Integer.parseInt(fila.removeLast());
                int ind = v - 1;
                els.add(v);
                for (int i = 0; i < ind; i++) {
                    int oidViz = i + 1;
                    int indViz = i;
                    if (getBooleanByEdge(ind + 1, indViz + 1) == true && marcados.get(indViz).equals(false)) {
                        marcados.set(indViz, true);
                        fila.addFirst(String.valueOf(oidViz));
                        int nivel = Integer.parseInt(niveis.get(ind).toString()) + 1;
                        niveis.set(indViz, nivel);
                        pais.set(indViz, v);
                        elementos.add(oidViz);
                    }
                }
            }
            arvoreGeradora.add(els);
        }

    }

    public void BFSCompleta() {
        if (elementos.size() != nVertices) {
            for (int a = 0; a < nVertices; a++) {
                int oid = -1;
                if (this.modo_de_representacao == 'l') {
                    Vertex vert2 = (Vertex) vertices.get(a);
                    oid = vert2.oid;
                } else if (this.modo_de_representacao == 'm') {
                    oid = a + 1;
                }
                if (!elementos.contains(oid)) {
                    realizarBFS(oid);
                }
            }
        }
    }

    public void DFS(int vertice) {
        this.busca = "DFS";
        if (this.modo_de_representacao == 'l') {
            desmarcarVertices();
        }
        if (this.modo_de_representacao == 'm') {
            defineParametrosLista();
        }
        elementos.clear();
        arvoreGeradora.clear();
        realizarDFS(vertice);
    }

    public void realizarDFS(int oid) {
        ArrayList arvore = new ArrayList();
        int nivel = 0;
        if (this.modo_de_representacao == 'l') {
            //erasim int indice = procurarVertice(oid);
            int indice = oid - 1;
            Vertex vertice = (Vertex) vertices.get(indice);

            Stack pilha = new Stack();
            pilha.push(String.valueOf(vertice.oid));

            while (pilha.size() != 0) {
                int ind = Integer.parseInt(pilha.pop().toString());
                //erasim int id = procurarVertice(ind);
                int id = ind - 1;
                Vertex vert = (Vertex) vertices.get(id);

                if (nivel == 0) {
                    arvore.add(vert);
                    vertice.nivel = nivel;
                    vertice.pai = 0;
                    elementos.add(vertice.oid);
                } else {
                    if (!elementos.contains(vert)) {
                        int indicePai = vert.pai;
                        //erasim int index2 = procurarVertice(indicePai);
                        int index2 = indicePai - 1;
                        Vertex pai = (Vertex) vertices.get(index2);
                        int nivelFilho = pai.nivel + 1;
                        arvore.remove(vert);
                        vert.pai = pai.oid;
                        vert.nivel = nivelFilho;
                        arvore.add(vert);
                    }
                }

                if (!vert.marcado) {
                    vert.marcar();

                    for (int i = 0; i < arestas.size(); i++) {
                        Edge aresta = (Edge) arestas.get(i);
                        int elemento1 = aresta.v1;
                        int elemento2 = aresta.v2;
                        //erasim                  int index1 = procurarVertice(elemento1);
                        //                        int index2 = procurarVertice(elemento2);
                        int index1 = elemento1 - 1;
                        int index2 = elemento2 - 1;

                        Vertex vertex1 = (Vertex) vertices.get(index1);
                        Vertex vertex2 = (Vertex) vertices.get(index2);
                        if (elemento1 == vert.oid && !vertex2.marcado) {
                            pilha.push(elemento2);
                            arvore.remove(vertex2);

                            vertex2.pai = vert.oid;
                            if (!elementos.contains(vertex2.oid)) {
                                elementos.add(vertex2.oid);
                            }
                            arvore.add(vertex2);
                            vertices.add(vertex2);
                            vertices.set(elemento2 - 1, vertex2);
                            vertices.remove(vertices.size() - 1);
                        } else if (elemento2 == vert.oid && !vertex1.marcado) {
                            pilha.push(elemento1);
                            arvore.remove(vertex1);

                            vertex1.pai = vert.oid;

                            arvore.add(vertex1);
                            if (!elementos.contains(vertex1.oid)) {
                                elementos.add(vertex1.oid);
                            }
                            vertices.add(vertex1);
                            vertices.set(elemento1 - 1, vertex1);
                            vertices.remove(vertices.size() - 1);
                        }
                    }
                    nivel++;

                }
            }
        } else if (this.modo_de_representacao == 'm') {
            //DFS para a estrutura de matriz
            Stack pilha = new Stack();
            pilha.push(String.valueOf(oid));

            while (pilha.size() != 0) {
                int vert = Integer.parseInt(pilha.pop().toString());
                int indiceVert = vert - 1;
                String el1 = String.valueOf(vert);

                if (nivel == 0) {
                    arvore.add(el1);
                    niveis.set(indiceVert, nivel);
                    pais.set(indiceVert, 0);
                    elementos.add(vert);
                }

                if (marcados.get(indiceVert).equals(false)) {
                    marcados.set(indiceVert, true);

                    for (int i = 0; i < arestas.size(); i++) {
                        Edge aresta = (Edge) arestas.get(i);
                        int elemento1 = aresta.v1;
                        int elemento2 = aresta.v2;
                        int index1 = elemento1 - 1;
                        int index2 = elemento2 - 1;
                        String ele1 = String.valueOf(elemento1);
                        String ele2 = String.valueOf(elemento2);
                        if (elemento1 == vert && marcados.get(index2).equals(false)) {
                            pilha.push(elemento2);
                            arvore.remove(ele2);

                            pais.set(index2, vert);
                            niveis.set(index2, Integer.parseInt(niveis.get(indiceVert).toString()) + 1);
                            if (!elementos.contains(elemento2)) {
                                elementos.add(elemento2);
                            }
                            arvore.add(ele2);
                        } else if (elemento2 == vert && marcados.get(index1).equals(false)) {
                            pilha.push(elemento1);
                            arvore.remove(ele1);

                            pais.set(index1, vert);
                            niveis.set(index1, Integer.parseInt(niveis.get(indiceVert).toString()) + 1);
                            arvore.add(ele1);
                            if (!elementos.contains(elemento1)) {
                                elementos.add(elemento1);
                            }
                        }
                    }
                    nivel++;

                }
            }
            completaDFS(arvore);
        }
    }

    public void completaDFS(ArrayList arvore) {
        if (elementos.size() != nVertices) {
            for (int a = 0; a < nVertices; a++) {
                int oid = -1;
                if (this.modo_de_representacao == 'l') {
                    Vertex vert2 = (Vertex) vertices.get(a);
                    oid = vert2.oid;
                } else if (this.modo_de_representacao == 'm') {
                    oid = a + 1;
                }
                if (!elementos.contains(oid)) {
                    realizarDFS(oid);
                }
            }
        }
        arvoreGeradora.add(arvore);
    }

    private void desmarcarVertices() {
        for (int i = 0; i < vertices.size(); i++) {
            Vertex v = (Vertex) vertices.get(i);
            v.desmarcar();
        }

    }

    public void obtemComponentesConexos(Vertex v) {
        BFS(v.oid);
        int maior = 0;
        int menor = 0;
        for (int i = 0; i < arvoreGeradora.size(); i++) {
            ArrayList arvore = arvoreGeradora.get(i);
            if (maior < arvore.size()) {
                maior = arvore.size();
            }

            if (menor > arvore.size()) {
                menor = arvore.size();
            }

        }

        System.out.println("Numero de componentes: " + arvoreGeradora.size());
        System.out.println("Maior componente: " + maior);
        System.out.println("Menor componente: " + menor);
    }

    public ArrayList< ArrayList> defineComponentesConexos() {
        componentesConexos.clear();

        Vertex vert = (Vertex) vertices.get(0);
        int vertice = vert.oid;

        desmarcarVertices();

        ArrayList analisados = new ArrayList();
        fiscalizarCC(vert, analisados);

        int size = componentesConexos.size();
        System.out.println("Numero de componentes conexos: " + size);
        System.out.println("Componentes conexos: ");
        ordernarComponentes();

        for (int i = 0; i < componentesConexos.size(); i++) {
            System.out.print(componentesConexos.get(i).size() + " ----> ");
            ArrayList lista = componentesConexos.get(i);
            for (int j = 0; j < lista.size(); j++) {
                Vertex vertex = (Vertex) lista.get(j);
                System.out.print("oid = " + vertex.oid + "|| ");
            }

            System.out.println();
        }

        System.out.println("Numero de componentes = " + componentesConexos.size());
        System.out.println("Menor componente = " + componentesConexos.get(0).size());
        System.out.println("Maior componente = " + componentesConexos.get(
                componentesConexos.size() - 1).size());

        return componentesConexos;
    }

    public void fiscalizarCC(Vertex vertice, ArrayList analisados) {
        if (vertice != null) {
            elementos.clear();
            realizarBFS(vertice.oid);
            Vertex elemDesmarcado = null;
            ArrayList usados = new ArrayList();
            for (int i = 0; i < vertices.size(); i++) {
                Vertex element = (Vertex) vertices.get(i);
                if (!element.marcado) {
                    elemDesmarcado = (Vertex) vertices.get(i);
                } else if (element.marcado && !analisados.contains(element)) {
                    usados.add(element);
                    analisados.add(element);
                }

            }
            componentesConexos.add(usados);
            fiscalizarCC(elemDesmarcado, analisados);
        } else {
            return;
        }

    }

    public Vertex returnVertexByOid(int oid) {
        Vertex resultado = null;
        for (int i = 0; i < vertices.size(); i++) {
            Vertex vert = (Vertex) vertices.get(i);
            if (vert.oid == oid) {
                resultado = vert;
                break;

            }


        }
        return resultado;
    }

    public int procurarVertice(int oid) {
        int resultado = -1;

        for (Vertex v : vertices) {
            if (v.oid == oid) {
                return resultado = v.oid;
            }

        }
        return resultado;
    }

    private void ordernarComponentes() {
        for (int i = 0; i < componentesConexos.size(); i++) {
            ArrayList comp1 = componentesConexos.get(i);
            for (int j = i + 1; j < componentesConexos.size(); j++) {
                ArrayList comp2 = componentesConexos.get(j);
                if (comp1.size() < comp2.size()) {
                    ArrayList apoio = comp2;
                    componentesConexos.set(j, comp1);
                    componentesConexos.set(i, apoio);
                    break;

                }


            }
        }
    }

    public void grau() {
        ArrayList graus = new ArrayList();
        int maiorGrau = -1;
        int menorGrau = 0;
        for (int i = 0; i < vertices.size(); i++) {
            Vertex v = (Vertex) vertices.get(i);
            int grau = v.vizinhos.size();
            if (grau > maiorGrau) {
                maiorGrau = grau;
            }

            if (grau < menorGrau) {
                menorGrau = grau;
            }

            graus.add(grau);
        }

        ArrayList< ArrayList> frequencia = new ArrayList< ArrayList>();
        for (int i = 0; i <= maiorGrau; i++) {
            int vezes = 0;
            for (int j = 0; j < graus.size(); j++) {
                int g = Integer.parseInt(graus.get(j).toString());
                if (i == g) {
                    vezes++;
                }

            }
            ArrayList ele = new ArrayList();
            double freq = (double) vezes / (double) nVertices;
            ele.add(i);
            ele.add(freq);
            frequencia.add(ele);
        }

        System.out.println("frequencia");
        int a = 0;
        for (int i = 0; i < frequencia.size(); i++) {
            String f = frequencia.get(i).get(1).toString();
            double freq = Double.parseDouble(f);
            System.out.println(freq);
            a++;

        }


        System.out.println("Maior grau: " + maiorGrau);
        System.out.println("Menor grau: " + menorGrau);
    }

    private void defineParametrosLista() {
        for (int i = 0; i < nVertices; i++) {
            pais.add(-1);
            niveis.add(-1);
            marcados.add(false);
        }
    }

    public void computePaths(int label) {

        int index = label - 1;
        Vertex source = (Vertex) vertices.get(index);
        source.minDistance = 0;
        PriorityQueue< Vertex> vertexQueue = new PriorityQueue< Vertex>();
        vertexQueue.add(source);
        while (!vertexQueue.isEmpty()) {
            Vertex u = vertexQueue.poll();

            //visita cada aresta existente em u
            for (int i = 0; i < u.vizinhos.size(); i++) {
                ElementoVizinho aux = u.vizinhos.get(i);
                int elemento = aux.getOid();
                int idx = elemento - 1;
                Vertex v = (Vertex) vertices.get(idx);
                float weight = aux.getWeight();
                float distanceThroughU = u.minDistance + weight;

                if (distanceThroughU < v.minDistance) {
                    vertexQueue.remove(v);

                    v.minDistance = distanceThroughU;
                    v.pai = u.oid;
                    vertexQueue.add(v);

                }

            }
        }
    }

//    public void computePathsHeapPriorityQueue(int label) {
//
//        int index = label - 1;
//        Vertex source = (Vertex) vertices.get(index);
//        source.minDistance = 0;
//        HeapPriorityQueue vertexQueue = new HeapPriorityQueue(this.nVertices);
//        vertexQueue.put(source);
//        while (!vertexQueue.isEmpty()) {
//            Vertex u = vertexQueue.get(); / dando erro aqui
//
//            //visita cada aresta existente em u
//            for (int i = 0; i < u.vizinhos.size(); i++) {
//                ElementoVizinho aux = u.vizinhos.get(i);
//                int elemento = aux.getOid();
//                int idx = elemento - 1;
//                Vertex v = (Vertex) vertices.get(idx);
//                float weight = aux.getWeight();
//                float distanceThroughU = u.minDistance + weight;
//
//                if (distanceThroughU < v.minDistance) {
//                    vertexQueue.remove(v);
//
//                    v.minDistance = distanceThroughU;
//                    v.pai = u.oid;
//                    vertexQueue.add(v);
//
//                }
//
//            }
//        }
//    }
    public void computeMST(int label) {

        int index = label - 1;
        Vertex source = (Vertex) vertices.get(index);
        source.minDistance = 0;
        PriorityQueue< Vertex> vertexQueue = new PriorityQueue< Vertex>();
        vertexQueue.add(source);

        while (!vertexQueue.isEmpty()) {
            Vertex u = vertexQueue.poll();
            u.marcado = true;
            //visita cada aresta existente em u
            for (int i = 0; i < u.vizinhos.size(); i++) {
                ElementoVizinho aux = u.vizinhos.get(i);
                int oidU = aux.getOid();
                float weightU = aux.getWeight();
                Vertex v = (Vertex) vertices.get(oidU - 1);
                if (v.minDistance > weightU && v.marcado == false) {
                    vertexQueue.remove(v);
                    v.minDistance = weightU;
                    v.pai = u.oid;
                    vertexQueue.add(v);
                }
            }
        }
    }

    void showDjikstra(String name_file) throws IOException {

        FileWriter writer = new FileWriter(new File(name_file));
        PrintWriter saida = new PrintWriter(writer, true);

        saida.println(IO.namefile);
        for (int i = 0; i < vertices.size(); i++) {
            Vertex v = (Vertex) vertices.get(i);

            System.out.println("Distance to " + v.oid + ": " + v.minDistance);
            saida.println("Distance to " + v.oid + ": " + v.minDistance);
            distancias.add(v.minDistance);
            List< Vertex> path = getShortestPathTo(v);
            saida.println("Path: " + path);


        }
        System.out.println("\n" + distancias);

        saida.close();
        writer.close();
    }

    void showMST(String name_file) throws IOException {

        FileWriter writer = new FileWriter(new File(name_file));
        PrintWriter saida = new PrintWriter(writer, true);
        float w = 0;

        for (Vertex v : vertices) {


            w = w + v.minDistance;
            System.out.println(v.pai + " " + v.oid + " " + v.minDistance);
            saida.println(v.pai + " " + v.oid + " " + v.minDistance);


        }

        System.out.println("Peso Total da MST = " + w);
        saida.println("Peso Total da MST = " + w);

        saida.close();
        writer.close();
    }

    List< Vertex> getShortestPathTo(Vertex target) {
        List< Vertex> path = new ArrayList< Vertex>();
        for (Vertex vertex = target; vertex.pai != -1; vertex = (Vertex) vertices.get(vertex.pai - 1)) {
            path.add(vertex);
        }

        Collections.reverse(path);
        return path;
    }

    void getDED() {


        for (float s : distancias) {

            int count = freqDist2.containsKey(s) ? freqDist2.get(s) : 0;
            freqDist2.put(s, count + 1);
        }


        System.out.println("\n" + freqDist2);
    }

    void getDistancias() {
        for (Vertex v2 : vertices) {
            int count = freqDist2.containsKey(v2.minDistance) ? freqDist2.get(v2.minDistance) : 0;
            if (v2.minDistance != 0) {
                freqDist2.put(v2.minDistance, count + 1);
            }
        }
    }

    void DjikstraTotal() {
        for (Vertex ver : vertices) {
            this.computePaths(ver.oid);
            this.getDistancias();
            this.resetaVertices(ver);
        }
    }

    void resetaVertices(Vertex v) {
        for (Vertex ve : vertices) {
            ve.cost = Float.POSITIVE_INFINITY;
            ve.pai = -1;
            ve.marcado = false;
            ve.minDistance = Float.POSITIVE_INFINITY;
            ve.nivel = 0;
        }
    }

    void displayMap(String name_file) throws IOException {
        FileWriter writer = new FileWriter(new File(name_file));
        PrintWriter saida = new PrintWriter(writer, true);
        Set< Float> keys = freqDist2.keySet(); // Obtem as chavas
        //Classifica as chaves
        TreeSet< Float> sortedKeys = new TreeSet< Float>(keys);
        float w = 0;
        float soma = 0;
        //iteracao no conjunto de chaves
        for (Float key : sortedKeys) {
            soma = soma + freqDist2.get(key) / 2;
        }
        saida.printf("\n%-10s%10s%20s\n", "Distancia", "Quantidade", "Frequencia");
        for (Float key : sortedKeys) {
            saida.printf("%-10.2f;%10d;%20.10f;\n", key, freqDist2.get(key) / 2, ((freqDist2.get(key) / 2) / soma));
        }
        saida.println("\nO numero de distancias e: " + soma);
        saida.println(freqDist2);
        saida.println("\nA distancia media e: " + displayMapDistMedia());
        saida.close();
        writer.close();
    }

    float displayMapDistMedia() {

        Set< Float> keys = freqDist2.keySet(); // Obtem as chavas
        //Classifica as chaves
        TreeSet< Float> sortedKeys = new TreeSet< Float>(keys);
        float w = 0;
        float resultado = 0;
        float freq = 0;
        float soma = 0;
        //iteracao no conjunto de chaves
        for (Float key : sortedKeys) {


            freq = freqDist2.get(key);

            w = key;
            resultado = resultado + (w * freq);
            soma = soma + freqDist2.get(key);
            //         System.out.printf(key + " " + (freq/freqDist.size()));
        }
        System.out.println("\nA distancia media e: " + (resultado / soma));
        return resultado / soma;

    }

    void showGraph() {

        for (Vertex v : vertices) {
            System.out.println(v.oid + " " + v.x + " " + v.y);
        }
    }

    void determinaCicloHamiltoniano() {
        int fronteiraInicio = 1; // vértice de fronteira em uma extremidade
        int fronteiraFinal = 2; // vértice de fronteira em outra extremidade
        double menorDistancia = mPesos[1][2]; // pego uma aresta qualquer e tomo como candidata a menor
        double maiorDistancia = mPesos[1][2]; // pego uma aresa qualquer e pego como candidata a maior
        int posicaoFronteiraInicio; // posição de uma das fronteiras no ciclo hamiltooniano
        int posicaoFronteiraFinal; // posição da outra fronteira no ciclo
        ArrayList<Boolean> verticesMarcados = new ArrayList<Boolean>();// vetor de vértices já adicionados ao ciclo
        int i, j;
        // achando aresta de maior valor e de menor valor (menor distância, primeira aresta adicionada)
        // por questões de projeto, assumo a menor distância inicial como a maior	
        for (i = 0; i < nVertices; i++) {
            for (j = 0; j < nVertices; j++) {
                if (i != j) {
                    if (mPesos[i + 1][j + 1] > maiorDistancia) {
                        maiorDistancia = mPesos[i + 1][j + 1];
                    }
                    if (mPesos[i + 1][j + 1] < menorDistancia) {
                        menorDistancia = mPesos[i + 1][j + 1];
                        fronteiraInicio = i + 1;
                        fronteiraFinal = j + 1;
                    }
                }
            }
        }
        //vertice marcado 0 será irrelevante para nosso sistema.
        for (int z = 0; z <= nVertices; z++) {
            verticesMarcados.add(z, Boolean.FALSE);
        }
        verticesMarcados.set(fronteiraInicio, Boolean.TRUE); // vértice i já foi adicionado ao menor caminho
        verticesMarcados.set(fronteiraFinal, Boolean.TRUE); // vértice j já foi adicinoado ao menor caminho
        for (int u = 0; u < nVertices; u++) {
            cicloHamiltoniano.add(u, 0);
        }
        cicloHamiltoniano.set(0, fronteiraInicio);// adicionando vértice i ao ciclo hamiltoniano
        cicloHamiltoniano.set(1, fronteiraFinal);// adicinoando vértice j ao ciclo hamiltoniano
        posicaoFronteiraInicio = 0; // posição da froonteira de início no vetor de ciclo hamiltoniano
        posicaoFronteiraFinal = 1; // posição da fronteira de final no vetor de ciclo hamiltoniano
        int a, b; // variáveis de controle
        Boolean aindaFalta = true;
        Boolean atualiza;
        while (aindaFalta) {
            int temp = 0;
            atualiza = false;
            aindaFalta = false;
            // definida menor distância inicial como a maior já calculada
            // calculando próxima fronteira pra fronteiraFinal
            menorDistancia = maiorDistancia;
            for (a = 1; a <= nVertices; a++) {
                if (fronteiraFinal != a) {
                    if (!(verticesMarcados.get(a).booleanValue())) {
                        if (mPesos[fronteiraFinal][a] < menorDistancia) {
                            aindaFalta = true;
                            temp = a;
                            atualiza = true;
                            menorDistancia = mPesos[fronteiraFinal][a];
                        }
                    }
                }
            }
            if (atualiza) {
                verticesMarcados.set(temp, true);
                posicaoFronteiraFinal = posicaoFronteiraFinal + 1;
//			cicloHamiltoniano[posicaoFronteiraFinal] = temp;
//                      verificar se existe erro aqui!!!  
                cicloHamiltoniano.set(posicaoFronteiraFinal, temp);

                fronteiraFinal = temp;
            }
            menorDistancia = maiorDistancia;
            atualiza = false;
            // calculando próxima fronteira pra fronteiraInicial
            menorDistancia = maiorDistancia;
            for (b = 1; b <= nVertices; b++) {
                if (fronteiraInicio != b) {
                    if (!verticesMarcados.get(b).booleanValue()) {
                        if (mPesos[fronteiraInicio][b] < menorDistancia) {
                            aindaFalta = true;
                            temp = b;
                            atualiza = true;
                            menorDistancia = mPesos[fronteiraInicio][b];
                        }
                    }
                }
            }
            if (atualiza) {
                verticesMarcados.set(temp, Boolean.TRUE);
                posicaoFronteiraInicio = posicaoFronteiraInicio - 1;
//			verifica!!!!
                if (posicaoFronteiraInicio < 0) {
//                            verificar!!!!
                    posicaoFronteiraInicio = nVertices - 1;
                }
//			cicloHamiltoniano[posicaoFronteiraInicio] = temp;
//                        verificar!!!!
                cicloHamiltoniano.set(posicaoFronteiraInicio, temp);
                fronteiraInicio = temp;
            }
            atualiza = false;
        }
    }

    static void setcustoTSP(double custoTSP) {
        Graph.custoTSP = custoTSP;
    }

    public void calculaTSP() {

        determinaCicloHamiltoniano();

        System.out.println("caminho: \n");



        for (int a = 0; a < cicloHamiltoniano.size(); a++) {

            System.out.println(cicloHamiltoniano.get(a));
        }

        double custo = 0;
        int m, n;
        for (int p = 0; p < nVertices; p++) {
            if (p == nVertices - 1) {
                m = cicloHamiltoniano.get(p);
                n = cicloHamiltoniano.get(0);
                custo = custo + mPesos[m][n];
                //System.out.println( "Custo"+p+"= "+custo+ "\n");
            } else {
                m = cicloHamiltoniano.get(p);
                n = cicloHamiltoniano.get(p + 1);
                custo = custo + mPesos[m][n];
                //System.out.println( "Custo"+p+"= "+custo+ "\n");
            }
        }
        System.out.println("Custo = " + custo + "\n");
        Graph.setcustoTSP(custo);
    }
}