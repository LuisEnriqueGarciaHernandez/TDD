package com.legh.rectas;

import org.hamcrest.core.Is;
import org.hamcrest.core.IsNull;
import org.hamcrest.number.IsCloseTo;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class InterseccionRectasTest {

    public static final double ERROR_PRESICION = 0.00001;
    @Rule
    public ExpectedException expected = ExpectedException.none();

    @Test
    public void newInstance() {
        Recta recta = new Recta(new Punto(1.0, 1.0), new Punto(4.0, 2.0));
        Assert.assertThat(recta, IsNull.notNullValue());
    }

    @Test
    public void encuentraLaInterseccionEntreDosRectasNoParalelas() {
        Recta recta1 = new Recta(new Punto(1.0, 1.0), new Punto(4.0, 2.0));
        Recta recta2 = new Recta(new Punto(5.0, 1.0), new Punto(4.0, 2.0));
        assertInterseccion(Recta.interseccion(recta1, recta2), 4.0, 2.0);
    }

    private void assertInterseccion(final Punto interseccion, final double enX, final double enY) {
        Assert.assertThat("Error en X ",interseccion.getX(), IsCloseTo.closeTo(enX, ERROR_PRESICION));
        Assert.assertThat("Error en Y", interseccion.getY(), IsCloseTo.closeTo(enY, ERROR_PRESICION));
    }

    @Test
    public void cuandoSeBuscaLaInterseccionEntreDosRectasParalelas_RegresaExcepcion() {
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

    @Test
    public void encuentraLaInterseccionEntreUnaRectaHorizontalYCualquierOtra() {
        Recta recta1 = new Recta(new Punto(-2.0, 2.0), new Punto(4.0, 2.0));
        Recta recta2 = new Recta(new Punto(5.0, 1.0), new Punto(4.0, 2.0));
        Punto interseccion = Recta.interseccion(recta1, recta2);
        Assert.assertThat(interseccion.getX(), Is.is(4.0));
        Assert.assertThat(interseccion.getY(), Is.is(2.0));
    }

    @Test
    public void encuentraLaInterseccionEntreDosRectasEnElCuadranteNegativo() {
        Recta recta1 = new Recta(new Punto(-1.0, -1.0), new Punto(-4.0, -2.0));
        Recta recta2 = new Recta(new Punto(-5.0, -1.0), new Punto(-3.0, -3.0));
        Punto interseccion = Recta.interseccion(recta1, recta2);
        Assert.assertThat(interseccion.getX(), Is.is(-4.0));
        Assert.assertThat(interseccion.getY(), Is.is(-2.0));
    }

    @Test
    public void lluviaDePruebas() {
        assertInterseccion(Recta.interseccion(crearRecta(-5,-2, 2, 4), crearRecta(5,-2,-2, 4)), 0.0, 2.28571);
        assertInterseccion(Recta.interseccion(crearRecta(-5000,-2000, -5000, 2000), crearRecta(5, 0, -2, 0)), -5000.0, 0);
        assertInterseccion(Recta.interseccion(crearRecta(-1,-1,-5000, -500), crearRecta(1,-1,5000,-500)), 0.0, -0.90018);
    }

    private Recta crearRecta(final double x1, final double y1, final double x2, final double y2) {
        return new Recta(new Punto(x1, y1), new Punto(x2, y2));
    }

}
