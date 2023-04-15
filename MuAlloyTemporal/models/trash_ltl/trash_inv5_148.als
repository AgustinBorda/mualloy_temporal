var sig File {
	var link : lone File
}
var sig Trash in File {}
var sig Protected in File {}

pred prop5_faulty { 
	
  	some f: File | after f not in Trash
}

run  prop5_faulty