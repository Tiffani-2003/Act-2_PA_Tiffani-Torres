package ec.edu.espe.inventario_hardware.repository;

import ec.edu.espe.inventario_hardware.entity.HardwareEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HardwareRepository
        extends JpaRepository<HardwareEntity, Long> {
}