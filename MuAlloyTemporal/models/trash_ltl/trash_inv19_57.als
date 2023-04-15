var sig File {
	var link : lone File
}
var sig Trash in File {}
var sig Protected in File {}

pred prop19_faulty { 
	eventually (all f : Protected | eventually f in Trash implies historically f in Protected)
}

run  prop19_faulty