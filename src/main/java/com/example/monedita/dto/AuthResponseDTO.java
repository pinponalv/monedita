package com.example.monedita.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"username", "message", "jwt", "status"})
public record AuthResponseDTO(String username, String message, String jwt, boolean status) {

    //estatus opcional para cuando tenga front y saber cual fue el resultado y validar
}
