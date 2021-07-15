package com.example.demo.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.example.demo.lib.CsvDatabase;
import com.example.demo.model.Battery;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BatteryController {
  final CsvDatabase<Battery> batteryDb = new CsvDatabase<>("battery.csv", Battery.class);

  @GetMapping(value = "/api/battery/{id}", produces = "application/json")
  public Optional<Battery> oneBattery(@PathVariable String id) throws IOException {
    return batteryDb.findFirst(UUID.fromString(id));
  }

  @GetMapping(value = "/api/battery", produces = "application/json")
  public List<Battery> allBatteries() throws IOException {
    return batteryDb.findAll();
  }

  @PostMapping(value = "/api/battery", produces = "application/json")
  public Battery addBattery(@RequestBody Battery newBattery, BindingResult bindingResult) throws IOException {
    Battery result = null;

    if (!bindingResult.hasErrors()) {
      result = batteryDb.save(newBattery);
    }

    return result;
  }

  @DeleteMapping(value = "/api/battery/{id}", produces = "application/json")
  public void deleteBattery(@PathVariable String id) throws IOException {
    batteryDb.delete(UUID.fromString(id));
  }
}
