var sig File {
	var link : lone File
}
var sig Trash in File {}
var sig Protected in File {}

pred prop12_faulty { 
	eventually some f : File | eventually f in Trash => after f not in Trash 
}

run  prop12_faulty