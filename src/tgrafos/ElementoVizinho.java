/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tgrafos;

/**
 *
 * @author Administrador
 */
class ElementoVizinho {

    int oid;
    float weight;

    ElementoVizinho(int oid, float weight) {
        this.oid = oid;
        this.weight = weight;
    }
    
    ElementoVizinho(int oid) {
        this.oid = oid;
       
    }

    int getOid() {
        return this.oid;
    }

    float getWeight() {
        return this.weight;
    }
}
