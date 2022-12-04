package com.example.moviereviewapplication

import java.io.Serializable


class MovieClass (var name :Serializable,
                  var desc: Serializable,
                  var language: Serializable,
                  var releaseDate: Serializable,
                  var suitable: Serializable,
                  var reason1: Serializable,
                  var reason2: Serializable,
                  var rating: Serializable?,
                  var review: Serializable?) :Serializable
{
    init {
        this.name = name;
        this.desc = desc;
        this.language = language;
        this.releaseDate = releaseDate;
        this.suitable = suitable;
        this.reason1 = reason1;
        this.reason2 = reason2;
        this.rating = rating;
        this.review = review;
    }
}
