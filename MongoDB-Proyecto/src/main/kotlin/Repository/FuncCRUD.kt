package org.example.Repository

import com.mongodb.client.MongoClients
import com.mongodb.client.MongoCollection
import com.mongodb.client.MongoDatabase
import com.mongodb.client.model.Filters
import com.mongodb.client.model.Sorts
import io.github.cdimascio.dotenv.Dotenv
import io.github.cdimascio.dotenv.dotenv
import org.bson.Document
import org.example.Model.Juegos

class FuncCRUD {
    val dotenv: Dotenv = dotenv()
    val urlMONGO: String = dotenv["URL_MONGODB"]
    var mongoClient = MongoClients.create(urlMONGO)

//Funcion para crear documentos de juegos
    fun createJuego(juegos: Juegos) {
        try {
            val bd: MongoDatabase = mongoClient.getDatabase("ProyectoAda")
            val collection = bd.getCollection("Juegos")
            val juego= Document()
                .append("titulo", juegos.titulo)
                .append("genero", juegos.genero)
                .append("precio", juegos.precio)
                .append("fecha", juegos.fecha)

            collection.insertOne(juego)
            println("Juego '"+juegos.titulo+"' añadido correctamente")

        } catch (e: Exception) {
            println("Error al conectar a MongoDB: ${e.message}")

        }
    }

    //Verifica que el titulo no este en uso ya
    fun checkTitulo(titulo: String): Boolean {
        if (titulo.isBlank()) {
            println("El título no puede ser nulo o vacío")
            return false
        }

        return try {
            val bd: MongoDatabase = mongoClient.getDatabase("ProyectoAda")
            val collection = bd.getCollection("Juegos")

            val filter = Filters.eq("titulo", titulo)
            val exist = collection.find(filter).first() //Seleciona el primer dato para tener constancia de que existe un dato con el mismo titulo

            if (exist != null) {
                println("Este juego ya existe")
                false
            } else {
                true
            }
        } catch (e: Exception) {
            println("Error al conectar a MongoDB: ${e.message}")
            false
        }
    }

    //Este metodo verifica si el titulo existe a diferencia del metodo checkTitulo, que verifica que no existe
    fun checkTituloExist(titulo: String): Boolean {
        if (titulo.isBlank()) {
            println("El título no puede ser nulo o vacío")
            return false
        }

        return try {
            val bd: MongoDatabase = mongoClient.getDatabase("ProyectoAda")
            val collection = bd.getCollection("Juegos")

            val filter = Filters.eq("titulo", titulo)
            val exist = collection.find(filter).first()

            if (exist == null) {
                println("Este juego no existe, prueba de nuevo")
                false
            } else {
                true
            }
        } catch (e: Exception) {
            println("Error al conectar a MongoDB: ${e.message}")
            false
        }
    }

    //Muestra los juegos en forma de tabla
    fun mostrarJuegos() {
        try {
            val bd: MongoDatabase = mongoClient.getDatabase("ProyectoAda")
            val collection = bd.getCollection("Juegos")

            collection.find().forEach { println(it) }

        } catch (e: Exception) {
            println("Error al conectar a MongoDB: ${e.message}")
        }
    }

    //Busca los juegos por su genero
    //El metodo sort se encarga de ordenarlos alfabaticamente
    fun buscarGenero(genero: String) {
        try {
            val database: MongoDatabase = mongoClient.getDatabase("ProyectoAda")
            val collection: MongoCollection<Document> = database.getCollection("Juegos")

            println("Juegos del género '$genero':")
            collection.find(Filters.eq("genero", genero))
                .sort(Sorts.ascending("titulo"))
                .forEach { println(it) }


        } catch (e: Exception) {
            println("Error al conectar a MongoDB o al realizar la búsqueda: ${e.message}")
        }
    }

    //Elimina todos los juegos que pertenezcan a un genero
    fun eliminarGenero(genero: String) {
        try {
            val database: MongoDatabase = mongoClient.getDatabase("ProyectoAda")
            val collection: MongoCollection<Document> = database.getCollection("Juegos")

            collection.deleteMany(Filters.eq("genero", genero))
            println("Todos los juegos del genero '"+genero+"' han sido eliminados correctamente")

        } catch (e: Exception) {
            println("Error al conectar a MongoDB o al realizar la búsqueda: ${e.message}")
        }
    }

    //Permite modificar toda la informacion de un juego
    fun modificarJuego(titulo: String, juegos: Juegos) {
        try {
            val database: MongoDatabase = mongoClient.getDatabase("ProyectoAda")
            val collection: MongoCollection<Document> = database.getCollection("Juegos")

            val filtroReplace = Filters.eq("titulo", titulo)

            val juegoRemplazar = Document()
                .append("titulo", juegos.titulo)
                .append("genero", juegos.genero)
                .append("precio", juegos.precio)
                .append("fecha", juegos.fecha)

            collection.replaceOne(filtroReplace, juegoRemplazar)
            println("Juego modificado correctamente")

        } catch (e: Exception) {
            println("Error al conectar a MongoDB o al realizar la búsqueda: ${e.message}")
        }

    }

    //Metodo que cierra conexion al salir de la app
    fun cerrarConexion() {
        mongoClient.close()
    }

}