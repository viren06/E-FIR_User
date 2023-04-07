package com.example.e_fir_user

data class showphonecomplaintModel (var sid:String?=null,var name:String?=null, var aadhar_number:String?=null ,var address:String?=null,
   var email:String?=null,var mobile_number:String?=null , var serial_number:String?=null , var relation_with_user:String?=null ,
   var mobile_details:String?=null ,var last_seen_date:String?=null , var status:String?=null, var phoneid:String?=null, var uid:String?=null) {
    constructor():this("","","","","","","",""
    ,"","","","","")

}
