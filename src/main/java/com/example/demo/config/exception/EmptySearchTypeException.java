package com.example.demo.config.exception;

public class EmptySearchTypeException extends RuntimeException {

  public EmptySearchTypeException() {
    super("search type enum is empty data");
  }
}
