var sig File {
	var link : lone File
}
var sig Trash in File {}
var sig Protected in File {}

pred prop7_faulty { 
	some File & Protected
}

run  prop7_faulty