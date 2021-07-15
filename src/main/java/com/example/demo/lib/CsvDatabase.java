package com.example.demo.lib;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.example.demo.interfaces.Entity;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

public class CsvDatabase<T extends Entity> {
  private final CsvMapper csvMapper;
  private final File dbFile;

  private Class<T> model;
  CsvSchema schema;

  public CsvDatabase(String filePath, Class<T> model) {
    csvMapper = new CsvMapper();
    this.dbFile = new File(filePath);
    this.model = model;
    schema = csvMapper.schemaFor(model);
  }

  private void createFile() throws IOException {
    List<T> empty = new ArrayList<>();

    csvMapper.writeValue(dbFile, empty);
  }

  public Optional<T> findFirst(UUID id) throws IOException {
    Optional<T> result = Optional.empty();
    List<T> rows;

    try {
      MappingIterator<T> it = csvMapper.readerFor(model).with(schema).readValues(dbFile);
      rows = it.readAll();
      result = rows.stream().filter(b -> b.getId().equals(id)).findFirst();
    } catch (IOException e) {
      createFile();
      e.printStackTrace();
    }

    return result;
  }

  public List<T> findAll() throws IOException {
    List<T> result = new ArrayList<>();

    try {
      MappingIterator<T> it = csvMapper.readerFor(model).with(schema).readValues(dbFile);
      List<T> rows = it.readAll();
      result = rows;
    } catch (IOException e) {
      createFile();
      e.printStackTrace();
    }

    return result;
  }

  public T save(T entity) throws IOException {
    try {
      MappingIterator<T> it = csvMapper.readerFor(model).with(schema).readValues(dbFile);
      List<T> rows = it.readAll();

      rows.add(entity);

      csvMapper.writer(schema).writeValue(dbFile, rows);
    } catch (IOException e) {
      createFile();
      e.printStackTrace();
    }

    return entity;
  }

  public void delete(UUID id) throws IOException {
    try {
      MappingIterator<T> it = csvMapper.readerFor(model).with(schema).readValues(dbFile);
      List<T> rows = it.readAll();

      rows.removeIf(b -> b.getId().equals(id));

      csvMapper.writer(schema).writeValue(dbFile, rows);
    } catch (IOException e) {
      createFile();
      e.printStackTrace();
    }
  }
}
