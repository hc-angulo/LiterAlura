package com.aluracursos.literalura.service;

public interface IDataConvertion {
    <T> T convertData(String json, Class<T> clase);
}
