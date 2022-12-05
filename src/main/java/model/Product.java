/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author hp
 */
public class Product {//membuat class product
    private String id;//deklarasi id dengan tipe data string
    private String name;//deklarasi name dengan tipe data string

    public String getId() {//membuat function untuk mengambil data
        return id;//mengembalikan id
    }

    public void setId(String id) {//membuat function input id
        this.id = id;//mengembalikan id
    }

    public String getName() {//membuat function untuk mengambil data
        return name;//mengembalikan name
    }   

    public void setName(String name) {//membuat function input name
        this.name = name;//mengembalikan name
    }

    
    
    
    
}
