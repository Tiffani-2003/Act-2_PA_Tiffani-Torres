package ec.edu.espe.inventario_hardware.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReporteCategoriaDTO {

    private String categoria;

    private BigDecimal valorTotal;

    private Double promedioPrecio;

    private String equipoMasCaro;

    private BigDecimal precioMasAlto;
}