var sig File {
	var link : lone File
}
var sig Trash in File {}
var sig Protected in File {}

pred prop18_faulty { 
	all f : File | always (f not in Protected since f in Trash)
}

run  prop18_faulty