/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tgrafos;

import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Principal {

    static IO io = new IO();
    static ArrayList<String> filesT2 = new ArrayList<String>();
    static ArrayList<String> filesT3 = new ArrayList<String>();

    static void filesAdd() {

        filesT2.add("grafo_1.txt");
        filesT2.add("grafo_2.txt");
        filesT2.add("grafo_3.txt");
        filesT2.add("grafo_4.txt");
        filesT2.add("grafo_5.txt");
        filesT2.add("grafo_5.txt");

        filesT3.add("points-5.txt");//0
        filesT3.add("points-10.txt");//1
        filesT3.add("points-20.txt");//2
        filesT3.add("points-50.txt");//3
        filesT3.add("points-80.txt");//4
        filesT3.add("points-100.txt");//5
        filesT3.add("points-150.txt");//6
        filesT3.add("points-200.txt");//7
        filesT3.add("points-500.txt");//8
        filesT3.add("points-1000.txt");//9
        filesT3.add("points-1500.txt");//10
        filesT3.add("points-2000.txt");//11
        filesT3.add("points-2500.txt");//12
    }

    static void rodaMST(int source) throws IOException {
        io.setVertice(source);
//                  io.setNamefile(filesT2.get(numGrafo));
//                  io.ler(filesT2.get(numGrafo));
        Graph grafo = io.grafo;
//                  grafo.modo_de_representacao = 'l';
//                  grafo.representacao();
        grafo.computeMST(io.getVertice());
//                  grafo.showMST("C:/Users/Administrador/Documents/Dropbox/t2Grafos/t2Grafos_novo/saidas/mst_grafo_1.txt");
    }

    static void rodaDjikstra(int source) throws IOException {
        io.setVertice(source);
        //io.setNamefile(filesT2.get(numGrafo));
        //io.ler(filesT2.get(numGrafo));
        Graph grafo = io.grafo;
        //grafo.modo_de_representacao = 'l';
        //grafo.representacao();
        grafo.computePaths(io.getVertice());
//                  grafo.showDjikstra("C:/Users/Administrador/Documents/Dropbox/t2Grafos/t2Grafos_novo/saidas/Djikstra_grafo_1.txt");
    }

    static void rodaFreq(int source) throws IOException {
        io.setVertice(source);
//                  System.out.println(filesT2.get(numGrafo));
//                  io.setNamefile(filesT2.get(numGrafo));
//                  io.ler(filesT2.get(numGrafo));
        Graph grafo = io.grafo;
//                  grafo.modo_de_representacao = 'l';
//                  grafo.representacao();  
        grafo.DjikstraTotal();
//                  grafo.displayMap("C:/Users/Administrador/Documents/Dropbox/t2Grafos/t2Grafos_novo/saidas/freq_grafo_1.txt");
    }

    public static void main(String[] args) throws IOException {


        filesAdd();
        io.dir = "C:/Users/Administrador/Documents/Dropbox/Teoria Grafos/Trabalhos/Trabalhos/tGrafos_novo/entradas";
        io.dirS = "C:/Users/Administrador/Documents/Dropbox/Teoria Grafos/Trabalhos/Trabalhos/tGrafos_novo/saidas";
        io.f(filesT3.get(12));  // define o arquivo do grafo a ser utilizado
        Graph grafo = io.grafo;
        grafo.modo_de_representacao = 'l';
        double init = System.currentTimeMillis();
        grafo.calculaTSP();
        double finish = System.currentTimeMillis();
        System.out.println(finish - init + " ms");


//                io.ler("C:/Users/Administrador/Documents/Dropbox/t2Grafos/t2Grafos_novo/entradas/grafo_1.txt");
//                Graph grafo = io.grafo;
//                double finish = System.currentTimeMillis();
//                rodaMST(1);
//                rodaDjikstra(1);
//                rodaFreq(1);
//                System.out.println(finish - init + " ms");                

    }
}