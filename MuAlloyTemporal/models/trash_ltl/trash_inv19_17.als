var sig File {
	var link : lone File
}
var sig Trash in File {}
var sig Protected in File {}

pred prop19_faulty { 
	eventually all f : Protected | f in Trash until f not in Protected
}

run  prop19_faulty