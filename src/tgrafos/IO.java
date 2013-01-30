/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tgrafos;

import java.io.*;
import java.util.*;
import java.util.regex.*;

/**
 *
 * @author Thiago Steiner Alfeu
 */
public class IO {

    public static String dir;
    public static String dirS;
    public static String namefile = "";
    public static int vertice;
    public Graph grafo;

    public void ler(String nome_arquivo) {
        try {
            //Leitura do io
            String io = nome_arquivo; 
            File file = new File(io);
            FileInputStream in = new FileInputStream(file);
            FileInputStream in2 = new FileInputStream(file);
            Scanner scanner = new Scanner(in);
            Scanner scanner2 = new Scanner(in2);

            int nVertices = 0;
            int num_arestas = 0;
            ArrayList arestas = new ArrayList();
            ArrayList counter = new ArrayList();
            ArrayList< Vertex> vertices = new ArrayList< Vertex>();

            while (scanner2.hasNext()) {
                String palavra = scanner2.next();
                counter.add(palavra);
            }
            nVertices = Integer.parseInt(counter.get(0).toString());
            counter.remove(0);
            for (int i = 0; i < nVertices; i++) {
                int indice = i + 1;
                Vertex vertice = new Vertex(indice);
                vertice.marcado = false;
                vertices.add(vertice);
            }

            while (scanner.hasNextLine()) {
                scanner.nextLine();
                num_arestas++;
            }
            num_arestas -= 1;


            Edge aresta;
            int vertice_elemento_1;
            int vertice_elemento_2;

            int i;
            int j;
            int k;
            int delim = counter.size();
            if (counter.size() == 3 * num_arestas) {

                for (i = 0, j = 1, k = 2; i < delim; i = i + 3, j = j + 3, k = k + 3) {
                    String elemento1 = counter.get(i).toString();
                    String elemento2 = counter.get(j).toString();
                    String weight = counter.get(k).toString();
                    aresta = new Edge(elemento1, elemento2, weight);
                    arestas.add(aresta);
                    
////////////////////////////////////////////////////////////////////////////// 
                    
                   
                    
                    vertice_elemento_1 = Integer.parseInt(elemento1);
                    vertice_elemento_2 = Integer.parseInt(elemento2);
                    
                     
                    
                    Vertex vertice1 = (Vertex) vertices.get(vertice_elemento_1 - 1);
                    Vertex vertice2 = (Vertex) vertices.get(vertice_elemento_2 - 1);
                    Float peso = new Float(weight);
                    
                    vertice1.AdicionaVizinho(vertice1,vertice2,peso);
                    vertice2.AdicionaVizinho(vertice2,vertice1,peso);
                    
            
                    
                    
                    
                }
            }
            if (counter.size() == 2 * num_arestas) {

                for (i = 0, j = 1; i < delim; i = i + 2, j = j + 2) {
                    String elemento1 = counter.get(i).toString();
                    String elemento2 = counter.get(j).toString();

                    aresta = new Edge(elemento1, elemento2);
                    arestas.add(aresta);
                    
                    vertice_elemento_1 = Integer.parseInt(elemento1);
                    vertice_elemento_2 = Integer.parseInt(elemento2);
                    Vertex vertice1 = (Vertex) vertices.get(vertice_elemento_1 - 1);
                    Vertex vertice2 = (Vertex) vertices.get(vertice_elemento_2 - 1);
                    
                    vertice1.AdicionaVizinho(vertice1,vertice2);
                    vertice2.AdicionaVizinho(vertice2,vertice1);
            
                  
                }
            }

            grafo = new Graph(nVertices, arestas, vertices);

            in.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void f(String nome) throws FileNotFoundException, IOException{
       
        File file = new File(nome);
        FileInputStream in = new FileInputStream(file);
        InputStreamReader b = new InputStreamReader(in);
        readInput(b);
        
    }


//
//    public void readInput(Reader r) throws IOException {
//
//
//        Vertex v;
//        ArrayList arestas = new ArrayList();
//        ArrayList< Vertex> vertices = new ArrayList< Vertex>();
//        double[][] mPesos = null;
//        int n = 0;
//
//        BufferedReader in = new BufferedReader(r);
//        Pattern data = Pattern.compile("([-+.0-9Ee]+)\\s+([-+.0-9Ee]+)\\s*");
//        String line;
//        while ((line = in.readLine()) != null) {
//
//            n = Integer.parseInt(line);
//            {
//
//                double x1, y1;
//                mPesos = new double[n + 1][n + 1];
//
//                for (int k = 0; k < n; k++) {
//                    line = in.readLine();
//                    Matcher m = data.matcher(line);
//                    m.matches();
//                    x1 = Double.parseDouble(m.group(1));
//                    y1 = Double.parseDouble(m.group(2));
//                    v = new Vertex(k + 1, x1, y1);
//                    vertices.add(v);
//
//                }
//                for (int i = 0; i < n; i++) {
//                    for (int j = 0; j < n; j++) {
//
//                        if (i != j) {
//                            double dx = vertices.get(i).x - vertices.get(j).x;
//                            double dy = vertices.get(i).y - vertices.get(j).y;
//                            double weight = (Math.sqrt(dx * dx + dy * dy));
//                            mPesos[i + 1][j + 1] = weight;
//                            if (i < j) {
//                                Edge aresta = new Edge(vertices.get(i).oid, vertices.get(j).oid, weight);
//                                arestas.add(aresta);
//                            }
//                        } else {
//
//                            mPesos[i + 1][j + 1] = 0;
//
//                        }
//
//                    }
//                }
//            }
//        }
//        grafo = new Graph(n, arestas, vertices, mPesos);
//
//        in.close();
//    }

    
    
    public void readInput(Reader r) throws IOException {
        
        
        Vertex v;
        ArrayList arestas = new ArrayList();
        ArrayList < Vertex > vertices = new ArrayList < Vertex > ();
        double[][] mPesos = null;
        int n=0;
        
        BufferedReader in = new BufferedReader(r);
//        Pattern specification = Pattern.compile("\\s*([A-Z_]+)\\s*(:\\s*([0-9]+))?\\s*");
//        Pattern data = Pattern.compile("\\s*([0-9]+)\\s+([-+.0-9Ee]+)\\s+([-+.0-9Ee]+)\\s*");
        Pattern data = Pattern.compile("([-+.0-9Ee]+)\\s+([-+.0-9Ee]+)\\s*");
        String line;
        while ((line = in.readLine()) != null) {
//          Matcher m = specification.matcher(line);
//          if (!m.matches()) continue;
//          String keyword = m.group(1);
//          if (keyword.equals("DIMENSION")) {
//          n = Integer.parseInt(m.group(3));
////            cost = new double[n][n];
//          } else if (keyword.equals("NODE_COORD_SECTION")) {
//            
            n = Integer.parseInt(line);  
            {
            
            double x1,y1;
            mPesos = new double[n+1][n+1];
            
            for (int k = 0; k < n; k++) {
              line = in.readLine();
              Matcher m = data.matcher(line);
              m.matches();
//              int i = Integer.parseInt(m.group(1)) - 1;
              x1 = Double.parseDouble(m.group(1));
              y1 = Double.parseDouble(m.group(2));
              v = new Vertex(k+1,x1,y1);
              vertices.add(v);
              
            }
            
            for (int i = 0; i < n; i++) {
              for (int j = 0; j < n; j++) {
                  
//                if(i!=j && i<j){  
//                double dx = vertices.get(i).x - vertices.get(j).x;
//                double dy = vertices.get(i).y - vertices.get(j).y;
//                double weight = Math.rint(Math.sqrt(dx * dx + dy * dy));
//                mPesos[i+1][j+1] = weight;
//                Edge aresta = new Edge(vertices.get(i).oid, vertices.get(j).oid, weight);
//                arestas.add(aresta);
//                }
                if(i!=j){  
                double dx = vertices.get(i).x - vertices.get(j).x;
                double dy = vertices.get(i).y - vertices.get(j).y;
                double weight = (Math.sqrt(dx * dx + dy * dy));
                //weight= Math.ceil(weight);
                mPesos[i+1][j+1] = weight;
                    if(i<j){
                    Edge aresta = new Edge(vertices.get(i).oid, vertices.get(j).oid, weight);
                    arestas.add(aresta);
                    }
                }
                else 
                {
                    
                    mPesos[i+1][j+1] = 0;
                    
                }
                
              }
            }
          }
        }
          grafo = new Graph(n, arestas, vertices,mPesos);

        in.close();
      }
    
    
    public void escrever() throws IOException {
        //Escrita dos ios
        FileWriter writer = new FileWriter(new File(dir, "saida.txt"));
        PrintWriter saida = new PrintWriter(writer, true);

        saida.println(grafo.nVertices);

        for (int i = 0; i < grafo.arestas.size(); i++) {
            Edge aresta = (Edge) grafo.arestas.get(i);
            saida.println(aresta.v1 + " " + aresta.v2);
        }

        saida.close();
        writer.close();

        FileWriter io = new FileWriter(new File(dir, "info.txt"));
        PrintWriter out = new PrintWriter(io, true);

        int n = grafo.nVertices;
        int m = grafo.arestas.size();
        out.println("# n = " + n);
        out.println("# m = " + m);

        //Calcula os graus dos vertices
        double grauMedio = ((double) (2 * m)) / (double) n;

        ArrayList graus = new ArrayList();
        for (int i = 0; i < m; i++) {
            int grau = 0;
            for (int j = 0; j < m; j++) {
                Edge are = (Edge) grafo.arestas.get(j);
                int vert1 = are.v1;
                int vert2 = are.v2;
                if (i + 1 == vert1) {
                    grau++;
                }
                if (i + 1 == vert2) {
                    grau++;
                }
            }
        }

        out.close();
        io.close();
    }

    public void escreverArvore() throws IOException {
        //Escrita dos ios
        FileWriter writer = new FileWriter(new File(dir, "arvoreGeradora.txt"));
        PrintWriter saida = new PrintWriter(writer, true);


        for (int i = 0; i < grafo.arvoreGeradora.size(); i++) {
            ArrayList els = grafo.arvoreGeradora.get(i);
            for (int j = 0; j < els.size(); j++) {
                int vertice = Integer.parseInt(els.get(j).toString());
                if (grafo.busca.equals("BFS")) {
                    if (grafo.modo_de_representacao == 'l') {
                        int index = vertice - 1;
                        Vertex vertex = (Vertex) grafo.vertices.get(index);
                        saida.print("{\"vertice\": " + vertex.oid + ", \"pai\": " + vertex.pai + ", \"nivel\":" + vertex.nivel + " }, ");
                    } else if (grafo.modo_de_representacao == 'm') {
                        int ind = vertice - 1;
                        saida.print("{\"vertice\": " + vertice + ", \"pai\": " + grafo.pais.get(ind).toString() + ", \"nivel\":" + grafo.niveis.get(ind).toString() + " }, ");
                    }
                } else if (grafo.busca.equals("DFS")) {
                    if (grafo.modo_de_representacao == 'l') {
                        Vertex vertex = (Vertex) els.get(j);
                        saida.print("|" + vertex.oid + "/" + vertex.pai + "/" + vertex.nivel + "| ");
                    } else if (grafo.modo_de_representacao == 'm') {
                        int ind = vertice - 1;
                        saida.print("|" + vertice + "/" + grafo.pais.get(ind).toString() + "/" + grafo.niveis.get(ind).toString() + "| ");
                    }

                }
            }
            saida.println();
            saida.println();
        }

        saida.close();
        writer.close();
    }

    public void setNamefile(String s) {
        this.namefile = s;
    }

    public String getName() {
        return this.namefile;
    }

    public void setVertice(int s) {
        this.vertice = s;
    }

    public int getVertice() {
        return this.vertice;
    }

    public void getResult() throws IOException {
        //Escrita dos ios
        FileWriter writer = new FileWriter(new File(dir, "log_Dist_grafo_5.txt"));
        PrintWriter saida = new PrintWriter(writer, true);
        saida.close();
        writer.close();
    }
    
     public void escreve_arquivo_TSP(Graph grafo,String arquivo_saida) throws IOException {

        FileWriter writer = new FileWriter(new File(arquivo_saida));
        PrintWriter saida = new PrintWriter(writer, true);


        //grafo.determinaCicloHamiltoniano();

        saida.println("caminho: \n");



        for (int a = 0; a < grafo.cicloHamiltoniano.size(); a++) {

            saida.println(grafo.cicloHamiltoniano.get(a));
        }


        saida.println("Custo = " + Graph.custoTSP + "\n");


        saida.close();
        writer.close();


    }
    
}