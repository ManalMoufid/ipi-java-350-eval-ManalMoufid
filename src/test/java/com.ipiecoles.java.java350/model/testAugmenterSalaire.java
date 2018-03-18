package com.ipiecoles.java.java350.model;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashSet;
import java.util.Set;

@RunWith(JUnitParamsRunner.class)
public class testAugmenterSalaire {

    @Test
    @Parameters({
            //"null,500d",
            "2000d,30d,2600d",
            "1000d,10d,1050d",
    })
    public void augmenterSalaireTest(Double salaire,Double pourcentage,Double ExpectedSalaire)
    {
        //GIVEN
        Manager manager = new Manager();
       Set<Technicien> equipe = new HashSet<>();
        int i=0;
        while (i < 10) {
            Technicien technicien = new Technicien();
            technicien.setGrade(i);
            equipe.add(technicien);
            i++;
        }
        manager.setEquipe(equipe);
        manager.setSalaire(salaire);

        //WHEN
        manager.augmenterSalaire(pourcentage);
        //THEN




    }
}
