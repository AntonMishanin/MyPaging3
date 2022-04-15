package com.my.mypaging3.room

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.room.Room
import com.my.mypaging3.R

class RoomActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room)

        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "database-name",
        )
            .fallbackToDestructiveMigration()
            .addMigrations(MIGRATION_2_3, MIGRATION_3_4)
            .allowMainThreadQueries()
            .build()

        val userDao = db.userDao()

        userDao.insertAll(User.getRandom(), User.getRandom())

        val users: List<User> = userDao.getAll()
        val userAndLibrary = userDao.loadAllUsersWithLibraries()
        Log.d("EE", "userAndLibrary = $userAndLibrary")

        findViewById<View>(R.id.root).setOnClickListener {
            Log.d("EE", "CLICK")
            val library = Library(userOwnerId = users[0].uid)
            userDao.insertLibraries(library)
            val crossRef = UserLibraryCrossRef(libraryId = library.libraryId, uid = users[0].uid)
            userDao.insertUserLibraryCrossRef(crossRef)
        }
    }
}