package com.ipiecoles.java.java350.repository;

import com.ipiecoles.java.java350.MySpringApplication;
import com.ipiecoles.java.java350.model.Commercial;
import com.ipiecoles.java.java350.model.Employe;
import com.ipiecoles.java.java350.model.Manager;
import com.ipiecoles.java.java350.model.Technicien;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = MySpringApplication.class)
public class testEmployePlusRicheSiSalaireIdentique {

    @Autowired
    private EmployeRepository employeRepository;

    @Test
    public void testFindEmployePlusRicheSalaireIdentique()
    {
        //GIVEN
      /*  Technicien t = new Technicien("novet",  "cyrille",  "CN11", null ,  2000d,1 );
        employeRepository.save(t);
        Manager m = new Manager("moufid",  "manal",  "AL123", null ,  2000d, new HashSet<>());
        employeRepository.save(m);
        Commercial c=new Commercial("dubois","sandrine","DS345",null,2000d,2345d);
        employeRepository.save(c);

        //WHEN
        List<Employe> listeEmploye =employeRepository.findEmployePlusRiches();

        //THEN
        Assertions.assertThat(listeEmploye).isEmpty();*/

    }
}
