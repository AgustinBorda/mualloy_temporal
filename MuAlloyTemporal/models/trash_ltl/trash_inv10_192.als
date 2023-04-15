var sig File {
	var link : lone File
}
var sig Trash in File {}
var sig Protected in File {}

pred prop10_faulty { 
  always all f: Protected | f not in Trash and f not in File
  
}

run  prop10_faulty