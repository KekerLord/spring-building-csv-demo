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
      int capacity, @NotBlank String voltage) {
    this.brand = brand;
    this.anode = anode;
    this.cathode = cathode;
    this.electrolyte = electrolyte;
    this.capacity = capacity;
    this.voltage = voltage;
  }

  // Тип собственности
  @NotBlank
  String brand;

  // Улица
  @NotBlank
  String anode;

  // Номер дома
  @NotBlank
  String cathode;

  // Дата сдачи в эксплуатацию
  @NotBlank
  String electrolyte;

  // Этажность
  int capacity;

  // Собственник
  @NotBlank
  String voltage;
}
