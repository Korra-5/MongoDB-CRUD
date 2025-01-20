package org.example

import org.example.Model.Juegos
import org.example.Repository.FuncCRUD

fun main() {
val funcCRUD: FuncCRUD = FuncCRUD()

    while (true){
        println("¿Que desea hacer?")
        println("1- Insertar juego")
        println("2- Mostrar juego")
        println("3- Filtrar juegos por genero")
        println("4- Eliminar juegos por genero")
        println("5- Modificar juego")
        println("6- Salir")

        val opcion= readlnOrNull()


     if (opcion=="1"){
         var titulo:String

         //Bucle que junto con la funcion checkTitulo verifican el titulo
         do {
             println("Inserta titulo")
             titulo = readlnOrNull().toString().trim()
         }while (!funcCRUD.checkTitulo(titulo))


         println("Inserta genero")
         val genero= readlnOrNull().toString().trim()

         println("Inserta precio")
         val precio= readlnOrNull()?.toDoubleOrNull()

         println("Inserta fecha")
         val fecha= readlnOrNull().toString().trim()

         funcCRUD.createJuego(Juegos(titulo, genero, precio, fecha))

     } else if (opcion=="2"){
         funcCRUD.mostrarJuegos()

     } else if (opcion=="3"){
         println("Indica genero a buscar:")
         val genero= readlnOrNull().toString().trim()
         funcCRUD.buscarGenero(genero)

     }else if (opcion=="4"){
         println("Indica genero a eliminar:")
         val genero= readlnOrNull().toString().trim()
         funcCRUD.eliminarGenero(genero)

     }else if (opcion=="5"){
         println("Indica titulo del juego que quieres modificar:")
            var tituloFilt:String

            //Bucle que verifica que este juego realmente existe
         do {
             tituloFilt= readlnOrNull().toString().trim()
         }while (!funcCRUD.checkTituloExist(tituloFilt))

         var titulo:String

         //Verificando nuevamente que el juego no existe
         do {
             println("Inserta nuevo titulo")
             titulo = readlnOrNull().toString().trim()

             //En caso de que el titulo de busqueda introducido y el nuevo titulo coincidan se rompe el bucle, con el fin de permitir mantener el mismo titulo
             if (tituloFilt==titulo) break
         }while (!funcCRUD.checkTitulo(titulo))


         println("Inserta nuevo genero")
         val genero= readlnOrNull().toString().trim()

         println("Inserta nuevo precio")
         val precio= readlnOrNull()?.toDoubleOrNull()

         println("Inserta nueva fecha")
         val fecha= readlnOrNull().toString().trim()

         funcCRUD.modificarJuego(tituloFilt, Juegos(titulo, genero, precio, fecha))

     }else if (opcion=="6"){
         //Cierra la conexion con la BBDD y finaliza el programa
         funcCRUD.cerrarConexion()
         break
     }else{
         println("Opcion no valida, intentelo de nuevo")
     }

    }


}