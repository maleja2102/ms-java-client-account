package com.devsu.ms_java_client.infrastructure.persistence.jpa;

import com.devsu.ms_java_client.infrastructure.persistence.entity.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientJpaRepository extends JpaRepository<ClientEntity, Long> {
}
