package com.javierf.ecoberde.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.javierf.ecoberde.data.entities.Material
import kotlinx.coroutines.tasks.await

// En este repositorio manejo TODA la lógica relacionada con Firebase Firestore (datos)
// y Firebase Storage (imágenes). Aquí guardo, actualizo, leo y elimino materiales en la nube.
// Dejé comentarios en primera persona para recordarme por qué hice las cosas así.

class MaterialRepository {

    // Instancia principal de Firestore. Aquí guardo los datos de los materiales.
    private val firestore = FirebaseFirestore.getInstance()

    // Instancia de Firebase Storage. Aquí subo las imágenes de cada material.
    private val storage = FirebaseStorage.getInstance()

    // Nombre de la colección donde voy a almacenar mis materiales.
    private val materialesRef = firestore.collection("materiales")

    // ================================================================
    // AGREGAR MATERIAL
    // ================================================================
    suspend fun agregarMaterial(material: Material, imageBytes: ByteArray?): Boolean {
        return try {

            // Primero le pido a Firestore un ID único para este material.
            val docId = materialesRef.document().id

            var imageUrl: String? = null

            // Si el usuario eligió una foto, la subo a Storage.
            if (imageBytes != null) {
                val ref = storage.reference.child("materiales/$docId.jpg")

                // Subo la imagen y espero (await = suspende hasta que termine).
                ref.putBytes(imageBytes).await()

                // Una vez subida la imagen, descargo la URL pública.
                imageUrl = ref.downloadUrl.await().toString()
            }

            // Ahora creo un objeto Material con el ID generado y la URL de la imagen.
            val dataToSave = material.copy(
                idMaterial = docId,
                fotoUri = imageUrl
            )

            // Guardo ese objeto en Firestore usando su ID.
            materialesRef.document(docId).set(dataToSave).await()

            true // Si todo sale bien
        } catch (e: Exception) {
            e.printStackTrace()
            false // Si algo falla
        }
    }

    // ================================================================
    // OBTENER TODOS LOS MATERIALES
    // ================================================================
    suspend fun obtenerMateriales(): List<Material> {
        return try {
            // Descargo toda la colección "materiales".
            val snapshot = materialesRef.get().await()

            // Firestore convierte automáticamente cada documento en un Material.
            snapshot.toObjects(Material::class.java)

        } catch (e: Exception) {
            e.printStackTrace()
            emptyList() // Si algo falla, devuelvo lista vacía.
        }
    }

    // ================================================================
    // OBTENER UN MATERIAL POR ID
    // ================================================================
    suspend fun obtenerPorId(id: String): Material? {
        return try {
            // Descargo el documento exacto por su ID.
            val snapshot = materialesRef.document(id).get().await()

            // Firestore lo convierte en Material (si existe).
            snapshot.toObject(Material::class.java)

        } catch (e: Exception) {
            null // Si falla, devuelvo null
        }
    }

    // ================================================================
    // ACTUALIZAR MATERIAL
    // ================================================================
    suspend fun actualizarMaterial(material: Material, imageBytes: ByteArray?): Boolean {
        return try {

            var newImageUrl = material.fotoUri

            // Si el usuario cambió la imagen, la vuelvo a subir al Storage.
            if (imageBytes != null && material.idMaterial != null) {
                val ref = storage.reference.child("materiales/${material.idMaterial}.jpg")

                // Subo la nueva imagen
                ref.putBytes(imageBytes).await()

                // Obtengo la URL nueva
                newImageUrl = ref.downloadUrl.await().toString()
            }

            // Creo una nueva versión del material con la URL actualizada (si aplica).
            val updatedMaterial = material.copy(
                fotoUri = newImageUrl
            )

            // Actualizo ese documento completo en Firestore.
            materialesRef.document(material.idMaterial!!).set(updatedMaterial).await()

            true
        } catch (e: Exception) {
            false
        }
    }

    // ================================================================
    // ELIMINAR MATERIAL
    // ================================================================
    suspend fun eliminarMaterial(id: String): Boolean {
        return try {
            // Elimino el documento de Firestore.
            materialesRef.document(id).delete().await()

            // Nota: si luego quiero, también puedo borrar la imagen en Storage.
            true
        } catch (e: Exception) {
            false
        }
    }
}
