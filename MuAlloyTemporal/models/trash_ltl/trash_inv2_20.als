var sig File {
	var link : lone File
}
var sig Trash in File {}
var sig Protected in File {}

pred prop2_faulty { 
  all f : File | after no File implies f in File 
}

run  prop2_faulty