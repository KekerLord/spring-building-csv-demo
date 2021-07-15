package com.example.demo.model;

import javax.validation.constraints.NotBlank;

import com.example.demo.interfaces.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class Battery extends Entity {

  public Battery(@NotBlank String brand, @NotBlank String anode, @NotBlank String cathode, @NotBlank String electrolyte,
      int capacity, int voltage) {
    this.brand = brand;
    this.anode = anode;
    this.cathode = cathode;
    this.electrolyte = electrolyte;
    this.capacity = capacity;
    this.voltage = voltage;
  }

  // Марка
  @NotBlank
  String brand;

  // Вещество анода
  @NotBlank
  String anode;

  // Вещество катода
  @NotBlank
  String cathode;

  // Электролит
  @NotBlank
  String electrolyte;

  // Ёмкость
  int capacity;

  // Напряжение
  int voltage;
}
