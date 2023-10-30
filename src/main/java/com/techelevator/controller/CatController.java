package com.techelevator.controller;

import com.techelevator.dao.CatCardDao;
import com.techelevator.model.CatCard;
import com.techelevator.model.CatFact;
import com.techelevator.model.CatPic;
import com.techelevator.services.CatFactService;
import com.techelevator.services.CatPicService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/api/cards")
public class CatController {

    private CatCardDao catCardDao;
    private CatFactService catFactService;
    private CatPicService catPicService;

    public CatController(CatCardDao catCardDao, CatFactService catFactService, CatPicService catPicService) {
        this.catCardDao = catCardDao;
        this.catFactService = catFactService;
        this.catPicService = catPicService;
    }

    @GetMapping
    public List<CatCard> getCatCards() {
        return catCardDao.getCatCards();
    }

    @GetMapping ("/{id}")
    public CatCard getCatCardById (@PathVariable ("id") int id) {
        return catCardDao.getCatCardById(id);
    }

    @GetMapping ("/random")
    public CatCard getCatCardRandomly () {
        CatFact catFact = catFactService.getFact();
        CatPic catPic = catPicService.getPic();
        CatCard catCard = new CatCard();
        catCard.setCatFact(catFact.getText());
        catCard.setImgUrl(catPic.getFile());
        return catCard;
    }

    @PostMapping
    public CatCard saveCatCard(@RequestBody CatCard catCard) {
        return catCardDao.createCatCard(catCard);
    }

    @PutMapping("/{id}")
    public CatCard updateCatCard(@PathVariable int id, @RequestBody CatCard catCard) {
        catCard.setCatCardId(id);
        CatCard updatedCatCard = catCardDao.updateCatCard(catCard);
        return updatedCatCard;
    }

    @DeleteMapping("/{id}")
    public String deleteCatCard(@PathVariable int id) {
        catCardDao.deleteCatCardById(id);
        return "A " + id + " Cat Card was successfully deleted.";
    }


}
