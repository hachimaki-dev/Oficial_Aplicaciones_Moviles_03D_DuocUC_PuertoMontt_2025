package com.otakuwear;
import org.example.com.otakuwear.Model.Viaje;
import org.example.com.otakuwear.Service.GestorViajes;

fun main() {
    val gestor = GestorViajes()
    gestor.addViaje(nuevoviaje = Viaje(
    ));
    gestor.mostrarViajes();
}
