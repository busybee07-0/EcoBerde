package com.javierf.ecoberde.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.javierf.ecoberde.data.dao.MaterialDao
import com.javierf.ecoberde.data.dao.MaterialRecicladoDao
import com.javierf.ecoberde.data.dao.GananciaDao
import com.javierf.ecoberde.data.entities.Material
import com.javierf.ecoberde.data.entities.MaterialReciclado
import com.javierf.ecoberde.data.entities.GananciaEntity

@Database(
    entities = [
        Material::class,
        MaterialReciclado::class,
        GananciaEntity::class   // 👈 NUEVA TABLA
    ],
    version = 2,               // 👈 subí la versión (antes estaba en 1)
    exportSchema = false
)
abstract class EcoBerdeDatabase : RoomDatabase() {

    abstract fun materialDao(): MaterialDao
    abstract fun materialRecicladoDao(): MaterialRecicladoDao

    // 👇 NUEVO DAO
    abstract fun gananciaDao(): GananciaDao

    companion object {
        @Volatile
        private var INSTANCE: EcoBerdeDatabase? = null

        fun getDatabase(context: Context): EcoBerdeDatabase {
            return INSTANCE ?: synchronized(this) {

                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    EcoBerdeDatabase::class.java,
                    "ecoberde_db"
                )
                    // Si cambias la versión (1 → 2) y no haces migraciones,
                    // Room borra y recrea las tablas. PERO entre ejecuciones normales
                    // la info queda guardada.
                    .fallbackToDestructiveMigration()
                    .build()

                INSTANCE = instance
                instance
            }
        }
    }
}


