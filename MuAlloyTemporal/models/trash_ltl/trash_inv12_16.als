var sig File {
	var link : lone File
}
var sig Trash in File {}
var sig Protected in File {}

pred prop12_faulty { 
	  	all f : File | (always eventually f in Trash) implies (always eventually f not in Trash)
}

run  prop12_faulty