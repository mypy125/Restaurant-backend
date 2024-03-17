package com.mygitgor.tacocloud.web.controller;

import com.mygitgor.tacocloud.domain.Ingredient;
import com.mygitgor.tacocloud.domain.Taco;
import com.mygitgor.tacocloud.domain.TacoOrder;
import com.mygitgor.tacocloud.domain.Type;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.mygitgor.tacocloud.domain.Type.*;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("tacoOrder")
public class DesignTacoController {

    @ModelAttribute
    public void AddIngredientsToModel(Model model){
        List<Ingredient> ingredients = Arrays.asList(
                new Ingredient("FLTO","Flour Tortilla", INGREDIENT_TYPE_WRAP),
                new Ingredient("COTO","Corn Tortilla",INGREDIENT_TYPE_WRAP),
                new Ingredient("GRBF", "Ground Beef", INGREDIENT_TYPE_PROTEIN),
                new Ingredient("CARN", "Carnitas", INGREDIENT_TYPE_PROTEIN),
                new Ingredient("TMTO","Dicet Tomatoes", INGREDIENT_TYPE_VEGGIES),
                new Ingredient("LETC", "Lettuce", INGREDIENT_TYPE_VEGGIES),
                new Ingredient("CHED", "Cheddar", INGREDIENT_TYPE_CHEESE),
                new Ingredient("JACK", "Monterrey Jack", INGREDIENT_TYPE_CHEESE),
                new Ingredient("SLSA", "Salsa", INGREDIENT_TYPE_SAUCE),
                new Ingredient("CRCR", "Sour Cream", INGREDIENT_TYPE_SAUCE)
        );

        Type[] types = Type.values();
        for(Type t : types){
            model.addAttribute(t.toString().toLowerCase(),
            filterByType(ingredients,t));
        }

    }
    @ModelAttribute(name = "tacoOrder")
    public TacoOrder order(){
        return new TacoOrder();
    }

    @ModelAttribute(name = "taco")
    public Taco taco(){
        return new Taco();
    }

    @GetMapping
    public String showDesignForm(){
        return "design";
    }

    private Iterable<Ingredient> filterByType(
            List<Ingredient> ingredients, Type type) {
        return ingredients.stream().filter(t -> t.getType().equals(type))
                .collect(Collectors.toList());
    }


}
