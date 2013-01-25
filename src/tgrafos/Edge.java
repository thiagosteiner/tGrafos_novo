/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tgrafos;

/**
 *
 * @author Administrador
 */
class Edge {

    public int v1;
    public int v2;
    public float w;
    public int nVezes = 0;

    Edge(String e1, String e2, String p) {
        this.v1 = Integer.parseInt(e1);
        this.v2 = Integer.parseInt(e2);
        this.w = Float.parseFloat(p);

    }

    Edge(int e1, int e2, double p) {
        this.v1 = e1;
        this.v2 = e2;
        this.w = (float) p;

    }

    Edge(int e1, int e2) {
        this.v1 = e1;
        this.v2 = e2;
    }

    Edge(String e1, String e2) {
        this.v1 = Integer.parseInt(e1);
        this.v2 = Integer.parseInt(e2);
    }

    float returnW() {
        return this.w;
    }
}
