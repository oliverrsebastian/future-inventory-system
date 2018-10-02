package com.blibli.inventory.controllers;

import com.blibli.inventory.models.Item;
import com.blibli.inventory.services.ItemService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import java.awt.print.Pageable;
import java.io.IOException;
import java.util.List;

@Controller
public class ItemController {

    ItemService itemService = new ItemService();
    Pageable pageable;

    @RequestMapping(value = "/api/items", consumes = "application/json", method = RequestMethod.GET)
    public ModelAndView listOfItem(ModelAndView model) throws IOException{
        List<Item> listOfItem = itemService.getItemList(pageable);
        model.addObject("listOfItem", listOfItem);
        model.setViewName("ItemList");

        return model;
    }

    @RequestMapping(value = "/api/items/save", consumes = "application/json", method = RequestMethod.POST)
    public ModelAndView saveItem(DefaultMultipartHttpServletRequest request){
        Item item = new Item();
        item.setId(request.getParameter("id"));
        item.setName(request.getParameter("name"));
        item.setSku(request.getParameter("sku"));
        item.setPrice(Integer.parseInt(request.getParameter("price"))); //ini gimana buat integer
        item.setLocation(request.getParameter("location"));
        itemService.saveItem(item);
        return new ModelAndView("redirect:/api/items");
    }

    @RequestMapping(value = "/api/items/delete", consumes = "application/json", method = RequestMethod.DELETE)
    public ModelAndView deleteItem(@RequestParam String id){
        itemService.deleteItemById(id);
        return new ModelAndView("redirect:/api/items");
    }
}
