package com.legh.rectas;

import org.hamcrest.core.Is;
import org.hamcrest.core.IsNull;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class InterseccionRectasTest {

    @Rule
    public ExpectedException expected = ExpectedException.none();

    @Test
    public void newInstance() {
        Recta recta = new Recta(new Punto(1.0, 1.0), new Punto(4.0, 2.0));
        Assert.assertThat(recta, IsNull.notNullValue());
    }

    @Test
    public void encuentraLaInterseccionEntreDosRectasConDiferentePendiente() {
        Recta recta1 = new Recta(new Punto(1.0, 1.0), new Punto(4.0, 2.0));
        Recta recta2 = new Recta(new Punto(5.0, 1.0), new Punto(4.0, 2.0));
        Punto interseccion = Recta.interseccion(recta1, recta2);
        Assert.assertThat(interseccion.getX(), Is.is(4.0));
        Assert.assertThat(interseccion.getY(), Is.is(2.0));
    }

    @Test
    public void cuandoSeBuscaLaInterseccionEntreDosRectasConLaMismaPendiente_RegresaExcepcion() {
        expected.expect(CouldNotInterceptedException.class);
        expected.expectMessage("Las rectas son paralelas");
        Recta recta1 = new Recta(new Punto(1.0, 1.0), new Punto(4.0, 2.0));
        Recta recta2 = new Recta(new Punto(1.0, 1.0), new Punto(4.0, 2.0));
        Recta.interseccion(recta1, recta2);
    }

    @Test
    public void encuentraLaInterseccionEntreUnaRectaVerticalYCualquierOtra() {
        Recta recta1 = new Recta(new Punto(4.0, 1.0), new Punto(4.0, 2.0));
        Recta recta2 = new Recta(new Punto(5.0, 1.0), new Punto(4.0, 2.0));
        Punto interseccion = Recta.interseccion(recta1, recta2);
        Assert.assertThat(interseccion.getX(), Is.is(4.0));
        Assert.assertThat(interseccion.getY(), Is.is(2.0));
    }

}
