package com.example.demo.sanpham.chip.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.security.SecureRandom;
import java.util.Random;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "chip")
public class Chip {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "ma_chip", unique = true, nullable = false)
    private String maChip;

    @Column(name = "ten_chip")
    private String tenChip;

    @Column(name = "mo_ta")
    private String moTa;

    @Column(name = "trang_thai")
    private int trangThai;

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int CODE_LENGTH = 8;

    public static String generateCode() {
        Random random = new SecureRandom();
        StringBuilder code = new StringBuilder(CODE_LENGTH);

        for (int i = 0; i < CODE_LENGTH; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            code.append(CHARACTERS.charAt(randomIndex));
        }
        return code.toString();
    }

    @PrePersist
    private void prePersist() {
        if (maChip == null || maChip.isEmpty()) {
            maChip = generateCode();
        }
    }
}
