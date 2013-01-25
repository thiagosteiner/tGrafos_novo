/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tgrafos;

/**
 *
 * @author Administrador
 */
class DepositoDoPeso {

    int nVezes = 0;
    float peso = 0;

    DepositoDoPeso(float peso, int nVezes) {
        this.nVezes = nVezes;
        this.peso = peso;
    }

    int getNVezes() {
        return this.nVezes;
    }

    float getPeso() {
        return this.peso;
    }
}
