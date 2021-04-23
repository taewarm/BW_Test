package com.example.bigwalk_test

import java.util.*
import kotlin.collections.ArrayList

data class APIData(
        val status:String,
        val endabled:Boolean,
        val endDate: String,
        val additionalServiceID:Int,
        val ServiceOnTime:Boolean,
        val startDate:String,
        val ratio:Int,
        val donatedSteps:Int,
        val organizations: Any,
        val goalPoint:Int,
        val detailThumbnailImagePath:String,
        val largeListThumbnailImagePath:String,
        val mediumListThumbnailImagePath:String,
        val smallListThumbnailImagePath:String,
        val participantCount:Int,
        val beneficiary:String,
        val beneficiaryLink:String,
        val beneficiaryBtn:String,
        val categoryId:Int,
        val smsId:Int,
        val campaignPromoter: Campaign,
        val formattedStartDate:String,
        val formattedEndDate:String,
        val name:String,
        val my: My,
        val event: Any,
        val service: Service

    )
