package com.blibli.inventory.repositories;

import com.blibli.inventory.models.Item;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.awt.print.Pageable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


public class ItemRepository {
    private JdbcTemplate jdbc;

    public ItemRepository(DataSource dataSource){jdbc = new JdbcTemplate();}

    public List<Item> getItemList(final Pageable pageable){
        String sql = "SELECT from Item";
        List<Item> listOfItem = jdbc.query(sql, new RowMapper<Item>() {
            @Override
            public Item mapRow(ResultSet resultSet, int rowNum) throws SQLException {
                if(rowNum > pageable.getNumberOfPages()){
                    return null;
                }
                Item item = new Item();

                item.setId(resultSet.getString("id"));
                item.setSku(resultSet.getString("sku"));
                item.setName(resultSet.getString("name"));
                item.setPrice(resultSet.getInt("price"));
                item.setLocation(resultSet.getString("location"));

                return item;
            }
        });
        return listOfItem;
    }

    public String saveItem(Item item){
        String sql = "";

        if(item.getId() == "not found"){
            sql = "INSERT INTO item (id, sku, name, price, location) VALUES (?, ?, ?, ?, ?)";
            jdbc.update(sql, item.getId(), item.getSku(), item.getName(), item.getPrice(), item.getLocation());
        }
        else{
            sql = "UPDATE item set id = {0}, sku = {1}, name = {2}, price = {3}, location = {4})" + "WHERE id = {5}";
            jdbc.update(sql, item.getId(), item.getSku(), item.getName(), item.getPrice(), item.getLocation(), item.getId());
        }
        return "success";
    }

    public String deleteItemById(String id){
        String sql = "DELETE from item WHERE id = " + id;

        if(jdbc.update(sql) == 1) {
            return "success";
        }else{
            return "failed";
        }

    }

}
