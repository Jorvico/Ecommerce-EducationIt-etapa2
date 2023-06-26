package com.jorge.ecommerceit.service;

import com.jorge.ecommerceit.model.Producto;

import java.util.List;
import java.util.Optional;

public interface ProductoService {
    public Producto create(Producto producto);
    public Optional<Producto> getProducto(Integer id);
    public void update(Producto producto);
    public void delete(Integer id);
    public List<Producto> findAll();
}
