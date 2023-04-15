var sig File {
	var link : lone File
}
var sig Trash in File {}
var sig Protected in File {}

pred prop19_faulty { 
	eventually (all f:Protected | eventually f not in Protected and f in Trash)
}

run  prop19_faulty