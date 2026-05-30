package ec.edu.espe.inventario_hardware.config;

import ec.edu.espe.inventario_hardware.entity.*;
import ec.edu.espe.inventario_hardware.repository.HardwareRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Random;

@Component
public class DataLoader implements CommandLineRunner {

    private final HardwareRepository repository;

    public DataLoader(HardwareRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... args) {

        if(repository.count() > 0){
            return;
        }

        Random random = new Random();

        Categoria[] categorias = Categoria.values();

        EstadoHardware[] estados = EstadoHardware.values();

        for(int i = 1; i <= 10000; i++){

            HardwareEntity hardware = HardwareEntity.builder()

                    .modelo("Equipo-" + i)

                    .categoria(
                            categorias[
                                    random.nextInt(
                                            categorias.length
                                    )
                                    ]
                    )

                    .precio(
                            BigDecimal.valueOf(
                                    1000 + random.nextInt(9000)
                            )
                    )

                    .fechaCompra(
                            LocalDate.now()
                                    .minusYears(
                                            random.nextInt(10)
                                    )
                    )

                    .estado(
                            estados[
                                    random.nextInt(
                                            estados.length
                                    )
                                    ]
                    )

                    .build();

            repository.save(hardware);
        }

        System.out.println(
                "10.000 registros cargados correctamente"
        );
    }
}