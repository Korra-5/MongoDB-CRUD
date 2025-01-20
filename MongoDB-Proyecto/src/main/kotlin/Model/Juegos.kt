package org.example.Model

data class Juegos(val titulo:String, val genero:String, val precio: Double?, val fecha:String){
}
/*
El motivo de la variable fecha como String es debido a la alta complejidad del objeto Date como para escribirlo en consola
ya debido a que este solicita instantes de tiempo muy exactos lo cual puede dificultar la funcion de modificarJuego
 */