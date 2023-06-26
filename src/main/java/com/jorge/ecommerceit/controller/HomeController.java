package com.jorge.ecommerceit.controller;
import com.jorge.ecommerceit.model.Orden;
import com.jorge.ecommerceit.model.Producto;
import com.jorge.ecommerceit.model.Usuario;
import com.jorge.ecommerceit.service.ProductoService;
import com.jorge.ecommerceit.model.DetalleOrden;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/")
public class HomeController {
    @Autowired
    ProductoService productoService;

    @GetMapping("")
    public String home(Model model) {
        List<Producto> productos = productoService.findAll();
        model.addAttribute("productos", productos);
        for (Producto producto : productos) {
            String imagenPath = "/img/" + producto.getImagen();
            System.out.println("Ruta de imagen: " + imagenPath);
        }
        String userDir = System.getProperty("user.dir");
        System.out.println(userDir);
        return "home.html";
    }

    @GetMapping("/producto/{id}")
    public String productoHome(@PathVariable Integer id, Model model){
        Producto producto;
        Optional<Producto> optionalProducto = productoService.getProducto(id);
        producto = optionalProducto.get();
        model.addAttribute("producto", producto);
        return "pages/itemdetail";
    }

    @GetMapping("/producto")
    public String itemdetail(){return "pages/itemdetail";}

    @GetMapping("/carrito")
    public String getCart(){return "pages/cart";}

    @GetMapping("/orden")
    public String orderdetail(){return "pages/orderdetail";}

    @GetMapping("/guardarorden")
    public String saveOrder(){return "redirect:/";}
}
