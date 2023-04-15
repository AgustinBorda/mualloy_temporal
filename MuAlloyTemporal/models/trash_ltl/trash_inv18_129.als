var sig File {
	var link : lone File
}
var sig Trash in File {}
var sig Protected in File {}

pred prop18_faulty { 
	all p: Protected | p in Protected until p in Trash
}

run  prop18_faulty