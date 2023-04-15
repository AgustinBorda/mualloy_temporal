var sig File {
	var link : lone File
}
var sig Trash in File {}
var sig Protected in File {}

pred prop18_faulty { 
	all p: Protected | always p not in Protected implies p in Trash
}

run  prop18_faulty