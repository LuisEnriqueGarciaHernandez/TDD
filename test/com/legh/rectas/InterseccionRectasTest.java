package com.legh.rectas;

import org.hamcrest.core.IsNull;
import org.junit.Assert;
import org.junit.Test;

public class InterseccionRectasTest {

    @Test
    public void newInstance() {
        Recta recta = new Recta(new Punto(1.0, 1.0), new Punto(4.0, 2.0));
        Assert.assertThat(recta, IsNull.notNullValue());
    }
}
