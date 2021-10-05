package com.mendelu.fbe.bookshelf.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Relation

data class BookWithBookMarks (
    @Embedded val book: Book,
    @Relation(
        parentColumn = "id",
        entityColumn = "bookId"
    )
    val bookMarks: List<BookMark>
)

