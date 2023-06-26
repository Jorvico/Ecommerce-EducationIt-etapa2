package com.jorge.ecommerceit.service;
import com.jorge.ecommerceit.model.Producto;
import com.jorge.ecommerceit.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProductoImpl implements ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public Producto create(Producto producto) {
        return productoRepository.save(producto);
    }

    @Override
    public Optional<Producto> getProducto(Integer id) { return productoRepository.findById(id); }

    @Override
    public void update(Producto producto) { productoRepository.save(producto); }

    @Override
    public void delete(Integer id) { productoRepository.deleteById(id); }

    @Override
    public List<Producto> findAll() {
        return productoRepository.findAll();
    }
}
