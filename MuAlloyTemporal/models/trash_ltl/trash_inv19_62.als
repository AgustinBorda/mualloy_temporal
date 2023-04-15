var sig File {
	var link : lone File
}
var sig Trash in File {}
var sig Protected in File {}

pred prop19_faulty { 
	all f : Protected | always (f not in Trash) until (f in Trash)
}

run  prop19_faulty