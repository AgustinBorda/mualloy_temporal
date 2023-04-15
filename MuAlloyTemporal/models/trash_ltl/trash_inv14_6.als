var sig File {
	var link : lone File
}
var sig Trash in File {}
var sig Protected in File {}

pred prop14_faulty { 
	all f:Protected | always some (f & Trash) implies no (Protected' & f) 
}

run  prop14_faulty