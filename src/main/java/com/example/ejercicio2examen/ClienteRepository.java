package com.example.ejercicio2examen;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    List<Cliente> findByEstadoAndTotalGreaterThan(String estado, double total);

    @Query("SELECT sum(c.total) FROM Cliente c")
    double calcularTotalVentas();

    @Query("SELECT avg(c.total) FROM Cliente c WHERE c.estado = 'Activo'")
    double calcularPromedioVentas();

    @Query("SELECT count(c) FROM Cliente c WHERE c.estado = 'Inactivo' AND c.total > 0")
    long contarInactivosConVentas();
}
