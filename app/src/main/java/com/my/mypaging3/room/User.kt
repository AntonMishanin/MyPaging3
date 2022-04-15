package com.my.mypaging3.room

import androidx.room.*

@Entity
data class User(
    @PrimaryKey val uid: Int,
    @ColumnInfo(name = "first_name") val firstName: String?,
    @ColumnInfo(name = "last_name") val lastName: String?,
    @Embedded val address: Address,
    @ColumnInfo(name = "sex") val sex: String
) {
    companion object {
        fun getRandom() = User(
            uid = (1..5000).random(),
            firstName = "first",
            lastName = "last",
            address = Address("dsdsdss"),
            sex = "male"
        )
    }
}

data class Address(
    val street: String
)

@Entity
data class Library(
    @PrimaryKey val libraryId: Int = (1..5000).random(),
    val name: String = "TEST NAME",
    val userOwnerId: Int
)

data class UserAndLibrary(
    @Embedded val user: User,
    @Relation(
        parentColumn = "uid",
        entityColumn = "userOwnerId"
    )
    val libraries: List<Library>?
)

@Entity(primaryKeys = ["libraryId", "uid"])
data class UserLibraryCrossRef(
    val libraryId: Int,
    val uid: Int
)

data class UserWithLibraries(
    @Embedded val user: User,
    @Relation(
        parentColumn = "uid",
        entityColumn = "libraryId",
        associateBy = Junction(UserLibraryCrossRef::class)
    )
    val libraries: List<Library>?
)