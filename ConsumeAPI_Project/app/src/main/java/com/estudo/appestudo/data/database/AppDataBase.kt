package com.estudo.appestudo.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.estudo.appestudo.data.database.model.DefinitionWordEntity
import com.estudo.appestudo.data.database.model.WordEntity

@Database(entities = [WordEntity::class, DefinitionWordEntity::class], version = 3, exportSchema = false)
abstract class AppDataBase: RoomDatabase() {
    abstract fun wordDao(): WordDao
    companion object {
        @Volatile
        private var INSTANCE: AppDataBase? = null

        fun getDataBase(context: Context, nomeBanco: String): AppDataBase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDataBase::class.java,
                    nomeBanco
                ).addMigrations(MIGRATION_1_2, MIGRATION_2_3)
                    .build()

                INSTANCE = instance
                instance
            }
        }

        private val MIGRATION_1_2 = object: Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE WordEntity ADD COLUMN test_migration TEXT NOT NULL DEFAULT ''")
            }
        }

        private val MIGRATION_2_3 = object: Migration(2, 3) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("""
                    CREATE TABLE DefinitionWordEntityNew (
                        definitionId INTEGER PRIMARY KEY NOT NULL,
                        definitionId_relation INTEGER NOT NULL,
                        t_definition TEXT NOT NULL 
                       )
                """.trimIndent())

                database.execSQL(""" 
                    INSERT INTO DefinitionWordEntityNew (definitionId, definitionId_relation, t_definition)
                        SELECT definitionId, definitionId_relation, text_definition FROM DefinitionWordEntity
                """.trimIndent())

                database.execSQL("DROP TABLE DefinitionWordEntity")
                database.execSQL("ALTER TABLE DefinitionWordEntityNew RENAME TO DefinitionWordEntity")
            }
        }
    }
}