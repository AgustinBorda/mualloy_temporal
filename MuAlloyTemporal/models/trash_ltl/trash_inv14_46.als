var sig File {
	var link : lone File
}
var sig Trash in File {}
var sig Protected in File {}

pred prop14_faulty { 
	always all p:Protected | p in Trash implies after p'=Protected
}

run  prop14_faulty