package tn.esprit.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entity.Categorie;
import tn.esprit.spring.repository.CategorieRepository;

import java.util.List;
@Service

public class CategorieService implements ICategorieService{
    @Autowired
    private CategorieRepository CR;

    @Override
    public Categorie createCategory(Categorie c) {

            return CR.save(c);
    }

    @Override
    public List<Categorie> fetchCategoryList() {
        return (List<Categorie>)CR.findAll();
    }

    @Override
    public Categorie updateCategory(Categorie category, long IdCategorie) {
        Categorie c = CR.findById(IdCategorie).get();
        c.setCategorieName(category.getCategorieName());
        c.setCategorieDescription(category.getCategorieDescription());
        return CR.save(c);
    }

    @Override
    public void deleteCategory(long IdCategorie) {
        CR.deleteById(IdCategorie);

    }

    @Override
    public Categorie findByIdCategory(long id) {
        Categorie category= CR.findById(id).get();
        return  category;    }

    @Override
    public List<Categorie> retrieveCategoryById(long id) {
        return null;
    }
}
