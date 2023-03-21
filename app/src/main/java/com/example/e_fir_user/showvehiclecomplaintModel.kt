package com.example.e_fir_user

data class showvehiclecomplaintModel(var sid:String?=null,var name:String?=null, var aadhar_number:String?=null ,var address:String?=null,
    var email:String?=null,var other_details:String?=null , var registration_number:String?=null , var relation_with_user:String?=null ,
    var vehicle_type:String?=null ,var last_seen_date:String?=null , var status:String?=null, var vehicleid:String?=null, var uid:String?=null) {
    constructor():this("","","","","","","",
    "", "","","","","")

}
