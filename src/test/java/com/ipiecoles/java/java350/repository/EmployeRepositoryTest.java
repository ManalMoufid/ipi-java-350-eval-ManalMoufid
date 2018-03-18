package com.ipiecoles.java.java350.repository;

import com.ipiecoles.java.java350.MySpringApplication;
import com.ipiecoles.java.java350.model.Commercial;
import com.ipiecoles.java.java350.model.Employe;
import com.ipiecoles.java.java350.model.Manager;
import com.ipiecoles.java.java350.model.Technicien;
import com.ipiecoles.java.java350.repository.EmployeRepository;
import org.assertj.core.api.Assertions;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.List;

/**
 * Created by HCHARBONNEYR on 12/03/2018.
 */
@RunWith(SpringRunner.class)
//@DataJpaTest
@SpringBootTest(classes = MySpringApplication.class)
public class EmployeRepositoryTest {

    @Autowired
    private EmployeRepository employeRepository;


    Commercial pierreDurand,rachidDurand,manuelPierre;
    Manager manalMoufid;
   // Technicien sandrineDubois;

  /*  @Before
    public void setUp(){
        employeRepository.deleteAll();

        pierreDurand = new Commercial();
        pierreDurand.setNom("Durand");
        pierreDurand.setPrenom("Pierre");
        pierreDurand.setSalaire(2000d);
        pierreDurand = employeRepository.save(pierreDurand);

        rachidDurand = new Commercial();
        rachidDurand.setNom("Durand");
        rachidDurand.setPrenom("Rachid");
        rachidDurand.setSalaire(2000d);
        rachidDurand = employeRepository.save(rachidDurand);

        manuelPierre = new Commercial();
        manuelPierre.setNom("Pierre");
        manuelPierre.setPrenom("Manuel");
        manuelPierre.setSalaire(2000d);
        manuelPierre = employeRepository.save(manuelPierre);


    }


    @Test
    public void testfindByNomOrPrenomAllIgnoreCasePrenom(){
        //given

        Commercial c = new Commercial();
        c.setPrenom("Pierre");
        c.setNom("Durand");
        c = employeRepository.save(c);

        //when
        List<Employe> employeList = employeRepository.findByNomOrPrenomAllIgnoreCase("pierre");
        //then
        Assertions.assertThat(employeList).isNotEmpty();
        Assertions.assertThat(employeList).hasSize(2);
        Assertions.assertThat(employeList).contains(pierreDurand,manuelPierre);
    }

    @Test
    public void testFindByNomOrPrenomAllIgnoreCaseNom(){
        //given

        Commercial c = new Commercial();
        c.setPrenom("Pierre");
        c.setNom("Durand");
        c = employeRepository.save(c);

        //when
        List<Employe> employeList = employeRepository.findByNomOrPrenomAllIgnoreCase("durand");
        //then
        Assertions.assertThat(employeList).isNotEmpty();
        Assertions.assertThat(employeList).hasSize(2);
        Assertions.assertThat(employeList).contains(pierreDurand);
        Assertions.assertThat(employeList).contains(rachidDurand);
    }*/
    /*@Test
    public void testFindByNomOrPrenomAllIgnoreCaseMulti(){
        //given

        //when
        List<Employe> employeList = employeRepository.findByNomOrPrenomAllIgnoreCase("durand");
        //then
        Assertions.assertThat(employeList).isNotEmpty();
        Assertions.assertThat(employeList).hasSize(2);
        Assertions.assertThat(employeList).contains(pierreDurand);
        Assertions.assertThat(employeList).contains(rachidDurand);
    }

    @Test
    public void testFindByNomOrPrenomAllIgnoreCasePrenomMaj(){
        //given

        //when
        List<Employe> employeList = employeRepository.findByNomOrPrenomAllIgnoreCase("RACHID");
        //then
        Assertions.assertThat(employeList).isNotEmpty();
        Assertions.assertThat(employeList).hasSize(1);
        Assertions.assertThat(employeList).contains(rachidDurand);
    }

    @Test
    public void testFindByNomOrPrenomAllIgnoreCaseNotFound(){
        //given

        Commercial c = new Commercial();
        c.setPrenom("Pierre");
        c.setNom("Durand");
        c = employeRepository.save(c);

        //when
        List<Employe> employeList = employeRepository.findByNomOrPrenomAllIgnoreCase("Valls");
        //then

        Assertions.assertThat(employeList).isEmpty();
    }*/

    @Test
    public void testFindEmployePlusRiche()
    {
        //GIVEN
        Technicien t = new Technicien("novet",  "cyrille",  "CN11", null ,  1560d,1 );
        employeRepository.save(t);
        Manager m = new Manager("moufid",  "manal",  "AL123", null ,  3000d, new HashSet<>());
        employeRepository.save(m);
        Commercial c=new Commercial("dubois","sandrine","DS345",null,900d,2345d);
        employeRepository.save(c);

        //WHEN
       List<Employe> listeEmploye =employeRepository.findEmployePlusRiches();

        //THEN
        Assertions.assertThat(listeEmploye).isNotEmpty();
        Assertions.assertThat(listeEmploye).hasSize(1);
        Assertions.assertThat(listeEmploye).contains(m);
    }

    @Test
    public void testFindEmployePlusRicheSalaireIdentique()
    {
        //GIVEN
        Technicien t = new Technicien("novet",  "cyrille",  "CN11", null ,  2000d,1 );
        employeRepository.save(t);
        Manager m = new Manager("moufid",  "manal",  "AL123", null ,  2000d, new HashSet<>());
        employeRepository.save(m);
        Commercial c=new Commercial("dubois","sandrine","DS345",null,2000d,2345d);
        employeRepository.save(c);

        //WHEN
        List<Employe> listeEmploye =employeRepository.findEmployePlusRiches();

        //THEN
        Assertions.assertThat(listeEmploye).isEmpty();

    }


}