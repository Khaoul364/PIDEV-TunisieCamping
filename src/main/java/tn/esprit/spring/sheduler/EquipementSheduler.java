package tn.esprit.spring.sheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import tn.esprit.spring.entity.Equipement;
import tn.esprit.spring.repository.EquipementRepository;

import java.util.ArrayList;
import java.util.List;

@Component
public class EquipementSheduler {

    @Autowired
    private EquipementRepository ER;
    private List<Equipement> equipmentList;

    @Autowired
    public EquipementSheduler(EquipementRepository ER){
        this.ER=ER;
        this.equipmentList=new ArrayList<>();
    }

    @Scheduled(fixedRate = 60000)
    public void refrechEquipList(){

        List<Equipement> updateList = ER.findAll();
        equipmentList.clear();
        equipmentList.addAll(updateList);
    }
}
