var sig File {
	var link : lone File
}
var sig Trash in File {}
var sig Protected in File {}

pred prop2_faulty { 
  all f : File | eventually f in File
}

run  prop2_faulty