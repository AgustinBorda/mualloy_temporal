var sig File {
	var link : lone File
}
var sig Trash in File {}
var sig Protected in File {}

pred prop14_faulty { 
	always (all f : Protected | f in Trash implies after f not in Trash)
}

run  prop14_faulty