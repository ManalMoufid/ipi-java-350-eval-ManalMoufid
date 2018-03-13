package com.ipiecoles.java.java350.model;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;


@RunWith(JUnitParamsRunner.class)
public class testManager {
    @Test
    @Parameters({
            "0,0,0",
            "2000d,5,3600d",
            "3000d,8,6300d",
    })

    public void testSetSalaire(Double salaire, Double taille, Double salaireExpected ) {
        //GIVEN
        Set<Technicien> equipe = new HashSet<>();
        Manager manager = new Manager();
        int i=0;
        while (i < taille) {
            Technicien technicien = new Technicien();
            technicien.setGrade(i);
            equipe.add(technicien);
            i++;
        }

        manager.setEquipe(equipe);

        //WHEN
        manager.setSalaire(salaire);


        //THEN
        Assertions.assertThat(equipe.size()).isNotNull();
        Double salaireFinal= manager.getSalaire();
        Assertions.assertThat(salaireFinal).isNotNull();
        Assertions.assertThat(salaireFinal).isEqualTo(salaireExpected);

    }


}
