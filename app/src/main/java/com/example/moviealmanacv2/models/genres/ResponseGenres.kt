package com.example.moviealmanacv2.models.genres

import com.google.gson.annotations.SerializedName

data class ResponseGenres(

	@field:SerializedName("genres")
	val genres: List<GenresItem?>? = null
)

data class GenresItem(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int? = null
)
