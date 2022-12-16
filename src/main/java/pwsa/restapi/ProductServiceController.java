/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pwsa.restapi;

import java.util.HashMap;
import java.util.Map;
import model.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author hp
 */

@RestController//import RestController untuk membuat response berupa REST API
public class ProductServiceController {
//membuat function ProductServiceController    
    private static Map<String, Product> productRepo = new HashMap<>();//membuat HashMap untuk menyimpan nilai variabel
    
    static {
        Product madu = new Product();//membuat object baru untuk class
        madu.setId("1");//mengisi variabel object setId
        madu.setName("Madu");//mengisi variabel object setName
        madu.setPrice(5000);
        madu.setDisc(5);
        double total = madu.getPrice()-(madu.getPrice()*(madu.getDisc()/100));
        madu.setTotal(total);
        productRepo.put(madu.getId(), madu);//menyimpan/mengisi nilai ke hashmap 
        
        Product kacang = new Product();//membuat object baru untuk class
        kacang.setId("2");//mengisi variabel object setId
        kacang.setName("Kacang");//mengisi variabel object setName
        kacang.setPrice(5000);
        kacang.setDisc(5);
        total = kacang.getPrice()-(kacang.getPrice()*(kacang.getDisc()/100));
        kacang.setTotal(total);
        productRepo.put(kacang.getId(), kacang);//menyimpan/mengisi nilai ke hashmap 
        
    }
    
    @RequestMapping(value = "/product")//membuat requestmapping untuk end point hit ke postman
    public ResponseEntity<Object> getProduct(){//membuat function ResponseEntity<Object> getProduct
        
        
        return new ResponseEntity<>(productRepo.values(), HttpStatus.OK);//mengembalikan nilai pada productrepo
    }
    
    @RequestMapping(value = "/product", method = RequestMethod.POST)//membuat requestmapping untuk end point hit ke postman
    public ResponseEntity<Object> createProduct(@RequestBody Product product)//membuat function untuk create product
    {
        if(productRepo.containsKey(product.getId()))//membuat statment if else untuk mengecek Id apakah sudah pernah keisi sama atau belum
        {
            
            return new ResponseEntity<>("product id has been created", HttpStatus.OK);//mengembalikan nilai dan pesan yang akan disampaikan
                    
        }else{//membuat statment if else untuk mengecek Id apakah sudah pernah keisi sama atau belum 
            double total = product.getPrice()-(product.getPrice()*(product.getDisc()/100));
            
            product.setTotal(total);
            productRepo.put(product.getId(), product);//menyimpan nilai variabel pada productrepo
            return new ResponseEntity<>("Product is create successfully", HttpStatus.CREATED);//mengembalikan nilai dan pesan yang akan disampaikan
            
        }
                  
    }
    
    @RequestMapping(value = "/product/{id}", method = RequestMethod.PUT)//membuat requestmapping untuk end point hit ke postman
    public ResponseEntity<Object> updateProduct(@PathVariable("id")String id, @RequestBody Product product)
            //membuat function untuk edit data
    {
        if(!productRepo.containsKey(id)){       //membuat if else untuk mengecek apakah id sudah pernah kebuat atau belum
            return new ResponseEntity<>("Product id data not found", HttpStatus.OK); //mengembalikan nilai dan pesan yang akan disampaikan
            
        }else{          //jika id ada dan bisa di edit
            productRepo.remove(id);//menghapus isi id sesuai id
            product.setId(id);//melihat id yang akan di edit
            productRepo.put(id, product);//menyimpan kembali id dan nama product
            return new ResponseEntity<>("Product is update successfully", HttpStatus.OK);//mengembalikan nilai dan pesan yang akan disampaikan
            
        }
        
   
    }
    
    @RequestMapping(value = "/product/{id}", method = RequestMethod.DELETE)//membuat requestmapping untuk end point hit ke postman
    public ResponseEntity<Object> delete(@PathVariable("id") String id){
        //membuat function untuk delete
        productRepo.remove(id);//menghapus isi id sesuai id
        return new ResponseEntity<>("Product is deleted successfully", HttpStatus.OK);//mengembalikan nilai dan pesan yang akan disampaikan
    }
    
    
    
    
}
