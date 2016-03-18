package com.legh.rectas;


public class Recta {
    private Punto punto1;
    private Punto punto2;
    private final double pendiente;
    private final double constanteB;

    public Recta(final Punto punto1, final Punto punto2) {
        this.punto1 = punto1;
        this.punto2 = punto2;
        this.constanteB = calcularConstanteB();
        this.pendiente = pendiente();
    }

    public static Punto interseccion(final Recta recta1, final Recta recta2) {
        double x = (recta2.getConstanteB() - recta1.getConstanteB()) / (recta1.getPendiente() - recta2.getPendiente());
        double y = recta1.getPendiente() * x + recta1.getConstanteB();
        return new Punto(x, y);
    }

    public double calcularConstanteB() {
        return punto1.getY() - (punto1.getX() * pendiente());
    }

    private double pendiente() {
        double cateto_adyacente = punto1.getX() - punto2.getX();
        double cateto_opuesto = punto1.getY() - punto2.getY();
        return cateto_opuesto / cateto_adyacente;
    }

    public double getPendiente() {
        return pendiente;
    }

    public double getConstanteB() {
        return constanteB;
    }
}
