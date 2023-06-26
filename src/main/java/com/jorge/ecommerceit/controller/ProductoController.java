package com.jorge.ecommerceit.controller;

import com.jorge.ecommerceit.model.Producto;
import com.jorge.ecommerceit.model.Usuario;
import com.jorge.ecommerceit.service.UploadFileService;
import com.jorge.ecommerceit.service.ProductoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Controller
@RequestMapping("/productos")
public class ProductoController {
    private final Logger LOGGER = LoggerFactory.getLogger(ProductoController.class);
    @Autowired
    private ProductoService productoService;
    @Autowired
    private UploadFileService uploadFileService;

    @GetMapping("")
    public String read(Model model) {
        model.addAttribute("productos", productoService.findAll());
        return "pages/itemread";
    }

    @GetMapping("/create")
    public String create() {
        return "pages/itemcreate";
    }

    @PostMapping("/update")
    public String update(Producto producto, @RequestParam("img") MultipartFile file) {
        Producto productoConImage;
        productoConImage = productoService.getProducto(producto.getId()).get();
        if(file.isEmpty()){
            producto.setImagen(productoConImage.getImagen());
        }else{
            if(!productoConImage.getImagen().equals("default.png")){
                uploadFileService.deleteImage(productoConImage.getImagen());
            }
            String imageName = uploadFileService.saveImage(file);
            producto.setImagen(imageName);
        }
        LOGGER.info("Producto: {}", producto);
        productoService.update(producto);
        return "redirect:/productos";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable Integer id, Model model) {
        Producto producto;
        Optional<Producto> optionalProducto = productoService.getProducto(id);
        producto = optionalProducto.get();
        LOGGER.info("Producto: {}", producto);
        model.addAttribute("producto", producto);
        return "pages/itemupdate";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        Producto producto;
        producto = productoService.getProducto(id).get();
        if(!producto.getImagen().equals("default.png")){
            uploadFileService.deleteImage(producto.getImagen());
        }
        productoService.delete(id);
        return "redirect:/productos";
    }

    @PostMapping("/save")
    public String save(Producto producto, @RequestParam("img") MultipartFile file) {
        LOGGER.info("producto: {}" + producto);
        Usuario usuario = new Usuario(1001,"Jorge","Jorge1989","jorge@gmail.com","Av siempre viva","321654987","654987");
        producto.setUsuario(usuario);
        if(producto.getId()==null){
            String imageName = uploadFileService.saveImage(file);
            producto.setImagen(imageName);
        }
        productoService.create(producto);
        return "redirect:/productos";
    }
}

