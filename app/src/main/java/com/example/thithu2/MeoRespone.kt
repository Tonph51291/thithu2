package com.example.thithu2

import com.google.gson.annotations.SerializedName

data class MeoRespone(
    @SerializedName("id") val id : String,
    @SerializedName("ten") val ten : String,
    @SerializedName("giong") val giong : String,
    @SerializedName("tuoi") val tuoi : Int,
    @SerializedName("hinhanh") val hinhanh : String,
    @SerializedName("moTa") val moTa : String,
)

fun MeoRespone.toMeo() : Meo{
    return Meo (
        id = this.id,
        ten = this.ten,
        giong = this.giong,
        tuoi = this.tuoi,
        hinhanh = this.hinhanh,
        moTa = this.moTa,
    )
}