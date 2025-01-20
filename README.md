# MongoDB-CRUD

**¿Qué ventajas e inconvenientes encuentras al usar una base de datos documental con MongoDB?**  
MongoDB tiene ventajas significativas, como su eficiencia, la versatilidad de no requerir una estructura rígida, su fácil escalabilidad y su uso intuitivo, lo que facilita su adopción en proyectos diversos. Sin embargo, también presenta desventajas importantes, como el riesgo de cometer errores al no seguir una estructura estricta, lo que puede generar inconsistencias, y su menor adecuación para aplicaciones que necesitan transacciones complejas o un alto nivel de relaciones entre los diferentes datos que la componen.

**¿Cuáles han sido los principales problemas que se han tenido al desarrollar la aplicación?**  
Los principales inconvenientes durante el desarrollo de la aplicación han sido las conexiones con MongoDB Atlas. Esto se debe al constante cambio de la dirección IP, así como a las desconexiones espontáneas del clúster. Otro gran desafío ha sido cerrar la conexión con la base de datos, ya que la complejidad de hacerlo tras cada operación llevó a optar por cerrar la conexión una vez finalizado el programa. De esta manera, se evita la creación constante de objetos de conexión MongoClient, lo que optimiza el rendimiento.

**¿Cómo solucionar el problema de la inconsistencia de datos en una base de datos documental?**  
La mejor manera de solucionar la inconsistencia de datos es mediante controles más estrictos y una lógica de negocio bien estructurada. Además, el uso de una clase externa, como en este caso la clase Juegos, facilita la creación de documentos que se rijan por los mismos atributos. Esto asegura que los datos se gestionen de manera uniforme y coherente, reduciendo así los errores derivados de inconsistencias en la base de datos.
