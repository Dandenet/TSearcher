package com.example.tsearcher.repository;

import com.example.tsearcher.models.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
