package org.example.com.otakuwear.models

import java.util.stream.IntStream

class Viaje (
    var destino: String,
    var precio: Int,
    var pasajesDisponibles: Int,
    var pasajesIniciales: Int
){
    init{
        require(destino.isNotBlank()){"El destino no puede estar vacio"}
    }

}
