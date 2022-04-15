package com.my.mypaging3.room

import androidx.room.*

@Dao
interface UserDao {
    @Query("SELECT * FROM user")
    fun getAll(): List<User>

    @Query("SELECT * FROM user WHERE uid IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<User>

    @Query(
        "SELECT * FROM user WHERE first_name LIKE :first AND " +
                "last_name LIKE :last LIMIT 1"
    )
    fun findByName(first: String, last: String): User

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg users: User)

    @Delete
    fun delete(user: User)

    @Transaction
    @Query("SELECT * FROM User")
    fun loadAllUserAndLibrary(): List<UserAndLibrary>

    @Insert
    fun insertLibraries(vararg library: Library)

    @Transaction
    @Query("SELECT * FROM User")
    fun loadAllUsersWithLibraries(): List<UserWithLibraries>

    @Insert
    fun insertUserLibraryCrossRef(vararg crossRefs: UserLibraryCrossRef)
}