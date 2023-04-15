var sig File {
	var link : lone File
}
var sig Trash in File {}
var sig Protected in File {}

pred prop11_faulty { 
	all f : File + Trash | f not in Protected implies after f in Protected
}

run  prop11_faulty