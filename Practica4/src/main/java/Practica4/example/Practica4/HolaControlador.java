package Practica4.example.Practica4;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HolaControlador {

    @GetMapping("/holaControlador")
    public String saludo() {
        return "Bienvenido al sistema Protocare - Práctica 5";
    }
}