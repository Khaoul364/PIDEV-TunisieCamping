package tn.esprit.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.entity.Categorie;
import tn.esprit.spring.service.CategorieService;

import java.util.List;

@RequestMapping("categories")
@RestController
@CrossOrigin
public class CategorieController {
    @Autowired
    private CategorieService CS;
    @CrossOrigin
    @PostMapping(value = "/add")
    public Categorie createCategory(@RequestBody Categorie category) {
        return CS.createCategory(category);
    }
    @CrossOrigin
    @GetMapping("/liste")
    public List<Categorie> fetchCategoryList() {

        return CS.fetchCategoryList();
    }

    @PutMapping("/update/{id}")
    public Categorie
    updateCAtegory(@RequestBody Categorie category,
                   @PathVariable("id") Long IdCategorie)
    {
        return CS.updateCategory(
                category, IdCategorie);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteCategory(@PathVariable("id")
                                 Long IdCategory)
    {
        CS.deleteCategory(IdCategory);

        return "Deleted Successfully";
    }

    @GetMapping("/find/{id}")
    public Categorie retrieveCategoryById(@PathVariable("id")
                                         Long IdCategorie)
    {

        return CS.findByIdCategory(IdCategorie);
    }
}
