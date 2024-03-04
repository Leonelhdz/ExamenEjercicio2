package com.example.ejercicio2examen;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @PostMapping("/nuevo")
    public Cliente crearCliente(@RequestBody Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @GetMapping("/{id}")
    public Cliente obtenerCliente(@PathVariable Long id) {
        return clienteRepository.findById(id).orElse(null);
    }

    @GetMapping("/activos/{totalMinimo}")
    public List<Cliente> obtenerClientesActivosConVentasMayores(@PathVariable double totalMinimo) {
        return clienteRepository.findByEstadoAndTotalGreaterThan("Activo", totalMinimo);
    }

    @GetMapping("/resumen")
    public String obtenerResumenEstadistico() {
        double totalVentas = clienteRepository.calcularTotalVentas();
        double promedioVentas = clienteRepository.calcularPromedioVentas();
        long inactivos = clienteRepository.contarInactivosConVentas();

        return "Total de ventas entre todos los clientes: " + totalVentas + "\n" +
                "Promedio de ventas de los clientes activos: " + promedioVentas + "\n" +
                "Cantidad de clientes inactivos que tienen total de ventas mayor a 0: " + inactivos;
    }
}
