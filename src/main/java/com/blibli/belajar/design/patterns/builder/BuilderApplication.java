package com.blibli.belajar.design.patterns.builder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class BuilderApplication {

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Mahasiswa {
        private String nama;
        private String nim;
        private String alamat;
        private Date tanggaLahir;
        private List<String> hobi;
    }

    public static void main(String[] args){
        Mahasiswa mahasiswa = Mahasiswa.builder()
                .nama("Eko")
                .nim("123")
                .alamat("Indonesia")
                .tanggaLahir(new Date())
                .hobi(Arrays.asList("Game", "Coding"))
                .build();
    }
}
