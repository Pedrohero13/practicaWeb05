/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.uv.PracticaWeb05.Controller;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.uv.PracticaWeb05.modelo.Empleado;
import org.uv.PracticaWeb05.modelo.EmpleadoRepository;

/**
 *
 * @author Pedro
 */
@RestController

@RequestMapping("/api/")

public class EmpleadoController {

    @Autowired
    EmpleadoRepository empleadoRepository;

    @GetMapping("/empleado/{id}")
    public ResponseEntity<Empleado> getEmpleado(@PathVariable("id") Long id) {
        Optional<Empleado> emp = empleadoRepository.findById(id);
        Empleado response = emp.get();
        return new ResponseEntity<>(response, HttpStatus.FOUND);
    }

    @PostMapping("/empleado")
    public ResponseEntity<Empleado> createEmpleado(@RequestBody Empleado empleado) {
        Empleado emp = empleadoRepository.save(empleado);
        return new ResponseEntity<>(emp, HttpStatus.CREATED);
    }
}
