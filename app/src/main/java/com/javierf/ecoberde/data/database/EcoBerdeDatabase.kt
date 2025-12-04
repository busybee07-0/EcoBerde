package com.javierf.ecoberde.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.javierf.ecoberde.data.dao.MaterialDao
import com.javierf.ecoberde.data.dao.MaterialRecicladoDao
import com.javierf.ecoberde.data.entities.Material
import com.javierf.ecoberde.data.entities.MaterialReciclado

@Database(
    entities = [
        Material::class,
        MaterialReciclado::class
    ],
    version = 1,
    exportSchema = false
)
abstract class EcoBerdeDatabase : RoomDatabase() {

    abstract fun materialDao(): MaterialDao
    abstract fun materialRecicladoDao(): MaterialRecicladoDao

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
                    .fallbackToDestructiveMigration() // simplifica migraciones
                    .build()

                INSTANCE = instance
                instance
            }
        }
    }
}
