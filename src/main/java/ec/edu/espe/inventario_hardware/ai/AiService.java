package ec.edu.espe.inventario_hardware.ai;

import org.springframework.stereotype.Service;

@Service
public class AiService {

    public String generarResumen(Integer categorias){

        return """
                Resumen Inteligente del Inventario
                
                Categorías analizadas: %d
                
                El sistema procesó correctamente
                los equipos activos adquiridos
                durante los últimos cinco años.
                """.formatted(categorias);
    }
}