package com.example.e_fir_user

data class showstationmodel(var email:String?=null, var username:String?=null,var district:String?=null , var status:String?=null ) {
    constructor():this("","","","")
}
