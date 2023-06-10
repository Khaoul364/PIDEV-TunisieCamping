package tn.esprit.spring.service;

import tn.esprit.spring.entity.Categorie;

import java.util.List;

public interface ICategorieService {

    public Categorie createCategory(Categorie c);
    public List<Categorie> fetchCategoryList();
    public Categorie updateCategory(Categorie category, long IdCategorie);
    public void deleteCategory(long IdCategorie);

    public Categorie findByIdCategory(long id);
    public List<Categorie> retrieveCategoryById(long id);
}
