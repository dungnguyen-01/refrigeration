package com.example.demo.model;

import com.example.demo.controller.body.ProductBody;
import com.example.demo.model.entities.Product;
import com.example.demo.repository.CategoryRepsitory;
import com.example.demo.repository.ProductRepsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;

@Service
public class ProductModel {
    @Autowired
    private ProductRepsitory productRepsitory;

    public void addP(MultipartFile imageF,double price, String name,int cateId,int qty,String description){
        Product p = new Product();
        p.setName(name);
        p.setCateId(cateId);
        p.setQty(qty);
        p.setPrice(price);
        p.setDescription(description);
        String fileName= StringUtils.cleanPath(imageF.getOriginalFilename());

        try {
            p.setImageF(Base64.getEncoder().encodeToString(imageF.getBytes()));

        } catch (IOException e) {
            e.printStackTrace();
        }
        productRepsitory.save(p);
    }

    public String addProduct(ProductBody body){
        try {
            Product product = new Product();
            product.setName(body.getName());
            product.setCateId(body.getCategory());
            product.setDescription(body.getDescription());
            product.setPrice(body.getPrice());
            product.setImageF(body.getImage());

            product.setQty(body.getQty());

            productRepsitory.save(product);
            return "Thêm sản phẩm thành công";
        } catch (Exception e){
            return "Có lỗi xảy ra !";
        }
    }
}
