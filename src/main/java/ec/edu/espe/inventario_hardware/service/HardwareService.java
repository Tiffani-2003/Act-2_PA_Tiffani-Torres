package ec.edu.espe.inventario_hardware.service;

import ec.edu.espe.inventario_hardware.dto.ReporteCategoriaDTO;
import ec.edu.espe.inventario_hardware.entity.Categoria;
import ec.edu.espe.inventario_hardware.entity.HardwareEntity;
import ec.edu.espe.inventario_hardware.repository.HardwareRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HardwareService {

    private final HardwareRepository repository;

    // --- ENFOQUE IMPERATIVO ---
    public List<ReporteCategoriaDTO> reporteImperativo() {
        List<HardwareEntity> items = repository.findAll();
        Map<Categoria, List<HardwareEntity>> mapa = new HashMap<>();

        // Agrupación manual
        for (HardwareEntity h : items) {
            mapa.computeIfAbsent(h.getCategoria(), k -> new ArrayList<>()).add(h);
        }

        List<ReporteCategoriaDTO> reportes = new ArrayList<>();
        for (Map.Entry<Categoria, List<HardwareEntity>> entry : mapa.entrySet()) {
            double suma = 0;
            BigDecimal max = BigDecimal.ZERO;
            for (HardwareEntity h : entry.getValue()) {
                suma += h.getPrecio().doubleValue();
                if (h.getPrecio().compareTo(max) > 0) max = h.getPrecio();
            }
            reportes.add(new ReporteCategoriaDTO(entry.getKey().toString(),
                    BigDecimal.valueOf(suma), suma / entry.getValue().size(),
                    entry.getValue().stream().max(Comparator.comparing(HardwareEntity::getPrecio)).map(HardwareEntity::getModelo).orElse("N/A"),
                    max));
        }
        return reportes;
    }

    // --- ENFOQUE FUNCIONAL ---
    public List<ReporteCategoriaDTO> reporteFuncional() {
        return repository.findAll().stream()
                .collect(Collectors.groupingBy(HardwareEntity::getCategoria))
                .entrySet().stream()
                .map(entry -> {
                    List<HardwareEntity> items = entry.getValue();

                    double avg = items.stream().mapToDouble(h -> h.getPrecio().doubleValue()).average().orElse(0.0);

                    // Optional para el equipo más caro
                    Optional<HardwareEntity> masCaro = items.stream().max(Comparator.comparing(HardwareEntity::getPrecio));

                    return new ReporteCategoriaDTO(
                            entry.getKey().toString(),
                            items.stream().map(HardwareEntity::getPrecio).reduce(BigDecimal.ZERO, BigDecimal::add),
                            avg,
                            masCaro.map(HardwareEntity::getModelo).orElse("N/A"),
                            masCaro.map(HardwareEntity::getPrecio).orElse(BigDecimal.ZERO)
                    );
                })
                .collect(Collectors.toList());
    }
}