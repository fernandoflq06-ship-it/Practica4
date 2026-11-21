package Practica4.example.Practica4.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Practica4.example.Practica4.model.Prueba;

@RestController
public class PruebaController {

    private List<Prueba> listPruebas = new ArrayList<>();

    // Constructor con un par de pruebas precargadas para probar directo en el navegador
    public PruebaController() {
        listPruebas.add(new Prueba("AN-01", "Hemograma completo", "Detección de anemias", false));
        listPruebas.add(new Prueba("AN-02", "Serología Hepatitis B", "Cribado infeccioso prioritario", true));
    }

    @GetMapping("/protocare")
    public String index() {
        return "Sistema Protocare - Protocolo Epidemiológico v1.0 (OMS) - Ingeniería del Software";
    }

    @GetMapping("/get_prueba/{codigo}")
    public Prueba getPrueba(@PathVariable("codigo") String codigo) {
        return listPruebas.stream()
                .filter(prueba -> prueba.getCodigo().equalsIgnoreCase(codigo))
                .findFirst()
                .orElse(null);
    }

    @GetMapping("/cribado")
    public String sugerirCribado(
            @RequestParam(value = "pais", defaultValue = "Marruecos") String pais,
            @RequestParam(value = "edad", defaultValue = "30") int edad) {
        return String.format("Calculando protocolo de cribado para paciente de %s (%d años)...", pais, edad);
    }

    @GetMapping("/riesgo/{codigo}")
    public String comprobarRiesgo(@PathVariable("codigo") String codigo) {
        Prueba p = getPrueba(codigo);
        if (p == null) {
            return "Prueba no encontrada en el cribado actual";
        }
        return p.isEsRiesgoAgudo()
                ? "ALERTA: La prueba " + p.getNombre() + " está marcada como Riesgo Agudo"
                : "La prueba " + p.getNombre() + " es de rutina (sin riesgo agudo)";
    }

    @PostMapping(value = "/newprueba", consumes = "application/json")
    public ResponseEntity<?> save(@RequestBody Prueba prueba) {
        if (listPruebas.contains(prueba)) {
            return ResponseEntity.internalServerError().body("La prueba ya existe en el cribado");
        } else {
            listPruebas.add(prueba);
            return ResponseEntity.ok().body("Nueva prueba añadida al protocolo de cribado");
        }
    }

    @DeleteMapping(value = "/deleteprueba/{codigo}")
    public ResponseEntity<?> delete(@PathVariable("codigo") String codigo) {
        Prueba prueba = listPruebas.stream()
                .filter(p -> p.getCodigo().equalsIgnoreCase(codigo))
                .findFirst()
                .orElse(null);
        if (prueba == null) {
            return ResponseEntity.internalServerError().body("La prueba no existe en el cribado");
        } else {
            listPruebas.remove(prueba);
            return ResponseEntity.ok().body("La prueba se ha descartado del cribado");
        }
    }
}
