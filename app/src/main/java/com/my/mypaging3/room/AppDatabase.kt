package com.my.mypaging3.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [User::class, Library::class, UserLibraryCrossRef::class], version = 5)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}

// Migration from 2 to 3, Room 2.4.2
val MIGRATION_2_3 = object : Migration(2, 3) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL(
            "ALTER TABLE User ADD COLUMN sex TEXT NOT NULL DEFAULT ''"
        )
    }
}

//Added Library, Room 2.4.2
val MIGRATION_3_4 = object : Migration(3, 4) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL(
            "CREATE TABLE IF NOT EXISTS `Library` (`libraryId` INTEGER NOT NULL, `name` TEXT NOT NULL, `userOwnerId` INTEGER NOT NULL, PRIMARY KEY(`libraryId`))"
        )
    }
}