package com.dicoding.mystudentdata.helper

import androidx.sqlite.db.SimpleSQLiteQuery

object SortUtils {
    fun getSortedQuery(sortyType: SortyType): SimpleSQLiteQuery {
        val simpleQuery = StringBuilder().append("SELECT * FROM student ")
        when (sortyType) {
            SortyType.ASCENDING -> {
                simpleQuery.append("ORDER BY name ASC")
            }
            SortyType.DESCENDING -> {
                simpleQuery.append("ORDER BY name DESC")
            }
            SortyType.RANDOM -> {
                simpleQuery.append("ORDER BY RANDOM()")
            }
        }
        return SimpleSQLiteQuery(simpleQuery.toString())
    }
}