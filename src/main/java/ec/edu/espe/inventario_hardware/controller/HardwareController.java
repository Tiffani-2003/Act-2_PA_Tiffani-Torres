package ec.edu.espe.inventario_hardware.controller;

import ec.edu.espe.inventario_hardware.ai.AiService;
import ec.edu.espe.inventario_hardware.dto.ReporteCategoriaDTO;
import ec.edu.espe.inventario_hardware.service.HardwareService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hardware")
@RequiredArgsConstructor
public class HardwareController {

    private final HardwareService hardwareService;

    private final AiService aiService;

    @GetMapping("/imperativo")
    public List<ReporteCategoriaDTO> reporteImperativo(){

        return hardwareService.reporteImperativo();
    }

    @GetMapping("/funcional")
    public List<ReporteCategoriaDTO> reporteFuncional(){

        return hardwareService.reporteFuncional();
    }

    @GetMapping("/resumen")
    public String resumen(){

        int categorias =
                hardwareService
                        .reporteFuncional()
                        .size();

        return aiService.generarResumen(
                categorias
        );
    }
}
