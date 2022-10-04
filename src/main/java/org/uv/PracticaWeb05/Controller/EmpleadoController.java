/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.uv.PracticaWeb05.Controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

        Empleado empleado = null;
        Optional<Empleado> optional = empleadoRepository.findById(id);
        if (optional.isPresent()) {
            empleado = optional.get();
            return new ResponseEntity<>(empleado, HttpStatus.FOUND);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        
    }

    @PostMapping("/empleado")
    public ResponseEntity<Empleado> createEmpleado(@RequestBody Empleado empleado) {
        Empleado emp = empleadoRepository.save(empleado);
        return new ResponseEntity<>(emp, HttpStatus.CREATED);
    }

    @GetMapping("/empleado")
    public List<Empleado> getAllEmpleados() {
        return empleadoRepository.findAll();
    }

    @PutMapping("/empleado/{id}")
    public ResponseEntity<Empleado> updateEmpleado(@PathVariable(value = "id") Long empleadoId,
            @RequestBody Empleado empleado) {
        Optional<Empleado> optional = empleadoRepository.findById(empleadoId);
        if (optional.isPresent()) {
            Empleado emp = optional.get();
            emp.setNombre(empleado.getNombre());
            emp.setDireccion(empleado.getDireccion());
            emp.setTelefono(empleado.getTelefono());
            Empleado updatedEmpleado = empleadoRepository.save(empleado);
            return new ResponseEntity<>(updatedEmpleado, HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @DeleteMapping("/empleado/{id}")
    public ResponseEntity<Boolean> deleteEmpleado(@PathVariable(value = "id") Long empleadoId) {
        Optional<Empleado> optional = empleadoRepository.findById(empleadoId);
        if(optional.isPresent()){
            empleadoRepository.delete(optional.get());
            return new ResponseEntity<>(Boolean.TRUE, HttpStatus.FOUND);
        }else{
            return new ResponseEntity<>(Boolean.FALSE, HttpStatus.NOT_FOUND);
        }
        
    }

}
