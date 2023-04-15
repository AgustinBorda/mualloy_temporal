var sig File {
	var link : lone File
}
var sig Trash in File {}
var sig Protected in File {}

pred prop10_faulty { 
	all p: Protected | p' in Protected
}

run  prop10_faulty