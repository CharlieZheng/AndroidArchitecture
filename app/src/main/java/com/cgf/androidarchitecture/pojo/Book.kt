package com.cgf.androidarchitecture.pojo

import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Entity
import android.arch.persistence.room.Index
import android.arch.persistence.room.PrimaryKey


@Entity(indices = arrayOf(Index("id")))
class Book {

    class Rating {
        var max: Int = 0
        var numRaters: Int = 0
        var average: String = ""
        var min: Int = 0
    }

    class Tag {
        var count: Int = 0
        var name: String = ""
        var title: String = ""
    }

    class Image {
        var small: String = ""
        var large: String = ""
        var medium: String = ""
    }

    @Embedded
    var rating: Rating = Rating()
    var subtitle: String = ""
    //    var author: List<String> = ArrayList<String>()
    var pubdate: String = ""
    /* @Embedded
     var tags: List<Tag> = ArrayList<Tag>()*/
    var origin_title: String = ""
    var image: String = ""
    var binding: String = ""
    //    var translator: List<String> = ArrayList<String>()
    var catalog: String = ""
    var pages: String = ""
    @Embedded
    var images: Image = Image()
    var alt: String = ""
    @PrimaryKey
    var id: String = ""
    var publisher: String = ""
    var isbn10: String = ""
    var isbn13: String = ""
    var title: String = ""
    var url: String = ""
    var alt_title: String = ""
    var author_intro: String = ""
    var summary: String = ""
    var price: String = ""
}