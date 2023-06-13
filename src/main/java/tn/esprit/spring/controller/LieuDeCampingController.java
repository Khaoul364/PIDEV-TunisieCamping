package tn.esprit.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.entity.LieuDeCamping;
import tn.esprit.spring.service.ILieuDeCampingService;

import java.util.List;

@RestController
@RequestMapping("lieu")
public class LieuDeCampingController {
    @Autowired
    ILieuDeCampingService ls;

    @PostMapping("/add-lieu")
    @ResponseBody
    public LieuDeCamping ajouterLieu(@RequestBody LieuDeCamping lieu){
        return ls.addLieu(lieu);
    }

    @GetMapping("/all-lieu")
    @ResponseBody
    public List<LieuDeCamping> getAllLieu(){
        return ls.getAllLieu();
    }

    @PutMapping("/edit-lieu/{idLieu}")
    public LieuDeCamping editLieu (@RequestBody LieuDeCamping lieu, @PathVariable("idLieu") int idLieu){
        return ls.editLieu(lieu,idLieu);
    }

    @DeleteMapping("/delete-lieu/{idLieu}")
    public void deleteLieu(@PathVariable("idLieu") int idLieu){
        ls.deleteLieu(idLieu);
    }

}
