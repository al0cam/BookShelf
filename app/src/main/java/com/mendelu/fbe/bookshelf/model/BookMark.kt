package com.mendelu.fbe.bookshelf.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.w3c.dom.Text

@Entity(tableName = "bookMarks")
data class BookMark(@ColumnInfo(name = "text") var text: String) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long? = null

    @ColumnInfo(name = "page")
    var page: Long? = null

    @ColumnInfo(name = "bookMarkedText")
    var bookMarkedText: String? = null

    @ColumnInfo(name = "bookId")
    var bookId: Long? = null

    @ColumnInfo(name = "dateCreated")
    var dateCreated: String? = null
}
