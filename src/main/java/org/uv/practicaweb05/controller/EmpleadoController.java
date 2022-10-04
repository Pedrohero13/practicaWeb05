/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.uv.practicaweb05.controller;

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
import org.uv.practicaweb05.modelo.Empleado;
import org.uv.practicaweb05.modelo.EmpleadoDTO;
import org.uv.practicaweb05.modelo.EmpleadoRepository;

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

        
        Optional<Empleado> optional = empleadoRepository.findById(id);
        if (optional.isPresent()) {
            Empleado empleado = optional.get();
            return new ResponseEntity<>(empleado, HttpStatus.FOUND);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        
    }

    @PostMapping("/empleado")
    public ResponseEntity<Empleado> createEmpleado(@RequestBody EmpleadoDTO dto) {
        Empleado persisEmpleado = new Empleado();
        persisEmpleado.setId(dto.getDni());
        persisEmpleado.setNombre(dto.getName());
        persisEmpleado.setDireccion(dto.getAdress());
        persisEmpleado.setTelefono(dto.getPhoneNumber());
        
        empleadoRepository.save(persisEmpleado);
        return new ResponseEntity<>(persisEmpleado, HttpStatus.CREATED);
    }

    @GetMapping("/empleado")
    public List<Empleado> getAllEmpleados() {
        return empleadoRepository.findAll();
    }

    @PutMapping("/empleado/{id}")
    public ResponseEntity<Empleado> updateEmpleado(@PathVariable(value = "id") Long empleadoId,
            @RequestBody EmpleadoDTO dto) {
        
        Empleado persisEmpleado = new Empleado();
        persisEmpleado.setId(dto.getDni());
        persisEmpleado.setNombre(dto.getName());
        persisEmpleado.setDireccion(dto.getAdress());
        persisEmpleado.setTelefono(dto.getPhoneNumber());
        
        Optional<Empleado> optional = empleadoRepository.findById(empleadoId);
        if (optional.isPresent()) {
            Empleado updatedEmpleado = empleadoRepository.save(persisEmpleado);
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
