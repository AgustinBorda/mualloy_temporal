var sig File {
	var link : lone File
}
var sig Trash in File {}
var sig Protected in File {}

pred prop18_faulty { 
	always all f : Protected | eventually f in Trash => always f not in Protected
}

run  prop18_faulty