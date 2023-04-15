var sig File {
	var link : lone File
}
var sig Trash in File {}
var sig Protected in File {}

pred prop7_faulty { 
	some f : File | eventually f in Protected
}

run  prop7_faulty