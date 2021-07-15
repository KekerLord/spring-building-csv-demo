package com.example.demo.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.example.demo.lib.CsvDatabase;
import com.example.demo.model.Battery;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BatteryQueryController {
  final CsvDatabase<Battery> batteryDb = new CsvDatabase<>("battery.csv", Battery.class);

  @GetMapping(value = "/q/battery/read/{id}", produces = "application/json")
  public Optional<Battery> oneBattery(@PathVariable String id) throws IOException {
    return batteryDb.findFirst(UUID.fromString(id));
  }

  @GetMapping(value = "/q/battery/read", produces = "application/json")
  public List<Battery> allBatteries() throws IOException {
    return batteryDb.findAll();
  }

  /*
   * e.g. http://localhost:8080/q/battery/create?brand=private&anode=lol&
   * cathode=11&electrolyte=today&storeyscathode=5&voltage=10
   */
  @GetMapping(value = "/q/battery/create", produces = "application/json")
  public void addBattery(@Valid Battery newBattery, BindingResult bindingResult, HttpServletResponse response)
      throws IOException {
    if (!bindingResult.hasErrors()) {
      batteryDb.save(newBattery);
    }

    response.sendRedirect("/");
  }

  @GetMapping(value = "/q/battery/delete/{id}", produces = "application/json")
  public void deleteBattery(@PathVariable String id, HttpServletResponse response) throws IOException {
    batteryDb.delete(UUID.fromString(id));

    response.sendRedirect("/");
  }
}
