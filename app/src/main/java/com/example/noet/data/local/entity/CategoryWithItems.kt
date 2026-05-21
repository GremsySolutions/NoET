package com.example.noet.data.local.entity

import androidx.room.Embedded
import androidx.room.Relation

data class CategoryWithVocabularies(
    @Embedded val category: Category,
    @Relation(
        parentColumn = "id",
        entityColumn = "category_id"
    )
    val vocabularies: List<Vocabulary>
)

data class VocabularyWithCategory(
    @Embedded val vocabulary: Vocabulary,
    @Relation(
        parentColumn = "category_id",
        entityColumn = "id"
    )
    val category: Category
)


