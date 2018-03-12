package com.ipiecoles.java.java350.model;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class CommercialTest {

    @Test
    public void testPrimeAnnuelleWithCANull()
    {
        //Given : Initialisation des données d'entrée
        Commercial commercial=new Commercial();
        commercial.setCaAnnuel(null);
        //When :
        double prime= commercial.getPrimeAnnuelle();
        //Then :
        Assertions.assertThat(prime).isEqualTo(500d);

    }

}
