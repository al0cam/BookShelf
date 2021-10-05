package com.mendelu.fbe.bookshelf.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "books")
data class Book(@ColumnInfo(name = "text") var text: String)
{
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long? = null

    @ColumnInfo(name = "pages")
    var pages: Long? = null

    @ColumnInfo(name = "name")
    var name: String? = null

    @ColumnInfo(name = "path")
    var path: String? = null

    @ColumnInfo(name = "author")
    var author: String? = null

    @ColumnInfo(name = "lastReadDate")
    var lastReadDate: String? = null

    @ColumnInfo(name = "isFavourite")
    var isFavourite: Boolean? = false

}