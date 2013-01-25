/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tgrafos;

import java.util.ArrayList;

/**
 *
 * @author Administrador
 */
class Vertex implements Comparable< Vertex> {


    public int oid;
    public ArrayList< ElementoVizinho> vizinhos = new ArrayList< ElementoVizinho>();
    public double x, y;
    public int pai;
    public int nivel;
    public boolean marcado;
    public float minDistance = Float.POSITIVE_INFINITY;
    public float cost = Float.POSITIVE_INFINITY;
    public boolean denovonao = false;

    Vertex(int oid) {
        this.oid = oid;
        this.pai = -1;
    }

    Vertex(int oid, double x, double y) {
        this.oid = oid;
        this.pai = -1;
        this.x = x;
        this.y = y;
    }

    public void desmarcar() {
        this.marcado = false;
    }

    public void marcar() {
        this.marcado = true;
    }
    
    public void AdicionaVizinho(Vertex vertice1,Vertex vertice2,Float peso){
        
        ArrayList vizinhos1 = vertice1.vizinhos;
                    
                    if (!vizinhos1.contains(vertice2)) {
                        ElementoVizinho elementovizinho1 = new ElementoVizinho(vertice2.oid, peso);
                        vertice1.vizinhos.add(elementovizinho1);
                    }
                    ArrayList vizinhos2 = vertice2.vizinhos;
                    if (!vizinhos2.contains(vertice1)) {
                        ElementoVizinho elementovizinho2 = new ElementoVizinho(vertice1.oid, peso);
                        vertice2.vizinhos.add(elementovizinho2);
                    }
    
}
    
        public void AdicionaVizinho(Vertex vertice1,Vertex vertice2){
        
        ArrayList vizinhos1 = vertice1.vizinhos;
                    
                    if (!vizinhos1.contains(vertice2)) {
                        ElementoVizinho elementovizinho1 = new ElementoVizinho(vertice2.oid);
                        vertice1.vizinhos.add(elementovizinho1);
                    }
                    ArrayList vizinhos2 = vertice2.vizinhos;
                    if (!vizinhos2.contains(vertice1)) {
                        ElementoVizinho elementovizinho2 = new ElementoVizinho(vertice1.oid);
                        vertice2.vizinhos.add(elementovizinho2);
                    }
    
}

    @Override
    public int compareTo(Vertex other) {

        return Float.compare(minDistance, other.minDistance);

    }

    @Override
    public String toString() {
        return String.valueOf(this.oid);
    }
}