var sig File {
	var link : lone File
}
var sig Trash in File {}
var sig Protected in File {}

pred prop8_faulty { 
	all f,g: File| f->g in link implies eventually always f in Trash
}

run  prop8_faulty