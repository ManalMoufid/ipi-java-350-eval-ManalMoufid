package com.ipiecoles.java.java350.service;

import com.ipiecoles.java.java350.model.Manager;
import com.ipiecoles.java.java350.model.Technicien;
import com.ipiecoles.java.java350.repository.ManagerRepository;
import com.ipiecoles.java.java350.repository.TechnicienRepository;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityNotFoundException;
import java.util.HashSet;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ManagerIntegrationTest {

    @Autowired
    public ManagerService managerService;

    @Autowired
    public ManagerRepository managerRepository;

    @Autowired
    TechnicienRepository technicienRepository;

    @Before
    public void setUp()
    {
        technicienRepository.deleteAll();
        managerRepository.deleteAll();
    }

    @Test
    public void testAddTechnicien()
    {
        //Given
        Technicien t1 = new Technicien("dubois",  "eric",  "BB23", null ,  1500d,3 );
        technicienRepository.save(t1);

        Manager m = new Manager("moufid",  "manal",  "AL123", null ,  3000d, new HashSet<>());
        managerRepository.save(m);

        //When
        managerService.addTechniciens(m.getId(),t1.getMatricule());
        //Then

        Manager finalManager = managerRepository.findOneWithEquipeById(m.getId());
        Technicien finalTechnicien = technicienRepository.findOne((t1.getId()));

        Assertions.assertThat(finalManager.getEquipe().contains(finalTechnicien));
        Assertions.assertThat(finalTechnicien.getManager()).isNotNull();
        Assertions.assertThat(finalTechnicien.getManager()).isEqualTo(finalManager);
    }

    @Test
    public void testAddTechnicienWithNoManager()
    {
        Technicien t2 = new Technicien("novet",  "cyrille",  "CN11", null ,  1560d,1 );
        technicienRepository.save(t2);

        try {
            managerService.addTechniciens(6L, t2.getMatricule());
            Assertions.fail("Cela aurait dû planter");
        }
        catch (Exception e) {
            Assertions.assertThat(e).isInstanceOf(EntityNotFoundException.class);
            Assertions.assertThat(e).hasMessage("Impossible de trouver le manager d'identifiant 6");
        }
    }





}
