package com.example.circularlistview


import com.google.gson.annotations.SerializedName

data class CrewListItem(
    @SerializedName("FirstName")
    val firstName: String,
    @SerializedName("_id")
    val id: String,
    @SerializedName("IsDefault")
    val isDefault: Boolean,
    @SerializedName("LastName")
    val lastName: String,
    @SerializedName("MobileNumber")
    val mobileNumber: String,
    @SerializedName("ProfilePicThumbUrl")
    val profilePicThumbUrl: String,
    @SerializedName("ProfilePicUrl")
    val profilePicUrl: String,
    @SerializedName("YearOfBirth")
    val yearOfBirth: Int,
    val lineFlag:Boolean = false
)