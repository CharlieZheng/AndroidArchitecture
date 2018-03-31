package com.iousave.www.repository.dataSource.db

import android.arch.persistence.room.TypeConverter

class Tc {

    companion object {

        @JvmStatic
        @TypeConverter
        fun stringListToString(stringList: ArrayList<String>): String {
            val stringBuffer = StringBuffer()
            stringList.forEachIndexed { index, s ->
                stringBuffer.append(s)
                if (index != s.length - 1) {
                    stringBuffer.append(",")
                }
            }
            return stringBuffer.toString()
        }

        @JvmStatic
        @TypeConverter
        fun stringToStringList(string: String): ArrayList<String> {
            val stringList = ArrayList<String>()
            string.split(",").forEach { stringList.add(it) }
            return stringList
        }
    }
}