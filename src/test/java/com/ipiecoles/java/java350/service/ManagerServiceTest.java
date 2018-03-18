package com.ipiecoles.java.java350.service;

import com.ipiecoles.java.java350.model.Employe;
import com.ipiecoles.java.java350.model.Manager;
import com.ipiecoles.java.java350.model.Technicien;
import com.ipiecoles.java.java350.repository.ManagerRepository;
import com.ipiecoles.java.java350.repository.TechnicienRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import javax.persistence.EntityNotFoundException;
import java.util.Set;

import static org.mockito.AdditionalAnswers.returnsFirstArg;

@RunWith(MockitoJUnitRunner.class)
public class ManagerServiceTest {

    @InjectMocks
    ManagerService managerService;

    @Mock
    ManagerRepository managerRepository;

    @Mock
    TechnicienRepository technicienRepository;

    @Test
    public void testDeleteTechniciens(){
        //Given
        Manager m = new Manager();
        Technicien t = new Technicien();
        m.getEquipe().add(t);
        Mockito.when(managerRepository.findOne(1L)).thenReturn(m);
        Mockito.when(technicienRepository.findOne(2L)).thenReturn(t);

        //When
        managerService.deleteTechniciens(1L, 2L);

        //Then
        ArgumentCaptor<Manager> argManager = ArgumentCaptor.forClass(Manager.class);
        Mockito.verify(managerRepository).save(argManager.capture());
        Assertions.assertThat(argManager.getValue().getEquipe()).isEmpty();

        ArgumentCaptor<Technicien> argTechnicien = ArgumentCaptor.forClass(Technicien.class);
        Mockito.verify(technicienRepository).save(argTechnicien.capture());
        Assertions.assertThat(argTechnicien.getValue().getManager()).isNull();
    }


    public void testAddTechnicien(){
        //Given

        Manager m = new Manager();
        Technicien t = new Technicien();
        m.getEquipe().add(t);
        Mockito.when(managerRepository.findOne(1L)).thenReturn(m);
        Mockito.when(technicienRepository.findByMatricule("AB12")).thenReturn(t);
        Mockito.when(managerRepository.save(Mockito.any(Manager.class))).then(returnsFirstArg());

        //When
        managerService.addTechniciens(1L, "AB12");

        //Then
        ArgumentCaptor<Manager> argManager = ArgumentCaptor.forClass(Manager.class);
        Mockito.verify(managerRepository).save(argManager.capture());
        Assertions.assertThat(argManager.getValue().getEquipe()).isNotEmpty();

        ArgumentCaptor<Technicien> argTechnicien = ArgumentCaptor.forClass(Technicien.class);
        Mockito.verify(technicienRepository).save(argTechnicien.capture());
        Assertions.assertThat(argTechnicien.getValue().getManager()).isNotNull();
    }

    @Test
    public void addTechnicienSiManagerNotFound()
    {
        Mockito.when(managerRepository.findOneWithEquipeById(5L)).thenReturn(null);
        Technicien t = new Technicien();
        Mockito.when(technicienRepository.findByMatricule("AB12")).thenReturn(t);

        try {
            managerService.addTechniciens(5L,"AB12");
            Assertions.fail("Cela aurait dû planter !");
        }
        catch (Exception e)
        {
            Assertions.assertThat(e).isInstanceOf(EntityNotFoundException.class);
            Assertions.assertThat(e).hasMessage("Impossible de trouver le manager d'identifiant 5");
        }
    }

    @Test
    public void addTechnicienSiTechnicienNotFound()
    {
        Manager m = new Manager();
        Mockito.when(managerRepository.findOneWithEquipeById(6L)).thenReturn(m);
        Mockito.when(technicienRepository.findByMatricule("CX45")).thenReturn(null);

        try{
            managerService.addTechniciens(6L,"CX45");
            Assertions.fail("Cela aurait dû planter !");
        }
        catch (Exception e)
        {
            Assertions.assertThat(e).isInstanceOf(EntityNotFoundException.class);
            Assertions.assertThat(e).hasMessage("Impossible de trouver le technicien de matricule CX45");
        }

    }
}